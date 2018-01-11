package nerualNet;

import ReadWrite.OpenFile;

import java.util.ArrayList;


public class NNMove {

    private int inputN;
    private int hiddenN;
    private int outputN;
    private int[] boardState;

    public NNMove(int input, int hidden, int output, int[] boardIn){

        inputN = input;
        hiddenN = hidden;
        outputN = output;
        boardState = boardIn;


    }
    public int[] sortOutput(){
        //getting the list of valid outputs
        ArrayList<Double> validOutputs = new ArrayList<Double>();
        ArrayList<Integer> validOutputsIndex = new ArrayList<Integer>();

        //getting the AI's move
        double[] tempOut = runMove();


        for(int q = 0; q < boardState.length; q++){

            if(boardState[q] == 0){

                validOutputs.add(tempOut[q] );
                validOutputsIndex.add(q );

            }
        }
        //get the highest value of the valid outputs

        double max = validOutputs.get(0);
        int index = validOutputsIndex.get(0);

        for (int i = 0; i < validOutputs.size(); i++) {
            if (validOutputs.get(i) > max) {
                max = validOutputs.get(i);
                index = validOutputsIndex.get(i);
            }
        }
        //geting the single highest move
        int[] coord = getMove(index);

        return coord;

    }
    //gets the output values from the input and the weights
    private double[] runMove(){

        TestNeuralNetwork net = new TestNeuralNetwork(inputN, hiddenN, outputN);

        double[] output =  net.callValue(boardState);

        return output;

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


}
