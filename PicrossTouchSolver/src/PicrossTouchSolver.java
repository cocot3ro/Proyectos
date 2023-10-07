import java.util.Arrays;

public class PicrossTouchSolver {
    private final int[][] colConstraints;
    private final int[][] rowConstraints;
    private final boolean[][] board;
    private final boolean[][] unmodifiables;
    private int rowSize;
    private int colSize;

    private PicrossTouchSolver(int[][] colConstraints, int[][] rowConstraints) {
        this.colConstraints = colConstraints;
        this.rowConstraints = rowConstraints;
        this.board = new boolean[rowConstraints.length][colConstraints.length];
        this.unmodifiables = new boolean[rowConstraints.length][colConstraints.length];
        this.rowSize = colConstraints.length;
        this.colSize = rowConstraints.length;

        markUnmodifiables();
        computeSizes();
        putConstants();

        //recursive(0);
    }

    private void markUnmodifiables() {
        for (int i = 0; i < colConstraints.length; i++) {
            if (colConstraints[i][0] == 0) {
                for (int j = 0; j < colSize; j++) {
                    unmodifiables[j][i] = true;
                }
            }
        }

        for (int i = 0; i < rowConstraints.length; i++) {
            if (rowConstraints[i][0] == 0) {
                for (int j = 0; j < rowSize; j++) {
                    unmodifiables[i][j] = true;
                }
            }
        }
    }

    private void computeSizes() {
        for (int[] colConstraint : colConstraints) {
            if (colConstraint[0] == 0) {
                this.rowSize--;
            } else {
                break;
            }
        }

        for (int i = 0; i < colConstraints.length; i++) {
            if (colConstraints[colConstraints.length - 1 - i][0] == 0) {
                this.rowSize--;
            } else {
                break;
            }
        }

        for (int[] rowConstraint : rowConstraints) {
            if (rowConstraint[0] == 0) {
                this.colSize--;
            } else {
                break;
            }
        }

        for (int i = 0; i < rowConstraints.length; i++) {
            if (rowConstraints[rowConstraints.length - 1 - i][0] == 0) {
                this.colSize--;
            } else {
                break;
            }
        }
    }

    private void putConstants() {
        for (int i = 0, offset = 0; i < colConstraints.length; i++) {
            while (unmodifiables[i][0]) {
                offset++;
            }
            if (colSize == colConstraints[i][0]) {
                for (int j = 0; j < colSize; j++) {
                    board[j + offset][i] = true;
                }
            } else if (colSize == sum(colConstraints[i])) {

            }
        }

        for (int i = 0, offset = 0; i < rowConstraints.length; i++) {
            if (rowSize == rowConstraints[i][0]) {
                for (int j = 0; j < rowSize; j++) {
                    if (unmodifiables[i][j + offset]) {
                        j--;
                        offset++;
                    } else {
                        board[i][j + offset] = true;
                    }
                }
            }
        }


    }

    private int sum(int[] arr) {
        int sum = 0;
        for (int i : arr) {
            sum += i;
        }
        return sum + arr.length - 1;
    }

    public static boolean[][] solve(int[][] colConstraints, int[][] rowConstraints) {
        PicrossTouchSolver solved = new PicrossTouchSolver(colConstraints, rowConstraints);
        return solved.board;
    }

    private boolean recursive(int idx) {
        if (idx == board.length * board[0].length) {
            return true;
        }

        int row = idx / board.length;
        int col = idx % board[row].length;

        board[row][col] = true;

        if (isValid(row, col) && recursive(idx + 1)) {
            return true;
        }

        board[row][col] = false;
        return false;
    }

    private boolean isValid(int row, int col) {
        return isValidRow(row) && isValidCol(col);
    }

    private boolean isValidRow(int row) {
        int[] expected = rowConstraints[row];
        int[] result = new int[expected.length];

        int idx = 0;
        for (int i = 0; i < board[row].length; i++) {
            if (board[row][i]) {
                result[idx]++;
            } else {
                idx++;
            }
        }

        return Arrays.equals(result, expected);
    }

    private boolean isValidCol(int col) {
        int[] expected = colConstraints[col];
        int[] result = new int[expected.length];

        int idx = 0;
        for (boolean[] row : board) {
            if (row[col]) {
                result[idx]++;
            } else {
                idx++;
            }
        }

        return Arrays.equals(result, expected);
    }
}
