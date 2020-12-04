package star_battle.model;

import java.util.ArrayList;

public class UserMatrix {
	
	private int dimension;
	private boolean[][] userMatrix;
	private int numberOfStars;


	public UserMatrix(int dimension, int numberOfStars) {
		
		this.dimension = dimension + 2;
		this.userMatrix = new boolean[this.dimension][this.dimension];
		this.numberOfStars = numberOfStars;

		
	}
	
	public int getDimension() {
		return dimension;
	}

	public void setStar(int i, int j, boolean setOrDelete){
		userMatrix[i+1][j+1] = setOrDelete;
	}

	public boolean get(int i, int j){
		return userMatrix[i][j];
	}



}
