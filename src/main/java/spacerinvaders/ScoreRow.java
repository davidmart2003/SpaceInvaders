package spacerinvaders;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;


public class ScoreRow extends JLabel {

    public ScoreRow(String name, int score) {
        
        
        setUserName(name);
        setScore(score);
        setForeground(Color.white);
        setSize(300,40);
        setFont(new Font("PixelGameFont", Font.ITALIC, 32));
        setText(String.format("%10s%8s",this.userName,score));
        
    }

    public ScoreRow(String[] split) {
        setUserName(split[0]);
        setScore(Integer.parseInt(split[1]));
        setForeground(Color.white);
        setSize(300,40);
        setFont(new Font("PixelGameFont", Font.ITALIC, 32));
        setText(String.format("%10s%8s",this.userName,score));
    }

    public void setUserName(String userName) {
        this.userName = userName.toUpperCase();
       
    }

    public String getUserName() {
        return userName;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getScore() {
        return score;
    }

    @Override
    public String toString() {
        return getUserName()+";"+score;
    }

    private String userName;
    private int score;

}
