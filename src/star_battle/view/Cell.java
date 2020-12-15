package star_battle.view;

import javax.swing.*;
import javax.swing.border.Border;

import star_battle.controller.Controller;
import star_battle.model.LogicCell;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Cell extends JLabel implements MouseListener {

    private boolean filled = false;
    private int size;
    private LogicCell logicCell = null;
    
    public Cell(int size, int i, int j, Color color){

        this.size = size;
        this.logicCell = new LogicCell(i, j);
        setBackground(color);
        setOpaque(true);
        setHorizontalAlignment(CENTER);
        setFont(new Font("Serif", Font.BOLD, size/2));
        this.addMouseListener(this);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(size,size);
    }

    public boolean isFilled() {
        return filled;
    }

    public void setFilled(boolean filled) {
        this.filled = filled;
    }
    
    public void color(Color color) {
    	setForeground(color);
    }


    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mousePressed(MouseEvent e) {
    	MatrixPanel matrixPanel = (MatrixPanel) this.getParent();
    	if(matrixPanel.isInGame()) {
	        filled = !filled;
	        if(filled) {
	            setText("\u2605");
	            matrixPanel.setStar(logicCell.getI(), logicCell.getJ(), true);
	
	        }
	        else {
	            setText("");
	            matrixPanel.setStar(logicCell.getI(), logicCell.getJ(), false);
	        }
    	}
    }

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}

    public LogicCell getLogicCell() {
        return logicCell;
    }

    public void setLogicCell(LogicCell logicCell) {
        this.logicCell = logicCell;
    }

    public LogicCell getLogicCellModified(){
        return new LogicCell(logicCell.getI()+1, logicCell.getJ()+1);
    }


}
