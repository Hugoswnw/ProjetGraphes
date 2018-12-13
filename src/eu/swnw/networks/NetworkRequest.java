package eu.swnw.networks;

import eu.swnw.networks.edges.Edge;
import eu.swnw.networks.nodes.Node;
import eu.swnw.networks.nodes.NodeRequest;

import java.util.ArrayList;
import java.util.HashMap;

public class NetworkRequest extends Network {
    public NetworkRequest(HashMap<String, Node> nodes, ArrayList<Edge> edges, Node source, Node sink) {
        super(nodes, edges, source, sink);
    }

    public static Network constructionEtape2(NetworkRequest G) {
        HashMap<String, Node> nodes = new HashMap<String, Node>();
        ArrayList<Edge> edges = new ArrayList<Edge>();
        Node newSink = new Node(G.sink.getName()+"new");
        Node newSource = new Node(G.source.getName()+"new");


        nodes.put(newSink.getName(), newSink);
        nodes.put(newSource.getName(), newSource);
        for (Node n : G.nodes.values()) {
            nodes.put(n.getName(), new Node(n.getName()));
            int request = ((NodeRequest) n).getRequest();
            if (request < 0) {
                edges.add(new Edge(newSource, nodes.get(n.getName()), 0, -request));
            }
            else {
                edges.add(new Edge(nodes.get(n.getName()), newSink, 0, request));
            }

        }
        for (Node from : G.nodes.values()) {
            for (String toKey : from.getEdgesOut().keySet()) {
                Edge toEdge = from.getEdgesOut().get(toKey);
                Edge newEdge = new Edge(nodes.get(from.getName()), nodes.get(toKey), toEdge.getFlow(), toEdge.getCapacity());
                edges.add(newEdge);
            }
        }
        return new Network(nodes, edges, newSource, newSink);
    }

    public static NetworkRequest constructionEtape3(NetworkMin G) {
        return NetworkMin.constructionEtape3(G);
    }
}