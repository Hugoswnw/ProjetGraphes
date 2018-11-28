package eu.swnw;

public class Edge {

    private Node from;
    private Node to;
    private double flow;
    private double capacity;
    private Edge inverted;


    public Edge(Node from, Node to, double flow, double capacity){
        this.from = from;
        this.to = to;
        this.flow = flow;
        this.capacity = capacity;

        this.inverted = new Edge();
        this.inverted.from = to;
        this.inverted.to = from;
        this.inverted.flow = -flow;
        this.inverted.capacity = 0;
        this.inverted.inverted = this;

        from.addEdgeOut(this);
        to.addEdgeOut(this.inverted);
        from.addEdgeIn(this.inverted);
        to.addEdgeIn(this);
    }

    public Edge(){

    }

    public  Node getFrom() {
        return from;
    }

    public  Node getTo() {
        return to;
    }

    public double getFlow() {
        return flow;
    }

    public void setFlow(double flow){
        this.flow = flow;
        this.inverted.flow = -flow;
    }

    public void incrementFlow(double flow){
        this.flow += flow;
        this.inverted.flow -= flow;
    }

    public Edge getInverted(){
        return inverted;
    }


    public double getCapacity() {
        return capacity;
    }


    @Override
    public String toString(){
        return from+"->"+to+" : "+flow+"/"+capacity;
    }

}
