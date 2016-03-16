package it226project5;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class CSVReader {

	public static void main(String[] args) {

		CSVReader obj = new CSVReader();
		obj.run();

	}

	public void run() {
		BufferedReader br = null;
		String readLine = "";

		String[] chooseProcess = new String[3];
		String userInput = "";
		ArrayList<String[][]> gradeBooks = new ArrayList<>();
		String[][] gradeBook2 = new String[0][];
		Scanner menuSelection = new Scanner(System.in);
		int i=-1;
		int k=-1;
		System.out
				.println("What Operation would you like to perform on the given data? \n "
						+ "'A' : Add Data \n 'S' : Save Data \n 'G' : Return Grade of Specific Course");

		userInput = menuSelection.nextLine();

		if (userInput.equalsIgnoreCase("a")) {
			System.out.println("What is the course section?");
			chooseProcess[0] = menuSelection.nextLine();

			System.out.println("What semester is the course in?");
			chooseProcess[1] = menuSelection.nextLine();

			System.out.println("What year was the course in?");
			chooseProcess[2] = menuSelection.nextLine();

		}
		String csvFile = "/Users/nicholasmessina/Desktop/IT 226/it226 data integration/src/it226project5/"
				+ chooseProcess[0]
				+ "-"
				+ chooseProcess[1]
				+ "-"
				+ chooseProcess[2] + ".csv";

		try {

			br = new BufferedReader(new FileReader(csvFile));
			while ((readLine = br.readLine()) != null) {

				// use comma as separator
				String[] values = readLine.replaceAll("^\"", "").split(
						"\"?(,|$)(?=(([^\"]*\"){2})*[^\"]*$) *\"?");
				
				gradeBooks.add(gradeBook2); 
				
				//System.out.println(values[15]);
				// if([0].contains("NAME")){
				// for(int i=0;i<values.length;i++){
				// System.out.print(values[i]+" ");
				// }
				// System.out.println("");
				// }
				//
				// if(values[0].equals("Y5w79x8w")){
				//
				// for(int i=0;i<values.length;i++){
				// System.out.print(values[25]);
				// }
				// / }
				i++;
//				String[][] temp = new String[i][];
//				for(int j=0;j<gradeBook2.length;j++){
//					temp[j]=gradeBook2[j];
//					gradeBook2=temp;
//				}
//				if(!values[0].equals("a"));
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			System.out.print(i);
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}

}