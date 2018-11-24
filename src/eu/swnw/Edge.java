package eu.swnw;

public class Edge {

    private Node from, to;
    private double flow, capacity;

    public Edge( Node from, Node to, double flow, double capacity){
        this.from = from;
        from.addEdge(this);
        this.to = to;
        this.capacity = capacity;
        this.flow = flow;
    }

    public Edge( Node from, Node to, double capacity ){
        this(from, to, 0, capacity);
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

    public void setFlow(double flow) {
        this.flow = flow;
    }

    public double getCapacity() {
        return capacity;
    }

    @Override
    public String toString(){
        return from+"->"+to+" : "+flow+"/"+capacity;
    }
}
