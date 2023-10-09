import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

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

        markUnmodifiables(true);
        computeSizes();
        putConstants();
        markUnmodifiables(false);

        recursive(0);
    }

    public static boolean[][] solve(int[][] colConstraints, int[][] rowConstraints) {
        PicrossTouchSolver solved = new PicrossTouchSolver(colConstraints, rowConstraints);
        return solved.board;
    }

    private boolean recursive(int idx) {
        if (isUnmodifiableCol(idx) && idx + 1 < board[0].length) {
            return recursive(idx + 1);
        }

        //TODO:
        // sacar todas as posibilidades da columna (espacios = sum(colConstraint[i]) - board.length)
        // probar a primeira, if idx dentro de tablero && recursive

        return false;
    }

    private boolean isUnmodifiableCol(int col) {
        for (int i = 0; i < board.length; i++) {
            if (!unmodifiables[i][col]) {
                return false;
            }
        }
        return true;
    }

    private void markUnmodifiables(boolean b) {
        if (b) {
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
        } else {
            for (int i = 0; i < board.length; i++) {
                if (isValidRow(i)) {
                    markUnmodifiableRow(i);
                }
                if (isValidCol(i)) {
                    markUnmodifiableCol(i);
                }
            }
        }
    }

    private void markUnmodifiableRow(int row) {
        for (int i = 0; i < board[row].length; i++) {
            unmodifiables[row][i] = true;
        }
    }
    
    private void markUnmodifiableCol(int col) {
        for (int i = 0; i < board.length; i++) {
            unmodifiables[i][col] = true;
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
        int offset = 0;
        for (int[] rowConstraint : rowConstraints) {
            if (rowConstraint[0] == 0) {
                offset++;
            } else {
                break;
            }
        }
        for (int i = 0; i < colConstraints.length; i++) {
            if (colSize == colConstraints[i][0]) {
                for (int j = offset; j < colSize + offset; j++) {
                    board[j][i] = true;
                }
            } else if (colSize == sum(colConstraints[i]) + colConstraints[i].length - 1) {
                for (int j = offset, k = 0, l = 0; l < colConstraints.length && j < colSize + offset; j++) {
                    board[j][i] = true;
                    if (++k == colConstraints[i][l]) {
                        j++;
                        l++;
                        k = 0;
                    }
                }
            }
        }

        offset = 0;
        for (int[] colConstraint : colConstraints) {
            if (colConstraint[0] == 0) {
                offset++;
            } else {
                break;
            }
        }
        for (int i = 0; i < rowConstraints.length; i++) {
            if (rowSize == rowConstraints[i][0]) {
                for (int j = offset; j < rowSize + offset; j++) {
                    board[i][j] = true;
                }
            } else if (rowSize == sum(rowConstraints[i]) + rowConstraints[i].length - 1) {
                for (int j = offset, k = 0, l = 0; l < rowConstraints.length && j < rowSize + offset; j++) {
                    board[i][j] = true;
                    if (++k == colConstraints[i][l]) {
                        j++;
                        l++;
                        k = 0;
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
        return sum;
    }

    private boolean isValid() {
        for (int i = 0; i < board.length; i++) {
            if (!isValid(i, i)) {
                return false;
            }
        }

        return true;
    }

    private boolean isValid(int row, int col) {
        return isValidRow(row) && isValidCol(col);
    }

    private boolean isValidRow(int row) {
        int[] expected = rowConstraints[row];
        int[] result = new int[expected.length];

        for (int i = 0, idx = 0; i < board[row].length && idx < result.length; i++) {
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

        for (int i = 0, idx = 0; i < board.length && idx < result.length; i++) {
            boolean[] row = board[i];
            if (row[col]) {
                result[idx]++;
            } else {
                idx++;
            }
        }

        return Arrays.equals(result, expected);
    }
}
