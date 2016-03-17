package it226project5;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class daniel {

    public static void main(String[] args) {

        daniel obj = new daniel();
        obj.run();

    }

    public void run() {
        BufferedReader br = null;
        String readLine = "";

        String[] chooseProcess;
        String userInput = "";
        //temp array of strings
        ArrayList<String[]> gradeBook1;
        //Array list of csv gradebooks
        //first line of each gradebook file name
        //second line is column titles
        ArrayList<ArrayList<String[]>> gradeBooks = new ArrayList<>();
        Scanner menuSelection = new Scanner(System.in);
        //checks if first gradesbook
        boolean firstBook=true;
        int numberAdded;
        int numberAlready;
        int numberTotal=0;
        ArrayList<Integer> idLine = new ArrayList<>();
        while(!userInput.equalsIgnoreCase("E")){
            System.out
                    .println("What Operation would you like to perform on the given data? \n "
                            + "'A' : Add Data \n 'S' : Save Data \n 'G' : Return Grade of Specific Course \n 'E' : Quit the Program");

            userInput = menuSelection.nextLine();

            if (userInput.equalsIgnoreCase("a")) {
                boolean firstLine=true;
                numberAdded=0;
                numberAlready=0;
                gradeBook1 = new ArrayList<>();
                chooseProcess= new String[3];
                System.out.println("What is the course section?");
                chooseProcess[0] = menuSelection.nextLine();

                System.out.println("What semester is the course in?");
                chooseProcess[1] = menuSelection.nextLine();

                System.out.println("What year was the course in?");
                chooseProcess[2] = menuSelection.nextLine();

                String csvFile = chooseProcess[0] + "-" + chooseProcess[1]+ "-" + chooseProcess[2] + ".csv";
                gradeBook1.add(chooseProcess);

                try {

                    br = new BufferedReader(new FileReader(csvFile));
                    while ((readLine = br.readLine()) != null) {

                        // use comma as separator
                        String[] values = readLine.replaceAll("^\"", "").split(
                                "\"?(,|$)(?=(([^\"]*\"){2})*[^\"]*$) *\"?");
                        if(firstLine){
                            numberAdded--;
                            for(int m=0;m<values.length;m++){
                                if(values[m].contains("ID")){
                                    idLine.add(m);
                                    break;
                                }
                                if(values[m].contains("Id")){
                                    idLine.add(m);
                                    break;
                                }
                                if(values[m].contains("iD")){
                                    idLine.add(m);
                                    break;
                                }
                                if(values[m].contains("id")){
                                    idLine.add(m);
                                    break;
                                }
                            }
                            firstLine=false;
                        }
                        numberAdded++;
                        //checks for matching student ids
                        if(!firstBook){
                            for(int n=0;n<gradeBooks.size();n++){
                                ArrayList<String[]> gradeBook2=gradeBooks.get(n);
                                for(int b=1;b<gradeBooks.get(n).size()-1;b++){
                                    String[] values2= gradeBook2.get(b);
                                    String id=values2[idLine.get(n)];
                                    if(id.equals(values[idLine.get(idLine.size()-1)])){
                                        numberAlready++;
                                    }
                                }
                            }
                        }
                        gradeBook1.add(values);
                    }
                }
                catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
                finally {
                    numberTotal=numberTotal+numberAdded;
                    System.out.println("Number of Students added: "+numberAdded);
                    System.out.println("Total Number of Students in database: "+numberTotal);
                    System.out.println("Number of Students already in database: "+numberAlready);
                    //adds gradebook to master gradebooks arraylist
                    gradeBooks.add(new ArrayList(gradeBook1));
                    gradeBook1.clear();
                    firstBook=false;
                    if (br != null) {
                        try {
                            br.close();
                        }
                        catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }
}