package star_battle.view;

import star_battle.controller.Controller;

import javax.swing.*;
import java.awt.*;

public class MatrixPanel extends JPanel {

    private int rows;
    private Controller controller = null;

    public MatrixPanel(int rows, Controller controller) {

        super();
        this.rows = rows;
        this.controller = controller;
        setLayout(new GridBagLayout());
        setOpaque(false);
        generateMatrix(rows);
    }

    public void generateMatrix(int rows) {

        GridBagConstraints gbc = new GridBagConstraints();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < rows; j++) {
                gbc.gridx = j;
                gbc.gridy = i;
                Cell l = new Cell();
                this.manageLabel(i, j, l, gbc);
            }
        }

    }

    public void manageLabel(int i, int j, Cell l, GridBagConstraints gbc) {
        int thickBorder = 5;

        l.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        boolean differentBottom = false, differentRight = false;

        if(i != rows - 1)
            if(controller.differentSectorOfBottomCell(i, j)) {
                l.setBorder(BorderFactory.createMatteBorder(1, 1, thickBorder, 1, Color.BLACK));
                differentBottom = true;
            }

        if(j != rows - 1)
            if(controller.differentSectorOfRightCell(i, j)) {
                l.setBorder(BorderFactory.createMatteBorder(1, 1, 1, thickBorder, Color.BLACK));
                differentRight = true;
            }

        if(differentBottom && differentRight)
            l.setBorder(BorderFactory.createMatteBorder(1, 1, thickBorder, thickBorder, Color.BLACK));

        add(l, gbc);
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }
}
