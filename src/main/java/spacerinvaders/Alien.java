package spacerinvaders;


import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Alien extends JLabel {
    public Alien(int x, int y) {
        setIcon(new ImageIcon("src/main/java/images/alien.png"));
        setSize(this.getPreferredSize());
        setLocation(x, y);


    }

    public Bullet shoot(){
        return new Bullet(getLocation().x+25, this.getLocation().y);
    }
    SClip AlienDeadSound = new SClip("src/main/java/music/invaderkilled.wav");
    
}
