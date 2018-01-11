package Genetic;

import ReadWrite.WriteFile;

import java.util.Arrays;

public class SavePool {

    public SavePool(){


    }
    //saves values to a txt document
    public void saveM(double[] weights, int gen){

        WriteFile wFile = new WriteFile(false);
        wFile.writeTOFile(Arrays.toString(weights), "src\\main\\resources\\NetOutputs\\WeightGA.txt");

    }
}
