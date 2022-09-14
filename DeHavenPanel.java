/*
This class is a Panel that will show a solid rectangle
    bouncing from the walls it touches
        
@author: ndehaven
@version 2022.29.2022
*/

import javax.swing.*;
import java.awt.*;

public class DeHavenPanel extends JPanel {
    
    int x; 
    int xspeed; 
    int y;
    int yspeed;
    // int count; 

    public DeHavenPanel () { // Tell what the constructor is doing 
      // Center position 
      x = 10; 
      y = 10; 
      xspeed = 3; 
      yspeed = 3; 
        
      JPanel dPanel1 = new JPanel(); 
      add(dPanel1); 
      JLabel dLabel1 = new JLabel("DeHaven Panel"); 
      dPanel1.add(dLabel1); 
      

    }
    
    public void paintComponent(Graphics g) { 
        
          this.setBackground(Color.WHITE); 
          g.drawRect(x, y, 40, 30); 
          g.fillRect(x, y, 40, 30); 
          g.setColor(Color.BLACK);
          x += xspeed; 
          y += yspeed;
         
          
          if (x + 40 >= 400 || x <= 0) { 
              // Oppositie direction horizontally
                xspeed = -xspeed; 
          }
          if ( y + 30 >= 300 || y <= 0) { 
              // Opposite direction vertically 
                yspeed = -yspeed; 
           
          }
           
    } 
    
}
    	
    	