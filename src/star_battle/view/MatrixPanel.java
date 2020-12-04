package star_battle.view;

import star_battle.controller.Controller;
import star_battle.model.LogicCell;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class MatrixPanel extends JPanel {

    private int rows;
    private ArrayList<Cell> cells;
    
    private Controller controller = null;

    public MatrixPanel(Controller controller) {

        super();
        this.controller = controller;
        this.rows = controller.getDimension();
        cells = new ArrayList<Cell>();
        setLayout(new GridBagLayout());
        setOpaque(false);
        generateMatrix(rows);
    }

    public void generateMatrix(int rows) {

        int cellsize = 50;
        if (controller.getDimension() > 8)
        	cellsize = 45;
        if (controller.getDimension() > 10)
        	cellsize = 35;
        if (controller.getDimension() > 15)
        	cellsize = 29;


        GridBagConstraints gbc = new GridBagConstraints();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < rows; j++) {
                gbc.gridx = j;
                gbc.gridy = i;
                Cell l = new Cell(cellsize, i, j);
                cells.add(l);
                this.manageLabel(i, j, l, gbc);
            }
        }
    }

    public void manageLabel(int i, int j, Cell l, GridBagConstraints gbc) {
        int thickBorder = 5;

        l.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        boolean differentBottom = false, differentRight = false;

        if(i != rows - 1)
            if(controller.differentSectorOfBottomCell(i, j)) {
                l.setBorder(BorderFactory.createMatteBorder(1, 1, thickBorder, 1, Color.BLACK));
                differentBottom = true;
            }

        if(j != rows - 1)
            if(controller.differentSectorOfRightCell(i, j)) {
                l.setBorder(BorderFactory.createMatteBorder(1, 1, 1, thickBorder, Color.BLACK));
                differentRight = true;
            }

        if(differentBottom && differentRight)
            l.setBorder(BorderFactory.createMatteBorder(1, 1, thickBorder, thickBorder, Color.BLACK));

        add(l, gbc);
    }
    
    public void colorCells(ArrayList<LogicCell> violatedCell) {

        for(Cell c: cells){
            if(violatedCell.contains(c.getLogicCellModified())){
                c.color(Color.red);
            }
            else{
                c.color(Color.black);
            }
        }
    }

    public void setStar(int i, int j, boolean setOrDelete){
        controller.setStar(i,j, setOrDelete);
        colorCells(controller.checkConstraints());
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }
    
    public Controller getController() {
    	return controller;
    }
}
