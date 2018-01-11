package ReadWrite;

 

import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.ArrayList;


public class WriteFile {
	
	private String filePath = "Default-Name";
	private Boolean appendFile = false;

    public WriteFile(String name) {
    		
    		filePath = name;
    		
    }
    
    public WriteFile(String name, boolean append ) {

		filePath = name;
		appendFile = append;


	}

	public WriteFile( boolean append ) {


		appendFile = append;


	}
    
    public void writeTOFile(String text){
    	
			 try{
			   
				FileWriter fwrite = new FileWriter(filePath, appendFile);
				
				PrintWriter print_line = new PrintWriter(fwrite);
				
				print_line.printf("%s" + "%n", text);//prints the string to the txt doc
				
				print_line.close();	//close the text file
					
			   }
			   
		 catch(IOException e){

					System.out.print(e);
			   	
			   }
			    	
			    }//end write to file

	public void writeTOFile(String text, String fileName){

		try{

			filePath = fileName;

			FileWriter fwrite = new FileWriter(filePath, appendFile);

			PrintWriter print_line = new PrintWriter(fwrite);

			print_line.printf("%s" + "%n", text);//prints the string to the txt doc

			print_line.close();	//close the text file

		}

		catch(IOException e){

			System.out.print(e);



		}

	}//end write to file
    
	public void writeTOFile(String text[]) {

		try {

			FileWriter fwrite = new FileWriter(filePath, appendFile);

			PrintWriter print_line = new PrintWriter(fwrite);

			for (int i = 0; i < text.length; i++) {

				print_line.printf("%s" + "%n", text[i]);//prints the string to the txt doc

			}

			print_line.close();    //close the text file

		}//end try

		catch (IOException e) {

			System.out.print(e);


		}
	}

	public void writeTOFile(int text[]) {

		try {

			FileWriter fwrite = new FileWriter(filePath, appendFile);

			PrintWriter print_line = new PrintWriter(fwrite);

			for (int i = 0; i < text.length; i++) {

				print_line.printf("%s" , text[i]);//prints the string to the txt doc

			}
            print_line.printf("%s", "\n");
			print_line.close();    //close the text file

		}//end try

		catch (IOException e) {

			System.out.print(e);


		}
	}

	public void writeTOFile(String text[], String fileName){

		try{

			filePath = fileName;

			FileWriter fwrite = new FileWriter(filePath, appendFile);

			PrintWriter print_line = new PrintWriter(fwrite);

			for(int i = 0; i < text.length; i++){

				print_line.printf("%s" + "%n", text[i]);//prints the string to the txt doc

			}

			print_line.close();	//close the text file

		}//end try

		catch(IOException e){

			System.out.print(e);

		}
    	
    }//end write to file
    
}//end