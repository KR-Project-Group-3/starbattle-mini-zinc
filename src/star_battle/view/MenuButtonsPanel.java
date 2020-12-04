package star_battle.view;

import star_battle.controller.Controller;

import javax.swing.*;
import java.awt.*;

public class MenuButtonsPanel extends JPanel {

    private Controller controller = null;

    public MenuButtonsPanel(int rows, int columns, Controller controller) {
            super();
            this.controller = controller;
            setLayout(new GridBagLayout());
            setOpaque(false);
            generateButtons(rows, columns);
    }

    public void generateButtons(int rows, int columns) {
        GridBagConstraints gbc = new GridBagConstraints();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                gbc.gridx = j;
                gbc.gridy = i;
                LevelButton l = new LevelButton(i*5+j+1);
                l.setText(""+(i*5+j+1));
                add(l, gbc);
            }
        }

    }

    public void createLevel(int level){
        Frame f = (Frame)this.getTopLevelAncestor();
        f.createGame(level);
    }
}