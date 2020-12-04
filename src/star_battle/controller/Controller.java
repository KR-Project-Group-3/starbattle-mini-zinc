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
		
		userMatrix = new UserMatrix(matrix.getDimension(), matrix.getStarsNumber());
		solutionMatrix = new SolutionMatrix("");
		
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

	public void setStar(int i, int j, boolean setOrDelete) {
		userMatrix.setStar(i,j, setOrDelete);
	}

	public ArrayList<LogicCell> checkConstraints(){
		int numberOfStars = getStarsNumber();
		ArrayList<LogicCell> cells = new ArrayList<>();

		//TODO find a better way

		//same line and same column and adjacent
		for(int i = 1; i < userMatrix.getDimension()-1; i++){
			ArrayList<LogicCell> cellsInaRow = new ArrayList<>();
			ArrayList<LogicCell> cellsInaColumn = new ArrayList<>();
			for(int j = 1; j < userMatrix.getDimension()-1; j++){

				if(userMatrix.get(i,j)){

					cellsInaRow.add(new LogicCell(i,j));
					boolean added = false;

					if(userMatrix.get(i,j-1)){
						cells.add(new LogicCell(i,j-1));
						added = true;
					}
					if(userMatrix.get(i-1,j-1)){
						cells.add(new LogicCell(i-1,j-1));
						added = true;
					}
					if(userMatrix.get(i-1,j)) {
						added = true;
						cells.add(new LogicCell(i - 1, j));
					}
					if(userMatrix.get(i-1,j+1)){
						added = true;
						cells.add(new LogicCell(i - 1, j+1));
					}
					if(userMatrix.get(i,j+1)){
						added = true;
						cells.add(new LogicCell(i, j+1));
					}
					if(userMatrix.get(i+1,j+1)) {
						added = true;
						cells.add(new LogicCell(i + 1, j + 1));
					}
					if(userMatrix.get(i+1,j)){
						added = true;
						cells.add(new LogicCell(i + 1, j));
					}
					if(userMatrix.get(i-1,j+1)){
						added = true;
						cells.add(new LogicCell(i - 1, j+1));
					}
					if(added){
						cells.add(new LogicCell(i,j));
					}
				}
				if(userMatrix.get(j,i)){
					cellsInaColumn.add(new LogicCell(j,i));
				}
			}
			if (cellsInaRow.size() > numberOfStars){
				cells.addAll(cellsInaRow);
			}
			if (cellsInaColumn.size() > numberOfStars){
				cells.addAll(cellsInaColumn);
			}
		}

		return cells;
	}
}
