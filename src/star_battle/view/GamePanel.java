package star_battle.view;

import star_battle.controller.Controller;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GamePanel extends JPanel implements KeyListener {

    private JSplitPane mainPanel = null;

    private JPanel matrixPanel;
    private JPanel buttonsPanel;
    private Image bgImage = null;
    private int w, h;

    public GamePanel(Controller controller, int width, int height){

        try {
            this.bgImage = ImageIO.read(new File("images/paper_background.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        w = width;
        h = height;
        //this.setPreferredSize(new Dimension(width, height));

        this.addKeyListener(this);

        this.matrixPanel = new MatrixPanel(controller);
        matrixPanel.setPreferredSize(new Dimension(width, width));

        this.buttonsPanel = new JPanel();
        buttonsPanel.setOpaque(false);
        buttonsPanel.add(new Button());

        this.mainPanel = new JSplitPane(JSplitPane.VERTICAL_SPLIT, matrixPanel, buttonsPanel);
        this.mainPanel.setOpaque(false);
        this.mainPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        this.mainPanel.setDividerLocation(width/2 + 100);
        this.mainPanel.setDividerSize(0);

        this.add(mainPanel);
        setFocusable(true);
        setVisible(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(bgImage, 0, 0, w, h,null);

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
            Frame f = (Frame) this.getTopLevelAncestor();
            f.createMenu();
        }
    }
}
