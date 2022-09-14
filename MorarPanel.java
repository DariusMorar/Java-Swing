import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

/**
 * This class creates a button asking to be clicked.
 * Once clicked, the background changes to a random color.
 * 
 * @author Darius Morar
 * @version 2022.05.31
 */

public class MorarPanel extends JPanel implements ActionListener {
	
	JFrame frame = new JFrame();
	JButton button = new JButton("Click Me!");
	
	public MorarPanel() {

		add(button);
		button.addActionListener(this);
		
	}
	
	public void actionPerformed(ActionEvent e) {
		
		int red, blue, green;
		red = (int)(Math.random() * 255);
		blue = (int)(Math.random() * 255);
		green = (int)(Math.random() * 255);
		Color c = new Color(red, blue, green);
		setBackground(c);
		
	}
	
}
