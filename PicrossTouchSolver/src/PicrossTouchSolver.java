import java.util.*;

public class PicrossTouchSolver {

    private static final String RESET = "\u001B[0m";
    private static final String RED = "\u001B[31m";
    private static final String GREEN = "\u001B[32m";
    private static final String BLUE = "\u001B[34m";
    private static final String YELLOW = "\u001B[33m";

    private final int[][] colConstraints;
    private final int[][] rowConstraints;
    private final boolean[][] board;
    private final List<List<boolean[]>> allPossibilities;
    private int rowSize;
    private boolean solved;

    private PicrossTouchSolver(int[][] colConstraints, int[][] rowConstraints) {
        this.colConstraints = colConstraints;
        this.rowConstraints = rowConstraints;
        this.board = new boolean[rowConstraints.length][colConstraints.length];

        this.rowSize = colConstraints.length;
        computeSizes();
        rowConstants();

        this.allPossibilities = generateArrays(colConstraints, rowConstraints.length);

        int col = 0;
        int total = 0;
        for (List<boolean[]> list : this.allPossibilities) {
            int cont = 0;
            for (boolean[] arr : list) {
                total++;
                cont++;
                System.out.println((col % 2 == 0 ? GREEN : BLUE) + "col: " + col + " -> " + Arrays.toString(arr).replace("false", "0").replace("true", "1") + RESET);
            }
            System.out.println((col % 2 == 0 ? GREEN : BLUE) + "cont: " + cont + RESET);
            col++;
        }
        System.out.println("total: " + total);

        solve(0);
    }

    public static boolean[][] solve(int[][] colConstraints, int[][] rowConstraints) {
        return new PicrossTouchSolver(colConstraints, rowConstraints).board;
    }

    private void solve(int col) {
        if (col == allPossibilities.size()) {
            if (!solved) {
                solved = isValid();
            }
            return;
        }

        for (boolean[] possibility : allPossibilities.get(col)) {
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

    private List<List<boolean[]>> generateArrays(int[][] colConstraints, int size) {
        List<List<boolean[]>> allPossibilities = new LinkedList<>();

        for (int[] colConstraint : colConstraints) {
            allPossibilities.add(generateArrays(colConstraint, size));
        }

        int col = 0;
        int total = 0;
        for (List<boolean[]> subList : allPossibilities) {
            int cont = 0;
            for (Iterator<boolean[]> it = subList.iterator(); it.hasNext(); ) {
                total++;
                cont++;
                boolean[] next = it.next();
                System.out.print((col % 2 == 0 ? BLUE : YELLOW) + "col: " + col + ", test: " + Arrays.toString(next).replace("false", "0").replace("true", "1") + " -> " + RESET);
                if (!fit(next, col)) {
                    System.out.println(RED + "false" + RESET);
                    it.remove();
                } else {
                    System.out.println(GREEN + "true" + RESET);
                }
            }
            System.out.println((col % 2 == 0 ? BLUE : YELLOW) + cont + RESET);
            col++;
        }
        System.out.println("total: " + total);

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

        //TODO
        // poñer as comuns

    }

    private int sum(int[] arr) {
        int sum = 0;
        for (int i : arr) {
            sum += i;
        }
        return sum;
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
