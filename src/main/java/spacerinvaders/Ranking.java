package spacerinvaders;

import java.awt.Color;
import java.awt.Font;
import java.io.File;
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class Ranking extends JFrame {
    public Ranking() {
        SpaceInvader.registerFont(FrameMain.class.getResource("Fonts/PixelGameFont.ttf"));

        lblBackgroundImage = new JLabel(new ImageIcon(FrameMain.class.getResource("images/galaxy.png")));
        lblBackgroundImage.setSize(lblBackgroundImage.getPreferredSize());
        lblBackgroundImage.setLocation(0, 0);
        setContentPane(lblBackgroundImage);

        Ranking = new JLabel();
        Ranking.setFont(new Font("PixelGameFont", Font.ITALIC, 32));
        Ranking.setText("<html><p>RANKING <br>&nbsp;&nbsp&nbsp;1.<br>&nbsp;&nbsp;&nbsp;2.<br>&nbsp;&nbsp;&nbsp;3.<br>&nbsp;&nbsp;&nbsp;4.<br>&nbsp;&nbsp;&nbsp;5.<br>&nbsp;&nbsp;&nbsp;6.<br>&nbsp;&nbsp;&nbsp;7.<br>&nbsp;&nbsp;&nbsp;8.<br>&nbsp;&nbsp;&nbsp;9.<br>&nbsp;&nbsp;&nbsp;10.</p></html>");
        Ranking.setSize(Ranking.getPreferredSize());
        Ranking.setForeground(Color.white);
        Ranking.setLocation(40, 10);
        add(Ranking);

        main = new JLabel();
        main.setFont(new Font("PixelGameFont", Font.ITALIC, 32));
        main.setText("<html>NAMES&nbsp;&nbsp;&nbsp;SCORE</html>");
        main.setSize(main.getPreferredSize());
        main.setForeground(Color.white);
        main.setLocation(250, 10);
        add(main);


        try (Scanner f = new Scanner(new File(System.getProperty("user.home")
        + "/AppData/Roaming/spaceinvaders/ranking.txt"))) {
            while(cont<10){
                namesAndScores=f.nextLine().split(";");
                name[cont]=namesAndScores[0];
                score[cont]=Integer.parseInt(namesAndScores[1]);
                cont++;
            }
        } catch (Exception e) {
            
        }
        
        int x=200 ,y=60;
        for (int i = 0; i < name.length; i++) {
            if(name[i]!=null ){

                ScoreRow scoreRow = new ScoreRow(name[i], score[i]);
                
                scoreRow.setLocation(x,y);
                add(scoreRow);
            }

            y+=40;
        }


        setSize(600, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setUndecorated(true);
        setVisible(true);
    }

    JLabel Ranking, names, main;
    JLabel lblBackgroundImage;
    String[] namesAndScores;
    String[] name= new String[10];
    int[] score = new int[10];
    int cont=0;
}
