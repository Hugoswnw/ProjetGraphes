package eu.swnw.networks;

import eu.swnw.networks.edges.Edge;
import eu.swnw.networks.edges.EdgePushRelabel;
import eu.swnw.networks.nodes.Node;
import eu.swnw.networks.nodes.NodePushRelabel;

import java.util.ArrayList;
import java.util.HashMap;

public class NetworkPushRelabel extends Network{


    public NetworkPushRelabel(HashMap<String, Node> nodes, ArrayList<Edge> edges, Node source, Node sink) {
        super(nodes, edges, source, sink);
    }

    public static NetworkPushRelabel networkToNPR(Network G){
        HashMap<String, Node> nodes = new HashMap<String, Node>();
        ArrayList<Edge> edges = new ArrayList<Edge>();
        for(Node n : G.nodes.values()) {
            nodes.put(n.getName(), new NodePushRelabel(n));
        }

        for(Node from : G.nodes.values()) {
            for( String toKey : from.getEdgesOut().keySet()){
                Edge toEdge = from.getEdgesOut().get(toKey);
                EdgePushRelabel newEdge = new EdgePushRelabel((NodePushRelabel) nodes.get(from.getName()), (NodePushRelabel) nodes.get(toKey), toEdge.getFlow(), toEdge.getCapacity());
                edges.add(newEdge);
                edges.add(newEdge.getInverted());
            }
        }


        return new NetworkPushRelabel(nodes, edges, nodes.get(G.source.getName()),  nodes.get(G.sink.getName()));
    }

    public static Network NPRtoNetwork(NetworkPushRelabel G){
        HashMap<String, Node> nodes = new HashMap<String, Node>();
        ArrayList<Edge> edges = new ArrayList<Edge>();

        for(Node n : G.nodes.values()) {
            nodes.put(n.getName(), new Node(n.getName()));
        }

        for(Node from : G.nodes.values()) {
            for( String toKey : from.getEdgesOut().keySet()){
                Edge edge = from.getEdgesOut().get(toKey);
                if(edge.getCapacity()!=0) // Only "real" edges
                    edges.add(new Edge(nodes.get(from.getName()), nodes.get(toKey), edge.getFlow(), edge.getCapacity()));
            }
        }

        return new NetworkPushRelabel(nodes, edges, nodes.get(G.source.getName()),  nodes.get(G.sink.getName()));
    }

    public void preflot(){
        init();
        boolean stop = false;
        while(!stop){
            stop = true;
            for(Node n : nodes.values()){
                NodePushRelabel u = (NodePushRelabel) n;
                if(preconRelabel(u)) {
                    relabel(u);
                    stop = false;
                }
                for (Edge e : u.getEdgesOut().values()) {
                    EdgePushRelabel out = (EdgePushRelabel) e;
                    if(preconPush(out)){
                        push(out);
                        stop = false;
                    }
                }
            }
        }
    }

    private void init(){
        for(Node n : this.nodes.values()) {
            NodePushRelabel v = (NodePushRelabel) n;
            v.setExcess(0);
            v.setHeight(0);
        }
        ((NodePushRelabel) this.source).setHeight(this.nodes.size());

        for(Edge edge : this.edges){
            edge.setFlow(0);
        }

        for (Edge edge : this.source.getEdgesOut().values()) {
            EdgePushRelabel e = (EdgePushRelabel) edge;
            e.setFlow(e.getCapacity());
            ((NodePushRelabel) e.getTo()).setExcess(e.getCapacity());
        }
    }

    private boolean preconPush(EdgePushRelabel e){
        NodePushRelabel u = (NodePushRelabel) e.getFrom();
        NodePushRelabel v = (NodePushRelabel) e.getTo();
        if(!u.equals(sink)&&!u.equals(source))
            if(u.getExcess()>0 && e.getCapacity()-e.getFlow()>0)
                if(u.getHeight()==v.getHeight()+1)
                    return true;

        return false;
    }

    private void push(EdgePushRelabel e){
        NodePushRelabel u = ((NodePushRelabel) e.getFrom()), v = ((NodePushRelabel) e.getTo());
        int d = Math.min(u.getExcess(), e.getCapacity()-e.getFlow());
        e.incrementFlow(d);
        u.incrementExcess(-d);
        v.incrementExcess(d);
        System.out.println("-- relabel "+u+" -> "+v);
    }

    private boolean preconRelabel(NodePushRelabel u){
        if(!u.equals(sink)&&!u.equals(source))
            if(u.getExcess()>0)
                for( Edge e : u.getEdgesOut().values())
                    if(((NodePushRelabel) e.getTo()).getHeight() >= u.getHeight())
                        return true;
        return false;
    }

    private void relabel(NodePushRelabel u){

        int min = Integer.MAX_VALUE;
        for(Edge out: u.getEdgesOut().values())
            if(out.getFlow()<((EdgePushRelabel) out).getCapacity()) {
                min = Math.min(min, ((NodePushRelabel) out.getTo()).getHeight());
            }

        for(Edge in: u.getEdgesIn().values())
            if(in.getFlow()>0) {
                min = Math.min(min, ((NodePushRelabel) in.getFrom()).getHeight());
            }

        u.setHeight(min+1);
        System.out.println("-- push "+u);
    }



}
