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

    private int dimension;
    private MiniZincConnector miniZincConnector;
    private String dynamicInstanceGeneratorDataFilePath;
    private int generatedInstanceStarsNumber;
    private String dynamicInstanceGeneratorModelFilePath;
    private String solutionCheckerDataFilePath;
    private String solutionCheckerModelFilePath;
    private boolean[][] solutionMatrix;


    public DynamicInstanceSolutionChecker(int[][] generatedMatrix){
        this.dimension = generatedMatrix[0].length;
        this.solutionMatrix = new boolean[this.dimension][this.dimension];
        this.solutionCheckerDataFilePath = "data" + File.separator + "data_optional_scheck.dzn";
        this.solutionCheckerModelFilePath = "models" + File.separator + "star_puzzle.mzn";
        this.dynamicInstanceGeneratorDataFilePath = "data" + File.separator + "data_optional.dzn";
        this.dynamicInstanceGeneratorModelFilePath = "models" + File.separator + "dynamic_star_puzzle.mzn";
        this.writeFile(this.dynamicInstanceGeneratorDataFilePath, generatedMatrix, true);
    }

    private void writeFile(String dataFileToWritePath, int[][] generatedMatrix, boolean optionalSectors) {

        Charset charset = StandardCharsets.US_ASCII;
        try {
            BufferedWriter writer = Files.newBufferedWriter(Path.of(dataFileToWritePath),
                    charset);

            String matrixName;
            String textToWrite = "dim = " + this.getDimension() + ";\n";

            if(optionalSectors) {
                matrixName = "init_sectors";
            } else {
                textToWrite += "num_stars = 1;\n";
                matrixName = "sectors";
            }

            textToWrite += matrixName + " = [|\n";

            for (int i = 0; i < this.getDimension(); ++i) {
                for (int j = 0; j < this.getDimension(); j++) {
                    if(generatedMatrix[i][j] == 0)
                        textToWrite += "<>";
                    else
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

    public boolean checkUniqueSolution (int[][] instancedMatrix) throws IOException {

        writeFile(this.solutionCheckerDataFilePath, instancedMatrix, false);

        MiniZincConnector miniZincConnector = new MiniZincConnector(this.solutionCheckerModelFilePath,
                this.solutionCheckerDataFilePath);

        BufferedReader reader = miniZincConnector.returnResponse();

        String line = "";

        for (int i = 0; (line = reader.readLine()) != null; ++i) {

            if (line.equals("=====UNSATISFIABLE====="))
                return false;

            String[] lineArray = line.split(" ");
            String last;


            if (line.equals("----------")){
                if(!reader.readLine().equals("=========="))
                    return false;
            } else {


                for (int j = 0; j < this.dimension; j++) {

                    if (Integer.parseInt(lineArray[j]) == 1)
                        solutionMatrix[i][j] = true;
                    else
                        solutionMatrix[i][j] = false;
                }

            }

        }

        return true;
    }

    public int[][] retrieveDynamicInstancesSectors() throws IOException {
        MiniZincConnector miniZincConnector = new MiniZincConnector(this.dynamicInstanceGeneratorModelFilePath,
                this.dynamicInstanceGeneratorDataFilePath);

        BufferedReader reader = miniZincConnector.returnResponse();
        String line = "";

        int[][] dynamicMatrix = new int[this.dimension][this.dimension];


        for (int i = 0; (line = reader.readLine()) != null; ++i) {

            String[] lineArray = line.split(" ");

            if (line.equals("----------") || line.equals("==========")){
                if(checkUniqueSolution(dynamicMatrix))
                    return dynamicMatrix;

                i = -1;
            } else {

                for (int j = 0; j < this.dimension; j++)
                    dynamicMatrix[i][j] = Integer.parseInt(lineArray[j]);
            }

        }

        return null;
    }

    public boolean[][] getSolutionMatrix() {
        return solutionMatrix;
    }

    public int getDimension() {
        return dimension;
    }
}
