package eu.swnw.networks.nodes;

import eu.swnw.networks.edges.Edge;
import java.util.ArrayList;

public class Node {

    protected String name;
    protected ArrayList<Edge> edgesOut;
    protected ArrayList<Edge> edgesIn;

    public Node( String name ) {
        this.name = name;
        this.edgesOut = new ArrayList<Edge>();
        this.edgesIn = new ArrayList<Edge>();
    }

    public ArrayList<Edge> getEdgesIn() {
        return edgesIn;
    }

    public void addEdgeIn(Edge edge) {
        this.edgesIn.add(edge);
    }

    public ArrayList<Edge> getEdgesOut() {
        return edgesOut;
    }

    public void addEdgeOut(Edge edge) {
        this.edgesOut.add(edge);
    }


    @Override
    public String toString(){
        return name;
    }
}
