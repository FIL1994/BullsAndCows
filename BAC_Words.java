/*
 * Authors:
 * Philip Van Raalte
*/
package BAC;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Font;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 *
 * @author Philip Van Raalte
 */
public class BAC_Words extends JFrame implements ActionListener {

    Random rnd = new Random();
    int randomNumber = rnd.nextInt(100);
    String outputLog = ""; //used to display output to user
    char userArray[] = new char[5];//an arrary to store the characters of the word that the user guessed
    char wordArray[] = new char[5]; //an array to store the characters of the word that the program chose

    //an array of five letter words
    String words[]
            = {
                "smart", "seven", "today", "never", "empty", "agent", "ready",
                "round", "bonus", "smoke", "agaze", "arrow", "ghost", "track",
                "drift", "forge", "funky", "exist", "glide", "guest", "happy",
                "index", "enemy", "laugh", "leech", "latch", "night", "magic",
                "maybe", "march", "mixer", "model", "naval", "piece", "wagon",
                "album", "title"
            };

    //the program randomly chooses a word from the word array
    String word = words[rnd.nextInt(words.length)];

    JPanel main = new JPanel();
    JPanel user = new JPanel();
    JPanel system = new JPanel();

    JTextArea input = new JTextArea("type here");
    JTextArea output = new JTextArea("Enter a five letter word");
    Button go = new Button("Go"); //user inputs data with this button
    Button restart = new Button("Restart"); //tells user they can restart the game

    BAC_Words() {
        setTitle("Bulls and Cows - Words");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //using monospaced font so that the characters will line up
        //so the user knows what characters they guessed right and what ones are wrong
        input.setFont(new Font("Monospaced", Font.BOLD, 15));
        output.setFont(new Font("Monospaced", Font.BOLD, 15));

        go.addActionListener(this);
        restart.addActionListener(this);
        output.setEditable(false); //stops the user from editing the text area

        //setting upthe layouts
        main.setLayout(new BorderLayout());

        user.setLayout(new BorderLayout());
        user.add(input);
        user.add(go, BorderLayout.SOUTH);

        system.add(output);

        main.add(user, BorderLayout.SOUTH);
        main.add(system, BorderLayout.CENTER);

        setContentPane(main);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        //if the game finished this will change the variables to their default values
        if (e.getActionCommand().equals("Restart")) {
            word = words[rnd.nextInt(words.length)];
            user.remove(restart);
            user.add(go, BorderLayout.SOUTH);
            outputLog = "";
        } else {

            //a cheat to see what word the programchose
            if (input.getText().equals("game.tell")) {
                outputLog += "\n" + word;
            }

            //makes the user input all lowercase so the program won't make mistakes
            //when it compares the user's word to the program's word
            String userInput = input.getText().toLowerCase();

            if (fiveLetters(userInput)) {
                outputLog += "\n" + userInput;
                output.setText(outputLog);

                userArray = stringToArray(userInput);
                wordArray = stringToArray(word);

                compareArrays(wordArray, userArray);
                output.setText(outputLog);

            } else {
                outputLog += "\nError\nEnter a five letter word";
                output.setText(outputLog);
            }
        }
    }

    //this method checks if the word the user entered has five letters
    public boolean fiveLetters(String userInput) {

        if (userInput.length() != 5) {
            return false;
        } else {
            return true;
        }
    }

    //this method takes a string and converts it to a char array
    public char[] stringToArray(String userInput) {
        char[] xArray = new char[5];

        for (int i = 0; i < 5; i++) {
            xArray[i] = userInput.charAt(i);
        }

        return xArray;
    }

    //this method compares the word the user entered to the word the program chose
    public void compareArrays(char[] wordArray, char[] userArray) {
        outputLog += "\n";

        String rightAndWrong = "";
        //rightAndWrong tells the user what they guessed right and what they guessed wrong
        for (int i = 0; i < 5; i++) {
            if (userArray[i] == wordArray[i]) {
                rightAndWrong += "^"; //tells user its in the right location
            } else if (userArray[i] == wordArray[0] || userArray[i] == wordArray[1]
                    || userArray[i] == wordArray[2] || userArray[i] == wordArray[3]
                    || userArray[i] == wordArray[4]) {
                rightAndWrong += "-";
                //tells user it is the right letter, but not in the right location
            } else {
                rightAndWrong += "x"; //tells user the number is incorrect
            }
        }

        if (rightAndWrong.equals("^^^^^")) {
            //if the user guessed the word correctly the program will let them know
            //they won the game and then will allow the user to restart the game
            rightAndWrong += "\nCongratulations, you won!";

            user.remove(go);
            user.add(restart, BorderLayout.SOUTH);

        }

        outputLog += rightAndWrong;
    }

}
