package eu.swnw.networks;

import eu.swnw.networks.edges.Edge;
import eu.swnw.networks.edges.EdgePushRelabel;
import eu.swnw.networks.nodes.Node;
import eu.swnw.networks.nodes.NodePushRelabel;

import java.util.HashMap;

public class NetworkPushRelabel extends Network{


    public NetworkPushRelabel( HashMap<String, Node> nodes, Edge[] edges, Node source, Node sink) {
        super(nodes, edges, source, sink);
    }

    public static NetworkPushRelabel networkToNPR(Network G){
        HashMap<String, Node> nodes = new HashMap<String, Node>();
        Edge [] edges = new Edge[G.edges.length*2];
        int i = 0;
        for(Node n : G.nodes.values()) {
            nodes.put(n.getName(), new NodePushRelabel(n));
        }

        for(Node from : G.nodes.values()) {
            System.out.println(i);
            for( String toKey : from.getEdgesOut().keySet()){
                Edge toEdge = from.getEdgesOut().get(toKey);
                EdgePushRelabel newEdge = new EdgePushRelabel((NodePushRelabel) nodes.get(from.getName()), (NodePushRelabel) nodes.get(toKey), toEdge.getFlow(), toEdge.getCapacity());
                edges[i++] = newEdge;
                edges[i++] = newEdge.getInverted();
                System.out.println(i);
            }
        }


        return new NetworkPushRelabel(nodes, edges, nodes.get(G.source.getName()),  nodes.get(G.sink.getName()));
    }

    public void pushRelabel(){
        init(this);
    }

    private static void init(NetworkPushRelabel G){
        for(Node n : G.nodes.values()) {
            NodePushRelabel v = (NodePushRelabel) n;
            v.setExcess(0);
            v.setHeight(0);
        }
        ((NodePushRelabel) G.source).setHeight(G.nodes.size());

        for(int i = 0; i<G.edges.length; i++){
            G.edges[i].setFlow(0);
        }

        for (Edge edge : G.source.getEdgesOut().values()) {
            EdgePushRelabel e = (EdgePushRelabel) edge;
            e.setFlow(e.getCapacity());
            ((NodePushRelabel) e.getTo()).setExcess(e.getCapacity());
        }
    }

    private static void push(NodePushRelabel u){
        System.out.println("-- push "+u);
        int min = Integer.MAX_VALUE;
        for(Edge out: u.getEdgesOut().values())
            if(out.getFlow()<((EdgePushRelabel) out).getCapacity())
                min = Math.min(min, ((NodePushRelabel) out.getTo()).getHeight());

        for(Edge in: u.getEdgesIn().values())
            if(in.getFlow()>0)
                min = Math.min(min, ((NodePushRelabel) in.getFrom()).getHeight());

        u.setHeight(min+1);
    }

    private static void relabel(EdgePushRelabel e){
        NodePushRelabel u = ((NodePushRelabel) e.getFrom()), v = ((NodePushRelabel) e.getTo());
        System.out.println("-- relabel "+u+" "+v);
        double d = Math.min(u.getExcess(), e.getCapacity()-e.getFlow());
        e.incrementFlow(d);
        u.incrementExcess(-d);
        v.incrementExcess(d);
    }



}
