package eu.swnw;

import eu.swnw.networks.*;

public class Main {

    public static void main(String[] args) {

        try {
            Grid g = Grid.gridFromFile("data/exemple3.txt").calculateSums();

            NetworkMin g1 = g.constructionEtape4();
            System.out.println(g1);
            NetworkRequest g2 = NetworkMin.constructionEtape3(g1);
            System.out.println(g2);
            Network g3 = Network.constructionEtape2(g2);
            System.out.println(g3);
            NetworkPushRelabel pr = NetworkPushRelabel.networkToNPR(g3);
            pr.preflot();
            System.out.println(NetworkPushRelabel.NPRtoNetwork(pr));
            g.print();
            System.out.println();
            g.printResult(NetworkPushRelabel.NPRtoNetwork(pr));
            int o = 0;
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
