import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This class make a calcluator
 *
 *Yaci Zhu
 *2.0
 */
public class ZhuPanel extends JPanel implements ActionListener {

    private JPanel jp_north = new JPanel();
    private JTextField input_text = new JTextField();
    private JButton c_Btn = new JButton("Clear");
    private JPanel jp_center = new JPanel();
    private String firstInput = null;
    private String symbol = null;

    public ZhuPanel() throws HeadlessException {
        setSize(300, 400);
        setLayout(new BorderLayout());
        addNorthComponent();
        addCenterButton();

    }

    private void addNorthComponent() {
        this.input_text.setPreferredSize(new Dimension(230, 30));
        jp_north.add(input_text);

        this.c_Btn.setForeground(Color.blue);
        jp_north.add(c_Btn);

        c_Btn.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                input_text.setText("");
            }
        });


        this.add(jp_north, BorderLayout.NORTH);
    }


    private void addCenterButton() {
        String bth_text = "123+456-789*.0=/";

        this.jp_center.setLayout(new GridLayout(4, 4));
        for (int i = 0; i < 16; i++) {
            String temp = bth_text.substring(i, i + 1);
            JButton btn = new JButton();
            btn.setText(temp);

            if (temp.equals("+") || temp.equals("-") || temp.equals("*") || temp.equals("/") || temp.equals(".") || temp.equals("=")) {
                btn.setFont(new Font(" ", Font.BOLD, 20));
                btn.setForeground(Color.red);
            }
            btn.addActionListener((ActionListener) this);
            jp_center.add(btn);
        }
        this.add(jp_center, BorderLayout.CENTER);
    }
    
/**
 * Actionperforem when get the user input
 *
 *@param ActionEvent e
 *return void
 */

    public void actionPerformed(ActionEvent e) {

        String click = e.getActionCommand();
        if (".0123456789".indexOf(click) != -1) {
            this.input_text.setText(input_text.getText() + click);
            this.input_text.setHorizontalAlignment(JTextField.RIGHT);

        } else if (click.matches("[\\+\\-*/]{1}")) {
            symbol = click;
            firstInput = this.input_text.getText();
            this.input_text.setText("");
        } else if (click.equals("=")) {
            Double a = Double.valueOf(firstInput);
            Double b = Double.valueOf(this.input_text.getText());
            Double result = null;
            switch (symbol) {
                case "+":
                    result = a + b;
                    break;
                case "-":
                    result = a - b;
                    break;
                case "*":
                    result = a * b;
                    break;
                case "/":
                    if (b != 0) {
                        result = a / b;
                    }
                    break;
            }
            this.input_text.setText(result.toString());
        }
    }


}
