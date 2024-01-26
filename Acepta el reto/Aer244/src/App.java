import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        testcase:
        for (int n = sc.nextInt(), queens = sc.nextInt(); n != 0 || queens != 0; n = sc.nextInt(), queens = sc.nextInt()) {
            boolean[][] tablero = new boolean[n][n];

            for (int i = 0; i < queens; i++) {
                int currRow = sc.nextInt() - 1;
                int currCol = sc.nextInt() - 1;
                if (tablero[currRow][currCol]) {
                    System.out.println("SI");
                    for (int j = i + 1; j < queens; j++) {
                        sc.nextInt();
                        sc.nextInt();
                    }
                    continue testcase;
                } else {
                    for (int j = 0; j < tablero.length; j++) {
                        tablero[j][currCol] = true;
                        tablero[currRow][j] = true;
                    }
                    for (int j = -1; j <= 1; j += 2) {
                        for (int k = -1; k <= 1; k += 2) {
                            for (int x = currRow + j, y = currCol + k; x >= 0 && x < tablero.length && y >= 0 && y < tablero.length; x += j, y += k) {
                                tablero[x][y] = true;
                            }
                        }
                    }
                }
            }
            System.out.println("NO");
        }
    }
}
