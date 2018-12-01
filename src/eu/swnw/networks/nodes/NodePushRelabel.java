package eu.swnw.networks.nodes;

public class NodePushRelabel extends Node {


    private double excess;
    private int height;


    public NodePushRelabel(String name ){
        super(name);
        this.excess = 0;
        this.height = 0;
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

}
