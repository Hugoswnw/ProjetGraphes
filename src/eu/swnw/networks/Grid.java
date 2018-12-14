package eu.swnw.networks;

import eu.swnw.networks.edges.Edge;
import eu.swnw.networks.edges.EdgeMin;
import eu.swnw.networks.nodes.Node;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;

public class Grid {

    private double [][] values;
    private int[][] result;
    private int lines, columns;

    public Grid(int lines, int columns) {
        this.lines = lines;
        this.columns = columns;

        this.values = new double [lines+1][columns+1];

    }

    public static Grid gridFromFile(String path) throws Exception {
        File file = new File(path);
        BufferedReader br = new BufferedReader(new FileReader(file));

        int lines = Integer.parseInt(br.readLine());
        int columns = Integer.parseInt(br.readLine());

        Grid g = new Grid(lines, columns);
        NumberFormat format = NumberFormat.getInstance(Locale.FRANCE);
        for (int i = 0; i < lines; i++) {
            String [] numbers = br.readLine().split("\\s+");
            for (int j = 0; j<columns; j++) {
                g.setValue(i, j, format.parse(numbers[j]).doubleValue());
            }
        }

        return g;
    }

    public Grid calculateSums(){

        for (int i = 0; i < lines; i++) {
            for (int j = 0; j<columns; j++) {
                double v = getValue(i, j);
                this.values[i][columns] += v;
                this.values[lines][j] += v;
            }
        }

        return this;
    }


    public NetworkMin constructionEtape4(){
        HashMap<String, Node> nodes = new HashMap<String, Node>();
        ArrayList<Edge> edges = new ArrayList<Edge>();

        Node source = new Node("s");
        Node sink = new Node("t");
        nodes.put(source.getName(), source);
        nodes.put(sink.getName(), sink);

        for (int i = 0; i < lines; i++){
            Node n = new Node(i+"l");
            //edges.add(new EdgeMin(nodes.get("s"), n, 0,  result[i][columns], (int) values[i][columns]+1));
            edges.add(new EdgeMin(nodes.get("s"), n, 0,  (int) values[i][columns], (int) values[i][columns]+1));
            nodes.put(n.getName(), n);
        }

        for (int j = 0; j < columns; j++){
            Node n = new Node(j+"c");
            //edges.add(new EdgeMin(n, nodes.get("t"),0, result[lines][j], (int) values[lines][j]+1));
            edges.add(new EdgeMin(n, nodes.get("t"),0, (int) values[lines][j], (int) values[lines][j]+1));
            nodes.put(n.getName(), n);
        }

        for (int i = 0; i < lines; i++) {
            for (int j = 0; j<columns; j++) {
                edges.add(new EdgeMin(nodes.get(i+"l"), nodes.get(j+"c"),0, (int) values[i][j], (int) values[i][j]+1));
            }
        }


        return new NetworkMin(nodes, edges, nodes.get("s"), nodes.get("t"));
    }

    public void calculateResult(Network G){
        this.result = new int [lines+1][columns+1];
        for (int i = 0; i < lines; i++) {
            for (int j = 0; j<columns; j++) {
                result[i][j] = (int) values[i][j];
                result[i][columns] += (int) values[i][j];
                result[lines][j] += (int) values[i][j];
            }
        }

        for(Edge e : G.nodes.get("s").getEdgesOut().values()){
            String numu = e.getTo().getName();
            if(Character.isDigit(numu.charAt(0))) {
                int i = Integer.parseInt(numu.substring(0, numu.length()-1));
                for (Edge e2 : e.getTo().getEdgesOut().values()) {
                    String numv = e2.getTo().getName();
                    if(Character.isDigit(numv.charAt(0))) {
                        int j = Integer.parseInt(numv.substring(0, numv.length()-1));
                        result[i][j] += e2.getFlow();
                        result[i][columns] += e2.getFlow();
                        result[lines][j] += e2.getFlow();
                    }

                }
            }
        }
    }

    public boolean checkSums(Network G){
        for (int i = 0; i <= lines; i++){
            if((result[i][columns]-((int)values[i][columns])<0)){
                return false;
            }
        }
        for (int j = 0; j <= columns; j++){
            if((result[lines][j]-((int)values[lines][j])<0)){
                return false;
            }
        }
        return true;
    }

    public void printResult(Network G){

        for (int i = 0; i < lines+1; i++){
            for (int j = 0; j < columns+1; j++){
                System.out.print((result[i][j])+"  ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public void printRelativeResult(Network G){

        for (int i = 0; i < lines+1; i++){
            for (int j = 0; j < columns+1; j++){
                System.out.print((result[i][j]-((int)values[i][j]))+"  ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public void print(){
        for (int i = 0; i < lines+1; i++){
            for (int j = 0; j < columns+1; j++){
                System.out.printf("%.1f ", values[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }

    public void setValue(int x, int y, double value){
        this.values[x][y] = value;
    }

    public double getValue(int x, int y){
        return this.values[x][y];
    }

    public int getLines() {
        return lines;
    }

    public int getColumns() {
        return columns;
    }
}

