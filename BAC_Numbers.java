/*
 * Authors:
 * Philip Van Raalte
*/
package BAC;

import java.awt.BorderLayout;
import java.awt.Button;
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
public class BAC_Numbers extends JFrame implements ActionListener {

    Random rnd = new Random();
    int randomNumber = rnd.nextInt(100);
    String outputLog = ""; //used to display output to user
    int num[] = new int[4]; //the number the user tries to guess
    int userArray[] = new int[4]; //an array to store the number the user enters

    JPanel main = new JPanel();
    JPanel user = new JPanel();
    JPanel system = new JPanel();

    JTextArea input = new JTextArea("type here");
    JTextArea output = new JTextArea("Enter four digits");
    Button go = new Button("Go"); //user inputs data with this button
    Button restart = new Button("Restart"); //tells user they can restart the game

    BAC_Numbers() {
        setTitle("Bulls and Cows - Numbers");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //filling array with random numbers
        //this is the array the user must guess
        for (int i = 0; i < num.length; i++) {
            num[i] = rnd.nextInt(10);
        }
        
        //using monospaced font so that the characters will line up
        //so the user knows what numbers they guessed right and what ones are wrong
        input.setFont(new Font("Monospaced", Font.BOLD, 15));
        output.setFont(new Font("Monospaced", Font.BOLD, 15));

        go.addActionListener(this);
        restart.addActionListener(this);
        output.setEditable(false); //stops the user from editing the text area

        
        //setting up the layouts
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
            outputLog = "";
            user.remove(restart);
            user.add(go, BorderLayout.SOUTH);
            for (int i = 0; i < num.length; i++) {
                num[i] = rnd.nextInt(10);
            }
        } else {

            //a cheat to see what number the program chose
            if (input.getText().equals("game.tell")) {
                outputLog += "\n" + num[0] + "" + num[1] + "" + num[2] + "" + num[3];
            }

            String userInput = cleanUpString(input.getText());

            if (fourDigits(userInput)) {
                outputLog += "\n" + userInput;
                output.setText(outputLog);

                userArray = stringToArray(userInput);
                compareArrays(num, userArray);
                output.setText(outputLog);

            } else {
                outputLog += "\nError\nEnter four digits";
                output.setText(outputLog);
            }
        }
    }

    //this method cleans up whatever the user entered.
    //with this method the user may enter 1-6-5 6
    //and the program will change it to 1656
    public String cleanUpString(String userInput) {
        String clean = userInput.replaceAll("\\s", "");
        clean = clean.replaceAll("\\D", "");
        return clean;
    }
    
    //this method checks if the number the user entered has four digits
    public boolean fourDigits(String userInput) {

        if (userInput.length() != 4) {
            return false;
        } else {
            return true;
        }
    }

    //this method takes a string and converts it to a int array
    public int[] stringToArray(String userInput) {
        int[] xArray = new int[4];

        for (int i = 0; i < 4; i++) {
            xArray[i] = Character.getNumericValue(userInput.charAt(i));
        }

        return xArray;
    }

    //this method compares the number the user entered to the number the program chose
    public void compareArrays(int[] num, int[] userArray) {
        outputLog += "\n";

        String rightAndWrong = ""; 
        //rightAndWrong tells the user what they guessed right and what they guessed wrong
        for (int i = 0; i < 4; i++) {
            if (userArray[i] == num[i]) {
                rightAndWrong += "^"; //tells user its in the right location
            } else if (userArray[i] == num[0] || userArray[i] == num[1]
                    || userArray[i] == num[2] || userArray[i] == num[3]) {
                rightAndWrong += "-";
                //tells user it is the right number, but not in the right location
            } else {
                rightAndWrong += "x"; //tells user the number is incorrect
            }
        }

        if (rightAndWrong.equals("^^^^")) {
            //if the user guess the number correctly the program will let them know
            //they won the game and then will allow the user to restart the game
            rightAndWrong += "\nCongratulations, you won!";

            user.remove(go);
            user.add(restart, BorderLayout.SOUTH);

        }

        outputLog += rightAndWrong;
    }

}
