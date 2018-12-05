package eu.swnw.networks.nodes;

public class NodePushRelabel extends Node {


    private int excess;
    private int height;


    public NodePushRelabel(String name ){
        super(name);
        this.excess = 0;
        this.height = 0;
    }

    public NodePushRelabel(Node v){
        super(v.name);
    }

    public int getExcess() {
        return excess;
    }

    public void setExcess(int excess) {
        this.excess = excess;
    }

    public void incrementExcess(int excess){ this.excess+=excess; }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    @Override
    public String toString(){
        return name+"(h"+height+"-e"+excess+")";
    }

}
