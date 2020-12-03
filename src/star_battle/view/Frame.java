package star_battle.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JSplitPane;

public class Frame extends JFrame {
	
	private static int WIDTH = 500;
	private static int HEIGHT = 500;
	
	private Panel logoPanel;
	private Panel matrixPanel;
	private Panel buttonsPanel;
	
	public Frame() {
		
		logoPanel = new Panel();
		matrixPanel = new Panel();
		buttonsPanel = new Panel();
		
		matrixPanel.setBackground(Color.white);
		
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

}
