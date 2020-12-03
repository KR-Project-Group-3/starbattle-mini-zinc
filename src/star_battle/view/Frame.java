package star_battle.view;

import java.awt.*;
import javax.swing.*;

import star_battle.controller.Controller;

public class Frame extends JFrame {
	
	private static final int WIDTH = 800;
	private static final int HEIGHT = 600;
	
	private int dim = 5;

	private JPanel mainPanel = null;

	public Frame(Controller controller) {

		this.dim = controller.getDimension();

		this.setSize(WIDTH, HEIGHT);
		this.setLayout(new BorderLayout());
		//this.mainPanel = new GamePanel(controller, WIDTH, HEIGHT);
		this.mainPanel = new MenuPanel(controller, WIDTH, HEIGHT);
		this.add(mainPanel, BorderLayout.CENTER);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void setAcutalPane(JPanel p) {
		if(mainPanel != null) {
			this.remove(mainPanel);
		}
		mainPanel = p;
		this.add(mainPanel);
		mainPanel.requestFocus();
		revalidate();
	}
}
