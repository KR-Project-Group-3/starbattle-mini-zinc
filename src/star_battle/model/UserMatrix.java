package star_battle.model;

public class UserMatrix {
	
	private int dimension;
	private boolean[][] userMatrix;
			
	public UserMatrix(int dimension) {
		
		this.dimension = dimension + 2;
		this.userMatrix = new boolean[this.dimension][this.dimension];
		
	}
	
	public int getDimension() {
		return dimension;
	}

}
