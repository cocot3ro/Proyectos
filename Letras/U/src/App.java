public class App {
    public static void main(String[] args) throws Exception {
        imprimirU(9, '#');
    }

    public static void imprimirU(int n, char c) {
        if (n < 0) {n = -n;}
        if ((n > 3) && (n % 2 != 0)) {
            for (int i = 0; i < n - 2; i++) {
                System.out.print(c);
                for (int j = 0; j < n + 2; j++) {
                    System.out.print(" ");
                }
                System.out.println(c);
            }

            System.out.print(" " + c);
            for (int i = 0; i < n; i++) {
                System.out.print(" ");
            }
            System.out.println(c);
            
            System.out.print("  ");
            for (int i = 0; i < n; i++) {
                System.out.print(c);
            }
            System.out.println();
        }

    }

    /*  U

    c...........c
    c...........c
    c...........c
    c...........c
    c...........c
    c...........c
    c...........c
    .c.........c-
    ..ccccccccc--


    c.........c
    c.........c
    c.........c
    c.........c
    c.........c
    .c.......c-
    ..ccccccc--


    c.......c
    c.......c
    c.......c
    .c.....c-
    ..ccccc--

    */
}
