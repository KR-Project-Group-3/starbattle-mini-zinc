package star_battle.controller;

import star_battle.exceptions.InvalidInstanceDimensionException;
import star_battle.exceptions.InvalidSectorValueException;
import star_battle.exceptions.InvalidStarsNumberException;
import star_battle.model.InstanceMatrix;

import java.io.IOException;

public class Controller {

	private InstanceMatrix matrix;
	
	
	public Controller() { }
	
	public void loadNewInstance(int level){

		matrix = new InstanceMatrix("data/data" + level + ".dzn");
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
	}


	public boolean differentSectorOfBottomCell(int i, int j) {
		if (matrix==null){
			throw new RuntimeException("Matrix not instanced!");
		}
		return matrix.getSectorsMatrix(i, j) != matrix.getSectorsMatrix(i+1, j);
	}


	public boolean differentSectorOfRightCell(int i, int j) {
		if (matrix==null){
			throw new RuntimeException("Matrix not instanced!");
		}
		return matrix.getSectorsMatrix(i, j) != matrix.getSectorsMatrix(i, j+1);
	}

    public int getDimension() {
		if (matrix==null){
			throw new RuntimeException("Matrix not instanced!");
		}
		return matrix.getDimension();
    }
}
