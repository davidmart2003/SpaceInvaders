package spacerinvaders;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

// String fuentes[] = ge.getAvailableFontFamilyNames();//Creamos un vector con todas las fuentes disponibles, incluso la que registramos.
// //Haz un for para imprimir todas las fuentes disponibles y asegurarte de que se encuentre la fuente que regustramos, de ser asi esta lista para ser usada!
// for(int i=0; i<fuentes.length; i++){
//     System.err.println(fuentes[i]);
// }

public class SpaceInvader extends JFrame {
    public SpaceInvader() {
        super("Space Invaders Menu");
        setLayout(null);
        registerFont("src/main/java/Fonts/ARCO for OSX.otf");

        lblBackgroundImage = new JLabel(new ImageIcon("src/main/java/images/galaxy.png"));
        lblBackgroundImage.setSize(lblBackgroundImage.getPreferredSize());
        lblBackgroundImage.setLocation(0, 0);
        setContentPane(lblBackgroundImage);

        lblTittle = new JLabel("Space");
        lblTittle.setSize(200, 50);
        lblTittle.setLocation(200, 40);
        lblTittle.setForeground(Color.white);
        lblTittle.setFont(new Font("ARCO", Font.ITALIC, 46));
        add(lblTittle);

        lblTittle2 = new JLabel("Invader");
        lblTittle2.setSize(250, 50);
        lblTittle2.setLocation(170, 90);
        lblTittle2.setForeground(Color.green);
        lblTittle2.setFont(new Font("ARCO", Font.ITALIC, 46));
        add(lblTittle2);

        lblPlay = new JLabel("Play Game");
        lblPlay.setSize(200, 50);
        lblPlay.setLocation(200, 300);
        lblPlay.setFont(new Font("ARCO", Font.ITALIC, 24));
        lblPlay.setForeground(Color.white);
        lblPlay.addMouseListener(new MouseHandler());
        add(lblPlay);

        lblHighScore = new JLabel("HighScore");
        lblHighScore.setSize(200, 50);
        lblHighScore.setLocation(200, 350);
        lblHighScore.setFont(new Font("ARCO", Font.ITALIC, 24));
        lblHighScore.setForeground(Color.white);
        add(lblHighScore);

        lblExit = new JLabel("Exit Game");
        lblExit.setSize(200, 50);
        lblExit.setLocation(200, 400);
        lblExit.setFont(new Font("ARCO", Font.ITALIC, 24));
        lblExit.setForeground(Color.white);
        lblExit.addMouseListener(new MouseHandler());
        add(lblExit);

        setSize(600, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setUndecorated(true);
        setVisible(true);
    }

    public static void registerFont(String path) {
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        try {
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File(path)));
        } catch (FontFormatException e) {

            e.printStackTrace();
        } catch (IOException e) {

            e.printStackTrace();
        }
    }

    class MouseHandler extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent e) {
            if (e.getSource() == lblPlay) {
                FrameMain frame = new FrameMain();
                frame.setSize(600, 600);
                frame.setLocationRelativeTo(null);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setUndecorated(true);
                frame.setVisible(true);
                dispose();
            }

            if (e.getSource() == lblExit) {
                System.exit(0);
            }
        }
    }

    JLabel lblPlay, lblHighScore, lblExit;
    JLabel lblTittle, lblTittle2, lblBackgroundImage;
}
