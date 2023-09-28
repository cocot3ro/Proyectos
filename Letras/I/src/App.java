public class App {
    public static void main(String[] args) throws Exception {
        imprimirI(5, '#');
    }

    public static void imprimirI(int n, char c) {
        if ((n > 1 || n < -1) && (n % 2 != 0)) {
            if (n < 0) {n = -n;}

            for (int i = 0; i < n; i++) {
                System.out.print(c);
            }
            System.out.println();

            for (int i = 0; i < n - 2; i++) {
                for (int j = 0; j < 0.5 * n - 0.5; j++) {
                    System.out.print(" ");
                }
                System.out.println(c);
            }
                

            for (int i = 0; i < n; i++) {
                System.out.print(c);
            }
            System.out.println();
        }
    }

    /*    I

    ccccccc
    ...c
    ...c
    ...c
    ...c
    ...c
    ccccccc


    ccccc
    ..c
    ..c
    ..c
    ccccc


    ccc
    .c
    ccc
    */
}
