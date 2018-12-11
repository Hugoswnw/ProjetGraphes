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
        Node requests = new Node("r");

        nodes.put(requests.getName(), requests);
        for (Node n : G.nodes.values()) {
            nodes.put(n.getName(), new Node(n.getName()));
            int request = ((NodeRequest) n).getRequest();
            if (request < 0)
                edges.add(new Edge(requests, nodes.get(n.getName()), 0, -request));
            else
                edges.add(new Edge(nodes.get(n.getName()), requests, 0, request));

        }
        for (Node from : G.nodes.values()) {
            for (String toKey : from.getEdgesOut().keySet()) {
                Edge toEdge = from.getEdgesOut().get(toKey);
                Edge newEdge = new Edge(nodes.get(from.getName()), nodes.get(toKey), toEdge.getFlow(), toEdge.getCapacity());
                edges.add(newEdge);
            }
        }
        return new Network(nodes, edges, nodes.get(G.source.getName()), nodes.get(G.sink.getName()));
    }

    public static NetworkRequest constructionEtape3(NetworkMin G) {
        return NetworkMin.constructionEtape3(G);
    }
}