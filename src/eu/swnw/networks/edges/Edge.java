package eu.swnw.networks.edges;

import eu.swnw.networks.nodes.Node;

public class Edge {

    protected Node from;
    protected Node to;
    protected int flow;
    protected int capacity;

    public Edge(Node from, Node to, int flow, int capacity){
        this.from = from;
        this.to = to;
        this.flow = flow;
        this.capacity = capacity;

        to.addEdgeIn(this);
        from.addEdgeOut(this);
    }

    public Edge(){

    }

    public Node getFrom() {
        return from;
    }

    public Node getTo() {
        return to;
    }

    public int getFlow() {
        return flow;
    }

    public void setFlow(int flow){
        this.flow = flow;
    }

    public void incrementFlow(int flow){
        this.flow += flow;
    }

    public int getCapacity() {
        return capacity;
    }


    @Override
    public String toString(){
        return from+"->"+to+" : "+flow+"/"+capacity;
    }

}
