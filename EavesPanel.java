import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
*This class displays a rocketship taking off in the sky and resets every time it reaches the top.
*@author Makayla Eaves
*@version 2022.05.31
*/

public class EavesPanel extends JPanel implements ActionListener {

	Timer time = new Timer(1,this);	
	int x = 150;
	int y = 300;
	int z = 280;
	int flameLength = 0;
	
	/**
	 * Paints picture of rocketship and background
	 * @param g the object to have access to draw on the screen
	 */
	@Override
	public void paintComponent(Graphics g) {
			super.paintComponent(g);
			this.setBackground(Color.CYAN);
			//draw white cloud
			g.setColor(Color.WHITE);
			g.fillOval(40, 100, 120, 50);	
			g.fillOval(50, 80, 40, 40);
			g.fillOval(65, 120, 40, 40);
			g.fillOval(70, 80, 40, 40);
			g.fillOval(100, 80, 40, 40);
			g.fillOval(100, 120, 40, 40);
			//draw yellow sun
			g.setColor(Color.YELLOW);
			g.fillOval(250, 50, 80, 80);
			//draw red 3D rocket body
			g.setColor(Color.RED);
			g.draw3DRect(x, y, 20, 40, getFocusTraversalKeysEnabled());		
			g.fill3DRect(x, y, 20, 40, getFocusTraversalKeysEnabled());
			g.drawPolygon(new int[] {150, 160, 170}, new int[] {y, z, y}, 3);		
			g.fillPolygon(new int[] {150, 160, 170}, new int[] {y, z, y}, 3);		
			g.fillPolygon(new int[] {140, 150, 150}, new int[] {y+40, z+40, y+40}, 3);		
			g.fillPolygon(new int[] {170, 170, 180}, new int[] {y+40, z+40, y+40}, 3);
			g.drawPolygon(new int[] {150, 154, 158}, new int[] {y+50, y+40, y+50}, 3);		 
			g.fillPolygon(new int[] {150, 154, 158}, new int[] {y+50, y+40, y+50}, 3);
			g.drawPolygon(new int[] {162, 166, 170}, new int[] {y+50, y+40, y+50}, 3);
			g.fillPolygon(new int[] {162, 166, 170}, new int[] {y+50, y+40, y+50}, 3);
			//draw flames coming from under rocket
			g.setColor(Color.ORANGE);
			g.fillRect(150, y+50, 8, flameLength);		
			g.fillRect(162, y+50, 8, flameLength);
			//draw black circle window
			g.setColor(Color.BLACK);
			g.fillOval(160, y+5, 10, 10);		
			time.start();		
	}
	
	/**
	 * Actions triggered by the timer
	 * @param e the event that calls the action
	 */
	public void actionPerformed(ActionEvent e) {	
			y--;		//vertical rocket coordinates all move up by 1 unit
			z--;
			flameLength++;		//flames grow vertically by 1 unit
			if (y < -40) {		//if rocket reaches the top, reposition at bottom again
				y = 300;
				z = 280;
				flameLength = 0;
			}
			repaint();			
	}
}