import java.util.*;

public class PicrossTouchSolver {
    private final int[][] colConstraints;
    private final int[][] rowConstraints;
    private final boolean[][] board;
    private final List<List<boolean[]>> allPossibilities;
    private boolean solved;
    private int rowSize;

    private PicrossTouchSolver(int[][] colConstraints, int[][] rowConstraints) {
        this.colConstraints = colConstraints;
        this.rowConstraints = rowConstraints;
        this.board = new boolean[rowConstraints.length][colConstraints.length];

        this.rowSize = colConstraints.length;
        computeSizes();
        rowConstants();

        this.allPossibilities = generateArrays(colConstraints, rowConstraints.length);

        int i = 0;
        for (List<boolean[]> list : this.allPossibilities) {
            for (boolean[] arr : list) {
                System.out.println(i + " -> " + Arrays.toString(arr));
            }
            i++;
        }

        solve(0);
    }

    public static boolean[][] solve(int[][] colConstraints, int[][] rowConstraints) {
        return new PicrossTouchSolver(colConstraints, rowConstraints).board;
    }

    private void solve(int idx) {
        if (idx == allPossibilities.size() || isValid()) {
            this.solved = isValid();
            return;
        }
        for (boolean[] possibility : allPossibilities.get(idx)) {
            applyPossibility(idx, possibility);
            if (isValid()) {
                this.solved = true;
                return;
            }
            solve(idx + 1);
        }
    }

    private void applyPossibility(int col, boolean[] possibility) {
        for (int i = 0; i < rowConstraints.length; i++) {
            board[i][col] = possibility[i];
        }
    }

    private List<List<boolean[]>> generateArrays(int[][] colConstraints, int size) {
        List<List<boolean[]>> allPossibilities = new LinkedList<>();

        for (int[] colConstraint : colConstraints) {
            allPossibilities.add(generateArrays(colConstraint, size));
        }

        int col = 0;
        for (List<boolean[]> subList : allPossibilities) {
            for (Iterator<boolean[]> it = subList.iterator(); it.hasNext(); ) {
                if (!fit(it.next(), col)) {
                    it.remove();
                }
            }
            col++;
        }

        return allPossibilities;
    }

    private boolean fit(boolean[] arr, int col) {
        for (int i = 0; i < arr.length; i++) {
            if (board[i][col] && !arr[i]) {
                return false;
            }
        }
        return true;
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
    }

    private void rowConstants() {
        int offset = 0;
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
                    if (++k == rowConstraints[i][l]) {
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
            if (!isValidRow(i)) {
                return false;
            }
        }
        return true;
    }

    private boolean isValidRow(int row) {
        int[] expected = rowConstraints[row];
        int[] result = new int[expected.length];

        boolean b = false;
        for (int i = 0, idx = 0; i < board[row].length && idx < result.length; i++) {
            if (board[row][i]) {
                result[idx]++;
                b = true;
            } else if (b) {
                idx++;
                b = false;
            }
        }

        return Arrays.equals(result, expected);
    }
}
