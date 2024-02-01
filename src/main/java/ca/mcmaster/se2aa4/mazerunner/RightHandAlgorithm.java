package ca.mcmaster.se2aa4.mazerunner;

public class RightHandAlgorithm implements MazeSolver {

    private enum Direction { left, right, up, down }
    private Direction currentDirection;
    private int currentRow;
    private int currentCol;
    private final char[][] maze;
    private final StringBuilder path;
    private static final int MAX_STEPS = 10000000;

    public RightHandAlgorithm(char[][] maze) {
        this.maze = maze;
        int[] entryPoint = EntryandExit.findEntryPoint(maze);
        this.currentRow = entryPoint[0];
        this.currentCol = entryPoint[1];
        this.currentDirection = Direction.right;
        this.path = new StringBuilder();
    }

    private boolean isValidMove(int row, int col) {
        return row >= 0 && row < maze.length && col >= 0 && col < maze[0].length && maze[row][col] == ' ';
    }

    private boolean canMove(Direction direction) {
        int newRow = currentRow;
        int newCol = currentCol;

        switch (direction) {
            case left -> newCol--;
            case right -> newCol++;
            case up -> newRow--;
            case down -> newRow++;
        }

        return isValidMove(newRow, newCol);
    }

    private void moveForward() {
        if (canMove(currentDirection)) {
            switch (currentDirection) {
                case up -> currentRow--;
                case down -> currentRow++;
                case left -> currentCol--;
                case right -> currentCol++;
            }
            path.append('F');
        }
    }

    private void turnRight() {
        currentDirection = switch (currentDirection) {
            case up -> Direction.right;
            case right -> Direction.down;
            case down -> Direction.left;
            case left -> Direction.up;
        };
        path.append('R');
    }

    private void turnLeft() {
        currentDirection = switch (currentDirection) {
            case up -> Direction.left;
            case left -> Direction.down;
            case down -> Direction.right;
            case right -> Direction.up;
        };
        path.append('L');
    }

    private boolean isExit() {
        int[] exitPoint = EntryandExit.findExitPoint(maze);
        return currentRow == exitPoint[0] && currentCol == exitPoint[1];
    }

    @Override
    public boolean solveMaze(char[][] maze) {
        int steps = 0;

        while (!isExit() && steps < MAX_STEPS) {
            if (canMove(getRightDirection(currentDirection))) {
                turnRight();
                moveForward();
            } else if (canMove(currentDirection)) {
                moveForward();
            } else {
                turnLeft();
            }

            steps++;
        }

        if (steps >= MAX_STEPS) {
            System.out.println("Reached maximum step limit. The maze may be too complex or too large.");
            return false;
        }

        return isExit();
    }

    private Direction getRightDirection(Direction direction) {
        return switch (direction) {
            case up -> Direction.right;
            case right -> Direction.down;
            case down -> Direction.left;
            case left -> Direction.up;
        };
    }

    public String getPath() {
        return path.toString();
    }
}
