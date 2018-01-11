package nerualNet;

import ReadWrite.OpenFile;

import java.util.ArrayList;


public class SplitFile {

    double[][] input;
    double[][] output;

    public SplitFile() {

        int i, j = 0, k = 0;

        OpenFile fileOpen = new OpenFile();
        ArrayList<String> inputList;
        inputList = fileOpen.getWords("src\\main\\resources\\NetInputs\\TrainingInput.txt");

        String inputS[] = new String[(inputList.size() / 2)];
        String outputS[] = new String[(inputList.size() / 2)];

        String sInput[][] = new String[(inputList.size() / 2)][9];
        String sOutput[][] = new String[(inputList.size() / 2)][9];

        double[][] input = new double[(inputList.size() / 2)][9];
        double[][] output = new double[(inputList.size() / 2)][9];

        //split inputs and outputs

        for (i = 0; i < inputList.size(); i++) {


            if (inputList.get(i).charAt(0) == 'i') {

                inputS[j] = inputList.get(i);
                j++;
            } else if (inputList.get(i).charAt(0) == 'o') {

                outputS[k] = inputList.get(i);
                k++;
            }
        }

        //change inputs so only numbers are left
        for (i = 0; i < inputS.length; i++) {

            inputS[i] = inputS[i].replace('i',' ');
            inputS[i] = inputS[i].replace('[',' ');
            inputS[i] = inputS[i].replace(']',' ');

            String[] a = inputS[i].split(",");
            sInput[i] = a;

        }
        //change outputs so only numbers are left
        for (i = 0; i < outputS.length; i++) {

            outputS[i] = outputS[i].replace('o',' ');
            outputS[i] = outputS[i].replace('[',' ');
            outputS[i] = outputS[i].replace(']',' ');

            String[] a = outputS[i].split(",");
            sOutput[i] = a;

        }

        //change the Strings into doubles
        for(i=0; i<sInput.length; i++) {
            for(j=0; j < sInput[i].length; j++) {
                input[i][j]= Double.parseDouble(sInput[i][j]);
            }
        }

        for( i=0; i<sOutput.length; i++) {
            for(j=0; j < sOutput[i].length; j++) {
                output[i][j]= Double.parseDouble(sOutput[i][j]);
            }
        }
        setInput(input);
        setOutput(output);

    }

    public double[][] getInput(){

        return input;
    }

    private void setInput(double[][] s){

        input = s;

    }

    public double[][] getOutput(){

        return output;

    }

    private void setOutput(double[][] s){

        output = s;

    }
}
