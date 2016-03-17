package it226project5;



import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class ian {

    public static void main(String[] args) {

        ian obj = new ian();
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
        boolean firstBook = true;
        int numberAdded;
        int numberAlready;
        int numberTotal = 0;
        ArrayList<Integer> idLine = new ArrayList<>();
        ArrayList<Integer> gradeLine = new ArrayList<>();
        while (!userInput.equalsIgnoreCase("E")) {
            System.out
                    .println("What Operation would you like to perform on the given data? \n "
                            + "'A' : Add Data \n 'S' : Save Data \n 'G' : Return Grade of Specific Course \n 'E' : Quit the Program");

            userInput = menuSelection.nextLine();

            if (userInput.equalsIgnoreCase("a")) {
                boolean firstLine = true;
                numberAdded = 0;
                numberAlready = 0;
                gradeBook1 = new ArrayList<>();
                chooseProcess = new String[3];
                System.out.println("What is the course section?");
                chooseProcess[0] = menuSelection.nextLine();

                System.out.println("What semester is the course in?");
                chooseProcess[1] = menuSelection.nextLine();

                System.out.println("What year was the course in?");
                chooseProcess[2] = menuSelection.nextLine();

                String csvFile = chooseProcess[0] + "-" + chooseProcess[1] + "-" + chooseProcess[2] + ".csv";
                gradeBook1.add(chooseProcess);

                try {

                    br = new BufferedReader(new FileReader(csvFile));
                    while ((readLine = br.readLine()) != null) {

                        // use comma as separator
                        String[] values = readLine.replaceAll("^\"", "").split(
                                "\"?(,|$)(?=(([^\"]*\"){2})*[^\"]*$) *\"?");
                        if (firstLine) {
                            numberAdded--;
                            for (int m = 0; m < values.length; m++) {
                                if (values[m].contains("ID")) {
                                    idLine.add(m);
                                    break;
                                }
                                if (values[m].contains("Id")) {
                                    idLine.add(m);
                                    break;
                                }
                                if (values[m].contains("iD")) {
                                    idLine.add(m);
                                    break;
                                }
                                if (values[m].contains("id")) {
                                    idLine.add(m);
                                    break;
                                }
                            }
                            firstLine = false;
                        }
                        numberAdded++;
                        //checks for matching student ids
                        if (!firstBook) {
                            for (int n = 0; n < gradeBooks.size(); n++) {
                                ArrayList<String[]> gradeBook2 = gradeBooks.get(n);
                                for (int b = 1; b < gradeBooks.get(n).size() - 1; b++) {
                                    String[] values2 = gradeBook2.get(b);
                                    String id = values2[idLine.get(n)];
                                    if (id.equals(values[idLine.get(idLine.size() - 1)])) {
                                        numberAlready++;
                                    }
                                }
                            }
                        }
                        gradeBook1.add(values);
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    numberTotal = numberTotal + numberAdded;
                    System.out.println("Number of Students added: " + numberAdded);
                    System.out.println("Total Number of Students in database: " + numberTotal);
                    System.out.println("Number of Students already in database: " + numberAlready);
                    //adds gradebook to master gradebooks arraylist
                    gradeBooks.add(new ArrayList(gradeBook1));
                    gradeBook1.clear();
                    firstBook = false;
                    if (br != null) {
                        try {
                            br.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
            if (userInput.equalsIgnoreCase("g")) {
                boolean firstLine = true;
                int[] grade = new int[5];
                int a = 0;
                int b = 0;
                int c = 0;
                int d = 0;
                int f = 0;
                chooseProcess = new String[3];
                System.out.println("What is the course section?");
                chooseProcess[0] = menuSelection.nextLine();

                System.out.println("What semester is the course in?");
                chooseProcess[1] = menuSelection.nextLine();

                System.out.println("What year was the course in?");
                chooseProcess[2] = menuSelection.nextLine();

                if (!chooseProcess[0].equalsIgnoreCase("none") && !chooseProcess[1].equalsIgnoreCase("none")
                        && !chooseProcess[2].equalsIgnoreCase("none")) { //grades for a course of a specific semester/year
                    String csvFile = chooseProcess[0] + "-" + chooseProcess[1] + "-" + chooseProcess[2] + ".csv";
                    try {

                        br = new BufferedReader(new FileReader(csvFile));
                        while ((readLine = br.readLine()) != null) {

                            // use comma as separator
                            String[] values = readLine.replaceAll("^\"", "").split(
                                    "\"?(,|$)(?=(([^\"]*\"){2})*[^\"]*$) *\"?");
                            //used to find column that the grade is located
                            if (firstLine) {
                                for (int m = 0; m < values.length; m++) {
                                    if (values[m].contains("GRADE")) {
                                        gradeLine.add(m);
                                        break;
                                    }
                                    if (values[m].contains("Grade")) {
                                        gradeLine.add(m);
                                        break;
                                    }
                                    if (values[m].contains("grade")) {
                                        gradeLine.add(m);
                                        break;
                                    }
                                }
                                firstLine = false;
                            }
                            if (values[gradeLine.get(gradeLine.size() - 1)].equalsIgnoreCase("a")) {
                                a++;
                            } else if (values[gradeLine.get(gradeLine.size() - 1)].equalsIgnoreCase("b")) {
                                b++;
                            } else if (values[gradeLine.get(gradeLine.size() - 1)].equalsIgnoreCase("c")) {
                                c++;
                            } else if (values[gradeLine.get(gradeLine.size() - 1)].equalsIgnoreCase("d")) {
                                d++;
                            } else if (values[gradeLine.get(gradeLine.size() - 1)].equalsIgnoreCase("f")) {
                                f++;
                            }
                        }
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    grade[0] = a;
                    grade[1] = b;
                    grade[2] = c;
                    grade[3] = d;
                    grade[4] = f;

                    System.out.println("A: " + grade[0]);
                    System.out.println("B: " + grade[1]);
                    System.out.println("C: " + grade[2]);
                    System.out.println("D: " + grade[3]);
                    System.out.println("F: " + grade[4]);
                } else if (chooseProcess[0].equalsIgnoreCase("none") && !chooseProcess[1].equalsIgnoreCase("none")
                        && !chooseProcess[2].equalsIgnoreCase("none")) { //grades for just semester/year
                    File file = new File(".");
                    ArrayList<File> fileAL = new ArrayList<>();
                    for (int j = 0; j < file.listFiles().length - 1; j++) {
                        fileAL.add(file.listFiles()[j]);
                    }
                    String filename;
                    ArrayList<String> matchingFiles = new ArrayList<>();
                    int i = 0;

                    while (!fileAL.isEmpty()) {
                        filename = fileAL.get(i).getName();
                        if ((filename).matches("\\d+" + "-" + chooseProcess[1] + "-" + chooseProcess[2] + "\\.csv")) {
                            matchingFiles.add(filename);
                            fileAL.remove(0);
                        } else {
                            fileAL.remove(0);
                        }
                    }
                    while (!matchingFiles.isEmpty()) {
                        try {
                            br = new BufferedReader(new FileReader(matchingFiles.get(0)));
                            while ((readLine = br.readLine()) != null) {

                                // use comma as separator
                                String[] values = readLine.replaceAll("^\"", "").split(
                                        "\"?(,|$)(?=(([^\"]*\"){2})*[^\"]*$) *\"?");
                                //used to find column that the grade is located
                                if (firstLine) {
                                    for (int m = 0; m < values.length; m++) {
                                        if (values[m].contains("GRADE")) {
                                            gradeLine.add(m);
                                            break;
                                        }
                                        if (values[m].contains("Grade")) {
                                            gradeLine.add(m);
                                            break;
                                        }
                                        if (values[m].contains("grade")) {
                                            gradeLine.add(m);
                                            break;
                                        }
                                    }
                                    firstLine = false;
                                }
                                if (values[gradeLine.get(gradeLine.size() - 1)].equalsIgnoreCase("a")) {
                                    a++;
                                } else if (values[gradeLine.get(gradeLine.size() - 1)].equalsIgnoreCase("b")) {
                                    b++;
                                } else if (values[gradeLine.get(gradeLine.size() - 1)].equalsIgnoreCase("c")) {
                                    c++;
                                } else if (values[gradeLine.get(gradeLine.size() - 1)].equalsIgnoreCase("d")) {
                                    d++;
                                } else if (values[gradeLine.get(gradeLine.size() - 1)].equalsIgnoreCase("f")) {
                                    f++;
                                }
                            }
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        matchingFiles.remove(0);
                        gradeLine.clear();
                        firstLine = true;
                    }
                    grade[0] = a;
                    grade[1] = b;
                    grade[2] = c;
                    grade[3] = d;
                    grade[4] = f;

                    System.out.println("A: " + grade[0]);
                    System.out.println("B: " + grade[1]);
                    System.out.println("C: " + grade[2]);
                    System.out.println("D: " + grade[3]);
                    System.out.println("F: " + grade[4]);
                } else if ((!chooseProcess[0].equalsIgnoreCase("none") && chooseProcess[1].equalsIgnoreCase("none")
                        && chooseProcess[2].equalsIgnoreCase("none")) || (!chooseProcess[0].equalsIgnoreCase("none")
                        && !chooseProcess[1].equalsIgnoreCase("none") && chooseProcess[2].equalsIgnoreCase("none"))
                        || (!chooseProcess[0].equalsIgnoreCase("none") && chooseProcess[1].equalsIgnoreCase("none")
                        && !chooseProcess[2].equalsIgnoreCase("none"))) { // grades for just courses
                    File file = new File(".");
                    ArrayList<File> fileAL = new ArrayList<>();
                    for (int j = 0; j < file.listFiles().length - 1; j++) {
                        fileAL.add(file.listFiles()[j]);
                    }
                    String filename;
                    ArrayList<String> matchingFiles = new ArrayList<>();
                    int i = 0;

                    while (!fileAL.isEmpty()) {
                        filename = fileAL.get(i).getName();
                        if ((filename).matches(chooseProcess[0] + "\\-\\w+\\-\\d{4}\\.csv")) {
                            matchingFiles.add(filename);
                            fileAL.remove(0);
                        } else {
                            fileAL.remove(0);
                        }
                    }
                    while (!matchingFiles.isEmpty()) {
                        try {
                            br = new BufferedReader(new FileReader(matchingFiles.get(0)));
                            while ((readLine = br.readLine()) != null) {

                                // use comma as separator
                                String[] values = readLine.replaceAll("^\"", "").split(
                                        "\"?(,|$)(?=(([^\"]*\"){2})*[^\"]*$) *\"?");
                                //used to find column that the grade is located
                                if (firstLine) {
                                    for (int m = 0; m < values.length; m++) {
                                        if (values[m].contains("GRADE")) {
                                            gradeLine.add(m);
                                            break;
                                        }
                                        if (values[m].contains("Grade")) {
                                            gradeLine.add(m);
                                            break;
                                        }
                                        if (values[m].contains("grade")) {
                                            gradeLine.add(m);
                                            break;
                                        }
                                    }
                                    firstLine = false;
                                }
                                if (values[gradeLine.get(gradeLine.size() - 1)].equalsIgnoreCase("a")) {
                                    a++;
                                } else if (values[gradeLine.get(gradeLine.size() - 1)].equalsIgnoreCase("b")) {
                                    b++;
                                } else if (values[gradeLine.get(gradeLine.size() - 1)].equalsIgnoreCase("c")) {
                                    c++;
                                } else if (values[gradeLine.get(gradeLine.size() - 1)].equalsIgnoreCase("d")) {
                                    d++;
                                } else if (values[gradeLine.get(gradeLine.size() - 1)].equalsIgnoreCase("f")) {
                                    f++;
                                }
                            }
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        matchingFiles.remove(0);
                        gradeLine.clear();
                        firstLine = true;
                    }
                    grade[0] = a;
                    grade[1] = b;
                    grade[2] = c;
                    grade[3] = d;
                    grade[4] = f;

                    System.out.println("A: " + grade[0]);
                    System.out.println("B: " + grade[1]);
                    System.out.println("C: " + grade[2]);
                    System.out.println("D: " + grade[3]);
                    System.out.println("F: " + grade[4]);
                }
            }
        	
    		if (userInput.equalsIgnoreCase("s")) {
    			System.out.println("What is the student's ID?");
    			String studentID = menuSelection.nextLine();
    			
    			for(int n=0;n<gradeBooks.size();n++){
    				ArrayList<String[]> gradeBook2=gradeBooks.get(n);
    				for(int b=1;b<gradeBooks.get(n).size()-1;b++){
    					String[] values2= gradeBook2.get(b);	
    					String id = values2[idLine.get(n)];
                        if (id.equals(studentID)){
                        	String[] fileName =gradeBook2.get(0);
                        	String[] columnTitles =gradeBook2.get(1);
                        	String[] studentInfo =gradeBook2.get(b);
                            for(int u=0;u<fileName.length;u++){
                            	System.out.print(fileName[u]+ " ");
                            }
                            System.out.println("");
                            for(int u=0;u<columnTitles.length;u++){
                            	System.out.print(columnTitles[u]+ " ");
                            }
                            System.out.println("");
                            for(int u=0;u<studentInfo.length;u++){
                            	System.out.print(studentInfo[u]+ " ");
                            }
                            System.out.println("");
                        }
    				
    				}
    			}
    		}
        }
    }
}