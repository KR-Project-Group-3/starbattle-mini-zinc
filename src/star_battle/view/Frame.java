package star_battle.view;

import java.awt.*;
import javax.swing.*;

import star_battle.controller.Controller;

public class Frame extends JFrame {
	
	private static final int WIDTH = 800;
	private static final int HEIGHT = 600;
	
	private int dim = 5;

	private JPanel mainPanel = null;
	private Controller controller = null;

	public Frame(Controller controller) {

		this.controller = controller;

		this.setSize(WIDTH, HEIGHT);
		this.setLayout(new BorderLayout());
		this.createMenu();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void createGame(int level) {

		if(mainPanel != null) {
			this.remove(mainPanel);
		}
		controller.loadNewInstance(level);
		mainPanel = new GamePanel(this.controller, WIDTH, HEIGHT);
		this.add(mainPanel, BorderLayout.CENTER);
		mainPanel.requestFocus();
		revalidate();
	}

	public void createMenu(){
		if(mainPanel != null) {
			this.remove(mainPanel);
		}
		this.mainPanel = new MenuPanel(controller, WIDTH, HEIGHT);
		this.add(mainPanel, BorderLayout.CENTER);
		mainPanel.requestFocus();
		revalidate();
	}
}
