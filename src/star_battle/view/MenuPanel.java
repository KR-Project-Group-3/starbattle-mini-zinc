package star_battle.view;

import star_battle.controller.Controller;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;

public class MenuPanel extends JPanel {

    private int w, h;
    private Controller controller;
    private Image bgImage = null;

    public MenuPanel(Controller controller, int w, int h) {

        this.controller = controller;
        this.w = w;
        this.h = h;
        try {
            this.bgImage = ImageIO.read(new File("images/paper_background.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }

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
        titlePanel.setOpaque(false);

        JSplitPane mainPanel = new JSplitPane(JSplitPane.VERTICAL_SPLIT, titlePanel, buttonsPanel);
        mainPanel.setOpaque(false);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));
        mainPanel.setDividerLocation(250);
        mainPanel.setDividerSize(0);

        this.add(mainPanel);

        setFocusable(true);
        setVisible(true);


    }



    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(bgImage, 0, 0, w, h,null);

    }


}
