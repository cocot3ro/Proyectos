public class App {
    public static void main(String[] args) throws Exception {
        imprimirV(9, '#');
    }

    public static void imprimirV(int n, char c) {
        if (n < 0) {n = -n;}
        if ((n > 1) && (n % 2 != 0)) {
            for (int i = 0; i < n - 1; i++) {
                for (int j = 0; j < 0 + i; j++) {
                    System.out.print(" ");
                }
                System.out.print(c);
                for (int j = 0; j < (2 * n - 3) - (2 * i) ; j++) {
                    System.out.print(" ");
                }
                System.out.println(c);
            }

            for (int i = 0; i < n - 1; i++) {
                System.out.print(" ");
            }
            System.out.println(c);
        }
    }
    
    /*      V
    
    c...............c
    .c.............c-
    ..c...........c--
    ...c.........c---
    ....c.......c----
    .....c.....c-----
    ......c...c------
    .......c.c-------
    ........c--------


    c...........c
    .c.........c-
    ..c.......c--
    ...c.....c---
    ....c...c----
    .....c.c-----
    ......c------


    c.......c
    .c.....c-
    ..c...c--
    ...c.c---
    ....c----


    c...c
    .c.c-
    ..c--
    */
}
