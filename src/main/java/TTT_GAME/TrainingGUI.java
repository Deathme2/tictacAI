package TTT_GAME;

import Genetic.GA;
import nerualNet.SplitFile;
import nerualNet.TestNeuralNetwork;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TrainingGUI extends JFrame{
    private JTextArea outputJTA;
    private JButton trainGeneticAlgorythmButton;
    private JButton trainNerualNetworkButton;
    private JPanel overviewJP;

    public TrainingGUI() {
        trainGeneticAlgorythmButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            //The GA button action
                GA ga = new GA(9, 12, 9, 1000, 1);
                ga.randomGenePool();
                outputJTA.setText("Running");
                ga.runGA();


            }
        });
        trainNerualNetworkButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //the NN button action
                SplitFile split = new SplitFile();
                TestNeuralNetwork test = new TestNeuralNetwork(split.getInput(), split.getOutput(), 12, 0.07, 0.07);
                test.trainNetWork();
                System.out.println("Done");

            }
        });
    }
    public void loadGUI(){

        //load the gui from a the gui form
        JFrame gui = new JFrame("TrainingGUI");// Create the GUI object
        gui.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );//for close on exit button
        gui.setContentPane(new TrainingGUI().overviewJP);
        gui.setSize( 400, 300 ); // set frame size
        gui.pack();
        gui.setLocationRelativeTo(null);//makes the gui appear at the center of the screen
        gui.setVisible( true ); // display frame
    }

}
