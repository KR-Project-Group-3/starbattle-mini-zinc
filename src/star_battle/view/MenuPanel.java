package star_battle.view;

import star_battle.controller.Controller;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
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


        this.setPreferredSize(new Dimension(w, h));
        MenuButtonsPanel buttonsPanel = new MenuButtonsPanel(2, 5, controller);
        buttonsPanel.setPreferredSize(new Dimension(w/2, w/2));

        JPanel titlePanel = new JPanel();
        titlePanel.setOpaque(false);

        JSplitPane mainPanel = new JSplitPane(JSplitPane.VERTICAL_SPLIT, buttonsPanel, titlePanel);
        mainPanel.setOpaque(false);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));
        mainPanel.setDividerLocation(w/2 + 50);
        mainPanel.setDividerSize(0);

        this.add(mainPanel);

       /* MatrixPanel matrixPanel = new MatrixPanel(2, controller);
        matrixPanel.setPreferredSize(new Dimension(w/2, w/2));

        this.buttonsPanel = new JPanel();
        buttonsPanel.setOpaque(false);
        buttonsPanel.add(new Button());

        this.mainPanel = new JSplitPane(JSplitPane.VERTICAL_SPLIT, matrixPanel, buttonsPanel);
        this.mainPanel.setOpaque(false);
        this.mainPanel.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));
        this.mainPanel.setDividerLocation(width/2 + 50);
        this.mainPanel.setDividerSize(0);

        this.add(mainPanel);*/


        setFocusable(true);
        setVisible(true);


    }



    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(bgImage, 0, 0, w, h,null);

    }


}
