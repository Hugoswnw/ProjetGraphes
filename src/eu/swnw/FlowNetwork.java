package eu.swnw;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class FlowNetwork {

    Node[] vertexes;
    Edge[] edges;
    Node source, sink;

    public FlowNetwork(String[] v, int[] froms, int[] tos, double[] capacities) {

        vertexes = new Node[v.length];
        for(int i = 0; i<v.length; i++)
            vertexes[i] = new Node(v[i]);

        edges = new Edge[froms.length];
        for(int i = 0; i<froms.length; i++) {
            edges[i] = new Edge(vertexes[froms[i]], vertexes[tos[i]], capacities[i]);
        }
        source = vertexes[0];
        sink = vertexes[vertexes.length-1];

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
