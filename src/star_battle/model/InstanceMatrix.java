package star_battle.model;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
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
        this.writeFile();
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

        try {
            Charset charset = StandardCharsets.US_ASCII;
            BufferedWriter bw = new BufferedWriter(Files.newBufferedWriter(Path.of("data" + File.separator
                    + "data.dzn"), charset));
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
                    if(i < tasks.length) {
                        bw.write(e + "|\n");
                        ++k;
                        j = 0;
                    } else {
                        bw.write(e + "|");
                    }
                }
                else{
                    bw.write(e+", ");
                }
                ++i;

            }

            bw.write("];");
            bw.close();

        } catch (IOException e) {
            System.err.format("IOException: %s%n", e);
        }
    }

    private void writeFile() {

        Charset charset = StandardCharsets.US_ASCII;
        try {
            BufferedWriter writer = Files.newBufferedWriter(Path.of("data" + File.separator + "data.dzn"), charset);
            String textToWrite = "num_stars = " + this.getStarsNumber() + ";\n";
            textToWrite += "dim = " + this.getDimension() + ";\n" + "sectors = [|\n";

            for (int i = 0; i < this.getDimension(); ++i) {
                for (int j = 0; j < this.getDimension(); j++) {
                    textToWrite += this.getSector(i, j);

                    if (j < this.getDimension() - 1) {
                        textToWrite += ", ";
                    }
                }

                textToWrite += "|";

                if (i < this.getDimension() - 1)
                    textToWrite += "\n";
            }

            textToWrite += "];";
            writer.write(textToWrite, 0, textToWrite.length());
            writer.close();
        } catch (IOException e){
            e.printStackTrace();
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
