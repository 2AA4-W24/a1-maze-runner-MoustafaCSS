package ca.mcmaster.se2aa4.mazerunner;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ReadingInMaze {
       
    public static char[][] convertToArray(String file) throws IOException {  
        
        BufferedReader reader = new BufferedReader(new FileReader(file));

        int numberOfRows = 0;
        int numberOfColumns = 0;
            
        String firstRow = reader.readLine();
        numberOfColumns = firstRow.length();

        while (firstRow != null) {  //counting the number of rows
            numberOfRows++;
            firstRow = reader.readLine();
        }   

        reader.close();
            
        BufferedReader reader2 = new BufferedReader(new FileReader(file));
            
        char[][] mazeArray = new char[numberOfRows][numberOfColumns]; 

        int row = 0;
        String secondRow = "";
            
        while ((secondRow = reader2.readLine()) != null) { 

            char[] characters = secondRow.toCharArray();
            for (int column = 0; column < numberOfColumns; column++) {
                if (column < characters.length) {
                    mazeArray[row][column] = characters[column];
                }
                else {
                    mazeArray[row][column] = ' ';
                }
                
            }
            row++;
        }    

        reader2.close();

        printmazeArray(mazeArray);
    
        return mazeArray;

    }   

    public static void printmazeArray(char[][] mazeArray) {

        for (int i = 0; i < mazeArray.length; i++) {
            for (int j = 0; j < mazeArray[i].length; j++) {
                System.out.print(mazeArray[i][j]);
            }
            System.out.println();
        }
    }
}    

        
            
            