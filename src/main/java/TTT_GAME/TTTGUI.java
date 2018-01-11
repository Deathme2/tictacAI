package TTT_GAME;

import Genetic.GAMove;
import nerualNet.NNMove;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class TTTGUI {

    public JPanel overveiwJP;
    private JPanel headerJP;
    private JPanel centerJP;
    private JButton r3c3JB;
    private JButton r3c1JB;
    private JButton r3c2JB;
    private JButton r2c2JB;
    private JButton r1c2JB;
    private JButton r1c3JB;
    private JButton r2c3JB;
    private JButton r2c1JB;
    private JButton r1c1JB;
    private JLabel headingJL;
    private JButton loadNNAIButton;
    private JButton loadGAAIButton;
    private JButton nnMoveJB;
    private JButton gaMoveJB;
    private JPanel gaMoveJP;
    private JPanel nnMoveJP;
    private JPanel loadJP;

    private int[] tempIn = new int[9];
    GameState state = new GameState();
    boolean playerTurn;

    public TTTGUI() {
        //add action listeners to all the button in the center JP
      for(int i = 0; i < centerJP.getComponentCount(); i++)
      {
          ((JButton) centerJP.getComponent(i)).addActionListener(new ActionListener() {
              public void actionPerformed(ActionEvent e) {
                  myEventHandler(e);
              }
          });
      }

        UpdateGUI();

        loadNNAIButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                nnMoveJP.setVisible(true);
                loadJP.setVisible(false);
            }
        });
        loadGAAIButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                gaMoveJP.setVisible(true);
                loadJP.setVisible(false);

            }
        });
        gaMoveJB.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

             int[][] board = state.getBoard();
                int c = 0;

                for(int i = 0; i < board.length; i++){
                    for(int j = 0; j < board[i].length; j++){

                        tempIn[c++] = board[i][j];

                    }
                }
            GAMove ga = new GAMove(9, 12, 9, tempIn );

                int[] outputCoord = ga.sortOutput();
                state.moveSet(outputCoord[0], outputCoord[1],playerTurn ? -1 : 1);
                UpdateGUI();
                state.checkWinners(playerTurn ? -1 : 1);
                UpdateGUI();
                playerTurn = !playerTurn;


            }
        });
        nnMoveJB.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                int[][] board = state.getBoard();
                int c = 0;

                for(int i = 0; i < board.length; i++){
                    for(int j = 0; j < board[i].length; j++){

                        tempIn[c++] = board[i][j];

                    }
                }
                NNMove nn = new NNMove(9, 12, 9, tempIn );

                int[] outputCoord = nn.sortOutput();
                state.moveSet(outputCoord[0], outputCoord[1],playerTurn ? -1 : 1);
                UpdateGUI();
                state.checkWinners(playerTurn ? -1 : 1);
                UpdateGUI();
                playerTurn = !playerTurn;

            }
        });
    }


        // get the player input from the button press
    public void myEventHandler(ActionEvent e) {
        JButton btn = ((JButton) e.getSource());
        int x, y;
        String c = btn.getName();
        x = Integer.parseInt(String.valueOf(c.charAt(1))) - 1;
        y = Integer.parseInt(String.valueOf(c.charAt(3))) - 1;

        state.moveSet(x, y, playerTurn ? -1 : 1);
        UpdateGUI();

        state.checkWinners(playerTurn ? -1 : 1);
        UpdateGUI();

        playerTurn = !playerTurn;
    }

    //update the GUI using the player/AI input
    public void UpdateGUI()
    {
        for(int i =0 ; i < 3; i++)
        {
            for(int j = 0; j < 3; j++){
                SetValue(i, j, state.getBoard()[i][j]);
            }
        }
    }
    //change the state of the button depending on the player
    public void SetValue(int ax, int ay, int state)
    {
        for(int i = 0; i < centerJP.getComponentCount(); i++) {
            JButton btn = ((JButton) centerJP.getComponent(i));

            int x, y;
            String c = btn.getName();

            x = Integer.parseInt(String.valueOf(c.charAt(1))) - 1;
            y = Integer.parseInt(String.valueOf(c.charAt(3))) - 1;


            if (x == ax && y == ay) {

                ImageIcon clr = new ImageIcon("src\\main\\resources\\Images\\OpenTTT.png");
                switch (state) {
                    case 0:

                        btn.setEnabled(true);
                        break;
                    case 1:

                        btn.setEnabled(false);
                         clr  = new ImageIcon("src\\main\\resources\\Images\\XTTT.png");
                        break;
                    case -1:

                         clr  = new ImageIcon("src\\main\\resources\\Images\\OTTT.png");
                        btn.setEnabled(false);
                        break;
                }


                btn.setIcon(clr);
                btn.setDisabledIcon(clr);
                btn.setText("");
                btn.setBackground(Color.gray);

                break;
            }

        }
    }

    public void loadGUI(){

        //load the gui from a the gui form
        JFrame gui = new JFrame("TTTGUI");// Create the GUI object
        gui.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );//for close on exit button
        gui.setContentPane(new TTTGUI().overveiwJP);
        gui.setSize( 400, 300 ); // set frame size
        gui.pack();
        gui.setLocationRelativeTo(null);//makes the gui appear at the center of the screen
        gui.setVisible( true ); // display frame
    }
}
