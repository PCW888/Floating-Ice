/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package floating.ice;

import java.awt.Color;
import javax.swing.JComponent;
import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JComponent;
import javax.swing.Timer;

/**
 *
 * @author workshopjan23
 */
public class FloatingIce extends JComponent  implements KeyListener, ActionListener{
    
    private Image Submarine;
    private Image Iceberg;
    
    
    private int subLeft=0;
    private int subTop=100;
    private int subWidth=300;
    private int subHeight=217;
    private int iceLeft= 700;
    private int iceTop=100;
    private int iceWidth=300;
    private int iceHeight=410;
    
    private int windowHeight=1000;
    private int windowWidth=1000;
    
    private int iceSpeed=25;
    private Random r = new Random();
    
    private boolean gone=false;
    
    public FloatingIce () throws IOException
            
    {
     Submarine = ImageIO.read(getClass().getResource("Submarine.gif"));
     Iceberg = ImageIO.read(getClass().getResource("iceberg.jpg"));
     
    }
            

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        JFrame window=new
                JFrame("FloatingIce");
        FloatingIce game = new FloatingIce();
        window.add(game);
        window.addKeyListener(game);
        window.pack();
        window.setVisible(true);
        window.setLocationRelativeTo(null);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);
        
        window.setBackground(Color.gray);
        
         Timer t = new Timer (100,game);
        t.start();
        
     
        
    }
    
    @Override
    public Dimension getPreferredSize()
    {
        return new Dimension(windowWidth, windowHeight);
    }

    @Override
    public void keyPressed(KeyEvent ke)
    {
        if (ke.getKeyCode()==KeyEvent.VK_DOWN)
        {
           subTop=subTop+50; 
            
            
        }
        if (ke.getKeyCode()==KeyEvent.VK_UP)
        {
          subTop=Math.max(100, subTop-50);
        }
        if (ke.getKeyCode()==KeyEvent.VK_LEFT)
        {subLeft = subLeft - 50;
        }
        if (ke.getKeyCode()==KeyEvent.VK_RIGHT)
        {subLeft = subLeft + 50;
        } 
        repaint();
    }
    
     @Override
    public void paintComponent(Graphics g)
    {
     g.setColor(Color.blue);
     g.fillRect(0, 100, 1000, 1000);
     g.drawImage(Submarine, subLeft, subTop, subWidth, subHeight, this);
     g.drawImage(Iceberg, iceLeft, iceTop, iceWidth, iceHeight, this);
     
     if (gone==true)
     {
         g.setColor(Color.black);
         g.fillRect(0, 0, windowWidth, windowHeight);
         
         
     }
     

     
    }

    @Override
    public void keyTyped(KeyEvent ke) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void keyReleased(KeyEvent ke) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void actionPerformed(ActionEvent ae) 
    {
        iceLeft -= iceSpeed;
        if(iceLeft+iceWidth<0)
        {
            iceLeft= windowWidth + r.nextInt(500); 
            iceSpeed= 15+ r.nextInt(60);
            iceWidth= 150 + 2*r.nextInt(150);
            iceHeight= 205 + 2*r.nextInt(205);
        }
        
        if(subLeft+subWidth<iceLeft&&
           iceLeft+ iceWidth<subLeft&&
           subTop + subHeight< iceTop&&
           iceTop+iceHeight<subTop)
        {
            gone=true;
        }
       
        repaint();
    }
    
}
