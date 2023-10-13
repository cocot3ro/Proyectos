public class PicrossTouchApp {
    public static final String RESET = "\033[0m";
    public static final String WHITE_BACKGROUND = "\033[0;107m";
    public static final String BLACK_BACKGROUND = "\033[40m";

    public static void main(String[] args) {
        int[][] colConstraints, rowConstraints;

        boolean enableOutput = false;

        //original -> 10x10 -> 87: Accepted 1ms
        //colConstraints = new int[][]{{3}, {2, 3}, {10}, {5, 2}, {1, 1, 1, 1}, {4, 1, 1}, {1, 1, 1, 1}, {5, 2}, {10}, {2, 3}};
        //rowConstraints = new int[][] {{1, 1}, {2, 2}, {7}, {2, 1, 2}, {9}, {3, 1, 3}, {1, 1}, {3, 3, 2}, {4, 3}, {10}};
        //solve(colConstraints, rowConstraints, enableOutput);

        //original -> 15x15 -> 5: Accepted 3ms
        //colConstraints = new int[][]{{15}, {15}, {2, 2}, {2, 3, 2}, {2, 3, 4, 2}, {2, 3, 4, 2}, {2, 5, 2}, {2, 5, 2}, {2, 5, 2}, {2, 3, 4, 2}, {2, 3, 4, 2}, {2, 3, 2}, {2, 2}, {15}, {15}};
        //rowConstraints = new int[][]{{15}, {15}, {2, 2}, {2, 3, 3, 2}, {2, 3, 3, 2}, {2, 3, 3, 2}, {2, 3, 2}, {2, 3, 2}, {2, 7, 2}, {2, 7, 2}, {2, 7, 2}, {2, 2, 2, 2}, {2, 2}, {15}, {15}};
        //solve(colConstraints, rowConstraints, enableOutput);

        //pack 1 -> 10x10 -> 1: Accepted 3ms
        //colConstraints = new int[][]{{1, 7}, {2, 1, 1}, {2, 2, 1, 1}, {2, 2, 1}, {2, 4}, {2, 4}, {2, 2, 1}, {2, 2, 1, 1}, {2, 1, 1}, {1, 7}};
        //rowConstraints = new int[][]{{2, 2}, {2, 2}, {2, 2}, {1, 4, 1}, {3, 2, 3}, {1, 2, 2, 1}, {1, 4, 1}, {1, 1, 2, 1, 1}, {1, 2, 1}, {10}};
        //solve(colConstraints, rowConstraints, enableOutput);

        //original -> 15x15 -> 7: Time Limit Exceeded
        //colConstraints = new int[][]{{4, 4}, {3, 5, 3}, {2, 7, 2}, {1, 3, 3, 1}, {3, 1, 4}, {3, 1, 1, 2}, {2, 3, 2}, {2, 2}, {2, 3, 2}, {3, 1, 1, 2}, {3, 1, 4}, {1, 3, 3, 1}, {2, 7, 2}, {3, 5, 3}, {4, 4}};
        //rowConstraints = new int[][]{{4, 1, 4}, {3, 5, 3}, {2, 3, 3, 2}, {1, 3, 3, 1}, {3, 3}, {3, 1, 1, 3}, {2, 1, 1, 2}, {2, 1, 1, 1, 1, 2}, {2, 1, 1, 2}, {5, 5}, {3, 3}, {1, 3, 3, 1}, {2, 3, 3, 2}, {3, 3, 3}, {4, 1, 4}};
        //solve(colConstraints, rowConstraints, enableOutput);

        //original -> 15x15 -> 24: Time Limit Exceeded => Accepted ~530ms
        //colConstraints = new int[][]{{15}, {3, 3}, {2, 8, 2}, {1, 2, 1, 1}, {1, 1, 5, 1, 1}, {1, 1, 2, 2, 1, 1}, {1, 1, 1, 1, 1, 1}, {1, 1, 1, 1, 1, 1}, {1, 1, 1, 2, 1, 1}, {1, 1, 6, 1, 1}, {1, 1, 2, 1, 1}, {1, 1, 1, 1, 1}, {2, 6, 3}, {3, 3}, {15}};
        //rowConstraints = new int[][]{{15}, {3, 3}, {2, 8, 2}, {1, 2, 1, 1}, {1, 1, 3, 1, 1, 1}, {1, 1, 2, 2, 1, 1}, {1, 1, 1, 1, 1, 1}, {1, 1, 1, 1, 1, 1}, {1, 1, 1, 1, 1, 1}, {1, 1, 2, 3, 1, 1}, {1, 1, 4, 2, 1}, {1, 1, 1}, {2, 11}, {3, 3}, {15}};
        //solve(colConstraints, rowConstraints, enableOutput);

        //pack 1 -> 10x10 -> 5: Time Limit Exceeded => Accepted ~1430ms
        colConstraints = new int[][]{{2}, {3}, {3}, {8}, {2, 1}, {2, 1}, {2, 1, 2}, {2, 1, 3}, {2, 1, 3}, {8}};
        rowConstraints = new int[][]{{4}, {7}, {3, 1}, {1, 4}, {3, 1}, {1, 1}, {1, 3}, {3, 4}, {4, 3}, {3}};
        solve(colConstraints, rowConstraints, enableOutput);
    }

    private static void solve(int[][] colConstraints, int[][] rowConstraints, boolean print) {
        long start = System.currentTimeMillis();
        boolean[][] board = PicrossTouchSolver.solve(colConstraints, rowConstraints, print);
        long end = System.currentTimeMillis();
        print(board);
        System.out.println((end - start) + "ms");
    }

    private static void print(boolean[][] board) {
        for (boolean[] row : board) {
            for (boolean b : row) {
                System.out.print((b ? BLACK_BACKGROUND : WHITE_BACKGROUND) + "   " + RESET);
            }
            System.out.println();
        }
    }
}
