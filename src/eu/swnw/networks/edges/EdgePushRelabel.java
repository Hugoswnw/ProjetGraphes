package eu.swnw.networks.edges;

import eu.swnw.networks.nodes.NodePushRelabel;

public class EdgePushRelabel extends Edge {

    private EdgePushRelabel inverted;


    public EdgePushRelabel(NodePushRelabel from, NodePushRelabel to, double flow, double capacity){
        super(from, to, flow, capacity);

        this.inverted = new EdgePushRelabel();
        this.inverted.from = to;
        this.inverted.to = from;
        this.inverted.flow = -flow;
        this.inverted.capacity = 0;
        this.inverted.inverted = this;

        to.addEdgeOut(this.inverted);
        from.addEdgeIn(this.inverted);
    }

    public EdgePushRelabel(){
        super();
    }

    @Override
    public void setFlow(double flow){
        this.flow = flow;
        this.inverted.flow = -flow;
    }

    @Override
    public void incrementFlow(double flow){
        this.flow += flow;
        this.inverted.flow -= flow;
    }

    public EdgePushRelabel getInverted(){
        return inverted;
    }


    public double getCapacity() {
        return capacity;
    }


}
