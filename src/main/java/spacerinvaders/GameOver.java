package spacerinvaders;


import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;

public class GameOver extends JDialog {
    public GameOver(FrameMain f){
        super(f,true);
        setLayout(null);
        addMouseListener(new MouseHandler());

        lblGameOver =new JLabel(new ImageIcon("src/main/java/images/game-over.gif"));
        setContentPane(lblGameOver);
        
    }
    JLabel lblGameOver;
}

class MouseHandler extends MouseAdapter{
    @Override
    public void mouseClicked(MouseEvent e){
        System.err.println("estoy aqui");
        System.exit(0);
    }
}
