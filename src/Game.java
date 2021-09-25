//XinCheng Chi
//Period 6

import java.applet.*;
import java.awt.FlowLayout;
import javax.swing.*;
public class Game
{
  JFrame frame;
  JFrame menu;
  private final AudioClip introSong;
  private final AudioClip attack;
  private final AudioClip get;
  private final AudioClip damage;
  private final AudioClip end;
  private final AudioClip win;
  private final Grid grid;
  private int userRow;
  private int msElapsed;
  private int timesGet;
  private int timesAvoid;
  private int power;
  private int life;
  
  public Game()
  {
    grid = new Grid(10, 10,"BG.gif");
    userRow = 0;
    msElapsed = 0;
    timesGet = 0;
    timesAvoid = 0;
    updateTitle();
    grid.setImage(new Location(userRow, 0), "user.gif");
    introSong=Applet.newAudioClip(this.getClass().getResource("BackGround.wav"));
    attack=Applet.newAudioClip(this.getClass().getResource("attack.wav"));
    get=Applet.newAudioClip(this.getClass().getResource("GET.wav"));
    damage=Applet.newAudioClip(this.getClass().getResource("damage.wav"));
    end=Applet.newAudioClip(this.getClass().getResource("GameOver.wav"));
    win=Applet.newAudioClip(this.getClass().getResource("victory.wav"));
    power=5;
    life=5;
  }
  
  public void play()
  {
    introSong.play();
    Guide();
    while (!isGameOver())
    {
      grid.pause(100);
      handleKeyPress();
      if (msElapsed % 300 == 0)
      {
        scrollLeft();
        populateRightEdge();
      }
      updateTitle();
      msElapsed += 100;
    }
  }
  public void Guide()
  {
      frame=new JFrame("Guide");
      frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
      JLabel label1 = new JLabel("You may press:");
      JLabel label2 = new JLabel("W / Upward arrow to move up");
      JLabel label3 = new JLabel("S / Dpward arrow to move up");
      JLabel label4 = new JLabel("Space to attack");
      JLabel label5 = new JLabel("Every attack takes 5 power");
      JLabel label6 = new JLabel("                           ");
      JLabel label7 = new JLabel("Good luck have fun");
      frame.setLayout(new FlowLayout());
      frame.getContentPane().add(label1);
      frame.getContentPane().add(label2);
      frame.getContentPane().add(label3);
      frame.getContentPane().add(label4);
      frame.getContentPane().add(label5);
      frame.getContentPane().add(label6);
      frame.getContentPane().add(label7);
      frame.setSize(200,190);
      frame.setLocation(1025, 200);
      frame.setVisible(true);
  }
  public void handleKeyPress()
  {
      int key = grid.checkLastKeyPressed();
      System.out.println(key);
      if((key==87||key==38)&&userRow!=0)
      {
          handleCollision(new Location(userRow-1,0));
          grid.setImage(new Location(userRow, 0), null);
          userRow--;
          grid.setImage(new Location(userRow, 0), "user.gif");
      }
      if((key==83||key==40)&&userRow!=grid.getNumRows()-1)
      {
          handleCollision(new Location(userRow+1,0));
          grid.setImage(new Location(userRow, 0), null);
          userRow++;
          grid.setImage(new Location(userRow, 0), "user.gif");
      }
      if(key==32&&power>=5)
      {
          attack.play();
          grid.setImage(new Location(userRow,0),"zhu.gif");
          for(int i=1;i<grid.getNumCols();i++)
          {
              grid.setImage(new Location(userRow,i), "fire.gif");
          }
          power=0;
          timesGet=timesAvoid;
      }
  }

  public void populateRightEdge()
  {
      int rad=(int)(Math.random()*grid.getNumRows());
      int rad1=(int)(Math.random()*grid.getNumRows());
      if(rad1!=rad)
      {
          grid.setImage(new Location(rad,grid.getNumCols()-1),"get.gif");
          grid.setImage(new Location(rad1,grid.getNumCols()-1),"avoid.gif");
      }
      else
          populateRightEdge();
  }
  
  public void scrollLeft()
  {
      handleCollision(new Location(userRow,1));
      for(int i=0;i<grid.getNumRows();i++)
      {
          for(int j=0;j<grid.getNumCols();j++)
          {
              if(grid.getImage(new Location(i,j))!=null&&!grid.getImage(new Location(i,j)).equals("user.gif")&&!grid.getImage(new Location(i,j)).equals("fire.gif")&&!grid.getImage(new Location(i,j)).equals("zhu.gif")&&!grid.getImage(new Location(i,j)).equals("sad.gif"))
              {
                  if(j==0)
                      grid.setImage(new Location(i,j),null);
                  else
                  {
                      handleCollision(new Location(i,j-1));
                      grid.setImage(new Location(i,j-1),grid.getImage(new Location(i,j)));
                      grid.setImage(new Location(i,j), null);
                  }
              }
          }
      }
  }
  
  public void handleCollision(Location loc)
  {
      if(grid.getImage(loc)==null)
          ;
      else
      {
          if(grid.getImage(loc).equals("get.gif"))
          {
              get.play();
              timesGet++;power++;
              grid.setImage(loc,null);
          }
          else if(grid.getImage(loc).equals("avoid.gif"))
          {
              damage.play();
              timesAvoid++;life--;
              grid.setImage(loc,null);
              grid.setImage(new Location(userRow,0),"sad.gif");
          } 
      }
  }
  public int getScore()
  {
    return timesGet-timesAvoid;
  }
  
  public void updateTitle()
  {
    grid.setTitle("Life: "+life+"                              GameScore:  " + getScore()+"         Power: "+power);
  }
  
  public boolean isGameOver()
  {
    if(getScore()<0||life<=0)
    {
        introSong.stop();
        end.play();
        grid.showMessageDialog("HAAAAAAA,You Lose>_<");
        return true;
    }
    else if(getScore()>=30)
    {
        introSong.stop();
        win.play();
        grid.showMessageDialog("Congratulation! You Win!");
        return true;
    }
    else
        return false;
  }
  
  public static void test() 
  {
    StartPage pg=new StartPage();
  }
  
  public static void main(String[] args)
  {
    test();
  }
}