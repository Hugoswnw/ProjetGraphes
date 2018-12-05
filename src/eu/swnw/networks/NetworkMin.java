package eu.swnw.networks;

import eu.swnw.networks.edges.Edge;
import eu.swnw.networks.nodes.Node;

import java.util.ArrayList;
import java.util.HashMap;

public class NetworkMin extends Network{

    public NetworkMin(HashMap<String, Node> nodes, ArrayList<Edge> edges, Node source, Node sink) {
        super(nodes, edges, source, sink);
    }

    //Etape 3
    public static NetworkMin NMtoNR(NetworkMin G){

        /* todo */
        return null;
    }

}
