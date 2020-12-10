package star_battle.controller;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

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
	
	private Set<LogicCell> stars;
	private Set<LogicCell> violatedCells; 
	private Set<LogicCell> rowCells;
	private Set<LogicCell> columnCells;
	private Set<LogicCell> sectorCells;
	
	private int numStars;
	
	public Controller() { 
		stars = new HashSet<>();
		violatedCells = new HashSet<>();
		rowCells = new HashSet<>();
		columnCells = new HashSet<>();
		sectorCells = new HashSet<>();
	}
	
	public void loadNewInstance(int level){

		matrix = new InstanceMatrix(level - 1);
		userMatrix = new UserMatrix(matrix.getDimension(), matrix.getStarsNumber());
		solutionMatrix = new SolutionMatrix(matrix);
		
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
		userMatrix.setStar(i, j, setOrDelete);
	}

	public HashSet<LogicCell> checkConstraints() {
		
		numStars = getStarsNumber();
		
		stars.clear();
		violatedCells.clear();
		
		for(int i = 1; i < userMatrix.getDimension()-1; i++){
			for(int j = 1; j < userMatrix.getDimension()-1; j++){
				if(userMatrix.get(i, j))
					stars.add(new LogicCell(i, j));
			}
		}
		
		boolean violation = false;
		
		//check adjacents constraints
		for (LogicCell s : stars) {
			for (LogicCell t : stars) {
				if(s.getI() == t.getI()) {
					if(s.getJ() == t.getJ()-1 || s.getJ() == t.getJ()+1) //horizontally adjacents
						violation = true;
				}
				
				else if(s.getJ() == t.getJ()) {
					if(s.getI() == t.getI()-1 || s.getI() == t.getI()+1) //vertically adjacents
						violation = true;
				}
				
				else if(s.getI() == t.getI()+1 || s.getI() == t.getI()-1) { 
					if(s.getJ() == t.getJ()-1 || s.getJ() == t.getJ()+1) //diagonally adjacents 
						violation = true;
				}

				if(violation) {
					violation = false;
					violatedCells.add(s);
					violatedCells.add(t);
				}
			}
		}
		
		//check rows, columns and sectors constraints
		for (LogicCell s : stars) {
			for (LogicCell t : stars) {
				if(s.getI() == t.getI() && s.getJ() != t.getJ()) {
					rowCells.add(s);
					rowCells.add(t);
				}
				
				if(s.getJ() == t.getJ() && s.getI() != t.getI()) {
					columnCells.add(s);
					columnCells.add(t);
				}
				
				if((s.getI() != t.getI() || s.getJ() != t.getJ()) && matrix.getSector(s.getI()-1, s.getJ()-1) == matrix.getSector(t.getI()-1, t.getJ()-1)) {
					sectorCells.add(s);
					sectorCells.add(t);
				}
			}

			if(rowCells.size() > numStars)
				violatedCells.addAll(rowCells);
			
			if(columnCells.size() > numStars) 
				violatedCells.addAll(columnCells);
			
			if(sectorCells.size() > numStars) 
				violatedCells.addAll(sectorCells);
			
			rowCells.clear();
			columnCells.clear();
			sectorCells.clear();
		}
		
		return (HashSet<LogicCell>) violatedCells;
	}
	
	public boolean hasUserWon() {

		if(!this.solutionMatrix.isMatrixInstantiated()) {
			try {
				solutionMatrix.parseMatrix();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		for(int i = 0; i < matrix.getDimension(); i++) {
			for(int j = 0; j < matrix.getDimension(); j++) {
				if(userMatrix.get(i + 1, j + 1) != solutionMatrix.get(i, j))
					return false;
			}
		}
		return true;
	}
}
