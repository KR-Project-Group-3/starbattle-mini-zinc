package star_battle.view;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MinizincButton extends JButton implements ActionListener {

    public MinizincButton() {
        super();
        setText("Done!");
        this.addActionListener(this);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        //TODO invocare minizinc
    	
    	System.out.println("ciao");
    }
}
