/**
 * Created by Tivadar Bocz on 2016.08.25..
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("Starting board" + System.lineSeparator());
        int[][] board = {
                {0,0,8,0,7,9,0,0,0},
                {6,0,0,0,5,4,0,3,0},
                {4,0,0,0,0,0,0,6,0},
                {0,7,0,8,0,0,0,0,3},
                {1,0,0,0,0,0,0,0,8},
                {9,0,0,0,0,1,0,4,0},
                {0,2,0,0,0,0,0,0,7},
                {0,1,0,9,8,0,0,0,6},
                {0,0,0,3,1,0,9,0,0}
        };
        Solver.print(board);
        System.out.println(System.lineSeparator());
        long startTime = System.currentTimeMillis();
        if (Solver.solve(board)) {
            long stopTime = System.currentTimeMillis();
            long elapsedTime = stopTime - startTime;
            System.out.println("Found a solution");
            System.out.println("Solving time in MS: " + elapsedTime + System.lineSeparator());
            Solver.print(board);
            System.out.println(System.lineSeparator());
        }
        else{
            System.out.println("No solution is found" + System.lineSeparator());
        }
    }
}
