package eu.swnw;

import java.util.Iterator;

public class FlowNetwork {

    Node[] vertexes;
    Edge[] edges;
    Node source, sink;

    public FlowNetwork(String[] v, int[] froms, int[] tos, double[] capacities) {

        vertexes = new Node[v.length];
        for(int i = 0; i<v.length; i++)
            vertexes[i] = new Node(v[i]);

        edges = new Edge[froms.length*2]; // Twice the length ( original and reverted edges )
        System.out.println(edges.length);
        for(int i = 0; i<froms.length; i++) {
            Edge e =new Edge(vertexes[froms[i]], vertexes[tos[i]], 0.0, capacities[i]);
            edges[(i*2)] = e;
            edges[(i*2)+1] = e.getInverted();
        }
        source = vertexes[0];
        sink = vertexes[vertexes.length-1];

    }

    public void pushRelabel(){
        init(this);
    }

    private static void init(FlowNetwork G){
        for(int i = 0; i<G.vertexes.length; i++){
            G.vertexes[i].setExcess(0);
            G.vertexes[i].setHeight(0);
        }
        G.source.setHeight(G.vertexes.length);

        for(int i = 0; i<G.edges.length; i++){
            G.edges[i].setFlow(0);
        }

        for (Edge e: G.source.getEdgesOut()) {
            e.setFlow(e.getCapacity());
            e.getTo().setExcess(e.getCapacity());
        }
    }

    private static void push(Node u){
        System.out.println("-- push "+u);
        int min = Integer.MAX_VALUE;
        for(Edge out: u.getEdgesOut())
            if(out.getFlow()<out.getCapacity())
                min = Math.min(min, out.getTo().getHeight());

        for(Edge in: u.getEdgesIn())
            if(in.getFlow()>0)
                min = Math.min(min, in.getFrom().getHeight());

        u.setHeight(min+1);
    }

    private static void relabel(Edge e){
        Node u = e.getFrom(), v = e.getTo();
        System.out.println("-- relabel "+u+" "+v);
        double d = Math.min(u.getExcess(), e.getCapacity()-e.getFlow());
        e.incrementFlow(d);
        u.incrementExcess(-d);
        v.incrementExcess(d);
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
