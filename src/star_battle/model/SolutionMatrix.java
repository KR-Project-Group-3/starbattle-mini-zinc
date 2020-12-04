package star_battle.model;

public class SolutionMatrix {

    private String solutionFilePath;
    private boolean[][] solutionMatrix = {
    		{false, false, true, false, false},
    		{true, false, false, false, false},
    		{false, false, false, false, true},
    		{false, true, false, false, false},
    		{false, false, false, true, false}
    };

    public SolutionMatrix(String solutionFilePath){
        this.solutionFilePath = solutionFilePath; 
    }

    public void parseMatrix(){

    }
    
    public boolean get(int i, int j) {
    	return solutionMatrix[i][j];
    }
}
