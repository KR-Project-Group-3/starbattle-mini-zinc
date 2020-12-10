package star_battle.model.embasp;

import it.unical.mat.embasp.languages.Id;
import it.unical.mat.embasp.languages.Param;

@Id("inMatrix")
public class MatrixSectorMapping {

    @Param(0)
    private int row;
    @Param(1)
    private int column;
    @Param(2)
    private int sector;

    public MatrixSectorMapping(int row, int column, int sector) {
        this.row = row;
        this.column = column;
        this.sector = sector;
    }

    public MatrixSectorMapping() {
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public int getSector() {
        return sector;
    }

    public void setSector(int sector) {
        this.sector = sector;
    }

    @Override
    public String toString() {
        return "MatrixSectorMapping{" +
                "row=" + row +
                ", column=" + column +
                ", sector=" + sector +
                '}';
    }
}