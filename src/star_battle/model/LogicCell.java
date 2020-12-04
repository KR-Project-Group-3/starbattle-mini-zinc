package star_battle.model;

import java.util.Objects;

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

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		LogicCell logicCell = (LogicCell) o;
		return i == logicCell.i && j == logicCell.j;
	}

	@Override
	public int hashCode() {
		return Objects.hash(i, j);
	}
}
