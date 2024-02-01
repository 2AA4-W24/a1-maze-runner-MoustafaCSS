package ca.mcmaster.se2aa4.mazerunner;

public class EntryandExit {

    public static int[] findEntryPoint(char[][] maze) {  
        for (int i = 0; i < maze.length; i++) {
            if (maze[i][0] == ' ') {
                return new int[]{i,0};
            }
        }
        return null;
    }

    public static int[] findExitPoint(char[][] maze) {  
        for (int i = 0; i < maze.length; i++) {
            if (maze[i][maze[0].length - 1] == ' ') {
                return new int[]{i,maze[0].length - 1};
            }
        }
        return null;
    }

}