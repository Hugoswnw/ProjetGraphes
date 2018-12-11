package eu.swnw;

import eu.swnw.networks.*;

public class Main {

    public static void main(String[] args) {

        if(args.length>0){
            try {
                for (String s: args) {
                    System.out.println(s);
                }
                Grid g = Grid.gridFromFile(args[0]).calculateSums();

                NetworkMin g1 = g.constructionEtape4();
                NetworkRequest g2 = NetworkMin.constructionEtape3(g1);
                Network g3 = Network.constructionEtape2(g2);
                NetworkPushRelabel pr = NetworkPushRelabel.networkToNPR(g3);
                Network network = NetworkPushRelabel.NPRtoNetwork(pr);

                g.print();

                if(args.length>1 && args[1].equals("--debug")){
                    System.out.println(g1);
                    System.out.println(g2);
                    System.out.println(g3);
                    pr.preflot();
                }

                g.printResult(network);

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("usage: java -jar <jar> <path> [--debug]");
        }

    }
}
