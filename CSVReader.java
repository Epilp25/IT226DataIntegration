package it226project5;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class CSVReader {

  public static void main(String[] args) {

	CSVReader obj = new CSVReader();
	obj.run();

  }

  public void run() {

	String csvFile = "/Users/nicholasmessina/Desktop/IT 226/it226 data integration/src/it226project5/380-fall-2002.csv";
	BufferedReader br = null;
	String line = "";
	
	try {

		br = new BufferedReader(new FileReader(csvFile));
		while ((line = br.readLine()) != null) {

		        // use comma as separator
//			String[] country = line.split(",");
			String[] values = line.replaceAll("^\"", "").split("\"?(,|$)(?=(([^\"]*\"){2})*[^\"]*$) *\"?");
			System.out.println(values[25]);
			//System.out.println(country[0]);
//			if(country[0].contains("NAME")){
//				for(int i=0;i<country.length;i++){
//					System.out.print(country[i]+" ");
//				}
//				System.out.println("");
//			}
//			
//			if(country[0].equals("Y5w79x8w")){
//				
//			for(int i=0;i<country.length;i++){
//				System.out.print(country[25]);
//			}
///			}
		}

	} catch (FileNotFoundException e) {
		e.printStackTrace();
	} catch (IOException e) {
		e.printStackTrace();
	} finally {
		if (br != null) {
			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	System.out.println("Done");
  }

}