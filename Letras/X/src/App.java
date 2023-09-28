public class App {
    public static void main(String[] args) throws Exception {
        imprimirX(9, '#');
    }

    public static void imprimirX(int n, char c) {
        if (n < 0) {n = -n;}
        if ((n > 1) && (n % 2 != 0)) {
            for (int i = 0; i < n / 2; i++) {
                for (int j = 0; j < 0 + i; j++) {
                    System.out.print(" ");
                }
                System.out.print(c);
                for (int j = 0; j < n - 2 - 2 * i; j++) {
                    System.out.print(" ");
                }
                System.out.println(c);
            }

            for (int i = 0; i < n / 2; i++) {
                System.out.print(" ");
            }
            System.out.println(c);

            for (int i = 0; i < n / 2; i++) {
                for (int j = 0; j < n / 2 - 1 - i; j++) {
                    System.out.print(" ");
                }
                System.out.print(c);
                for (int j = 0; j < 1 + 2 * i; j++) {
                    System.out.print(" ");
                }
                System.out.println(c);
            }
            
        }
    }

    /*  X
    
    c.......c
    .c.....c-
    ..c...c--
    ...c.c---
    ....c----
    ...c.c---
    ..c...c--
    .c.....c-
    c.......c


    c.....c
    .c...c-
    ..c.c--
    ...c---
    ..c.c--
    .c...c-
    c.....c


    c...c
    .c.c-
    ..c--
    .c.c-
    c...c


    c.c
    .c-
    c.c

    */
}
