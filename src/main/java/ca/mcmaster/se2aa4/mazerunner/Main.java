package ca.mcmaster.se2aa4.mazerunner;

import java.io.BufferedReader;
import java.io.FileReader;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;

public class Main {

    private static final Logger logger = LogManager.getLogger();

    public static void main(String[] args) {

        Options options = new Options();
        options.addOption("i", "input", true, "Input maze file");
        options.addOption("p", "path", true, "Input path");
        options.addOption("a", "algorithm", true, "Maze solving algorithm");  //not for assignment 1
        
        logger.info("** Starting Maze Runner");
        
        String file;
        String path = "";
        String userPath = ""; 
        String algorithm = "righthand"; //default algorithm (not for assignment 1)

        try {
            
            CommandLine commandLine = new DefaultParser().parse(options, args);
            
            if (commandLine.hasOption("i")){
                file = commandLine.getOptionValue("i");
            } else {
                throw new IllegalArgumentException();
            }


            if (commandLine.hasOption("p")){
                userPath = commandLine.getOptionValue("p");
            }

            logger.info("**** Reading the maze from file " + file);
            BufferedReader reader = new BufferedReader(new FileReader(file));
            
            char[][] maze = ReadingInMaze.convertToArray(file);

            logger.info("**** Computing path");
            
            MazeSolver solver = null;

            switch (algorithm) { //not for this assignment
                case "righthand":
                    solver = new RightHandAlgorithm(maze);
                    break;
                
                default:
                    break; //unknown algorithm
            }
            
            boolean isPathSolved = solver.solveMaze(maze);

            
            if (solver instanceof RightHandAlgorithm) {
                RightHandAlgorithm rightHandSolver = (RightHandAlgorithm) solver;
                String nonFactorizedPath = rightHandSolver.getPath();
                String factorizedPath = rightHandSolver.factorizePath(nonFactorizedPath);
                System.out.println("The correct path to solve this maze using the righthandalgorithm in factorized form is: " + factorizedPath);
            }

        

            if (commandLine.hasOption("p")){
                userPath = commandLine.getOptionValue("p");
                String expandedUserPath = expandPath(userPath);
                
                PathChecker pathChecker = new PathChecker(maze);
                boolean isUserPathValid = pathChecker.solveMaze(maze, expandedUserPath);
                System.out.println("Does the user's entered path solve the maze? " + isUserPathValid);
            }



        } 
        catch(IllegalArgumentException e) {
            logger.error("/!\\ An error has occured /!\\");
        } 
        catch(Exception e) {
            logger.error("/!\\ An error has occured /!\\");
        }

        logger.info("**** Computing path");
        logger.info("PATH NOT COMPUTED");
        logger.info("** End of MazeRunner");

    }

    public static String expandPath(String path) {
        StringBuilder expandedPath = new StringBuilder();
        int count = 0; 
        for (int i = 0; i < path.length(); i++) {
            char character = path.charAt(i);
            if (Character.isDigit(character)) {
                count = count * 10 + (character - '0'); 
            } else {
                int repetitions = Math.max(count, 1); 
                for (int j = 0; j < repetitions; j++) {
                    expandedPath.append(character);
                }
                count = 0; 
            }
        }
        return expandedPath.toString();
    }
}    
    