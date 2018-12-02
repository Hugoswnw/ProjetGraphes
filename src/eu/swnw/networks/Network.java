package eu.swnw.networks;

import eu.swnw.networks.edges.Edge;
import eu.swnw.networks.nodes.Node;

import java.util.HashMap;

public class Network {

    protected HashMap<String, Node> nodes;
    protected Edge[] edges;
    protected Node source, sink;


    public Network(HashMap<String, Node> nodes, Edge[] edges, Node source, Node sink){
        this.nodes = nodes;
        this.edges = edges;
        this.sink = sink;
        this.source = source;
    }

    public Network(String[] v, String[] froms, String[] tos, double[] capacities, String source, String sink){
        this.nodes = new HashMap<String, Node>();
        for(int i = 0; i<v.length; i++)
            this.nodes.put(v[i], new Node(v[i]));

        this.edges = new Edge[froms.length];
        for(int i = 0; i<froms.length; i++) {
            Edge e = new Edge(nodes.get(froms[i]), nodes.get(tos[i]), 0.0, capacities[i]);
            edges[i] = e;
        }

        this.source = nodes.get(source);
        this.sink = nodes.get(sink);
    }


    @Override
    public String toString(){
        String str = "nodes : \n";
        for ( Node v : nodes.values() ) {
            str += " "+v;
        }
        str += "\n source : "+source;
        str += "\n sink :   "+sink;
        str += "\nEdges : \n";
        for ( Edge e : edges ) {
            str += " "+e+"\n";
        }
        return str;
    }
}
