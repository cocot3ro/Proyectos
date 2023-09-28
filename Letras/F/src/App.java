public class App {
    public static void main(String[] args) throws Exception {
        imprimirF(7, '#');
    }

    public static void imprimirF(int n, char c) {
        if ((n > 3 || n < -3) && (n % 2 != 0)) {
            for (int i = 0; i < (n > 0?n : -n); i++) {
                System.out.print(c);
            }
            System.out.println();

            for (int i = 0; i < 0.5 * (n > 0?n : -n) - 1.5; i++) {
                for (int j = 0; j < -n - 1; j++) {
                    System.out.print(" ");
                }
                System.out.println(c);
            }

            for (int i = 0; i < (n > 0?n : -n); i++) {
                System.out.print(c);
            }
            System.out.println();

            for (int i = 0; i < 0.5 * (n > 0?n : -n) - 0.5; i++) {
                for (int j = 0; j < -n - 1; j++) {
                    System.out.print(" ");
                }
                System.out.println(c);
            }
        }
    }

    /*           F

    ccccccccccc     ccccccccccc
    c               ..........c
    c               ..........c
    c               ..........c
    c               ..........c
    ccccccccccc     ccccccccccc
    c               ..........c
    c               ..........c
    c               ..........c
    c               ..........c
    c               ..........c


    ccccccccc       ccccccccc
    c               ........c
    c               ........c
    c               ........c
    ccccccccc       ccccccccc
    c               ........c
    c               ........c
    c               ........c
    c               ........c


    ccccccc         ccccccc
    c               ......c
    c               ......c
    ccccccc         ccccccc
    c               ......c
    c               ......c
    c               ......c


    ccccc           ccccc
    c               ....c
    ccccc           ccccc
    c               ....c
    c               ....c

    */
}
