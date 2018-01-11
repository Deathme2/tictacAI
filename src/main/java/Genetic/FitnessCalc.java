package Genetic;


import ReadWrite.WriteFile;
import TTT_GAME.GameState;
import nerualNet.NNMove;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;


public class FitnessCalc {

    private int[][] board;

    private int[] tempIn = new int[9];

    private double[] weightSize;

    private int fitnessScore;

    private boolean gamerun = true;

    private int playerNum = -1;

    private GameState gameTTT = new GameState();

    Random rnd = new Random();



    public FitnessCalc(){



    }

    public int[] boardStateToInputs(){
        //get the current board and convert it from a matrix to an array

        int c=0;
        board = gameTTT.getBoard();

        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[i].length; j++){

                tempIn[c++] = board[i][j];

            }
        }

        return tempIn;

    }
    //the code that trains the AI
    public int applyWeights(double[] weightsInput, int inputCount, int hiddenCount, int outputCount){


        weightSize = weightsInput;
        if(weightSize.length < (inputCount* hiddenCount) + (outputCount * hiddenCount)){//removes and weights that have errors

            System.out.println("Invalid");
            return 0;

        }

        gameTTT.resetBoard();//resets the game board

        while(gamerun = true){

            int[] inputs = boardStateToInputs();


            //apply the weights to the formula

            GANetwork nn = new GANetwork(inputCount,hiddenCount, outputCount);

            nn.computeOutputs(inputs, weightSize);

            double[] output = nn.computeOutputs(inputs, weightSize);




            //get the highest number of the outputs

                double max = output[0];
                int index = 0;
                for (int i = 0; i < output.length; i++) {
                    if (output[i] > max) {
                        max = output[i];
                        index = i;
                    }
                }


            //send the index for the highest move to be converted into a coordenate

            //if the move is an illegal move return a bad score
            int[] move = getMove(index);

            if(inputs[index] != 0 ){

                fitnessScore = 0;
                break;

            }

            if(move[1] >= 3 || move[0] >=3 ){

                System.out.println(move[0] + " "+ move[1]);

            }
            //send the move
            gameTTT.moveSet(move[0], move[1], playerNum);
            fitnessScore = fitnessScore + 1; //add a fitness point for completing a move

            // get the board state to check for a victory or draw
           int[][] tempBoard = gameTTT.getBoard();

           boolean victory = gameTTT.checkForWin(tempBoard);
           boolean draw = gameTTT.checkForDraw(tempBoard);

            if( victory){//if victory add give a score

                gamerun = false;
                fitnessScore = fitnessScore + 7;
                System.out.println(("AI") + " has WON");
                break;
            }
            else if(draw){ // if a draw give a score

                gamerun = false;
                fitnessScore = fitnessScore + 5;
                System.out.println("Draw");
                break;

            }
            else{


                //Opponents move

                /*
                random move each time
                int h = 0;
                int m = 0;
                ArrayList<Integer> movesAvailable = new ArrayList<Integer>();
                //get the list of avaiable moves
                for(int i = 0; i < tempBoard.length; i++) {
                    for (int j = 0; j < tempBoard[i].length; j++) {

                        if (tempBoard[i][j] == 0) {

                            movesAvailable.add(h++, m);

                        }
                        m++;
                    }
                }
                //get a random move
                Collections.shuffle(movesAvailable);

                int[] opMove = getMove(movesAvailable.get(0));
                //set the random move
                if(opMove[1] >= 3 || opMove[0] >=3 ){

                    System.out.println(opMove[0] + " "+ opMove[1]);

                }

                gameTTT.moveSet(opMove[0], opMove[1], 1);
                */

                //train from the neural network
                NNMove nnMove = new NNMove(9, 12, 9, boardStateToInputs() );

                int[] outputCoord = nnMove.sortOutput();
                gameTTT.moveSet(outputCoord[0], outputCoord[1], 1);

                tempBoard = gameTTT.getBoard();
                //check to see if opponent won
                 boolean enVictory = gameTTT.checkForWin(tempBoard);

                if(enVictory){//if opponent winns return bad score

                    gamerun = false;
                    System.out.println(("NME") + " has WON");
                    fitnessScore = 0;
                    break;

                }

            }

        }

        saveBoard();//save the board

        return fitnessScore;

    }

    //connvert an int into a coordinate
    private int[] getMove(int index){

        int row = 0;
        int col = 0;

        if(index < 3){

            row = 0;
            col = index;

        }
        else if(index < 6){

            row = 1;
            col = (index - 3);

        }
        else if(index < 9){

            row = 2;
            col = (index - 6);

        }
        else{

            System.out.println(index);
        }

        int[] move = new int[]{row, col};

        return move;

    }
    private void saveBoard(){

        //save the board into a txt document
        int[] board = boardStateToInputs();
        WriteFile fileW = new WriteFile("src\\main\\resources\\NetOutputs\\outputs.txt", true);
        fileW.writeTOFile( board );

    }

}
