package star_battle.model;

import star_battle.model.minizinc.MiniZincConnector;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class DynamicInstanceSolutionChecker {

    private int[][] generatedMatrix;
    private int dimension;
    private MiniZincConnector miniZincConnector;
    private String dynamicInstanceGeneratorDataFilePath;
    private int generatedInstanceStarsNumber;
    private String dynamicInstanceGeneratorModelFilePath;


    public DynamicInstanceSolutionChecker(int[][] generatedMatrix){
        this.generatedMatrix = generatedMatrix;
        this.dimension = this.generatedMatrix[0].length;
        this.dynamicInstanceGeneratorDataFilePath = "data" + File.separator + "data.dzn";
        this.dynamicInstanceGeneratorDataFilePath = "models" + File.separator + "instance_checker.dzn";
        this.writeFile();
    }

    private void writeFile() {

        Charset charset = StandardCharsets.US_ASCII;
        try {
            BufferedWriter writer = Files.newBufferedWriter(Path.of(this.dynamicInstanceGeneratorDataFilePath),
                    charset);
            String textToWrite = "dim = " + this.getDimension() + ";\n" + "sectors = [|\n";

            for (int i = 0; i < this.getDimension(); ++i) {
                for (int j = 0; j < this.getDimension(); j++) {
                    textToWrite += generatedMatrix[i][j];

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

    public boolean checkUniqueSolution() throws IOException {
        MiniZincConnector miniZincConnector = new MiniZincConnector( this.dynamicInstanceGeneratorModelFilePath,
                this.dynamicInstanceGeneratorDataFilePath);

        BufferedReader reader = miniZincConnector.returnResponse();
        String line = "";

        ArrayList<Integer> starsSolutions = new ArrayList<>();

        for (int i = 0; (line = reader.readLine()) != null; ++i) {

            if (line.equals("=====UNSATISFABLE====="))
                return false;

            if (line.equals("----------"))
                continue;

            starsSolutions.add(Integer.parseInt(line));
        }

        starsSolutions.sort(Integer::compareTo);

        int counter = 0;
        for (int i = 0; i < starsSolutions.size() - 1; i++) {
            if(!(starsSolutions.get(i).equals(starsSolutions.get(i + 1)))) {
                if(counter == 0) {
                    this.generatedInstanceStarsNumber = starsSolutions.get(i);
                    return true;
                } else {
                    counter = 0;
                }
            } else {
                counter++;
            }
        }

        return false;
    }

    public int getDimension() {
        return dimension;
    }

    public int getGeneratedInstanceStarsNumber() {
        return generatedInstanceStarsNumber;
    }

    public void loadNewMatrix(int[][] generatedMatrix){
        this.generatedMatrix = generatedMatrix;
        writeFile();
    }
}
