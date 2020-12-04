package star_battle.controller;

import star_battle.model.InstanceMatrix;

import java.io.IOException;

public class Controller {

	private InstanceMatrix matrix = null;
	
	
	public Controller() { }
	
	public void instanceMatrix(int level){
		level -= 1;
		matrix = new InstanceMatrix("data/data"+level+".dzn");
		try {
			matrix.parseMatrix();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	public boolean differentSectorOfBottomCell(int i, int j) {
		if (matrix==null){
			throw new RuntimeException("Matrix not instanced!");
		}
		return matrix.getSectorsMatrix()[i][j] != matrix.getSectorsMatrix()[i+1][j];
	}


	public boolean differentSectorOfRightCell(int i, int j) {
		if (matrix==null){
			throw new RuntimeException("Matrix not instanced!");
		}
		return matrix.getSectorsMatrix()[i][j] != matrix.getSectorsMatrix()[i][j+1];
	}

    public int getDimension() {
		if (matrix==null){
			throw new RuntimeException("Matrix not instanced!");
		}
		return matrix.getDimension();
    }
}
