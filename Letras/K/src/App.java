public class App {
    public static void main(String[] args) throws Exception {
        imprimirK(-9, '#');
    }

    public static void imprimirK(int n, char c) {
        if ((n > 1 || n < -1) && (n % 2 != 0)) {
            for (int i = 0; i < (n > 0? n : -n) / 2; i++) {
                for (int j = 0; j < (n > 0? 0 : 0 + 2 * i); j++) {
                    System.out.print(" ");
                }
                System.out.print(c);
                for (int j = 0; j < (n > 0? n : -n) - 2 - 2 * i; j++) {
                    System.out.print(" ");
                }
                System.out.println(c);
            }

            for (int i = 0; i < (n > 0?0 : -n - 2); i++) {
                System.out.print(" ");
            }
            for (int i = 0; i < 2; i++) {
                System.out.print(c);
            }
            System.out.println();

            for (int i = 0; i < (n > 0? n : -n) / 2; i++) {
                for (int j = 0; j < (n > 0?0 : -n - 3 - 2 * i); j++) {
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

    /* K
    
    c.......c       c.......c
    c.....c         ..c.....c
    c...c           ....c...c
    c.c             ......c.c
    cc              .......cc
    c.c             ......c.c
    c...c           ....c...c
    c.....c         ..c.....c
    c.......c       c.......c


    c.....c         c.....c
    c...c           ..c...c
    c.c             ....c.c
    cc              .....cc
    c.c             ....c.c
    c...c           ..c...c
    c.....c         c.....c


    c...c           c...c
    c.c             ..c.c
    cc              ...cc
    c.c             ..c.c
    c...c           c...c


    c.c
    cc
    c.c

    */
}
