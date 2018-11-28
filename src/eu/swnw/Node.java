package eu.swnw;

import java.util.ArrayList;

public class Node {

    private String name;
    private ArrayList<Edge> edgesOut;
    private ArrayList<Edge> edgesIn;
    private double excess;
    private int height;


    public Node( String name ){
        this.name = name;
        this.edgesOut = new ArrayList<Edge>();
        this.edgesIn = new ArrayList<Edge>();
        this.excess = 0;
        this.height = 0;
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

    public double getExcess() {
        return excess;
    }

    public void setExcess(double excess) {
        this.excess = excess;
    }

    public void incrementExcess(double excess){ this.excess+=excess; }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }


    @Override
    public String toString(){
        return name;
    }
}
