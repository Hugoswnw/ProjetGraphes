package eu.swnw;

import eu.swnw.networks.Grid;
import eu.swnw.networks.Network;
import eu.swnw.networks.NetworkPushRelabel;

public class Main {

    public static void main(String[] args) {
        /*
        String[] v  = {"s", "1", "2", "3", "t"};
        String[] froms      = {"s", "s", "1", "1", "2", "3"};
        String[] tos        = {"1", "2", "2", "3", "3", "t"};
        int[] capacities = { 5,   3,   4,   6,   8,   9 };
        Network network = new Network(v, froms, tos, capacities, "s", "t");
        */

        try {
            Grid g = Grid.gridFromFile("data/exemple.txt");
            int o = 0;
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
