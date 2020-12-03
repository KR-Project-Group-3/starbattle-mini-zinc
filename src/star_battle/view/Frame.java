package star_battle.view;

import java.awt.*;
import javax.swing.*;

import star_battle.controller.Controller;

public class Frame extends JFrame {
	
	private static final int WIDTH = 800;
	private static final int HEIGHT = 600;
	
	private int dim = 5;

	private JSplitPane mainPanel = null;
	
	private JPanel matrixPanel;
	private JPanel buttonsPanel;

	public Frame(Controller controller) {

		this.dim = controller.getDimension();

		this.setLocation(WIDTH-this.getSize().width/2, HEIGHT/2-this.getSize().height/2);
		this.setSize(WIDTH, HEIGHT);

		this.matrixPanel = new MatrixPanel(dim, controller);
		matrixPanel.setPreferredSize(new Dimension(WIDTH/2, WIDTH/2));

		this.buttonsPanel = new JPanel();
		buttonsPanel.setOpaque(false);
        buttonsPanel.add(new Button());

		this.mainPanel = new JSplitPane(JSplitPane.VERTICAL_SPLIT, matrixPanel, buttonsPanel);
		this.mainPanel.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));
		this.mainPanel.setDividerLocation(WIDTH/2 + 50);
		this.mainPanel.setDividerSize(0);
		this.mainPanel.setBackground(Color.CYAN);

		this.add(mainPanel);

		this.setVisible(true);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
