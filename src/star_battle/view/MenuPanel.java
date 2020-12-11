package star_battle.view;

import star_battle.controller.Controller;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.GroupLayout.Alignment;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;

public class MenuPanel extends JPanel {

    private int w, h;
    private Controller controller;

    public MenuPanel(Controller controller, int w, int h) {

        this.controller = controller;
        this.w = w;
        this.h = h;

        this.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                        System.exit(0);
                }
            }
        });
        
        this.setLayout(new BorderLayout());
        this.setSize(new Dimension(w, h));

        JPanel titlePanel = createTitle();
       
        JSplitPane lowMenuSplit = configureLowMenu();
        
        
        JSplitPane mainPanel = new JSplitPane(JSplitPane.VERTICAL_SPLIT, titlePanel, lowMenuSplit);
        
        mainPanel.setOpaque(false);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));
        mainPanel.setDividerLocation(Frame.HEIGHT/3);
        mainPanel.setDividerSize(0);

        this.add(mainPanel);

        setFocusable(true);
        setVisible(true);
    }

    public JPanel createTitle() {
    	
    	JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new BorderLayout());

        Image newLogo = Frame.logo.getScaledInstance(this.w/2, this.h/6, Image.SCALE_SMOOTH);
        titlePanel.add(new JLabel(new ImageIcon(newLogo)));
        titlePanel.setOpaque(false);
        
        return titlePanel;
    }
    
    public JSplitPane configureLowMenu() {

        JSplitPane splitButtons = createButtonsPanel();
        JSplitPane splitRandomPlay = createRandomPlayPanel();
        
        JSplitPane lowSplitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, splitButtons, splitRandomPlay);
        lowSplitPane.setOpaque(false);
        lowSplitPane.setDividerLocation(this.w/2 - 50);
        lowSplitPane.setDividerSize(0);
        lowSplitPane.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));
        
        return lowSplitPane;
    }
    
    public JSplitPane createButtonsPanel() {
    	
    	MenuButtonsPanel buttonsPanel = new MenuButtonsPanel(2, 5, controller);
        buttonsPanel.setPreferredSize(new Dimension(w/2, w/2));
        
        JLabel levelLabel = new JLabel("Choose your level...", SwingConstants.CENTER);
        levelLabel.setFont(new Font("Serif", Font.BOLD, 20));
        levelLabel.setForeground(Color.WHITE);

        JPanel levelPanel = new JPanel();
        levelPanel.setOpaque(false);
        levelPanel.add(levelLabel);
        
        JSplitPane splitButtons = new JSplitPane(JSplitPane.VERTICAL_SPLIT, levelPanel, buttonsPanel);
        splitButtons.setOpaque(false);
        splitButtons.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));
        splitButtons.setDividerSize(0);
    	
        return splitButtons;
    }
    
    public JSplitPane createRandomPlayPanel() {
    	
    	JLabel randomPlayLabel = new JLabel("...or play randomly!", SwingConstants.CENTER);
    	randomPlayLabel.setFont(new Font("Serif", Font.BOLD, 20));
    	randomPlayLabel.setForeground(Color.WHITE);
    	
    	JButton randomPlayButton = new JButton("Play");
    	randomPlayButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					controller.loadNewInstance();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

        JPanel randomPlayPanel = new JPanel();
        randomPlayPanel.setLayout(new GridBagLayout());
        randomPlayPanel.setOpaque(false);
        randomPlayPanel.add(randomPlayLabel);
        randomPlayPanel.add(randomPlayButton);
    	
    	JSplitPane splitRandomPlay = new JSplitPane(JSplitPane.VERTICAL_SPLIT, randomPlayLabel, randomPlayPanel);
    	splitRandomPlay.setOpaque(false);
    	splitRandomPlay.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));
    	splitRandomPlay.setDividerSize(0);
    	
    	return splitRandomPlay;
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(Frame.bgImage, 0, 0, w, h,null);
        
    }


}
