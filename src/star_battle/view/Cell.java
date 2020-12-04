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
    
    
    public Cell(int size){

        this.size = size;
        setBackground(Color.WHITE);
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
        filled = !filled;
        if(filled) {
            setText("\u2605");
            matrixPanel.colorCells(matrixPanel.getController().checkConstraints());
        }
        else {
            setText("");
            matrixPanel.colorCells(matrixPanel.getController().checkConstraints());
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}

}
