package spacerinvaders;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Blocks extends JLabel {
    public Blocks(int xBlock, int yBlock){
        setIcon(new ImageIcon("src/main/java/images/bloque3.png"));
        setSize(this.getPreferredSize());
        setLocation(xBlock, yBlock);
        
    }

    public void setHP(int hP){
        this.hP=hP;
    }
    public int getHP(){
        return hP;
    }

    public void hit(){
        hP--;
        setIcon(new ImageIcon("src/main/java/images/bloque"+hP+".png"));
    }
    
    private int hP=3;
    
}

