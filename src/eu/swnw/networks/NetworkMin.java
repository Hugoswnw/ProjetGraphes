package eu.swnw.networks;

import eu.swnw.networks.edges.Edge;
import eu.swnw.networks.edges.EdgeMin;
import eu.swnw.networks.nodes.Node;
import eu.swnw.networks.nodes.NodeRequest;

import java.util.ArrayList;
import java.util.HashMap;

public class NetworkMin extends Network{

    public NetworkMin(HashMap<String, Node> nodes, ArrayList<Edge> edges, Node source, Node sink) {
        super(nodes, edges, source, sink);
    }

    public static NetworkRequest constructionEtape3(NetworkMin G){
        HashMap<String, Node> nodes = new HashMap<String, Node>();
        ArrayList<Edge> edges = new ArrayList<Edge>();

        for(Node n : G.nodes.values()) {
            nodes.put(n.getName(), new NodeRequest(n.getName(), 0));
        }

        for(Node from : G.nodes.values()) {
            for( String toKey : from.getEdgesOut().keySet()){
                EdgeMin toEdge = (EdgeMin) from.getEdgesOut().get(toKey);
                Edge newEdge = new Edge(nodes.get(from.getName()), nodes.get(toKey), toEdge.getFlow(), toEdge.getCapacity()-toEdge.getMin());

                ((NodeRequest) newEdge.getTo()).incrementRequest(-toEdge.getMin());
                ((NodeRequest) newEdge.getFrom()).incrementRequest(toEdge.getMin());

                edges.add(newEdge);
            }
        }


        return new NetworkRequest(nodes, edges, nodes.get(G.source.getName()),  nodes.get(G.sink.getName()));
    }

}
