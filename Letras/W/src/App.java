public class App {
    public static void main(String[] args) throws Exception {
        imprimirW(9, '#');
    }

    public static void imprimirW(int n, char c) {
        if (n < 0) {n = -n;}
        if ((n > 1) && (n % 2 != 0)) {
            for (int i = 0; i < n / 2; i++) {
                for (int j = 0; j < 0 + i; j++) {
                    System.out.print(" ");
                }
                System.out.print(c);
                for (int j = 0; j < (3 * n - 4) - (2 * i); j++) {
                    System.out.print(" ");
                }
                System.out.println(c);
            }

            for (int i = 0; i < n / 2; i++) {
                System.out.print(" ");
            }
            System.out.print(c);
            for (int i = 0; i < n - 2; i++) {
                System.out.print(" ");
            }
            System.out.print(c);
            for (int i = 0; i < n - 2; i++) {
                System.out.print(" ");
            }
            System.out.println(c);

            for (int i = 0; i < 0.5 * n - 1.5; i++) {
                for (int j = 0; j < n / 2 + 1 + i; j++) {
                    System.out.print(" ");
                }
                System.out.print(c);
                for (int j = 0; j < (n - 4) - (2 * i); j++) {
                    System.out.print(" ");
                }
                System.out.print(c);
                for (int j = 0; j < 1 + 2 * i; j++) {
                    System.out.print(" ");
                }
                System.out.print(c);
                for (int j = 0; j < (n - 4) - (2 * i); j++) {
                    System.out.print(" ");
                }
                System.out.println(c);
            }

            for (int i = 0; i < n - 1; i++) {
                System.out.print(" ");
            }
            System.out.print(c);
            for (int i = 0; i < n - 2; i++) {
                System.out.print(" ");
            }
            System.out.println(c);

        }
    }

    /* W 

    c.......................c
    .c.....................c-
    ..c...................c--
    ...c.................c---
    ....c.......c.......c----
    .....c.....c.c.....c-----
    ......c...c...c...c------
    .......c.c.....c.c-------
    ........c.......c--------

    c.................c
    .c...............c-
    ..c.............c--
    ...c.....c.....c---
    ....c...c.c...c----
    .....c.c...c.c-----
    ......c.....c------


    c...........c
    .c.........c-
    ..c...c...c--
    ...c.c.c.c---
    ....c...c----

    c.....c
    .c.c.c-
    ..c.c--

    */
}
