public class App {
    public static void main(String[] args) throws Exception {
        imprimirC(-11, '#');
    }

    public static void imprimirC(int n, char c) {
        if ((n > 3 || n < -3) && (n % 2 != 0)) {
            
            for (int i = 0; i < (n > 0?n / 2 : 1); i++) {
                System.out.print(" ");
            }
            for (int i = 0; i < (n > 0?n : -n) - 2; i++) {
                System.out.print(c);
            }
            System.out.println();

            for (int i = 0; i < (n > 0?n / 2 - 1 : 0); i++) {
                System.out.print(" ");
            }
            System.out.print(c);
            for (int i = 0; i < (n > 0?n : -n) - 2; i++) {
                System.out.print(" ");
            }
            System.out.println(c);

            for (int i = 0; i < (n > 0? n : -n) / 2 - 2; i++) {
                for (int j = 0; j < (n > 0?n / 2 - 2 - i : -n + i); j++) {
                    System.out.print(" ");
                }
                System.out.println(c);
            }

            for (int i = 0; i < (n > 0? 0 : (0.5 * -n - 2.5) + -n); i++) {
                System.out.print(" ");
            }
            System.out.println(c);

            for (int i = 0; i < (n > 0? n : -n) / 2 - 2 ; i++) {
                for (int j = 0; j < (n > 0?1 + i : 0.5 * -n - 3.5) + -n - i; j++) {
                    System.out.print(" ");
                }
                System.out.println(c);
            }

            for (int i = 0; i < (n > 0?n / 2 - 1 : 0); i++) {
                System.out.print(" ");
            }
            System.out.print(c);
            for (int i = 0; i < (n > 0?n : -n) - 2; i++) {
                System.out.print(" ");
            }
            System.out.println(c);

            for (int i = 0; i < (n > 0?n / 2 : 1); i++) {
                System.out.print(" ");
            }
            for (int i = 0; i < (n > 0?n : -n) - 2; i++) {
                System.out.print(c);
            }
            System.out.println();
        }
    }

    /*               C
    .....ccccccccc      .ccccccccc
    ....c.........c     c.........c
    ...c                ...........c
    ..c                 ............c
    .c                  .............c
    c                   ..............c
    .c                  .............c
    ..c                 ............c
    ...c                ...........c
    ....c.........c     c.........c
    .....ccccccccc      .ccccccccc


    ....ccccccc         .ccccccc
    ...c.......c        c.......c
    ..c                 .........c
    .c                  ..........c
    c                   ...........c
    .c                  ..........c
    ..c                 .........c
    ...c.......c        c.......c
    ....ccccccc         .ccccccc


    ...ccccc            .ccccc
    ..c.....c           c.....c
    .c                  .......c
    c                   ........c
    .c                  .......c
    ..c.....c           c.....c
    ...ccccc            .ccccc


    ..ccc               .ccc
    .c...c              c...c
    c                   .....c
    .c...c              c...c
    ..ccc               .ccc

    */
}
