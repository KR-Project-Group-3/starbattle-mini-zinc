package star_battle.controller;

public class Controller {

	//private Model model;
	
	private int n = 5;
	private int[][] sectors =  {
			{1, 2, 2, 2, 3},
			{1, 2, 2, 2, 3},
			{1, 1, 4, 5, 5},
			{1, 1, 4, 4, 4},
			{1, 4, 4, 4, 4}
	};
	
	
	public Controller() {
		
		//model = new Model();
	}
	
	
	public boolean differentSectorOfBottomCell(int i, int j) {
		return sectors[i][j] != sectors[i+1][j];
	}


	public boolean differentSectorOfRightCell(int i, int j) {
		return sectors[i][j] != sectors[i][j+1];
	}
	
}
