package star_battle.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSplitPane;

import star_battle.controller.Controller;

public class Frame extends JFrame {
	
	private static int WIDTH = 1000;
	private static int HEIGHT = 1000;
	
	private int dim = 5;
	
	private Controller controller;
	
	private Panel logoPanel;
	private Panel matrixPanel;
	private Panel buttonsPanel;
	
	public Frame(Controller controller) {
		
		this.controller = controller;
		
		this.logoPanel = new Panel();
		this.matrixPanel = new Panel();
		this.buttonsPanel = new Panel();
		
		matrixPanel.setBackground(Color.white);
		matrixPanel.setLayout(new GridLayout(dim, dim));
        for (int i = 0; i < dim; i++) {
            for (int j = 0; j < dim; j++) {
            	Label l = new Label();
            	this.manageLabel(i, j, l);
            	
            }
        }
		
		JSplitPane internalSplit = new JSplitPane(JSplitPane.VERTICAL_SPLIT, matrixPanel, buttonsPanel);
		JSplitPane mainSplit = new JSplitPane(JSplitPane.VERTICAL_SPLIT,
                logoPanel, internalSplit);
		
		mainSplit.setDividerLocation(HEIGHT/5);
		
		internalSplit.setDividerLocation(HEIGHT/2 + 40);
		mainSplit.setDividerSize(1);
		internalSplit.setDividerSize(1);
		
		this.add(mainSplit);
		this.setSize(WIDTH, HEIGHT);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		
		this.setVisible(true);
		this.setResizable(false);
	}
	
	
	public void manageLabel(int i, int j, Label l) {
		int thickBorder = 5;
		
		l.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		boolean differentBottom = false, differentRight = false;
		
		if(i != dim - 1) 
    		if(controller.differentSectorOfBottomCell(i, j)) {
    			l.setBorder(BorderFactory.createMatteBorder(1, 1, thickBorder, 1, Color.BLACK));
    			differentBottom = true;
    		}
    	
		if(j != dim - 1)
			if(controller.differentSectorOfRightCell(i, j)) {
				l.setBorder(BorderFactory.createMatteBorder(1, 1, 1, thickBorder, Color.BLACK));
				differentRight = true;
			}
    	
    	if(differentBottom && differentRight)
    		l.setBorder(BorderFactory.createMatteBorder(1, 1, thickBorder, thickBorder, Color.BLACK));

        matrixPanel.add(l);
	}

}
