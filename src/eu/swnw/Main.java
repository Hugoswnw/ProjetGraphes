package eu.swnw;

import eu.swnw.networks.*;

public class Main {

    public static void main(String[] args) {

        if(args.length>0){
            try {
                boolean debug = (args.length>1 && args[1].equals("-debug"));
                Grid g = Grid.gridFromFile(args[0]).calculateSums();

                NetworkMin g1 = g.constructionEtape4();
                NetworkRequest g2 = NetworkMin.constructionEtape3(g1);
                Network network, g3;
                int fixedFlow = Math.max(g.getColumns(), g.getLines());
                do {
                    g3 = Network.constructionEtape2(g2).constructionEtape1(--fixedFlow%Math.max(g.getColumns(), g.getLines()));
                    NetworkPushRelabel pr = NetworkPushRelabel.networkToNPR(g3);
                    pr.preflot(debug);
                    network = NetworkPushRelabel.NPRtoNetwork(pr);
                    g.calculateResult(network);
                }while (!g.checkSums(network) && fixedFlow>=0);

                if(debug){
                    System.out.println(g1);
                    System.out.println(g2);
                    System.out.println(g3);
                    System.out.println(network);
                    System.out.println("Fixed value : "+fixedFlow%Math.max(g.getColumns(), g.getLines()));
                    g.printRelativeResult(network);
                }

                g.print();
                g.printResult(network);

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("usage: java -jar <jar> <path> [-debug]");
        }

    }
}
