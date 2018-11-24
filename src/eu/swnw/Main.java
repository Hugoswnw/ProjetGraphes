package eu.swnw;

public class Main {

    public static void main(String[] args) {

        String[] v = {"s", "1", "2", "3", "t"};
        int[] froms         = {0, 0, 1, 1, 2, 3};
        int[] tos           = {1, 2, 2, 3, 3, 4};
        double[] capacities = {5, 3, 4, 6, 8, 9};
        FlowNetwork network = new FlowNetwork(v, froms, tos, capacities);
        System.out.println(network);
    }
}
