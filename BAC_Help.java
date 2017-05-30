/*
 * Authors:
 * Philip Van Raalte
*/
package BAC;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 *
 * @author Philip Van Raalte
 */
public class BAC_Help extends JFrame {

    BAC_Help() {
        JTextArea help = new JTextArea(
                  "     INSTRUCTIONS\n"
                + "\nTo play Bulls and Cows"
                + "\nyou must enter four digits"
                + "\nor a five letter word."
                + "\n"
                + "\nThen the game will tell"
                + "\nyou what letters/digits"
                + "\nare in the word/number"
                + "\nand what letters/digits"
                + "\nare in the right location."
                + "\n"
                + "\nSymbols:"
                + "\n^ --> correct location"
                + "\n- --> correct letter/digit"
                + "\n x --> incorrect letter/digit"
        );
        help.setEditable(false);
        JPanel main = new JPanel();
        main.add(help);
        setContentPane(main);
    }

}
