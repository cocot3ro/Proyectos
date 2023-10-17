import java.util.*;

public class PicrossTouchSolver {

    private final int[][] colConstraints;
    private final int[][] rowConstraints;
    private final boolean[][] board;
    private final List<List<boolean[]>> allColPossibilities;
    private boolean solved;

    private PicrossTouchSolver(int[][] colConstraints, int[][] rowConstraints) {
        this.colConstraints = colConstraints;
        this.rowConstraints = rowConstraints;
        this.board = new boolean[rowConstraints.length][colConstraints.length];
        colCommons();
        rowCommons();
        this.allColPossibilities = generateAllPossibilities(colConstraints, rowConstraints.length);
        solve(0);
    }

    public static boolean[][] solve(int[][] colConstraints, int[][] rowConstraints) {
        return new PicrossTouchSolver(colConstraints, rowConstraints).board;
    }

    private void solve(int col) {
        if (col == allColPossibilities.size()) {
            if (!solved) {
                solved = isValid();
            }
            return;
        }

        for (boolean[] possibility : allColPossibilities.get(col)) {
            applyPossibility(col, possibility);
            solve(col + 1);
            if (solved) {
                return;
            }
        }
    }

    private void applyPossibility(int col, boolean[] possibility) {
        for (int i = 0; i < rowConstraints.length; i++) {
            board[i][col] = possibility[i];
        }
    }

    private List<List<boolean[]>> generateAllPossibilities(int[][] colConstraints, int size) {
        List<List<boolean[]>> allPossibilities = new LinkedList<>();

        for (int[] colConstraint : colConstraints) {
            allPossibilities.add(generateArrays(colConstraint, size));
        }

        int col = 0;
        for (List<boolean[]> subList : allPossibilities) {
            for (Iterator<boolean[]> it = subList.iterator(); it.hasNext(); ) {
                if (!fitOnCol(it.next(), col)) {
                    it.remove();
                }
            }
            col++;
        }

        return allPossibilities;
    }

    private List<boolean[]> generateArrays(int[] blocks, int size) {
        List<boolean[]> result = new ArrayList<>();
        boolean[] currentArray = new boolean[size];
        generateArraysHelper(result, currentArray, blocks, 0, 0);
        return result;
    }

    private void generateArraysHelper(List<boolean[]> result, boolean[] currentArray, int[] blocks, int blockIndex, int startIndex) {
        if (blockIndex == blocks.length) {
            result.add(Arrays.copyOf(currentArray, currentArray.length));
            return;
        }

        for (int i = startIndex; i <= currentArray.length - blocks[blockIndex]; i++) {
            for (int j = i; j < i + blocks[blockIndex]; j++) {
                currentArray[j] = true;
            }

            generateArraysHelper(result, currentArray, blocks, blockIndex + 1, i + blocks[blockIndex] + 1);

            for (int j = i; j < i + blocks[blockIndex]; j++) {
                currentArray[j] = false;
            }
        }
    }

    private void colCommons() {
        List<List<boolean[]>> colPossibilities = new LinkedList<>();

        for (int[] colConstraint : colConstraints) {
            colPossibilities.add(generateArrays(colConstraint, rowConstraints.length));
        }

        List<boolean[]> colCommons = new LinkedList<>();
        for (List<boolean[]> subList : colPossibilities) {
            boolean[] base = new boolean[subList.get(0).length];
            Arrays.fill(base, true);

            for (int i = 0; i < base.length; i++) {
                for (boolean[] arr : subList) {
                    base[i] &= arr[i];
                }
            }
            colCommons.add(base);
        }

        for (int i = 0; i < board.length; i++) {
            applyPossibility(i, colCommons.get(i));
        }
    }

    private void rowCommons() {
        List<List<boolean[]>> rowPossibilities = new LinkedList<>();

        for (int[] rowConstraint : rowConstraints) {
            rowPossibilities.add(generateArrays(rowConstraint, colConstraints.length));
        }

        int row = 0;
        for (List<boolean[]> subList : rowPossibilities) {
            for (Iterator<boolean[]> it = subList.iterator(); it.hasNext(); ) {
                if (!fitOnRow(it.next(), row)) {
                    it.remove();
                }
            }
            row++;
        }

        List<boolean[]> rowCommons = new LinkedList<>();
        for (List<boolean[]> subList : rowPossibilities) {
            boolean[] base = new boolean[subList.get(0).length];
            Arrays.fill(base, true);

            for (int i = 0; i < base.length; i++) {
                for (boolean[] arr : subList) {
                    base[i] &= arr[i];
                }
            }
            rowCommons.add(base);
        }

        for (int i = 0; i < board.length; i++) {
            System.arraycopy(rowCommons.get(i), 0, board[i], 0, board[i].length);
        }
    }

    private boolean fitOnRow(boolean[] arr, int row) {
        for (int col = 0; col < arr.length; col++) {
            if (board[row][col] && !arr[col]) {
                return false;
            }
        }
        return true;
    }

    private boolean fitOnCol(boolean[] arr, int col) {
        for (int row = 0; row < arr.length; row++) {
            if (board[row][col] && !arr[row]) {
                return false;
            }
        }
        return true;
    }

    private boolean isValid() {
        for (int row = 0; row < board.length; row++) {
            if (!isValidRow(row)) {
                return false;
            }
        }
        return true;
    }

    private boolean isValidRow(int row) {
        int[] expected = rowConstraints[row];
        int[] result = new int[expected.length];

        boolean canJump = false;
        for (int col = 0, idx = 0; col < board[row].length && idx < result.length; col++) {
            if (board[row][col]) {
                result[idx]++;
                canJump = true;
            } else if (canJump) {
                idx++;
                canJump = false;
            }
        }

        return Arrays.equals(result, expected);
    }
}
