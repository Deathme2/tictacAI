package nerualNet;

import ReadWrite.OpenFile;

import java.util.ArrayList;

public class TrainedData {

    public TrainedData(){

    }
    // get the trained values from the matrix file
    public double[] parseMatrix(){

        OpenFile fileOpen = new OpenFile();
        ArrayList<String> m =  fileOpen.getWords("src\\main\\resources\\NetOutputs\\Matrix.txt");
        String[] m2 = new String[m.size()];
        String[] m3 = new String[m.size()];


        for(int i = 0; i < m.size(); i++){

            m2[i] = m.get(i);

        }
        //remove the unwanted characters
        for (int i = 0; i < m2.length; i++) {


            m2[i] = m2[i].replace('[',' ');
            m2[i] = m2[i].replace(']',' ');

            m3 = m2[i].split(",");

        }
             double[] matrix = new double[m3.length];

        //change the Strings into doubles
        for(int i=0; i<m3.length; i++) {
            matrix[i]= Double.parseDouble(m3[i]);
        }



        return matrix;
    }
    //get the threshold values
    public double[] parseThreshold(){

        OpenFile fileOpen = new OpenFile();
        ArrayList<String> m =  fileOpen.getWords("src\\main\\resources\\NetOutputs\\Threshold.txt");
        String[] m2 = new String[m.size()];
        String[] m3 = new String[m.size()];


        for(int i = 0; i < m.size(); i++){

            m2[i] = m.get(i);

        }
        //remove the unwanted characters
        for (int i = 0; i < m2.length; i++) {


            m2[i] = m2[i].replace('[',' ');
            m2[i] = m2[i].replace(']',' ');

            m3 = m2[i].split(",");

        }
        double[] threshold = new double[m3.length];

        //change the Strings into doubles
        for(int i=0; i<m3.length; i++) {
            threshold[i]= Double.parseDouble(m3[i]);
        }



        return threshold;
    }

}
