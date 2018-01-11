package TTT_GAME;

import sun.applet.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class MainMenu extends JFrame{

    public JPanel overviewJP;
    private JButton playTicTacToeButton;
    private JButton trainAIButton;
    //JFrame thisGUI = new JFrame();

    public MainMenu() {

        super("Main Menu");
        overviewJP = new JPanel(new GridLayout(2,1));
        playTicTacToeButton = new JButton("Play TicTacToe");
        trainAIButton = new JButton("Train AI");
        overviewJP.add(playTicTacToeButton);
        overviewJP.add(trainAIButton);

        add(overviewJP);



        playTicTacToeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                TTTGUI gui = new TTTGUI();
                gui.loadGUI();
                dispose();





            }
        });
        trainAIButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                TrainingGUI gui = new TrainingGUI();
                dispose();
                gui.loadGUI();



            }
        });
    }
    public void loadGUI(){

        //load the gui from a the gui form


    }

}

