package Genetic;


import TTT_GAME.TrainingGUI;
import com.sun.org.apache.xerces.internal.util.SynchronizedSymbolTable;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Random;



public class GA {

    private int weightCount;
    private int poolSize;
    private int mutateRate;
    private int inputNet;
    private int outputNet;
    private int hiddenNet;
    private int gen = 0;
    private Random rnd = new Random();
    TrainingGUI gui = new TrainingGUI();

    private double[][] pool;


     //   getting the initial variables to set up the network

    public GA(int inputCount, int hiddenCount, int outputCount, int poolS, int mutate) {

        inputNet = inputCount;
        hiddenNet = hiddenCount;
        outputNet = outputCount;

        weightCount = (inputCount * hiddenCount) + (hiddenCount * outputCount);
        poolSize = poolS;
        pool = new double[poolSize][weightCount];
        mutateRate = mutate;

    }

//runs the training for the genetic algorithm
    public void runGA() {
        while (true) {

            double[][] tmpPool = new double[pool.length][weightCount];
            ArrayList<Integer> tmpPoolScores = new ArrayList<Integer>(pool.length);

            //runs and evaluates the fitness of each value in the pool
            for (int i = 0; i < pool.length; i++) {
                tmpPool[i] = pool[i];
                int ft = evaluateFitness(tmpPool[i], i);
                tmpPoolScores.add(i, ft);


            }

            // take the top 20% and merge them

            double[][] sortedPool = new double[pool.length][weightCount];

            int c = sortedPool.length-1;

            while (tmpPoolScores.size() != 0) {
                int best = findHeight(tmpPoolScores);
                sortedPool[c--] = tmpPool[best];
                tmpPoolScores.remove(best);
            }

            int j = sortedPool.length - (sortedPool.length * 20 / 100);

            //Merge the fittest
            for (int i = Math.round(sortedPool.length - (sortedPool.length * 20 / 100)); i < sortedPool.length; i += 3) {
                pool[j++] = splice(sortedPool[i], sortedPool[i + 1]);
                pool[j++] = sortedPool[i];

            }

            //Remove the unfit Numbers
            for (int i = 0; i < sortedPool.length - (sortedPool.length * 20 / 100); i++) {
                pool[i] = mutate(sortedPool[i]);
            }

            System.out.println("Gen " + gen +" Done!");
            gen++;
        }
    }
//finds the fittest variables
    private int findHeight( ArrayList<Integer> a) {
        int re = 0;
        int biggest = 0;
        for (int i = 0; i < a.size(); i++) {
            if (biggest <= a.get(i)) {
                biggest = a.get(i);
                re = i;
            }
        }
        return re;
    }
    //calculates the fitness
    public int evaluateFitness(double[] poolWeighths, int i) {

        FitnessCalc fit = new FitnessCalc();

        int fitScore = fit.applyWeights(poolWeighths, inputNet, hiddenNet, outputNet);

        if(fitScore >= 6){

            SavePool sP = new SavePool();
            sP.saveM(poolWeighths, i);

        }

        return fitScore;

    }

    //merge the fittest numbers together from bit level
    @SuppressWarnings("Since15") public double[] splice(double[] a, double[] b) {
        byte[] re;

       final BitSet bitsetA = BitSet.valueOf(doubleArrayToByteArray(a));
       final BitSet bitsetB = BitSet.valueOf(doubleArrayToByteArray(b));

        BitSet bitsetre = BitSet.valueOf(doubleArrayToByteArray(new double[a.length]));


        for(int i = 0; i < bitsetA.length() /2; i++)
        {
            bitsetre.set(i, bitsetA.get(i));
        }

        for(int i = bitsetA.length() /2 ; i < bitsetB.length(); i++)
        {
            bitsetre.set(i, bitsetB.get(i));
        }


        re = bitsetre.toByteArray();

        return byteArrayToDoubleArray(re);
    }
    //change values from double to byte
    private byte[] doubleArrayToByteArray(double[] a) {
        byte[] re = new byte[a.length * 8];
        int c = 0;

        for (int i = 0; i < a.length; i++) {
            byte[] tmp = toByteArray(a[i]);
            for (int j = 0; j < 8; j++) {
                re[c++] = tmp[j];
            }
        }

        return re;
    }
    //change from byte to double
    private double[] byteArrayToDoubleArray(byte[] a) {
        double[] re = new double[a.length / 8];

        for(int i = 0; i < a.length / 8; i++)
        {
            byte[] tmp = new byte[8];
            for(int j = 0; j < 8; j++)
            {
                tmp[j] = a[(i * 8) + j];
                re[i] = toDouble(tmp);
            }
        }

        return re;
    }
    //mutation allows for non stale number
    private double[] mutate(double[] a) {
        double[] re = new double[a.length];

        for (int i = 0; i < a.length; i++) {
            int mutate = rnd.nextInt(100);
            if (mutate <= mutateRate) {
                re[i] = rnd.nextDouble();
            } else {
                re[i] = a[i];
            }
        }

        return re;
    }
    // randomises an initial gene pool
    public void randomGenePool() {
        for (int c = 0; c < poolSize; c++) {
            double[] tmpArray = new double[weightCount];
            for (int i = 0; i < weightCount; i++) {
                tmpArray[i] = rnd.nextDouble();
            }

            pool[c] = tmpArray;
        }
    }

    private byte[] toByteArray(double value) {
        byte[] bytes = new byte[8];
        ByteBuffer.wrap(bytes).putDouble(value);
        return bytes;
    }

    private double toDouble(byte[] bytes) {
        return ByteBuffer.wrap(bytes).getDouble();
    }

}