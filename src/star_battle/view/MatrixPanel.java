package star_battle.view;

import star_battle.controller.Controller;
import star_battle.model.LogicCell;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashSet;

public class MatrixPanel extends JPanel {

    private int rows;
    private ArrayList<Cell> cells;
    
    private boolean inGame;
    
    private Controller controller = null;

    private final ArrayList<Color> colors = new ArrayList<>();


    public MatrixPanel(Controller controller) {

        super();
        colors.add(new Color(255,102,102));
        colors.add(new Color(255,255,255));
        colors.add(new Color(51,204, 255));
        colors.add(new Color(102,255,102));
        colors.add(new Color(255,153, 1));
        colors.add(new Color(103,2, 1));
        colors.add(Color.PINK);
        colors.add(Color.YELLOW);
        colors.add(Color.MAGENTA);
        colors.add(new Color(0,102,0));
        colors.add(new Color(204, 0, 0));
        colors.add(Color.BLUE);
        colors.add(new Color(127, 255, 212));
        colors.add(new Color(102, 0, 153));
        colors.add(new Color(153, 102, 0));
        colors.add(new Color(95,158, 160));
        colors.add(Color.gray);


        this.controller = controller;
        this.rows = controller.getDimension();
        inGame = true;
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
        	cellsize = 30;


        GridBagConstraints gbc = new GridBagConstraints();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < rows; j++) {
                gbc.gridx = j;
                gbc.gridy = i;
                int colorCode = controller.getMatrix().getSector(i,j) - 1;
                Cell l = new Cell(cellsize, i, j, colors.get(colorCode));
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
    
    public void colorCells(HashSet<LogicCell> violatedCell) {

        for(Cell c: cells){
            if(violatedCell.contains(c.getLogicCellModified())){
                c.color(Color.RED);
            }
            else{
                c.color(Color.BLACK);
            }
        }
    	
    }
    
    public void givesHint() {
    	LogicCell logicCell = controller.hint();
    	if(logicCell != null) {
    		for(Cell c : cells) {
    			if(c.getLogicCell().equals(logicCell)) {
    				c.setText("\u2605");
    				c.color(Color.BLACK);
    				c.setFilled(true);
    				controller.setStar(logicCell.getI(), logicCell.getJ(), true);
    				colorCells(controller.checkConstraints());
    				return;
    			}
    		}
    	}
    }

    public void setStar(int i, int j, boolean setOrDelete){
        controller.setStar(i, j, setOrDelete);
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
    
    public boolean isInGame() {
    	return this.inGame;
    }
    
    public void noMoreInGame() {
    	this.inGame = false;
    }
}
