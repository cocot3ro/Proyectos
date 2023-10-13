import java.math.BigInteger;
import java.util.*;

public class PicrossTouchSolver {

    private static final String RESET = "\u001B[0m";
    private static final String RED = "\u001B[31m";
    private static final String GREEN = "\u001B[32m";
    private static final String BLUE = "\u001B[34m";
    private static final String YELLOW = "\u001B[33m";
    private static final String WHITE_BACKGROUND = "\033[0;107m";
    private static final String BLACK_BACKGROUND = "\033[40m";

    private final int[][] colConstraints;
    private final int[][] rowConstraints;
    private final boolean[][] board;
    private final List<List<boolean[]>> allPossibilities;
    private int rowSize;
    private boolean solved;
    private final boolean enableOutput;

    private PicrossTouchSolver(int[][] colConstraints, int[][] rowConstraints, boolean enableOutput) {
        this.colConstraints = colConstraints;
        this.rowConstraints = rowConstraints;
        this.board = new boolean[rowConstraints.length][colConstraints.length];
        this.rowSize = colConstraints.length;
        this.enableOutput = enableOutput;

        computeSizes();
        rowCommons();

        this.allPossibilities = generateAllPossibilities(colConstraints, rowConstraints.length);

        if (enableOutput) {
            int col = 0;
            int total = 0;
            BigInteger combinaciones = new BigInteger("1");
            for (List<boolean[]> list : this.allPossibilities) {
                int cont = 0;
                for (boolean[] arr : list) {
                    total++;
                    cont++;
                    System.out.println((col % 2 == 0 ? GREEN : BLUE) + "col: " + col + " -> " + Arrays.toString(arr).replace("false", "0").replace("true", "1") + RESET);
                }
                System.out.println(YELLOW + "cont: " + cont + RESET);
                combinaciones = combinaciones.multiply(new BigInteger(String.valueOf(cont)));
                col++;
            }
            System.out.println("total: " + total + ", combinaciones totales: " + combinaciones);
        }
        solve(0);
    }

    public static boolean[][] solve(int[][] colConstraints, int[][] rowConstraints, boolean print) {
        return new PicrossTouchSolver(colConstraints, rowConstraints, print).board;
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

    private List<List<boolean[]>> generateAllPossibilities(int[][] colConstraints, int size) {
        List<List<boolean[]>> allPossibilities = new LinkedList<>();

        for (int[] colConstraint : colConstraints) {
            allPossibilities.add(generateArrays(colConstraint, size));
        }

        int col = 0;
        int total = 0;
        BigInteger combinaciones = new BigInteger("1");

        for (List<boolean[]> subList : allPossibilities) {
            int cont = 0;
            for (Iterator<boolean[]> it = subList.iterator(); it.hasNext(); ) {
                if (enableOutput) {
                    total++;
                    cont++;
                }
                boolean[] next = it.next();
                if (enableOutput) {
                    System.out.print((col % 2 == 0 ? BLUE : GREEN) + "col: " + col + ":" + YELLOW + " test: " + Arrays.toString(next).replace("false", "0").replace("true", "1") + " -> " + RESET);
                }
                if (!fit(next, col)) {
                    if (enableOutput) {
                        System.out.println(RED + "false" + RESET);
                    }
                    it.remove();
                } else if (enableOutput) {
                    System.out.println(GREEN + "true" + RESET);
                }
            }
            if (enableOutput) {
                combinaciones = combinaciones.multiply(new BigInteger(String.valueOf(cont)));
                System.out.println(YELLOW + "cont: " + cont + RESET);
            }
            col++;
        }
        if (enableOutput) {
            System.out.println("total: " + total + ", combinaciones totales: " + combinaciones);
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

    private void rowCommons() {
        List<List<boolean[]>> rowPossibilities = new LinkedList<>();

        for(int[] rowConstraint : rowConstraints) {
            rowPossibilities.add(generateArrays(rowConstraint, colConstraints.length));
        }

        if (enableOutput) {
            int row = 0;
            int total = 0;
            BigInteger combinaciones = new BigInteger("1");
            for (List<boolean[]> list : rowPossibilities) {
                int cont = 0;
                for (boolean[] arr : list) {
                    total++;
                    cont++;
                    System.out.println((row % 2 == 0 ? BLUE : GREEN) + "row possibility: " + row + " -> " + Arrays.toString(arr).replace("false", "0").replace("true", "1") + RESET);
                }
                combinaciones = combinaciones.multiply(new BigInteger(String.valueOf(cont)));
                System.out.println(YELLOW + "cont: " + cont + RESET);
                row++;
            }
            System.out.println("total: " + total + ", combinaciones totales: " + combinaciones);
        }

        List<boolean[]> result = new LinkedList<>();
        for (List<boolean[]> subList : rowPossibilities) {
            boolean[] base = new boolean[subList.get(0).length];
            Arrays.fill(base, true);

            for (int i = 0; i < base.length; i++) {
                for (boolean[] arr : subList) {
                    base[i] &= arr[i];
                }
            }
            result.add(base);
        }

        if (enableOutput) {
            int index = 0;
            for (boolean[] arr : result) {
                System.out.println((index % 2 == 0 ? GREEN : BLUE) + "row common: " + String.format("%02d", index++) + " - > " + Arrays.toString(arr).replace("true", "1").replace("false", "0") + RESET);
            }
        }

        for (int i = 0; i < board.length; i++) {
            System.arraycopy(result.get(i), 0, board[i], 0, board[i].length);
        }
    }

    private int sum(int[] arr) {
        int sum = 0;
        for (int num : arr) {
            sum += num;
        }
        return sum;
    }

    private boolean fit(boolean[] arr, int col) {
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
