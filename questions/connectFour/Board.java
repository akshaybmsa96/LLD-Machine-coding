package questions.connectFour;

public class Board {
    // Standard Connect Four board: 6 rows, 7 columns
    // 0 = Empty, 1 = Player 1, 2 = Player 2
    private static final int WIN_LENGTH = 4;
    private static final int[][] DIRECTIONS = {{0, 1}, {1, 0}, {1, 1}, {1, -1}};

    private final int ROWS = 6;
    private final int COLS = 7;
    private final int[][] grid = new int[ROWS][COLS];

    /** @return row where the disc landed, or -1 if the move was invalid */
    public int dropDisc(int col, int discId) {
        if (col < 0 || col >= COLS) {
            System.out.println("Invalid column choice.");
            return -1;
        }

        if (grid[0][col] != 0) {
            System.out.println("Invalid move, Column already filled");
            return -1;
        }

        for (int row = ROWS - 1; row >= 0; row--) {
            if (grid[row][col] == 0) {
                grid[row][col] = discId;
                return row;
            }
        }
        return -1;
    }

    public boolean checkIfBoardFilled() {
        // If any top cell in a column is empty, the board is not full
        for (int c = 0; c < COLS; c++) {
            if (grid[0][c] == 0) {
                return false;
            }
        }
        return true;
    }

    public boolean checkIfWon(int row, int col, int discId) {
        for (int[] direction : DIRECTIONS) {
            int count = 1;
            count += countInDirection(row, col, direction[0], direction[1], discId);
            count += countInDirection(row, col, -direction[0], -direction[1], discId);
            if (count >= WIN_LENGTH) {
                return true;
            }
        }
        return false;
    }

    private int countInDirection(int row, int col, int dRow, int dCol, int discId) {
        int count = 0;
        int r = row + dRow;
        int c = col + dCol;
        while (r >= 0 && r < ROWS && c >= 0 && c < COLS && grid[r][c] == discId) {
            count++;
            r += dRow;
            c += dCol;
        }
        return count;
    }

    public void printBoard() {
        System.out.println("\n 0 1 2 3 4 5 6"); // Column headers
        System.out.println("---------------");
        for (int r = 0; r < ROWS; r++) {
            System.out.print("|");
            for (int c = 0; c < COLS; c++) {
                if (grid[r][c] == 0) {
                    System.out.print(". "); // Period for empty slots
                } else {
                    System.out.print(grid[r][c] + " ");
                }
            }
            System.out.println("|");
        }
        System.out.println("---------------");
    }
}