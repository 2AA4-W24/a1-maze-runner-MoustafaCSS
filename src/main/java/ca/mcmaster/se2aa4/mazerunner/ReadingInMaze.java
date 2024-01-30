package ca.mcmaster.se2aa4.mazerunner;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ReadingInMaze {
       
    public static char[][] convertToArray(String file) throws IOException {  
        
        BufferedReader reader = new BufferedReader(new FileReader(file));

        int rows = 0;
        int columns = 0;
            
        String firstRow = reader.readLine();

        while (firstRow != null) {  //counting the number of lines
            rows++;
            if (firstRow.length() > columns) {
                    columns = firstRow.length();
                }
                firstRow = reader.readLine();
        }   

        reader.close();
            
        BufferedReader reader2 = new BufferedReader(new FileReader(file));
            
        char[][] mazeArray = new char[rows][columns]; 

        int row = 0;
        String secondRow = "";
            
        while ((secondRow = reader2.readLine()) != null) { 

            char[] characters = secondRow.toCharArray();
            for (int column = 0; column < columns; column++) {
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

    private static void printmazeArray(char[][] mazeArray) {

        for (int i = 0; i < mazeArray.length; i++) {
            for (int j = 0; j < mazeArray[i].length; j++) {
                System.out.print(mazeArray[i][j]);
            }
            System.out.println();
        }
    }
}    

        
            