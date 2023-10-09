public class PicrossTouchApp {
    public static void main(String[] args) {
        int[][] colConstraints = {{15}, {15}, {2, 2}, {2, 3, 2}, {2, 3, 4, 2}, {2, 3, 4, 2}, {2, 5, 2}, {2, 5, 2}, {2, 5, 2}, {2, 3, 4, 2}, {2, 3, 4, 2}, {2, 3, 2}, {2, 2}, {15}, {15}};
        int[][] rowConstraints = {{15}, {15}, {2, 2}, {2, 3, 3, 2}, {2, 3, 3, 2}, {2, 3, 3, 2}, {2, 3, 2}, {2, 3, 2}, {2, 7, 2}, {2, 7, 2}, {2, 7, 2}, {2, 2, 2, 2}, {2, 2}, {15}, {15}};

        boolean[][] solvedBoard = PicrossTouchSolver.solve(colConstraints, rowConstraints);

        System.out.println("SOLUTION");
        System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
        for (boolean[] row : solvedBoard) {
            for (boolean b : row) {
                System.out.print((b ? 1 : ".") + " ");
            }
            System.out.println();
        }
        System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
    }
}
