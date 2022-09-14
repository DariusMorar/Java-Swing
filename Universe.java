import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

/**
 * Our Universe.
 * This is a collage of student's panel creations
 *
 * @author Javier Gonzalez-Sanchez
 * @version 2022.05.25
 */
public class Universe extends JFrame {

	/**
	 * Main method
	 *
	 * @param args not used
	 */
	public static void main(String[] args) {
		Universe universe = new Universe();
		universe.setSize(400,300);
		universe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		universe.setVisible(true);
	}
	
	public Universe () {
		Border border = BorderFactory.createLineBorder(Color.WHITE);
		GridLayout grid = new GridLayout(3,4);
		setLayout (grid);
		JPanel [] panels = new JPanel[15];
		final String [] FILES = new String[]{
			"DeHavenPanel", "EavesPanel", "HuertaPanel",
			"JeonPanel", "LiuPanel", "MachirajuPanel", "MalladiPanel", "MorarPanel",
			"OlivasPanel", "PallePanel", "ZhangPanel", "ZhuPanel",
		};
		for (int i = 0; i<FILES.length; i++) {
			try {
				// panels[i] = new JPanel();
				Class<?> tabClass = Class.forName(FILES[i]);
				Object panel = tabClass.getDeclaredConstructor().newInstance();
				panels[i] = (JPanel)panel;
			} catch (Exception exception) {
				panels[i] = new JPanel();
				panels[i].setBackground(Color.ORANGE);
				panels[i].add(new JLabel (FILES[i] + " missing!"));
			}
			panels[i].setBorder (border);
			add(panels[i]);
			
		}
	}
	
}