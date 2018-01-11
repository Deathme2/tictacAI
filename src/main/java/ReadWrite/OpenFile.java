package ReadWrite;

import java.util.*;
import java.io.*;

public class OpenFile {

    public OpenFile() {

    }

    public ArrayList<String> getWords(String fileName) {

        // This will reference one line at a time
        String line = null;

        ArrayList<String> lineList = new ArrayList<String>();

        try {



            // FileReader reads text files in the default encoding.

            FileReader fileReader =
                new FileReader(fileName);

            // Always wrap FileReader in BufferedReader.

            BufferedReader bufferedReader =
                new BufferedReader(fileReader);

            while((line = bufferedReader.readLine()) != null) {

                lineList.add(line);

            }

            // Always close files.
            bufferedReader.close();
        }



        catch(FileNotFoundException ex) {
            System.out.println(
                "Unable to open file '" +
                fileName + "'");
        }
        catch(IOException ex) {
            System.out.println(
                "Error reading file '"
                + fileName + "'");
        }

        return lineList;

    }
    
}