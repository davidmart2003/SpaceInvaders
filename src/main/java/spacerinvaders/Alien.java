package spacerinvaders;


import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Alien extends JLabel {
    public Alien(int x, int y) {
        setIcon(new ImageIcon(FrameMain.class.getResource("images/alien.png")));
        setSize(this.getPreferredSize());
        setLocation(x, y);


    }

    public Bullet shoot(){
        return new Bullet(getLocation().x+25, this.getLocation().y);
    }
    SClip AlienDeadSound = new SClip(FrameMain.class.getResource("music/invaderkilled.wav"));
    
}
