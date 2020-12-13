package star_battle.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;

import star_battle.controller.Controller;

public class GamePanel extends JPanel implements KeyListener {

    private JSplitPane mainPanel = null;

    private JPanel matrixPanel;
    private JPanel buttonsPanel;
    
    private int w, h;

    public GamePanel(Controller controller, int width, int height){

        w = width;
        h = height;
        
        this.addKeyListener(this);
        
        
        this.matrixPanel = new MatrixPanel(controller);
        this.setLayout(new BorderLayout());
        
        
        matrixPanel.setPreferredSize(new Dimension(width, width));

        this.buttonsPanel = new JPanel();
        buttonsPanel.setOpaque(false);
       
        JLabel stars = new JLabel("\u2605 " + controller.getStarsNumber());
        stars.setFont(new Font("Serif", Font.PLAIN, 20));
        buttonsPanel.add(stars);

		JLabel result = new JLabel();
        result.setFont(new Font("Serif", Font.PLAIN, 20));
        
        JLabel hintLabel = new JLabel();
        hintLabel.setFont(new Font("Serif", Font.PLAIN, 20));
        
        JButton goBack = new JButton("Go back");
        goBack.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				goBack();				
			}
		});
        
        JButton hint = new JButton("Hint?");
        hint.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(controller.deservesHint())
					((MatrixPanel) matrixPanel).givesHint();
				else
					hintLabel.setText("Too many hints!");
			}
		});
        
        JButton done = new JButton("Done");
        done.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(controller.hasUserWon()) {
					result.setText("Good j\u2605b!");
					result.setForeground(Color.WHITE);
					((MatrixPanel) matrixPanel).noMoreInGame();
				}
				
				else {
					result.setText("Keep trying!");
					result.setForeground(Color.BLACK);
				}
			}
		});
        
        buttonsPanel.add(goBack);
        buttonsPanel.add(hint);
        buttonsPanel.add(hintLabel);
        
        buttonsPanel.add(done);
        buttonsPanel.add(result);
        
        JPanel logoPanel = new JPanel();
        logoPanel.setOpaque(false);
        logoPanel.add(new JLabel(new ImageIcon(Frame.logo)));
        
        JSplitPane splitMatrix = new JSplitPane(JSplitPane.VERTICAL_SPLIT, logoPanel, matrixPanel);
        splitMatrix.setOpaque(false);
        splitMatrix.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        splitMatrix.setDividerSize(0);
        
        this.mainPanel = new JSplitPane(JSplitPane.VERTICAL_SPLIT, splitMatrix, buttonsPanel);
        this.mainPanel.setOpaque(false);
        this.mainPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        this.mainPanel.setDividerLocation(width/2 + 100);
        this.mainPanel.setDividerSize(0);

        this.add(mainPanel);
        setFocusable(true);
        setVisible(true);
    }
    
    public void addButtons() {
    	
    	
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); 
        g.drawImage(Frame.bgImage, 0, 0, w, h, null);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            goBack();
        }
    }
    
    public void goBack() {
    	Frame f = (Frame) this.getTopLevelAncestor();
        f.createMenu();
    }
}
