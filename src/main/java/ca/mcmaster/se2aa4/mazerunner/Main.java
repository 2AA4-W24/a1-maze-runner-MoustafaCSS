package ca.mcmaster.se2aa4.mazerunner;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.IOException;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;

public class Main {

    private static final Logger logger = LogManager.getLogger();

    public static void main(String[] args) {

        Options options = new Options();
        options.addOption("i", "input", true, "Input maze file");
        options.addOption("p", "path", true, "Input path");

        logger.info("** Starting Maze Runner");
        
        String file;
        String path = "";

        try {
            
            CommandLine commandLine = new DefaultParser().parse(options, args);
            
            if (commandLine.hasOption("i")){
                file = commandLine.getOptionValue("i");
            }
            else {
                throw new IllegalArgumentException();
            }

            if (commandLine.hasOption("p")){
                path = commandLine.getOptionValue("p");
            }
            else {
                throw new IllegalArgumentException();
            }

            logger.info("**** Reading the maze from file " , file);
            BufferedReader reader = new BufferedReader(new FileReader(file));

            char[][] mazeArray = ReadingInMaze.convertToArray(file);
            
            PathSolver pathSolver = new PathSolver(mazeArray);
            boolean isPathSolved = pathSolver.solveMaze(mazeArray, path);
            System.out.println("Does the path solve the maze? " + isPathSolved);


            /*String line;
            while ((line = reader.readLine()) != null) {
                for (int idx = 0; idx < line.length(); idx++) {
                    if (line.charAt(idx) == '#') {
                        logger.trace("WALL ");
                    } else if (line.charAt(idx) == ' ') {
                        logger.trace("PASS ");
                    }
                }
                logger.info(System.lineSeparator());
            }*/
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
}    