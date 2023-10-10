public class PicrossTouchApp {
    public static void main(String[] args) {
        int[][] colConstraints = {{3}, {2, 3}, {10}, {5, 2}, {1, 1, 1, 1}, {4, 1, 1}, {1, 1, 1, 1}, {5, 2}, {10}, {2, 3}};
        int[][] rowConstraints = {{1, 1}, {2, 2}, {7}, {2, 1, 2}, {9}, {3, 1, 3}, {1, 1}, {3, 3, 2}, {4, 2}, {15}};

        boolean[][] solvedBoard = PicrossTouchSolver.solve(colConstraints, rowConstraints);

        System.out.println("SOLUTION");
        System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
        for (int i = 0; i < solvedBoard[0].length; i++) {
            System.out.print(i + "\t");
        }
        System.out.println();
        for (boolean[] row : solvedBoard) {
            for (boolean b : row) {
                System.out.print((b ? 1 : ".") + "\t");
            }
            System.out.println();
        }
        System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
    }
}
