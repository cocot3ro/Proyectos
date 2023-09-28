public class App {
    public static void main(String[] args) throws Exception {
        imprimirQ(-11, '#');
    }

    public static void imprimirQ(int n, char c) {
        if ((n > 5 || n < -5) && (n % 2 != 0)) {
            for (int i = 0; i < 2; i++) {
                System.out.print(" ");
            }
            for (int i = 0; i < (n > 0? n : -n) - 4; i++) {
                System.out.print(c);
            }
            System.out.println();

            System.out.print(" ");
            System.out.print(c);
            for (int i = 0; i < (n > 0? n : -n) - 4; i++) {
                System.out.print(" ");
            }
            System.out.println(c);

            for (int i = 0; i < 0.5 * (n > 0? n : -n) - 2.5; i++) {
                System.out.print(c);
                for (int j = 0; j < (n > 0? n : -n) - 2; j++) {
                    System.out.print(" ");
                }
                System.out.println(c);
            }

            System.out.print(" ");
            System.out.print(c);
            for (int i = 0; i < ((n > 0? n : -n) - 4) / 2; i++) {
                System.out.print(" ");
            }
            System.out.print(c);
            for (int i = 0; i < ((n > 0? n : -n) - 4) / 2; i++) {
                System.out.print(" ");
            }
            System.out.println(c);

            for (int i = 0; i < 2; i++) {
                System.out.print(" ");
            }
            for (int i = 0; i < (n > 0? n : -n) - 4; i++) {
                System.out.print(c);
            }
            System.out.println();

            for (int i = 0; i < (n > 0?0.5 * n + 0.5 : 0); i++) {
                System.out.print(" ");
            }
            for (int j = 0; j < 0.5 * (n > 0? n : -n) - 0.5; j++) {
                System.out.print(c);
            }
            for (int i = 0; i < (n > 0?0 : 0.5 * -n + 0.5); i++) {
                System.out.print(" ");
            }
            System.out.println();
        }
    }

    /*           Q
    11
    ..ccccccc       ..ccccccc
    .c.......c      .c.......c
    c.........c     c.........c
    c.........c     c.........c
    c.........c     c.........c
    .c...c...c      .c...c...c
    ..ccccccc       ..ccccccc
    ......ccccc     ccccc

    9
    ..ccccc         ..ccccc
    .c.....c        .c.....c
    c.......c       c.......c
    c.......c       c.......c
    .c..c..c        .c..c..c
    ..ccccc         ..ccccc
    .....cccc       cccc

    7
    ..ccc           ..ccc
    .c...c          .c...c
    c.....c         c.....c
    .c.c.c          .c.c.c
    ..ccc           ..ccc
    ....ccc         ccc
    */
}
