//XinCheng Chi
//Period 6

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.JComponent;

public class Background extends JComponent
{
    private final Image image;
    
    public Background(Image image)
    {
        this.image=image;
    }
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        g.drawImage(image,0,0,this);
    }
}