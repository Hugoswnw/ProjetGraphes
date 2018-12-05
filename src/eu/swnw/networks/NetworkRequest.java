package eu.swnw.networks;

import eu.swnw.networks.edges.Edge;
import eu.swnw.networks.nodes.Node;

import java.util.ArrayList;
import java.util.HashMap;

public class NetworkRequest extends Network{

    public NetworkRequest(HashMap<String, Node> nodes, ArrayList<Edge> edges, Node source, Node sink) {
        super(nodes, edges, source, sink);
    }

    //Etape 2
    public static Network NRtoNetwork(NetworkRequest G){

        /* todo */
        return null;
    }

}
