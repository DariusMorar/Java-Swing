import java.awt.Color;
import java.awt.Font;

import javax.swing.*;

/**
 * What is doing the class?
 *
 * @author
 * @version
 */
public class HuertaPanel extends JPanel {

    HuertaPanel(){
        JFrame frame = new JFrame(); 
        frame.setTitle("Himays Panel");
        frame.getContentPane().setBackground(new Color(137,207,240)); // Sets color to baby blue

        JLabel label = new JLabel();
        label.setText("Himay");
        label.setFont(new Font("MV Boli", Font.BOLD, 20));

        frame.add(label);

        label.setVerticalAlignment(JLabel.CENTER);
        label.setHorizontalAlignment(JLabel.CENTER);

    }
}