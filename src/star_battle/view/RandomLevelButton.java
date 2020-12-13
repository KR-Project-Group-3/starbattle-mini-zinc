package star_battle.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JLabel;

import star_battle.controller.Controller;

public class RandomLevelButton extends JButton implements MouseListener {

	private Controller controller;
	
	public RandomLevelButton(Controller controller) {
		this.controller = controller;
		
		this.setText("Play!");
		this.addMouseListener(this);
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		try {
			controller.loadNewInstance();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Frame f = (Frame) this.getTopLevelAncestor();
		f.createGame(controller.getDimension());
	}
		

	@Override
	public void mouseEntered(MouseEvent arg0) {}


	@Override
	public void mouseExited(MouseEvent arg0) {}


	@Override
	public void mousePressed(MouseEvent arg0) {}


	@Override
	public void mouseReleased(MouseEvent arg0) {}

}
