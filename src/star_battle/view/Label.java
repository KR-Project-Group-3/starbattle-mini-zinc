package star_battle.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class Label extends JLabel {
	
	private boolean filled;

	public Label() {
		
		this.filled = false;
		
		setOpaque(true);
		setBackground(Color.WHITE);
		
		setHorizontalAlignment(CENTER);
		setFont(new Font("Serif", Font.BOLD, 30));
	
		this.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent arg0) {
				
			}
			
			@Override
			public void mousePressed(MouseEvent arg0) {
				
				filled = !filled;
				if(filled) {
					setText("\u2605");
					//add stars
				}
				else
					setText("");
					//remove stars
			}
			
			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
	}

	
}
