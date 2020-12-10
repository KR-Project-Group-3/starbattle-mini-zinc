package star_battle.model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class SolutionMatrix {

    private InstanceMatrix instanceMatrix;
    private boolean[][] solutionMatrix;
    private String generatedInstanceFilePath;
    private Process process;

    public SolutionMatrix(InstanceMatrix instanceMatrix){
        this.instanceMatrix = instanceMatrix;
        this.generatedInstanceFilePath = "instance.dzn";
    }
    private void generateDataFile() throws IOException {

        Charset charset = StandardCharsets.US_ASCII;
        BufferedWriter writer = Files.newBufferedWriter(Path.of(this.generatedInstanceFilePath), charset);
        String textToWrite = "num_stars = " + this.instanceMatrix.getStarsNumber() + ";\n";
        textToWrite += "dim = " + this.instanceMatrix.getDimension() + ";\n" + "sectors = [|\n";

        for (int i = 0; i < instanceMatrix.getDimension(); ++i){
            for (int j = 0; j < instanceMatrix.getDimension(); j++) {
                textToWrite += this.instanceMatrix.getSector(i, j);

                if(j < this.instanceMatrix.getDimension() - 1){
                    textToWrite += ", ";
                }
            }

            textToWrite += "|";

            if(i < this.instanceMatrix.getDimension() - 1)
                textToWrite += "\n";
        }

        textToWrite += "];";
        writer.write(textToWrite, 0, textToWrite.length());
        writer.close();
    }

    public void parseMatrix() throws IOException {

        generateDataFile();
        File instanceFile = new File(this.generatedInstanceFilePath);

        boolean isWindows = System.getProperty("os.name")
                .toLowerCase().startsWith("windows");

        if(isWindows) {
            this.process = Runtime.getRuntime()
                    .exec("minizinc models" + File.separator + "star_puzzle.mzn " +
                                    this.generatedInstanceFilePath
                            , null, new File(instanceFile.getAbsoluteFile().getParent()));
        } else {
            this.process = Runtime.getRuntime()
                    .exec("minizinc models " + File.separator + "star_puzzle.mzn " +
                            this.generatedInstanceFilePath + " --solver Gecode", null, new File(instanceFile.getAbsoluteFile().getParent()));
        	
        }

        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String line = "";

        this.solutionMatrix = new boolean[this.instanceMatrix.getDimension()][this.instanceMatrix.getDimension()];
        for (int i = 0; (line = reader.readLine()) != null; ++i) {

            if(i == 0 || i == this.instanceMatrix.getDimension() + 1)
                continue;

            String[] lineArray = line.split(" ");
            System.out.println(line);
            for (int j = 0; j < this.instanceMatrix.getDimension(); j++) {

                if(Integer.parseInt(lineArray[j]) == 1)
                    solutionMatrix[i - 1][j] = true;
                else
                    solutionMatrix[i - 1][j] = false;
            }
        }
    }

    // Dummy commit
    public boolean isMatrixInstantiated(){
        return this.solutionMatrix != null;
    }
    
    public boolean get(int i, int j) {
    	return solutionMatrix[i][j];
    }
}
