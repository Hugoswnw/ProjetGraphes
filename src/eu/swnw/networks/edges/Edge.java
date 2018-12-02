package eu.swnw.networks.edges;

import eu.swnw.networks.nodes.Node;

public class Edge {

    protected Node from;
    protected Node to;
    protected double flow;
    protected double capacity;

    public Edge(Node from, Node to, double flow, double capacity){
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

    public double getFlow() {
        return flow;
    }

    public void setFlow(double flow){
        this.flow = flow;
    }

    public void incrementFlow(double flow){
        this.flow += flow;
    }

    public double getCapacity() {
        return capacity;
    }


    @Override
    public String toString(){
        return from+"->"+to+" : "+flow+"/"+capacity;
    }

}
