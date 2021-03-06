package star_battle.model;

import star_battle.model.minizinc.MiniZincConnector;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;

public class SolutionMatrix extends Thread{

    private InstanceMatrix instanceMatrix;
    private boolean[][] solutionMatrix;
    private String generatedInstanceFilePath;
    private Process process;
    private MiniZincConnector miniZincConnector;
    private boolean finished = false;

    public SolutionMatrix(InstanceMatrix instanceMatrix){
        this.instanceMatrix = instanceMatrix;
        this.generatedInstanceFilePath = "data" + File.separator + "data.dzn";
    }

    public SolutionMatrix(boolean[][] solutionMatrix){
        this.solutionMatrix = solutionMatrix;

        for (int i = 0; i < solutionMatrix.length; i++) {
            for (int j = 0; j < solutionMatrix.length; j++) {
                if (solutionMatrix[i][j])
                    System.out.print("1 ");
                else
                    System.out.print("0 ");
            }
            System.out.println();
        }

        this.finished = true;
    }

    public void parseMatrix() throws IOException {
        MiniZincConnector miniZincConnector = new MiniZincConnector("models" + File.separator + "star_puzzle.mzn",
                this.generatedInstanceFilePath);
        BufferedReader reader = miniZincConnector.returnResponse();
        String line = "";

        this.solutionMatrix = new boolean[this.instanceMatrix.getDimension()][this.instanceMatrix.getDimension()];
        for (int i = 0; (line = reader.readLine()) != null; ++i) {

            if(i >= this.instanceMatrix.getDimension())
                continue;

            String[] lineArray = line.split(" ");
            System.out.println(line);
            for (int j = 0; j < this.instanceMatrix.getDimension(); j++) {

                if(Integer.parseInt(lineArray[j]) == 1)
                    solutionMatrix[i][j] = true;
                else
                    solutionMatrix[i][j] = false;
            }
        }
        this.finished = true;
    }

    @Override
    public void run() {
        super.run();
        try {
            parseMatrix();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean isMatrixInstantiated(){
        return this.solutionMatrix != null;
    }
    
    public boolean get(int i, int j) {
    	return solutionMatrix[i][j];
    }

    public boolean isFinished() {
        return finished;
    }
}
