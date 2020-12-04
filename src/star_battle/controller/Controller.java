package star_battle.controller;

import java.io.IOException;
import java.util.ArrayList;

import star_battle.exceptions.InvalidInstanceDimensionException;
import star_battle.exceptions.InvalidSectorValueException;
import star_battle.exceptions.InvalidStarsNumberException;
import star_battle.model.InstanceMatrix;
import star_battle.model.LogicCell;
import star_battle.model.SolutionMatrix;
import star_battle.model.UserMatrix;

public class Controller {

	private InstanceMatrix matrix;
	private UserMatrix userMatrix;
	private SolutionMatrix solutionMatrix;
	
	public Controller() { }
	
	public void loadNewInstance(int level){

		matrix = new InstanceMatrix("data/data" + (level - 1) + ".dzn");
		try {
			matrix.parseMatrix();
		} catch (IOException e) {
			// TODO: Call a UI Method to show the error
		} catch (InvalidStarsNumberException e){
			// TODO: Call a UI Method to show the error
		} catch (InvalidSectorValueException e){
			// TODO: Call a UI Method to show the error
		} catch (InvalidInstanceDimensionException e){
			// TODO: Call a UI Method to show the error
		}
		
		userMatrix = new UserMatrix(matrix.getDimension());
		solutionMatrix = new SolutionMatrix("");
		
	}
	
	
	public ArrayList<LogicCell> checkConstraints() {
		
		ArrayList<LogicCell> violatedCells = new ArrayList<LogicCell>();
		
		
		return violatedCells;
	}


	public boolean differentSectorOfBottomCell(int i, int j) {
		if (matrix==null){
			throw new RuntimeException("Matrix not instanced!");
		}
		return matrix.getSector(i, j) != matrix.getSector(i+1, j);
	}


	public boolean differentSectorOfRightCell(int i, int j) {
		if (matrix==null){
			throw new RuntimeException("Matrix not instanced!");
		}
		return matrix.getSector(i, j) != matrix.getSector(i, j+1);
	}

    public int getDimension() {
		if (matrix==null){
			throw new RuntimeException("Matrix not instanced!");
		}
		return matrix.getDimension();
    }
    
    public int getStarsNumber() {
    	if (matrix==null){
			throw new RuntimeException("Matrix not instanced!");
		}
		return matrix.getStarsNumber();
    }
}
