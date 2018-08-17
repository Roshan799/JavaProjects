
/*MADE BY PRAFFUL ROSHAN TOPNO*/
import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Dimension;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.util.Random;
import javax.swing.Timer;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.border.EmptyBorder;

class Dots extends JFrame
{
    public Dots()
    {
        Arena a=new Arena();
        add(a);
        add(a.informationpanel(),BorderLayout.EAST);
        setSize(680,500);
        setTitle("The Big Chase:Single Player");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        

    }
    public static void main(String[] args)
    {
        Dots gm=new Dots();
        gm.setLocationRelativeTo(null);
        gm.setVisible(true);
        gm.setResizable(false);
    }
}

class Arena extends JPanel implements ActionListener
{
    Player pl;
    food f;
    Bot bot1;
    Bot bot2;
    Bot bot3;
    Timer timer;
    JLabel player=new JLabel("PLAYER");
    JLabel b1=new JLabel("BOT 1(BLUE)");
    JLabel b2=new JLabel("BOT 2(GREEN)");
    JLabel b3=new JLabel("BOT 3(ORANGE)");
    JLabel pause=new JLabel("P:pause/resume");
    JLabel st_p=new JLabel("0");
    JLabel st_b1=new JLabel("0");
    JLabel st_b2=new JLabel("0");
    JLabel st_b3=new JLabel("0");
    JLabel act_pl=new JLabel("Active");
    JLabel act_b1=new JLabel("Active");
    JLabel act_b2=new JLabel("Active");
    JLabel act_b3=new JLabel("Active");
    JLabel info=new JLabel("Game Running");
    JLabel winner=new JLabel("        ");
    JLabel reset=new JLabel("R:reset");
    boolean ispaused=false;
    public Arena()
    {
     pl=new Player();
     bot1=new Bot(Color.BLUE,300,100);
     bot2=new Bot(Color.GREEN,100,300);
     bot3=new Bot(Color.ORANGE,300,300);
     f=new food();
     f.isactive=true;
     timer = new Timer(100, this);
     timer.start();
     setFocusable(true);
     addKeyListener(new Tadpter());
    }
     public JPanel informationpanel()
    {
        JPanel show=new JPanel();
                show.setLayout(new BoxLayout(show,BoxLayout.Y_AXIS));
                show.setBackground(Color.CYAN);
                show.setBorder(new EmptyBorder(new Insets(0,0,80,80)));
                show.add(player);
                show.add(st_p);
                show.add(act_pl);
                show.add(Box.createRigidArea(new Dimension(2,8)));
                show.add(b1);
                show.add(st_b1);
                show.add(act_b1);
                show.add(Box.createRigidArea(new Dimension(2,8)));
                show.add(b2);
                show.add(st_b2);
                show.add(act_b2);
                show.add(Box.createRigidArea(new Dimension(2,8)));
                show.add(b3);
                show.add(st_b3);
                show.add(act_b3);
                show.add(Box.createRigidArea(new Dimension(2,8)));
                show.add(pause);
                show.add(Box.createRigidArea(new Dimension(2,8)));
                show.add(info);
                show.add(winner);
                show.add(reset);
                return show;
    }
     public void reset()
     {
          st_p.setText("0");
     st_b1.setText("0");
     st_b2.setText("0");
     st_b3.setText("0");
     act_pl.setText("Active");
     act_b1.setText("Active");
     act_b2.setText("Active");
     act_b3.setText("Active");
     info.setText("Game Running");
     winner.setText("        ");
        pl=new Player();
     bot1=new Bot(Color.BLUE,300,100);
     bot2=new Bot(Color.GREEN,100,300);
     bot3=new Bot(Color.ORANGE,300,300);
     f=new food();
     f.isactive=true;
     timer.start();
     ispaused=false;
     }
      public void pause()
    {
        if(ispaused==true)
        {
            ispaused=false;
            timer.start();
            info.setText("Game Running");
        }
        else
        {
            timer.stop();
            ispaused=true;
            info.setText("Paused");
        }
    }
      public void checkwin()
      {
          if(pl.isactive&&!(bot1.isactive)&&!(bot2.isactive)&&!(bot3.isactive))
          {
              timer.stop();
              pause();
              winner.setText("PLAYER WON");
          }
          if(bot1.isactive&&!(pl.isactive)&&!(bot2.isactive)&&!(bot3.isactive))
          {
              timer.stop();
              pause();
              winner.setText("BOT 1 WON");
          }
          if(bot2.isactive&&!(bot1.isactive)&&!(pl.isactive)&&!(bot3.isactive))
          {
              timer.stop();
              pause();
              winner.setText("BOT 2 WON");
          }
          if(bot3.isactive&&!(bot1.isactive)&&!(bot2.isactive)&&!(pl.isactive))
          {
              timer.stop();
              pause();
              winner.setText("BOT 3 WON");
          }
          if(!(pl.isactive)&&!(bot1.isactive)&&!(bot2.isactive)&&!(bot3.isactive))
          {
              timer.stop();
              pause();
              winner.setText("Everyone eliminated");
          }
      }
    @Override
    public void paint(Graphics g)
    {
        super.paint(g);
        g.setColor(Color.black);
        g.drawRect(0, 0, 500, 465);
        if(f.isactive)
        {
        g.setColor(Color.BLACK);
        g.drawRect(f.getLeft(), f.getTop(), f.getWidth(), f.getHeight());
        g.fillRect(f.getLeft(), f.getTop(), f.getWidth(), f.getHeight());
        }
        if(pl.isactive)
        {
        g.setColor(pl.getColor());
        g.drawRect(pl.getLeft(), pl.getTop(), pl.getWidth(), pl.getHeight());
        g.fillRect(pl.getLeft(), pl.getTop(), pl.getWidth(), pl.getHeight());
        }
        if(bot1.isactive)
        {
        g.setColor(bot1.getColor());
        g.drawRect(bot1.getLeft(), bot1.getTop(), bot1.getWidth(), bot1.getHeight());
        g.fillRect(bot1.getLeft(), bot1.getTop(), bot1.getWidth(), bot1.getHeight());
        }
        if(bot2.isactive)
        {
        g.setColor(bot2.getColor());
        g.drawRect(bot2.getLeft(), bot2.getTop(), bot2.getWidth(), bot2.getHeight());
        g.fillRect(bot2.getLeft(), bot2.getTop(), bot2.getWidth(), bot2.getHeight());
        }
        if(bot3.isactive)
        {
        g.setColor(bot3.getColor());
        g.drawRect(bot3.getLeft(), bot3.getTop(), bot3.getWidth(), bot3.getHeight());
        g.fillRect(bot3.getLeft(), bot3.getTop(), bot3.getWidth(), bot3.getHeight());
        }
    }
    @Override
public void actionPerformed(ActionEvent e) {
    checkwin();
if(f.isactive&&f.getLeft()>=pl.getLeft()&&f.getLeft()<=pl.getLeft()+pl.getsize()&&f.getTop()>=pl.getTop()&&f.getTop()<=pl.getTop()+pl.getsize())
{
    f=new food();
    f.isactive=true;
    pl.changesize();
    pl.incstrength();
    pl.decspeed();
    st_p.setText(String.valueOf(pl.getStrenght()));
    repaint();
}
if(bot1.isactive)
{
bot1.towardsfood();
repaint();
}
if(bot2.isactive)
{
bot2.towardsfood();
repaint();
}
if(bot3.isactive)
{
bot3.towardsfood();
repaint();
}
if(f.isactive&&f.getLeft()>=bot1.getLeft()&&f.getLeft()<=bot1.getLeft()+bot1.getsize()&&f.getTop()>=bot1.getTop()&&f.getTop()<=bot1.getTop()+bot1.getsize())
{
     f=new food();
    f.isactive=true;
    bot1.changesize();
    bot1.incstrength();
    bot1.decspeed();
    st_b1.setText(String.valueOf(bot1.strength));
    repaint();
}
if(f.isactive&&f.getLeft()>=bot2.getLeft()&&f.getLeft()<=bot2.getLeft()+bot2.getsize()&&f.getTop()>=bot2.getTop()&&f.getTop()<=bot2.getTop()+bot2.getsize())
{
     f=new food();
    f.isactive=true;
    bot2.changesize();
    bot2.incstrength();
    bot2.decspeed();
    st_b2.setText(String.valueOf(bot2.strength));
    repaint();
}
if(f.isactive&&f.getLeft()>=bot3.getLeft()&&f.getLeft()<=bot3.getLeft()+bot3.getsize()&&f.getTop()>=bot3.getTop()&&f.getTop()<=bot3.getTop()+bot3.getsize())
{
     f=new food();
    f.isactive=true;
    bot3.changesize();
    bot3.incstrength();
    bot3.decspeed();
    st_b3.setText(String.valueOf(bot3.strength));
    repaint();
}
if(check(bot1,pl)&&bot1.isactive&&pl.isactive)
{
    bot1.isactive=false;
    act_b1.setText("Eliminated");
    /*if(bot1.strength==pl.strength)
    {
        pl.isactive=false;
        act_pl.setText("Eliminated");
    }*/
}
if(check(bot2,pl)&&bot2.isactive&&pl.isactive)
{
    bot2.isactive=false;
    act_b2.setText("Eliminated");
    /*if(bot2.strength==pl.strength)
    {
        pl.isactive=false;
        act_pl.setText("Eliminated");
    }*/
}
if(check(bot3,pl)&&bot3.isactive&&pl.isactive)
{
    bot3.isactive=false;
    act_b3.setText("Eliminated");
    /*if(bot3.strength==pl.strength)
    {
        pl.isactive=false;
        act_pl.setText("Eliminated");
    }*/
}
if(check(pl,bot1)&&pl.isactive&&bot1.isactive)
{
    pl.isactive=false;
    act_pl.setText("Eliminated");
    /*if(bot1.strength==pl.strength)
    {
        bot1.isactive=false;
        act_b1.setText("Eliminated");
    }*/
}
if(check(pl,bot2)&&pl.isactive&&bot2.isactive)
{
    pl.isactive=false;
    act_pl.setText("Eliminated");
    /*if(bot2.strength==pl.strength)
    {
        bot2.isactive=false;
        act_b2.setText("Eliminated");
    }*/
}
if(check(pl,bot3)&&pl.isactive&&bot3.isactive)
{
    pl.isactive=false;
    act_pl.setText("Eliminated");
    /*if(bot3.strength==pl.strength)
    {
        bot3.isactive=false;
        act_b3.setText("Eliminated");
    }*/
}
if(check(bot1,bot2)&&bot1.isactive&&bot2.isactive)
{
    bot1.isactive=false;
    act_b1.setText("Eliminated");
    /*if(bot1.strength==bot2.strength)
    {
        bot2.isactive=false;
        act_b2.setText("Eliminated");
    }*/
}
if(check(bot1,bot3)&&bot1.isactive&&bot3.isactive)
{
    bot1.isactive=false;
    act_b1.setText("Eliminated");
    /*if(bot1.strength==bot3.strength)
    {
        bot3.isactive=false;
        act_b3.setText("Eliminated");
    }*/
}
if(check(bot2,bot1)&&bot2.isactive&&bot1.isactive)
{
    bot2.isactive=false;
    act_b2.setText("Eliminated");
    /*if(bot1.strength==bot2.strength)
    {
        bot1.isactive=false;
        act_b1.setText("Eliminated");
    }*/
}
if(check(bot2,bot3)&&bot2.isactive&&bot3.isactive)
{
    bot2.isactive=false;
    act_b2.setText("Eliminated");
    /*if(bot2.strength==bot3.strength)
    {
        bot3.isactive=false;
        act_b3.setText("Eliminated");
    }*/
}
if(check(bot3,bot1)&&bot3.isactive&&bot1.isactive)
{
    bot3.isactive=false;
    act_b3.setText("Eliminated");
    /*if(bot1.strength==bot3.strength)
    {
        bot1.isactive=false;
        act_b1.setText("Eliminated");
}*/
}
if(check(bot3,bot2)&&bot3.isactive&&bot2.isactive)
{
    bot3.isactive=false;
    act_b3.setText("Eliminated");
    /*if(bot3.strength==bot2.strength)
    {
        bot2.isactive=false;
        act_b2.setText("Eliminated");
    }*/
}
}
   public boolean candraw()
   {
       if(pl.isactive&&f.getLeft()>=pl.getLeft()&&f.getLeft()<=(pl.getLeft()+pl.getsize())&&f.getTop()>=pl.getTop()&&f.getTop()<=(pl.getTop()+pl.getsize()))
       {
        return false;   
       }
       if(bot1.isactive&&f.getLeft()>=bot1.getLeft()&&f.getLeft()<=(bot1.getLeft()+bot1.getsize())&&f.getTop()>=bot1.getTop()&&f.getTop()<=(bot1.getTop()+bot1.getsize()))
       {
        return false;   
       }
       if(bot2.isactive&&f.getLeft()>=bot2.getLeft()&&f.getLeft()<=(bot2.getLeft()+bot2.getsize())&&f.getTop()>=bot2.getTop()&&f.getTop()<=(bot2.getTop()+bot2.getsize()))
       {
        return false;   
       }if(bot3.isactive&&f.getLeft()>=bot3.getLeft()&&f.getLeft()<=(bot3.getLeft()+bot3.getsize())&&f.getTop()>=bot3.getTop()&&f.getTop()<=(bot3.getTop()+bot3.getsize()))
       {
        return false;   
       }
       if(f.getLeft()<5||f.getLeft()>450||f.getTop()<5||f.getTop()>450)
       {
           return false;
       }
       return true;
   }
 
class Tadpter extends KeyAdapter
    {
    @Override
    public void keyPressed(KeyEvent e) {
            int keycode = e.getKeyCode();
             if(e.getKeyCode()=='p'||e.getKeyCode()=='P')
            {
                pause();
            }
             if(e.getKeyCode()=='r'||e.getKeyCode()=='R')
            {
                reset();
            }
            if(ispaused)
            {
                return;
            }
            if(pl.isactive)
            {
        switch(keycode)
        {
            case KeyEvent.VK_UP:
                pl.moveUp();
                repaint();
                break;
            case KeyEvent.VK_DOWN:
                pl.moveDown();
                repaint();
                break;
            case KeyEvent.VK_RIGHT:
                pl.moveRight();
                repaint();
                break;
            case KeyEvent.VK_LEFT:
                pl.moveLeft();
                repaint();
                break;    
            
        }
            }
    }
    }
public boolean check(Bot b,Player p)
{
 if(p.getStrenght()>b.getStrenght()&&b.getLeft()>=p.getLeft()&&b.getLeft()<=p.getLeft()+p.getsize()&&(b.getTop()>=p.getTop()&&b.getTop()<=p.getTop()+p.getsize()))
 {
     return true;
 }
 if(p.getStrenght()>b.getStrenght()&&b.getLeft()+b.getsize()>=p.getLeft()&&b.getLeft()+b.getsize()<=p.getLeft()+p.getsize()&&(b.getTop()>=p.getTop()&&b.getTop()<=p.getTop()+p.getsize()))
 {
     return true;
 }
 if(p.getStrenght()>b.getStrenght()&&b.getLeft()>=p.getLeft()&&b.getLeft()<=p.getLeft()+p.getsize()&&(b.getTop()+b.getsize()>=p.getTop()&&b.getTop()+b.getsize()<=p.getTop()+p.getsize()))
 {
     return true;
 }
 if(p.getStrenght()>b.getStrenght()&&b.getLeft()+b.getsize()>=p.getLeft()&&b.getLeft()+b.getsize()<=p.getLeft()+p.getsize()&&(b.getTop()+b.getsize()>=p.getTop()&&b.getTop()+b.getsize()<=p.getTop()+p.getsize()))
 {
     return true;
 }
 return false;
}
public boolean check(Bot b1,Bot b2)
{
    if(b2.getStrenght()>b1.getStrenght()&&b1.getLeft()>=b2.getLeft()&&b1.getLeft()<=b2.getLeft()+b2.getsize()&&(b1.getTop()>=b2.getTop()&&b1.getTop()<=b2.getTop()+b2.getsize()))
 {
     return true;
 }
 if(b2.getStrenght()>b1.getStrenght()&&b1.getLeft()+b1.getsize()>=b2.getLeft()&&b1.getLeft()+b1.getsize()<=b2.getLeft()+b2.getsize()&&(b1.getTop()>=b2.getTop()&&b1.getTop()<=b2.getTop()+b2.getsize()))
 {
     return true;
 }
 if(b2.getStrenght()>b1.getStrenght()&&b1.getLeft()>=b2.getLeft()&&b1.getLeft()<=b2.getLeft()+b2.getsize()&&(b1.getTop()+b1.getsize()>=b2.getTop()&&b1.getTop()+b1.getsize()<=b2.getTop()+b2.getsize()))
 {
     return true;
 }
 if(b2.getStrenght()>b1.getStrenght()&&b1.getLeft()+b1.getsize()>=b2.getLeft()&&b1.getLeft()+b1.getsize()<=b2.getLeft()+b2.getsize()&&(b1.getTop()+b1.getsize()>=b2.getTop()&&b1.getTop()+b1.getsize()<=b2.getTop()+b2.getsize()))
 {
     return true;
 }
    return false;
}
public boolean check(Player p,Bot b)
{
    if(b.getStrenght()>p.getStrenght()&&p.getLeft()>=b.getLeft()&&p.getLeft()<=b.getLeft()+b.getsize()&&(p.getTop()>=b.getTop()&&p.getTop()<=b.getTop()+b.getsize()))
 {
     return true;
 }
 if(b.getStrenght()>p.getStrenght()&&p.getLeft()+p.getsize()>=b.getLeft()&&p.getLeft()+p.getsize()<=b.getLeft()+b.getsize()&&(p.getTop()>=b.getTop()&&p.getTop()<=b.getTop()+b.getsize()))
 {
     return true;
 }
 if(b.getStrenght()>p.getStrenght()&&p.getLeft()>=b.getLeft()&&p.getLeft()<=b.getLeft()+b.getsize()&&(p.getTop()+p.getsize()>=b.getTop()&&p.getTop()+p.getsize()<=b.getTop()+b.getsize()))
 {
     return true;
 }
 if(b.getStrenght()>p.getStrenght()&&p.getLeft()+p.getsize()>=b.getLeft()&&p.getLeft()+p.getsize()<=b.getLeft()+b.getsize()&&(p.getTop()+p.getsize()>=b.getTop()&&p.getTop()+p.getsize()<=b.getTop()+b.getsize()))
 {
     return true;
 }
    return false;
}
public boolean canmoveDown(Bot b)
    {
        if(b.top+b.size_val+b.speed>=465)
        {
            return false;
        }
        if((b.getStrenght()<=pl.getStrenght())&&(pl.getTop()>=b.top+b.size_val&&pl.getTop()<=b.top+b.size_val+30)&&((b.left>=pl.getLeft()&&b.left<=pl.getLeft()+pl.getsize())||(b.left+b.size_val>=pl.getLeft()&&b.left<=pl.getLeft()+pl.getsize())))
        {
         return false;   
        }
        if(b!=bot1)
        {
           if((b.getStrenght()<=bot1.getStrenght())&&(bot1.getTop()>=b.top+b.size_val&&bot1.getTop()<=b.top+b.size_val+30)&&((b.left>=bot1.getLeft()&&b.left<=bot1.getLeft()+bot1.getsize())||(b.left+b.size_val>=bot1.getLeft()&&b.left<=bot1.getLeft()+bot1.getsize())))
           {
               return false;
           }
        }
        if(b!=bot2)
        {
            if((b.getStrenght()<=bot2.getStrenght())&&(bot2.getTop()>=b.top+b.size_val&&bot2.getTop()<=b.top+b.size_val+30)&&((b.left>=bot2.getLeft()&&b.left<=bot2.getLeft()+bot2.getsize())||(b.left+b.size_val>=bot2.getLeft()&&b.left<=bot2.getLeft()+bot2.getsize())))
           {
               return false;
           }
        }
        if(b!=bot3)
        {
            if((b.getStrenght()<=bot3.getStrenght())&&(bot3.getTop()>=b.top+b.size_val&&bot3.getTop()<=b.top+b.size_val+30)&&((b.left>=bot3.getLeft()&&b.left<=bot3.getLeft()+bot3.getsize())||(b.left+b.size_val>=bot3.getLeft()&&b.left<=bot3.getLeft()+bot3.getsize())))
           {
               return false;
           }
        }
        return true;
    }
    public boolean canmoveUp(Bot b)
    {
        if(b.top-b.speed<=0)
        {
            return false;
        }
        if((b.getStrenght()<=pl.getStrenght())&&(pl.getTop()+pl.getsize()>=b.top-30&&pl.getTop()+pl.getsize()<=b.top)&&((b.left>=pl.getLeft()&&b.left<=pl.getLeft()+pl.getsize())||(b.left+b.size_val>=pl.getLeft()&&b.left<=pl.getLeft()+pl.getsize())))
        {
            return false;
        }
        if(b!=bot1)
        {
            if((b.getStrenght()<=bot1.getStrenght())&&(bot1.getTop()+bot1.getsize()>=b.top-30&&bot1.getTop()+bot1.getsize()<=b.top)&&((b.left>=bot1.getLeft()&&b.left<=bot1.getLeft()+bot1.getsize())||(b.left+b.size_val>=bot1.getLeft()&&b.left<=bot1.getLeft()+bot1.getsize())))
            {
            return false;
            }
        }
        if(b!=bot2)
        {
            if((b.getStrenght()<=bot2.getStrenght())&&(bot2.getTop()+bot2.getsize()>=b.top-30&&bot2.getTop()+bot2.getsize()<=b.top)&&((b.left>=bot2.getLeft()&&b.left<=bot2.getLeft()+bot2.getsize())||(b.left+b.size_val>=bot2.getLeft()&&b.left<=bot2.getLeft()+bot2.getsize())))
        {
            return false;
        }
        }
        if(b!=bot3)
        {
            if((b.getStrenght()<=bot3.getStrenght())&&(bot3.getTop()+bot3.getsize()>=b.top-30&&bot3.getTop()+bot3.getsize()<=b.top)&&((b.left>=bot3.getLeft()&&b.left<=bot3.getLeft()+bot3.getsize())||(b.left+b.size_val>=bot3.getLeft()&&b.left<=bot3.getLeft()+bot3.getsize())))
        {
            return false;
        }
        }
        return true;
    }
    public boolean canmoveRight(Bot b)
    {
        if(b.left+b.size_val+b.speed>=500)
        {
            return false;
        }
        if((b.getStrenght()<=pl.getStrenght())&&(pl.getLeft()>=b.left+b.size_val&&pl.getLeft()<=b.left+b.size_val+30)&&((b.top>=pl.getTop()&&b.top<=pl.getTop()+pl.getsize())||(b.top+b.size_val>=pl.getTop()&&b.top<=pl.getTop()+pl.getsize())))
        {
            return false;
        }
        if(b!=bot1)
        {
            if((b.getStrenght()<=bot1.getStrenght())&&(bot1.getLeft()>=b.left+b.size_val&&bot1.getLeft()<=b.left+b.size_val+30)&&((b.top>=bot1.getTop()&&b.top<=bot1.getTop()+bot1.getsize())||(b.top+b.size_val>=bot1.getTop()&&b.top<=bot1.getTop()+bot1.getsize())))
        {
            return false;
        }
        }
        if(b!=bot2)
        {
            if((b.getStrenght()<=bot2.getStrenght())&&(bot2.getLeft()>=b.left+b.size_val&&bot2.getLeft()<=b.left+b.size_val+30)&&((b.top>=bot2.getTop()&&b.top<=bot2.getTop()+bot2.getsize())||(b.top+b.size_val>=bot2.getTop()&&b.top<=bot2.getTop()+bot2.getsize())))
        {
            return false;
        }
        }
        if(b!=bot3)
        {
            if((b.getStrenght()<=bot3.getStrenght())&&(bot3.getLeft()>=b.left+b.size_val&&bot3.getLeft()<=b.left+b.size_val+30)&&((b.top>=bot3.getTop()&&b.top<=bot3.getTop()+bot3.getsize())||(b.top+b.size_val>=bot3.getTop()&&b.top<=bot3.getTop()+bot3.getsize())))
        {
            return false;
        }
        }
        return true;
    }
    public boolean canmoveLeft(Bot b)
    {
        if(b.left-b.speed<=0)
        {
            return false;
        }
        if((b.getStrenght()<=pl.getStrenght())&&(pl.getLeft()+pl.getsize()>=b.left-30&&pl.getLeft()+pl.getsize()<=b.left)&&((b.top>=pl.getTop()&&b.top<=pl.getTop()+pl.getsize())||(b.top+b.size_val>=pl.getTop()&&b.top<=pl.getTop()+pl.getsize())))
        {
            return false;
        }
        if(b!=bot1)
        {
            if((b.getStrenght()<=bot1.getStrenght())&&(bot1.getLeft()+bot1.getsize()>=b.left-30&&bot1.getLeft()+bot1.getsize()<=b.left)&&((b.top>=bot1.getTop()&&b.top<=bot1.getTop()+bot1.getsize())||(b.top+b.size_val>=bot1.getTop()&&b.top<=bot1.getTop()+bot1.getsize())))
        {
            return false;
        }
        }
        if(b!=bot2)
        {
            if((b.getStrenght()<=bot2.getStrenght())&&(bot2.getLeft()+bot2.getsize()>=b.left-30&&bot2.getLeft()+bot2.getsize()<=b.left)&&((b.top>=bot2.getTop()&&b.top<=bot2.getTop()+bot2.getsize())||(b.top+b.size_val>=bot2.getTop()&&b.top<=bot2.getTop()+bot2.getsize())))
        {
            return false;
        }
        }
        if(b!=bot3)
        {
            if((b.getStrenght()<=bot3.getStrenght())&&(bot3.getLeft()+bot3.getsize()>=b.left-30&&bot3.getLeft()+bot3.getsize()<=b.left)&&((b.top>=bot3.getTop()&&b.top<=bot3.getTop()+bot3.getsize())||(b.top+b.size_val>=bot3.getTop()&&b.top<=bot3.getTop()+bot3.getsize())))
        {
            return false;
        }
        }
        return true;
    }
class Bot
{
    private int left;//change it's value for movement
    private int top;//change it's value for movement
    private int width;//change it's value to change size
    private int height;//change it's value to change size
    private Color color;
    private int size_val;
    private int strength;
    private int speed;
    boolean isactive;
    public Bot(Color c,int x,int y)
    {
        color=c;
        width=50;
        height=50;
        left=x;
        top=y;
        speed=7;
        strength=0;
        size_val=50;
        isactive=true;
    }
    public void towardsfood()
    {
        if(f.getLeft()>=left&&f.getLeft()<=left+size_val||f.getTop()>=top&&f.getTop()<=top+size_val)
        {
            if(f.getLeft()>=left&&f.getLeft()<=left+size_val)
            {
                if(f.getTop()<top)
                {
                    if(canmoveUp(this))
                    {
                    moveUp();
                    }
                    else
                    {
                        getaway();
                    }
                }
                else
                {
                    if(canmoveDown(this))
                    {
                    moveDown();
                    }
                    else
                    {
                        getaway();
                    }
                }
            }
            else
            {
                if(f.getLeft()<left)
                {
                    if(canmoveRight(this))
                    {
                    moveLeft();
                    }
                    else
                    {
                        getaway();
                    }
                }
                else
                {
                    if(canmoveLeft(this))
                    {
                    moveRight();
                    }
                    else
                    {
                        getaway();
                    }
                }
            }
        }
        else
        {
            if(f.getLeft()<left)
            {
                if(canmoveLeft(this))
                {
                moveLeft();
                }
                else
                {
                    getaway();
                }
            }
            else
            {
                if(canmoveRight(this))
                {
                moveRight();
                }
                else
                {
                    getaway();
                }
            }
        }
    }
    
    public void getaway()
    {
        if(canmoveUp(this))
        {
            moveUp();
        }
        if(canmoveDown(this))
        {
            moveDown();
        }
        if(canmoveLeft(this))
        {
            moveLeft();
        }
        if(canmoveRight(this))
        {
            moveRight();
        }
    }
    public void incstrength()
    {
        strength=strength+1;
    }
    public void decspeed()
    {
        speed=speed-1;
    }
    public void changesize()
    {
        width=width+50;
        height=height+50;
        size_val=size_val+50;
            
    }
    public int getsize()
    {
        return size_val;
    }
    public void moveLeft()
    {
        if(left-speed>0)
        {
        left=left-speed;
        }
    }
    public void moveRight()
    {
        if(left+size_val+speed<500)
        {
        left=left+speed;
        }
    }
    public void moveUp()
    {
        if(top-speed>0)
        {
        top=top-speed;
        }
    }    
    public void moveDown()
    { 
        if(top+size_val+speed<465)
        {
        top=top+speed;
        }
    }
    public Color getColor()
    {
        return color;
    }
    public int getLeft()
    {
        return left;
    }
    public int getTop()
    {
        return top;
    }
    public int getWidth()
    {
        return width;
    }
    public int getHeight()
    {
        return height;
    }
    public int getStrenght()
    {
        return strength;
    }
    public int getSpeed()
    {
        return speed;
    }
}
class food
    {
        private int ftop;
        private int fleft;
        private final int fwidth=5;
        private final int fheight=5;
        public boolean isactive;
        public food()
        {
            do
            {
            ftop=Math.abs(new Random().nextInt())%450;
            fleft=Math.abs(new Random().nextInt())%450;
            }while(candraw());
            isactive=false;
        }
        public int getLeft()
    {
        return fleft;
    }
        public boolean candraw()
        {
             if(pl.isactive&&fleft>=pl.getLeft()&&fleft<=(pl.getLeft()+pl.getsize())&&ftop>=pl.getTop()&&ftop<=(pl.getTop()+pl.getsize()))
       {
        return true;   
       }
       if(bot1.isactive&&fleft>=bot1.getLeft()&&fleft<=(bot1.getLeft()+bot1.getsize())&&ftop>=bot1.getTop()&&ftop<=(bot1.getTop()+bot1.getsize()))
       {
        return true;   
       }
       if(bot2.isactive&&fleft>=bot2.getLeft()&&fleft<=(bot2.getLeft()+bot2.getsize())&&ftop>=bot2.getTop()&&ftop<=(bot2.getTop()+bot2.getsize()))
       {
        return true;   
       }if(bot3.isactive&&fleft>=bot3.getLeft()&&fleft<=(bot3.getLeft()+bot3.getsize())&&ftop>=bot3.getTop()&&ftop<=(bot3.getTop()+bot3.getsize()))
       {
        return true;   
       }
       if(fleft<5||fleft>450||ftop<5||ftop>450)
       {
           return true;
       }
       return false;
        }
    public int getTop()
    {
        return ftop;
    }
    public int getWidth()
    {
        return fwidth;
    }
    public int getHeight()
    {
        return fheight;
    }
   }
}


class Player
{
    private int left;//change it's value for movement
    private int top;//change it's value for movement
    private int width;//change it's value to change size
    private int height;//change it's value to change size
    private Color color;
    private int size_val;
    private int strength;
    private int speed;
    boolean isactive;
    public Player()
    {
        color=new Color(255, 0, 0);
        width=50;
        height=50;
        left=100;
        top=100;
        speed=7;
        strength=0;
        size_val=50;
        isactive=true;
    }
    public void incstrength()
    {
        strength=strength+1;
    }
    public void decspeed()
    {
        speed=speed-1;
    }
    public void changesize()
    {
        width=width+50;
        height=height+50;
        size_val=size_val+50;
            
    }
    public int getsize()
    {
        return size_val;
    }
    public void moveLeft()
    {
        if(left-speed>0)
        {
        left=left-speed;
        }
    }
    public void moveRight()
    {
        if(left+size_val+speed<500)
        {
        left=left+speed;
        }
    }
    public void moveUp()
    {
        if(top-speed>0)
        {
        top=top-speed;
        }
    }    
    public void moveDown()
    { 
        if(top+size_val+speed<465)
        {
        top=top+speed;
        }
    }
    public Color getColor()
    {
        return color;
    }
    public int getLeft()
    {
        return left;
    }
    public int getTop()
    {
        return top;
    }
    public int getWidth()
    {
        return width;
    }
    public int getHeight()
    {
        return height;
    }
    public int getStrenght()
    {
        return strength;
    }
    public int getSpeed()
    {
        return speed;
    }
}
