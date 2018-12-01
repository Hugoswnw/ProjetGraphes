package eu.swnw.networks;

import eu.swnw.networks.edges.Edge;
import eu.swnw.networks.nodes.Node;

public class Network {

    protected Node[] vertexes;
    protected Edge[] edges;
    protected Node source, sink;


    public Network(Node[] vertexes, Edge[] edges, Node source, Node sink){
        this.vertexes = vertexes;
        this.edges = edges;
        this.sink = sink;
        this.source = source;
    }

    public Network(String[] v, int[] froms, int[] tos, double[] capacities){
        this.vertexes = new Node[v.length];
        for(int i = 0; i<v.length; i++)
            vertexes[i] = new Node(v[i]);

        this.edges = new Edge[froms.length];
        for(int i = 0; i<froms.length; i++) {
            Edge e = new Edge(vertexes[froms[i]], vertexes[tos[i]], 0.0, capacities[i]);
            edges[i] = e;
        }

        this.source = vertexes[0];
        this.sink = vertexes[vertexes.length-1];
    }


    @Override
    public String toString(){
        String str = "Vertexes : \n";
        for ( Node v : vertexes ) {
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
