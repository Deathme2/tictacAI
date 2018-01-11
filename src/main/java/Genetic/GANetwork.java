package Genetic;

public class GANetwork {

    private int inputsNet;
    private int hiddenNet;
    private int outputNet;

    private double[] fire;



    //  getting the initial variables to set up the network
    public GANetwork(int inputs, int hidden, int outputs){

        inputsNet = inputs;
        hiddenNet = hidden;
        outputNet = outputs;

    }

    //get the outputs from an input and a weight
    public double[] computeOutputs(int input[], double weights[]) {



        int i, j;
        int hiddenIndex = inputsNet;
        int outIndex = inputsNet + hiddenNet;
        int neuronCount = inputsNet + hiddenNet + outputNet;
        fire = new double[neuronCount];

        for (i = 0; i < inputsNet; i++) {
            fire[i] = input[i];
        }

        // first layer
        int inx = 0;

        for (i = hiddenIndex; i < outIndex; i++) {
            //double sum = thresholds[i];
            double sum = 0;
            for (j = 0; j < inputsNet; j++) {
                sum += fire[j] * weights[inx++];
            }
            fire[i] = threshold(sum);
        }

        // hidden layer

        double result[] = new double[outputNet];

        for (i = outIndex; i < neuronCount; i++) {
            //double sum = thresholds[i];
            double sum = 0;

            for (j = hiddenIndex; j < outIndex; j++) {
                sum += fire[j] * weights[inx++];
            }
            fire[i] = threshold(sum);
            result[i-outIndex] = fire[i];
        }



        return result;
    }


    //The threshold method.
    public double threshold(double sum) {

        return 1.0 / (1 + Math.exp(-1.0 * sum));
    }



}
