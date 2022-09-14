import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*
 * This class draws pacman eating pellets
 * 
 * @author Aditya Malladi
 * @version 2022.05.31
 */

public class MalladiPanel extends JPanel implements ActionListener {
	
	int angle;
	int width;
	int height;
	int x_pos;
	boolean increasing;
	
	/*
	 * Constructor initializes variables
	 */
	public MalladiPanel() {
		angle = 135;
		width = (int)(getSize().getWidth());
		height = (int)(getSize().getHeight());
		x_pos = 0;
		increasing = true;
		setLayout(new GridLayout(1,1));
		setBackground(Color.BLACK);
		Timer timer = new Timer(100, this);
		timer.setRepeats(true);
		timer.start();
	}

	/*
	 * Animates pacman and pellets when timer is fired
	 * 
	 * @param ActionEvent e
	 * @return void
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// pacman
		if (increasing == true) {
			angle = angle + 15;
		}
		else {
			angle = angle - 15;
		}
		if (angle == 180) {
			increasing = false;
		}
		else if (angle == 135) {
			increasing = true;
		}
		// pellets
		x_pos = x_pos - width/20;
		if (x_pos < width/3) {
			x_pos = width;
		}
		repaint();
		
	}
	
	/*
	 * Draws pacman and pellets
	 * 
	 * @param Graphics g
	 * @return void
	 */
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		width = (int)(getSize().getWidth());
		height = (int)(getSize().getHeight());
		// pellets
		int pellet_radius = width/8;
		g.setColor(Color.WHITE);
		g.fillOval(x_pos, height/2 - pellet_radius/2, pellet_radius, pellet_radius);
		// pacman
		int radius = width/2;
		g.setColor(Color.YELLOW);
		g.fillArc(width/3 - radius/3, height/2 - radius/2, radius, radius, 180, -angle);
		g.fillArc(width/3 - radius/3, height/2 - radius/2, radius, radius, 180, angle);
	}
}