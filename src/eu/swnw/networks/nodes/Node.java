package eu.swnw.networks.nodes;

import eu.swnw.networks.edges.Edge;

import java.util.HashMap;

public class Node {

    protected String name;
    protected HashMap<String, Edge> edgesOut;
    protected HashMap<String, Edge> edgesIn;

    public Node( String name ) {
        this.name = name;
        this.edgesOut = new HashMap<String, Edge>();
        this.edgesIn = new HashMap<String, Edge>();
    }

    public HashMap<String, Edge> getEdgesIn() {
        return edgesIn;
    }

    public void addEdgeIn(Edge edge) {
        this.edgesIn.put(edge.getFrom().name, edge);
    }

    public HashMap<String, Edge> getEdgesOut() {
        return edgesOut;
    }

    public void addEdgeOut(Edge edge) {
        this.edgesOut.put(edge.getTo().name, edge);
    }

    public String getName() {
        return name;
    }


    @Override
    public String toString(){
        return name;
    }
}
