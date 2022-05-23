package spacerinvaders;




import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Bullet extends JLabel {
    public Bullet(int x, int y) {
        setIcon(new ImageIcon(FrameMain.class.getResource("images/bullet.png")));
        setSize(this.getPreferredSize());
        setLocation(x, y);
        shootSound.volume(-35.0f);
        shootSound.play();
    }

    SClip shootSound = new SClip(FrameMain.class.getResource("music/shoot.wav"));

}
