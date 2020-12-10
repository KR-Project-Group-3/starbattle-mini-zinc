package star_battle.view;

import star_battle.controller.Controller;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.GroupLayout.Alignment;

import java.awt.*;
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

        this.setPreferredSize(new Dimension(w, h));

        MenuButtonsPanel buttonsPanel = new MenuButtonsPanel(2, 5, controller);
        buttonsPanel.setPreferredSize(new Dimension(w/2, w/2));

        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new BorderLayout());

        Image newLogo = Frame.logo.getScaledInstance(this.w/2, this.h/6, Image.SCALE_SMOOTH);
        titlePanel.add(new JLabel(new ImageIcon(newLogo)));
        titlePanel.setOpaque(false);
        
        JLabel levelLabel = new JLabel("Choose your level", SwingConstants.CENTER);
        levelLabel.setFont(new Font("Serif", Font.BOLD, 20));
        levelLabel.setForeground(Color.WHITE);
        titlePanel.add(levelLabel, BorderLayout.PAGE_END);
        
        JPanel levelPanel = new JPanel();
        levelPanel.setOpaque(false);
        levelPanel.add(levelLabel);
        
        JSplitPane splitButtons = new JSplitPane(JSplitPane.VERTICAL_SPLIT, levelPanel, buttonsPanel);
        splitButtons.setOpaque(false);
        splitButtons.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));
        splitButtons.setDividerSize(0);
        
        JSplitPane mainPanel = new JSplitPane(JSplitPane.VERTICAL_SPLIT, titlePanel, splitButtons);
        
        mainPanel.setOpaque(false);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));
        mainPanel.setDividerLocation(Frame.HEIGHT/2);
        mainPanel.setDividerSize(0);

        this.add(mainPanel);

        setFocusable(true);
        setVisible(true);


    }



    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(Frame.bgImage, 0, 0, w, h,null);
        
    }


}
