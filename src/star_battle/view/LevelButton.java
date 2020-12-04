package star_battle.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class LevelButton extends JLabel implements MouseListener {

    private int level;

    public LevelButton(int level){
        this.level =  level;
        setBackground(Color.WHITE);
        setOpaque(true);

        setHorizontalAlignment(CENTER);
        setFont(new Font("Serif", Font.BOLD, 30));
        setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, Color.BLACK));
        this.addMouseListener(this);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(50,50);
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        MenuButtonsPanel p = (MenuButtonsPanel) this.getParent();
        p.createLevel(this.level);
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
