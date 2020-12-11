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
        this.generatedInstanceFilePath = "data" + File.separator + "data.dzn";
    }

    public void parseMatrix() throws IOException {

        File instanceFile = new File(this.generatedInstanceFilePath);

        boolean isWindows = System.getProperty("os.name")
                .toLowerCase().startsWith("windows");

        if(isWindows) {
            this.process = Runtime.getRuntime()
                    .exec("minizinc models" + File.separator + "star_puzzle.mzn " +
                                    this.generatedInstanceFilePath
                            , null, new File(new File(instanceFile.getAbsoluteFile().getParent()).getParent()));
        } else {
            this.process = Runtime.getRuntime()
                    .exec("minizinc models" + File.separator + "star_puzzle.mzn " +
                            this.generatedInstanceFilePath + " --solver Gecode", null,
                            new File(new File(instanceFile.getAbsoluteFile().getParent()).getParent()));
        	
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

    public boolean isMatrixInstantiated(){
        return this.solutionMatrix != null;
    }
    
    public boolean get(int i, int j) {
    	return solutionMatrix[i][j];
    }
}
