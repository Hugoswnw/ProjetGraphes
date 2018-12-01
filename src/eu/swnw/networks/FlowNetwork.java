package eu.swnw.networks;

import eu.swnw.networks.edges.Edge;
import eu.swnw.networks.edges.EdgePushRelabel;
import eu.swnw.networks.nodes.Node;
import eu.swnw.networks.nodes.NodePushRelabel;

public class FlowNetwork extends Network{

    public FlowNetwork(Network G) {
        super(G.vertexes.clone(), getResidualEdges(G.edges), G.vertexes[0], G.vertexes[G.vertexes.length]);
    }

    public static Edge[] getResidualEdges( Edge[] edges){
        Edge[] residualEdges = new Edge[edges.length];
        java.lang.System.arraycopy(edges, 0, residualEdges, 0, edges.length);
        return residualEdges;
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

        for (EdgePushRelabel e: G.source.getEdgesOut()) {
            e.setFlow(e.getCapacity());
            e.getTo().setExcess(e.getCapacity());
        }
    }

    private static void push(NodePushRelabel u){
        System.out.println("-- push "+u);
        int min = Integer.MAX_VALUE;
        for(EdgePushRelabel out: u.getEdgesOut())
            if(out.getFlow()<out.getCapacity())
                min = Math.min(min, out.getTo().getHeight());

        for(EdgePushRelabel in: u.getEdgesIn())
            if(in.getFlow()>0)
                min = Math.min(min, in.getFrom().getHeight());

        u.setHeight(min+1);
    }

    private static void relabel(EdgePushRelabel e){
        NodePushRelabel u = e.getFrom(), v = e.getTo();
        System.out.println("-- relabel "+u+" "+v);
        double d = Math.min(u.getExcess(), e.getCapacity()-e.getFlow());
        e.incrementFlow(d);
        u.incrementExcess(-d);
        v.incrementExcess(d);
    }



}
