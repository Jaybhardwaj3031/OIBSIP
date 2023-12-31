import javax.swing.*;
import javax.swing.border.SoftBevelBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GamePage extends JFrame {

    static GamePage frame;
    private JTextField textField;
    private JTextField textField_2;
    JLabel trial;
    JButton Guess;
    int i = 0, guess;
    int MaxChance = 5;
    int remainingTrials = MaxChance; 
    int randomNum = 1 + (int) (100 * Math.random());

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                frame = new GamePage();
                frame.setVisible(true);
            }
        });
    }

    public GamePage() {
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setUndecorated(false);
        setTitle("Number Guessing Game");
        setBounds(145, 40, 950, 640);

        
        getContentPane().setBackground(new Color(173, 216, 230));

        JLabel lblContent = new JLabel();
        lblContent.setBounds(70, 60, 800, 460);
        lblContent.setBackground(new Color(255, 250, 160)); 
        lblContent.setBorder(new SoftBevelBorder(SoftBevelBorder.RAISED));
        lblContent.setOpaque(true);
        add(lblContent);

        JLabel text = new JLabel("Choose a number between 1 to 100");
        text.setBounds(320, 18, 390, 70);
        text.setFont(new Font("Comic Sans MS", Font.PLAIN, 24));
        lblContent.add(text);

        JLabel Enter = new JLabel();
        Enter.setText("Enter Your Number");
        Enter.setForeground(new Color(255, 0, 0));
        Enter.setAlignmentX(JLabel.CENTER);
        Enter.setBounds(410, 95, 200, 60);
        Enter.setFont(new Font("comic Sans MS", Font.BOLD, 20));
        Enter.setBackground(new Color(255, 250, 160)); 
        Enter.setOpaque(true);
        lblContent.add(Enter);

        trial = new JLabel();
        updateTrialLabel(); 
        trial.setForeground(new Color(255, 0, 0));
        trial.setAlignmentX(JLabel.CENTER);
        trial.setBounds(50, 50, 190, 60);
        trial.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
        trial.setBackground(new Color(255, 250, 160)); 
        trial.setOpaque(true);
        lblContent.add(trial);

        textField = new JTextField();
        textField.setBounds(450, 150, 110, 60);
        textField.setHorizontalAlignment(JTextField.CENTER);
        textField.setFont(new Font("Comic Sans MS", Font.PLAIN, 25));
        textField.setColumns(10);
        lblContent.add(textField);

        textField_2 = new JTextField();
        textField_2.setBounds(280, 310, 460, 120);
        textField_2.setBackground(new Color(255, 250, 160));
        textField_2.setHorizontalAlignment(JTextField.CENTER);
        textField_2.setEditable(false);
        textField_2.setFont(new Font("Comic Sans MS", Font.PLAIN, 24));
        lblContent.add(textField_2);

        Guess = new JButton("GUESS");
        Guess.setBounds(320, 230, 150, 55);
        Guess.setBackground(new Color(167, 199, 231));
        Guess.setFont(new Font("Comic Sans MS", Font.BOLD, 23));
        lblContent.add(Guess);
        Guess.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String input = textField.getText();
                guess = Integer.parseInt(input);

                if (guess > 100 || guess < 1) {
                    JOptionPane.showMessageDialog(GamePage.this, "Invalid Number! Please Enter Valid Number", "Wrong Number", JOptionPane.OK_CANCEL_OPTION);
                    textField_2.setText("");
                } else {
                    
                    if (randomNum == guess) {
                        textField_2.setText("Congratulations! You guessed the number");
                    } else if (randomNum > guess) {
                        textField_2.setText("The number is greater than " + guess);
                    } else if (randomNum < guess) {
                        textField_2.setText("The number is less than " + guess);
                    }
                    
                    remainingTrials--;
                    updateTrialLabel();
                    if (remainingTrials <= 0) {
                        JOptionPane.showMessageDialog(GamePage.this, "Game Over, Trial: 0", "Try Again", JOptionPane.OK_CANCEL_OPTION);
                        textField.setText("");
                        Guess.setEnabled(false); 
                    }
                }
            }
        });

        JButton playAgain = new JButton("RESTART");
        playAgain.setBounds(550, 230, 150, 55);
        playAgain.setBackground(new Color(167, 199, 231));
        playAgain.setFont(new Font("Comic Sans MS", Font.BOLD, 23));
        lblContent.add(playAgain);
        playAgain.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                remainingTrials = MaxChance;
                updateTrialLabel();
                randomNum = 1 + (int) (100 * Math.random());
                textField.setText("");
                textField_2.setText("");
                Guess.setEnabled(true);
            }
        });

        JButton btnEnd = new JButton("End Game");
        btnEnd.setFont(new Font("Comic Sans MS", Font.BOLD, 30));
        btnEnd.setBorder(new SoftBevelBorder(SoftBevelBorder.RAISED));
        btnEnd.setBackground(new Color(210, 125, 45));
        btnEnd.setBounds(732, 535, 152, 50);
        add(btnEnd);
        btnEnd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });
    }

    
    private void updateTrialLabel() {
        trial.setText("Remaining Trials: " + remainingTrials);
    }
}
