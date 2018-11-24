package eu.swnw;

import java.util.ArrayList;

public class Node {

    private String name;
    private ArrayList<Edge> edges;

    public Node( String name ){
        this.name = name;
        edges = new ArrayList<Edge>();
    }

    public ArrayList<Edge> getEdges() {
        return edges;
    }

    public void addEdge(Edge edge) {
        this.edges.add(edge);
    }

    @Override
    public String toString(){
        return name;
    }
}
