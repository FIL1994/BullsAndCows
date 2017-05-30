/*
 * Authors:
 * Philip Van Raalte
*/
package BAC;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 *
 * @author Philip Van Raalte
 */
public class BAC_Main extends JFrame implements ActionListener {

    public void run() {
        BAC_Main b = new BAC_Main();
        b.setSize(300, 300);
        b.setVisible(true);
    }

    BAC_Main() {
        setTitle("Bulls and Cows");
        JPanel main = new JPanel();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Button numbers = new Button("Numbers");
        numbers.addActionListener(this);
        numbers.setPreferredSize(new Dimension(80, 35));
        numbers.setBackground(Color.white);

        Button words = new Button("Words");
        words.addActionListener(this);
        words.setPreferredSize(new Dimension(80, 35));
        words.setBackground(Color.white);

        Button help = new Button("Help");
        help.addActionListener(this);
        help.setPreferredSize(new Dimension(80, 35));
        help.setBackground(Color.white);

        JTextArea head = new JTextArea("Select Game Mode: ");
        head.setEditable(false);

        main.add(head);
        main.add(Box.createRigidArea(new Dimension(300, 0)));
        main.add(numbers);
        main.add(Box.createRigidArea(new Dimension(300, 0)));
        main.add(words);
        main.add(Box.createRigidArea(new Dimension(300, 0)));
        main.add(help);

        main.setBackground(Color.orange);

        setContentPane(main);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Numbers")) {

            BAC_Numbers b = new BAC_Numbers();
            b.setSize(300, 550);
            b.setVisible(true);

        } else if (e.getActionCommand().equals("Words")) {
            BAC_Words b = new BAC_Words();
            b.setSize(300, 550);
            b.setVisible(true);
        } else {
            BAC_Help b = new BAC_Help();
            b.setSize(300, 550);
            b.setVisible(true);
        }
    }
}
