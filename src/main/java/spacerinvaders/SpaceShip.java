package spacerinvaders;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class SpaceShip extends JLabel {
    public SpaceShip(){
        super();
        setIcon(new ImageIcon(FrameMain.class.getResource("images/nave.png")));
        setSize(this.getPreferredSize());
        setLocation(250, 450);
    }
    public void setHp(int hp){
        this.hp=hp;
    }

    public int gethp(){
        return hp;
    }
    public void hit(){
        hp--;
    }
    public Bullet shoot(){
        return new Bullet(getLocation().x+25, 457);
    }

    int hp=3;
    
}
