public class App {
    public static void main(String[] args) throws Exception {
        imprimirJ(11, '#');
    }

    public static void imprimirJ(int n, char c) {
        for (int i = 0; i < (n > 0?n : -n); i++) {
            System.out.print(c);
        }
        System.out.println();

        for (int i = 0; i < (n > 0? n : -n) / 2; i++) {
            for (int j = 0; j < (n > 0?n - 1 : 0); j++) {
                System.out.print(" ");
            }
            System.out.println(c);
        }

        for (int i = 0; i < 0.5 * (n > 0?n : -n) - 0.5; i++) {
            System.out.print(c);
            for (int j = 0; j < (n > 0? n : -n) - 2; j++) {
                System.out.print(" ");
            }
            System.out.println(c);
        }

        System.out.print(" ");
        for (int i = 0; i < (n > 0?n : -n) - 2; i++) {
            System.out.print(c);
        }
        System.out.println();
    }

    /*          J

    ccccccccc       ccccccccc
    ........c       c
    ........c       c
    ........c       c
    c.......c       c
    c.......c       c.......c
    c.......c       c.......c
    c.......c       c.......c
    .ccccccc        .ccccccc


    ccccccc         ccccccc
    ......c         c
    ......c         c
    ......c         c
    c.....c         c.....c
    c.....c         c.....c
    .ccccc          .ccccc


    ccccc           ccccc
    ....c           c
    ....c           c
    c...c           c...c
    .ccc            .ccc

    */
}
