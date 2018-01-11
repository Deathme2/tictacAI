package nerualNet;

import ReadWrite.*;
import java.text.*;
import java.util.*;

public class TestNeuralNetwork {

    private double tttInput[][];
    private double tttIdeal[][];
    private int hiddenLayerCount;
    private double learnRate;
    private double momentum;

    private int inputN;
    private int outputN;

    //get the initial values needed for the network
    public TestNeuralNetwork(double[][] trainInput, double[][] idealOutput, int hiddenLayer, double learnR, double momen) {

        tttInput = trainInput;
        tttIdeal = idealOutput;
        hiddenLayerCount = hiddenLayer;
        learnRate = learnR;
        momentum = momen;

        inputN = tttInput.length;
        outputN = tttIdeal.length;

    }

    public TestNeuralNetwork(int input, int hiddenLayer , int output){

        inputN = input;
        outputN = output;
        hiddenLayerCount = hiddenLayer;

    }

    public NeuralNet getNetwork() {
        return new NeuralNet(tttInput[0].length, hiddenLayerCount, tttIdeal[0].length, learnRate, momentum);
    }

    //run training once
    public void trainNetWork() {

        System.out.println("Learn:");

        NeuralNet network = new NeuralNet(tttInput[0].length, hiddenLayerCount, tttIdeal[0].length, learnRate, momentum);

        WriteFile wFile = new WriteFile(false);

        NumberFormat percentFormat = NumberFormat.getPercentInstance();
        percentFormat.setMinimumFractionDigits(4);


        network.reset();//reset the random values in the network

        //training code for 10000 repetitions
        for (int i = 0; i < 10000; i++) {
            for (int j = 0; j < tttInput.length; j++) {

                network.computeOutputs(tttInput[j]);
                network.calcError(tttIdeal[j]);
                network.learn();
            }
            System.out.println("Trial #" + i + ",Error:" +
                    percentFormat.format(network.getError(tttInput.length)));
        }
        //save the final outputs into a txt document;
        wFile.writeTOFile(Arrays.toString(network.getMatrix()), "src\\main\\resources\\NetOutputs\\Matrix.txt");

        wFile.writeTOFile(Arrays.toString(network.getThresholds()), "src\\main\\resources\\NetOutputs\\Threshold.txt");

    }

    //run the trained network
    public double[] callValue(int inputDesired[]) {

        NeuralNet network = new NeuralNet(inputN, hiddenLayerCount, outputN, learnRate, momentum);
        double out[];

        TrainedData td = new TrainedData();
        double[] a = td.parseMatrix();
        double[] b = td.parseThreshold();

        out = network.computeOutputs(inputDesired, a, b);

        return out;
    }
}
