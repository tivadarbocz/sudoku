/**
 * Created by Tivadar Bocz on 2016.08.25..
 */
public class Solver {

    public static boolean solve(int[][] board) {
        //support array to keep track the status
        int[][] status = new int[board.length][board[0].length];
        //we set status to 2 in status array it means its fixed
        for (int i = 0; i < 9; ++i) {
            for (int j = 0; j < 9; ++j) {
                status[i][j] = board[i][j] > 0 ? 2 : 0;
            }
        }
        return solve(board, status, 0, 0);
    }

    public static void print(int[][] board) {
        //System.out.println("- - - - - - - - -");
        for (int i = 0; i < 9; ++i) {
            for (int j = 0; j < 9; ++j) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    //searching method
    // x,y is the interested coordinate
    public static boolean solve(int[][] board, int[][] status, int x, int y) {
        //
        //we repeat this from 0,0 until 8,8 while board is invalid
        if (x == 9) {
            //check all values(81) are set
            int count = 0;
            for (int i = 0; i < 9; ++i) {
                for (int j = 0; j < 9; ++j) {
                    count += status[i][j] > 0 ? 1 : 0;
                }
            }
            if (count == 81) {//all set
                return true;//find a possible solution
            } else {
                return false;//failed
            }

        }
        if (status[x][y] >= 1) {//current position already has been set
            //step next value
            int nextX = x;
            int nextY = y + 1;
            if (nextY == 9) {//last element in a row
                nextX = x + 1;
                nextY = 0;
            }
            //recursive call next pos
            return solve(board, status, nextX, nextY);
        } else {
            //check 3-3 box to decide all possible values
            //create 9 length array to check what values have been used in nearby position thus cannot be used
            boolean[] used = new boolean[9];
            //check
            for (int i = 0; i < 9; ++i) {
                if (status[x][i] >= 1) {
                    used[board[x][i] - 1] = true;
                }
            }
            //check column
            for (int i = 0; i < 9; ++i) {
                if (status[i][y] >= 1) {
                    used[board[i][y] - 1] = true;
                }
            }
            //now we check 3x3 associated box
            //rows star from 0,3,6
            //columns start from 0,3,6
            for (int i = x - (x % 3); i < x - (x % 3) + 3; ++i) {
                for (int j = y - (y % 3); j < y - (y % 3) + 3; ++j) {
                    if (status[i][j] >= 1) {
                        used[board[i][j] - 1] = true;
                    }
                }
            }

            //after check all row=column/3x3 box we try to assign each possible value to current position
            //use status array is for the further recovery
            for (int i = 0; i < used.length; ++i) {
                if (!used[i]) {//notice only those unused value can be set here
                    status[x][y] = 1;//1 as we set status, diferent from 0 - nonset and 2 - pre fixed
                    board[x][y] = i + 1;
                    //we repeat the index increasing operation
                    int nextX = x;
                    int nextY = y + 1;
                    if (nextY == 9) {//last element in a row
                        nextX = x + 1;
                        nextY = 0;
                    }
                    if (solve(board, status, nextX, nextY)) {
                        return true;
                    }//now it mean the setting

                    //now t means the setting failed so we should reverse the setting to try next value
                    for (int m = 0; m < 9; ++m) {
                        for (int n = 0; n < 9; ++n) {
                            //only reverse set those values behind current (x,y) position
                            if (m > x || (m == x && n >= y)) {
                                if (status[m][n] == 1) {
                                    status[m][n] = 0;
                                    board[m][n] = 0;
                                }
                            }
                        }
                    }
                }
            }
        }
        //return default value
        return false;
    }
}
