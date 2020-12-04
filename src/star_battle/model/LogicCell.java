package star_battle.model;

public class LogicCell {

	private int i;
	private int j;
	
	public LogicCell(int row, int column) {
		this.i = row;
		this.j = column;
	}

	public int getI() {
		return i;
	}

	public void setI(int i) {
		this.i = i;
	}

	public int getJ() {
		return j;
	}

	public void setJ(int j) {
		this.j = j;
	}
	
}
