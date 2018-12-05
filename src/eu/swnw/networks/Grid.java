package eu.swnw.networks;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.text.NumberFormat;
import java.util.Locale;

public class Grid {

    private double [][] values;
    private int [][] valuesMin;
    private int [][] valuesMax;
    private double [] sumsl, sumsc;
    private double [] sumslmin, sumscmin;
    private double [] sumslmax, sumscmax;
    private int lines, columns;

    public Grid(int lines, int columns) {
        this.lines = lines;
        this.columns = columns;

        this.values = new double [lines][columns];
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

    public void setValue(int x, int y, double value){
        this.values[x][y] = value;
    }

}

