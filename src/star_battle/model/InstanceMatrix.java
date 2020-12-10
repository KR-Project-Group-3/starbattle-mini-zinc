package star_battle.model;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InstanceMatrix {

    private int dimension;
    private int starsNumber;
    private int[][] sectorsMatrix;
    private String instanceFilePath;

    public InstanceMatrix(int level){

        parseHTML(level);

    }

    public InstanceMatrix(int[][] sectorsMatrix, int starsNumber){
        this.sectorsMatrix = sectorsMatrix;
        this.dimension = sectorsMatrix[0].length;
        this.starsNumber = starsNumber;
    }

    private void parseHTML(int level){
        String content = null;
        URLConnection connection = null;
        String pattern = "var task = '(.*?)'";
        try {
            connection =  new URL("https://it.puzzle-star-battle.com/?size="+level).openConnection();
            Scanner scanner = new Scanner(connection.getInputStream());
            scanner.useDelimiter("\\Z");
            content = scanner.next();
            scanner.close();
        }catch ( Exception ex ) {
            ex.printStackTrace();
        }

        Pattern r = Pattern.compile(pattern);
        assert content != null;
        Matcher m = r.matcher(content);

        String[] tasks = null;
        if (m.find( )) {
            tasks = m.group(1).split(",");
        }else {
            throw new RuntimeException("Cannot read the instance! Please retry!");
        }


        this.dimension = (int)Math.sqrt(tasks.length);

        if(level < 9){
            r = Pattern.compile("<div class=\"puzzleInfo\"><p>.*/(\\d+?)");
            m = r.matcher(content);

            if (m.find( )) {
                this.starsNumber = Integer.parseInt(m.group(1));
            }else {
                throw new RuntimeException("Cannot read the instance! Please retry!");
            }
        }
        else{
            r = Pattern.compile("<div class=\"puzzleInfo\"><p>(\\d+?)");
            m = r.matcher(content);

            if (m.find( )) {
                this.starsNumber = Integer.parseInt(m.group(1));
            }else {
                throw new RuntimeException("Cannot read the instance! Please retry!");
            }
        }
        writeFile(tasks);
    }

    private void writeFile(String[] tasks){

        try (FileWriter writer = new FileWriter("data/data.dzn");
             BufferedWriter bw = new BufferedWriter(writer)) {
            bw.write("num_stars = "+this.starsNumber+";\n");
            bw.write("dim = " +this.dimension + ";\n");
            bw.write("sectors = [|\n");
            int i = 1;
            int j = 0, k=0;

            this.sectorsMatrix = new int[dimension][dimension];
            for(String e: tasks) {
                sectorsMatrix[k][j] = Integer.parseInt(e);
                ++j;
                if(i % this.dimension == 0){
                   bw.write(e+"|\n");
                   ++k;
                   j=0;
                }
                else{
                    bw.write(e+", ");
                }
                ++i;

            }
            bw.write("];");

        } catch (IOException e) {
            System.err.format("IOException: %s%n", e);
        }
    }

    public int getDimension() {
        return dimension;
    }

    public int getStarsNumber() {
        return starsNumber;
    }

    public void setStarsNumber(int starsNumber) {
        this.starsNumber = starsNumber;
    }

    public int getSector(int row, int column) {
        return sectorsMatrix[row][column];
    }
}
