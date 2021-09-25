//XinCheng Chi
//Period 6

import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.*;

public class StartPage extends JComponent implements MouseListener
{
    private final JFrame frame;
    private final BufferedImage background;
    private final JButton button;
    private boolean startGame;
    
    public StartPage()
    { 
        startGame=false;
        frame=new JFrame("Menu");
        background=loadImage("main.jpg");
        
        frame.setContentPane(new Background(background));
        
        button=new JButton();
        button.setLocation(StartPage.WIDTH/2+185,100);
        button.setSize(110, 50);
        
        button.setFont(new Font("arial",Font.BOLD,28));
        button.setText("PLAY");
        
        button.addMouseListener(this);
        
        frame.add(button);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500,376);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        
    }
    private BufferedImage loadImage(String imageFileName)
    {
        URL url = getClass().getResource(imageFileName);
        if (url == null)
            throw new RuntimeException("cannot find file:  " + imageFileName);
            try
            {
              return ImageIO.read(url);
            }
            catch(IOException e)
            {
              throw new RuntimeException("unable to read from file:  " + imageFileName);
            }
    }
    public static void main(String[]args)
    {
        StartPage pg=new StartPage();
        while(!pg.getStartGame())
        {
            System.out.println("waiting to start game");
        }
        Game game=new Game();
        game.play();
    }
    public boolean getStartGame()
    {
        return startGame;
    }
    @Override
    public void mouseClicked(MouseEvent me) 
    {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mousePressed(MouseEvent e) 
    {
        if(e.getSource().equals(button))
        {
            startGame=true;
        }
    }

    @Override
    public void mouseReleased(MouseEvent me) 
    {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseEntered(MouseEvent me) 
    {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseExited(MouseEvent me) 
    {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
