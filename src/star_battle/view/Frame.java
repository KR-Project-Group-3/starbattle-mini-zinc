package star_battle.view;

import java.awt.*;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

import star_battle.controller.Controller;

public class Frame extends JFrame {
	
	public static final int WIDTH = 1000;
	public static final int HEIGHT = 800;
	
	public static Image bgImage;
    public static Image logo;
	
	private int dim = 5;

	private JPanel mainPanel = null;
	private Controller controller = null;

	public Frame(Controller controller) {

		this.controller = controller;
		
		try {
            this.bgImage = ImageIO.read(new File("images/paper_background.jpg"));
            this.logo = ImageIO.read(new File("images/star-battle-logo.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

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
