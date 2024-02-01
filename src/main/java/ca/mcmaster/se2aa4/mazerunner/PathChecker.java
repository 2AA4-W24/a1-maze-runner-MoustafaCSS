package ca.mcmaster.se2aa4.mazerunner;

public class PathChecker {

    private enum Direction { left, right, up, down }
    private Direction currentDirection;
    private int currentRow;
    private int currentCol;
    private char[][] maze;

    public PathChecker(char[][] maze) {  
        int[] entryPoint = EntryandExit.findEntryPoint(maze);
        this.currentRow = entryPoint[0];
        this.currentCol = entryPoint[1];
        this.currentDirection = Direction.right;
        this.maze = maze;
    }

    private boolean isValidMove(int row, int col) {
        return ((row >= 0) && (row < maze.length) && (col >= 0) && (col < maze[0].length) && (maze[row][col] == ' '));
    }

    private void turnLeft() {
        switch (currentDirection) {
            case left:
                currentDirection = Direction.down;
                break;
            case right:
                currentDirection = Direction.up;
                break;    
            case up:
                currentDirection = Direction.left;
                break;
            case down:
                currentDirection = Direction.right;
                break;
        }
    }

    private void turnRight() {
        switch (currentDirection) {
            case left:
                currentDirection = Direction.up;
                break;
            case right:
                currentDirection = Direction.down;
                break;    
            case up:
                currentDirection = Direction.right;
                break;
            case down:
                currentDirection = Direction.left;
                break;
        }
    }

    private boolean moveForward() {
        int newRow = currentRow;
        int newCol = currentCol;

        switch (currentDirection) {
            case left -> newCol--;
            case right -> newCol++;
            case up -> newRow--;
            case down -> newRow++;
        }

        if (isValidMove(newRow, newCol)) {
            currentRow = newRow;
            currentCol = newCol;
            return true;
        }
        return false;
    }
    
    public boolean solveMaze(char[][] maze, String path) {
        for (char move : path.toCharArray()) {
            switch (move) {
                case 'F':
                    if (!moveForward()) return false;
                    break;
                case 'R':
                    turnRight();
                    break;
                case 'L':
                    turnLeft();
                    break;
            }
        }

        int[] exitPoint = EntryandExit.findExitPoint(maze);
        return currentRow == exitPoint[0] && currentCol == exitPoint[1];
    }
}
