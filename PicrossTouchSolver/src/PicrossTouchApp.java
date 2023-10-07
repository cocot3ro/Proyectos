public class PicrossTouchApp {
    public static void main(String[] args) {
        int[][] colConstraints = {{0}, {3}, {1, 1}, {3}, {0}};
        int[][] rowConstraints = {{0}, {3}, {1, 1}, {3}, {0}};

        boolean[][] solvedBoard = PicrossTouchSolver.solve(colConstraints, rowConstraints);

        for (boolean[] row : solvedBoard) {
            for (boolean b : row) {
                System.out.print(b ? 1 : 0);
            }
            System.out.println();
        }
    }
}
