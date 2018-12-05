package eu.swnw;

import eu.swnw.networks.Network;
import eu.swnw.networks.NetworkPushRelabel;

public class Main {

    public static void main(String[] args) {

        String[] v  = {"s", "1", "2", "3", "t"};
        String[] froms      = {"s", "s", "1", "1", "2", "3"};
        String[] tos        = {"1", "2", "2", "3", "3", "t"};
        int[] capacities = { 5,   3,   4,   6,   8,   9 };
        Network network = new Network(v, froms, tos, capacities, "s", "t");
        network.addFlowLimit(6);
        System.out.println(network);

        NetworkPushRelabel solved = NetworkPushRelabel.networkToNPR(network);
        solved.pushRelabel();
        System.out.println(solved);
        System.out.println(NetworkPushRelabel.NPRtoNetwork(solved));
    }
}
