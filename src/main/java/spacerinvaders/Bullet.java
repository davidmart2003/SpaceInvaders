package spacerinvaders;




import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Bullet extends JLabel {
    public Bullet(int x, int y) {
        setIcon(new ImageIcon("src/main/java/images/bullet.png"));
        setSize(this.getPreferredSize());
        setLocation(x, y);
        shootSound.volume(-35.0f);
        shootSound.play();
    }

    SClip shootSound = new SClip("src/main/java/music/shoot.wav");

}
