package spacerinvaders;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Blocks extends JLabel {
    public Blocks(int xBlock, int yBlock){
        setIcon(new ImageIcon(FrameMain.class.getResource("images/bloque3.png")));
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
        if(hP>0){
            setIcon(new ImageIcon(FrameMain.class.getResource("images/bloque"+hP+".png")));
        }
    }
    
    private int hP=3;
    
}

