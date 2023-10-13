import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class SudokuSolver {
    private final char[][] BOARD;
    private final boolean[][] ROWS;
    private final boolean[][] COLS;
    private final boolean[][][] BLOCKS;
    private final List<Character> ALFABETO;
    private final int BLOCK_SIZE;

    private SudokuSolver(char[][] board, Set<Character> alfabeto) {
        this.BOARD = board;

        this.ROWS = new boolean[BOARD.length][BOARD[0].length];
        this.COLS = new boolean[BOARD.length][BOARD[0].length];
        this.BLOCK_SIZE = (int) Math.sqrt(BOARD.length);
        this.BLOCKS = new boolean[BLOCK_SIZE][BLOCK_SIZE][BOARD.length];
        this.ALFABETO = new LinkedList<>(alfabeto);

        init();
        backtrack(0, 0);
    }

    public static char[][] solve(char[][] board, Set<Character> alfabeto) {
        return new SudokuSolver(board, alfabeto).BOARD;
    }

    private boolean backtrack(int row, int col) {
        if (row == BOARD.length && col == 0) {
            return true;
        }

        if (ALFABETO.contains(BOARD[row][col])) {
            int[] nextCell = getNextCell(row, col);
            return backtrack(nextCell[0], nextCell[1]);
        }

        for (char digit : ALFABETO) {
            if (isValidPlacement(row, col, digit)) {
                addDigit(row, col, digit);
                int[] nextCell = getNextCell(row, col);
                if (backtrack(nextCell[0], nextCell[1])) {
                    return true;
                }
                removeDigit(row, col, digit);
            }
        }
        return false;
    }

    private void init() {
        for (int row = 0; row < BOARD.length; row++) {
            for (int col = 0; col < BOARD[row].length; col++) {
                if (ALFABETO.contains(BOARD[row][col])) {
                    int index = ALFABETO.indexOf(BOARD[row][col]);
                    ROWS[row][index] = true;
                    COLS[col][index] = true;
                    BLOCKS[row / BLOCK_SIZE][col / BLOCK_SIZE][index] = true;
                }
            }
        }
    }

    private boolean isValidPlacement(int row, int col, char digit) {
        int index = ALFABETO.indexOf(digit);
        return (!ROWS[row][index] && !COLS[col][index] && !BLOCKS[row / BLOCK_SIZE][col / BLOCK_SIZE][index]);
    }

    private int[] getNextCell(int row, int col) {
        if (col < BOARD.length - 1) {
            return new int[]{row, col + 1};
        } else {
            return new int[]{row + 1, 0};
        }
    }

    private void addDigit(int row, int col, char digit) {
        int index = ALFABETO.indexOf(digit);
        BOARD[row][col] = ALFABETO.get(index);
        ROWS[row][index] = true;
        COLS[col][index] = true;
        BLOCKS[row / BLOCK_SIZE][col / BLOCK_SIZE][index] = true;
    }

    private void removeDigit(int row, int col, char digit) {
        int index = ALFABETO.indexOf(digit);
        BOARD[row][col] = (char) 0;
        ROWS[row][index] = false;
        COLS[col][index] = false;
        BLOCKS[row / BLOCK_SIZE][col / BLOCK_SIZE][index] = false;
    }
}
