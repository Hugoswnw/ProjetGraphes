package eu.swnw.networks.nodes;

public class NodeRequest extends Node{

    private int request;

    public NodeRequest(String name, int request) {
        super(name);
        this.request = request;
    }

    public int getRequest() {
        return request;
    }

    @Override
    public String toString(){
        return name+"(r"+request+")";
    }
}
