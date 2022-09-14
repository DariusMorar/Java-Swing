import javax.swing.*;
import java.awt.*;

/*
 * This class display Chessboard
 * (Change)
 * @author: Cungang Zhang
 * @version: 2022.05.30
 */
public class ZhangPanel extends JPanel{

    public ZhangPanel() {
        JLabel label1 = new JLabel("Chess");
        label1.setFont(new Font("Serif", Font.BOLD, 10));
        add(label1);

    }

    @Override
    public void paintComponent(Graphics g) {

        g.setColor(Color.YELLOW);
        g.fillRect(50, 50, 400, 400);
        for (int i = 50; i <= 400; i += 100) {
            for (int j = 50; j <= 400; j += 100) {
                g.clearRect(i, j, 50, 50);
            }
        }
        for (int i = 100; i <= 400; i += 100) {
            for (int j = 100; j <= 400; j += 100) {
                g.clearRect(i, j, 50, 50);
            }
        }
    }
}