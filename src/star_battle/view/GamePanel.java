package star_battle.view;

import star_battle.controller.Controller;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GamePanel extends JPanel{

    private JSplitPane mainPanel = null;

    private JPanel matrixPanel;
    private JPanel buttonsPanel;
    private int dim = 5;
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
        this.setPreferredSize(new Dimension(width, height));

        this.matrixPanel = new MatrixPanel(dim, controller);
        matrixPanel.setPreferredSize(new Dimension(width/2, width/2));

        this.buttonsPanel = new JPanel();
        buttonsPanel.setOpaque(false);
        buttonsPanel.add(new Button());

        this.mainPanel = new JSplitPane(JSplitPane.VERTICAL_SPLIT, matrixPanel, buttonsPanel);
        this.mainPanel.setOpaque(false);
        this.mainPanel.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));
        this.mainPanel.setDividerLocation(width/2 + 50);
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
}
