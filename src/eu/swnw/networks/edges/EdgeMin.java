package eu.swnw.networks.edges;

import eu.swnw.networks.nodes.Node;

public class EdgeMin extends Edge {

    private int min;

    public EdgeMin(Node from, Node to, int flow, int min, int capacity) {
        super(from, to, flow, capacity);
        this.min = min;
    }

    public int getMin() {
        return min;
    }

    @Override
    public String toString(){
        return from+"->"+to+" : "+flow+"/"+min+"-"+capacity;
    }
}
