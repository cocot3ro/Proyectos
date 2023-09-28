public class App {
    public static void main(String[] args) throws Exception {
        imprimirG(-9, '#');
    }

    public static void imprimirG(int n, char c) {
        if ((n > 5 || n < -5) && (n % 2 != 0)) {

            for (int i = 0; i < (n > 0?n / 2 : 1); i++) {
                System.out.print(" ");
            }
            for (int i = 0; i < (n > 0? n : -n) - 2; i++) {
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
            //centro
            for (int j = 0; j < (n > 0?1 : -n / 2 + 1); j++) {
                System.out.print(c);
            }
            for (int j = 0; j < (n > 0?n : -n) - 3; j++) {
                System.out.print(" ");
            }
            for (int j = 0; j < (n > 0?n / 2 + 1 : 1); j++) {
                System.out.print(c);
            }
            System.out.println();
            
            for (int i = 0; i < (n > 0? n : -n) / 2 - 2; i++) {
                for (int j = 0; j < (n > 0?1 + i : 0); j++) {
                    System.out.print(" ");
                }
                System.out.print(c);
                for (int j = 0; j < 1.5 * (n > 0? n : -n) - 4.5 - i; j++) {
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

    /*               G
    .....ccccccccc      .ccccccccc
    ....c.........c     c.........c
    ...c                ...........c
    ..c                 ............c
    .c                  .............c
    c........cccccc     cccccc........c
    .c............c     c............c
    ..c...........c     c...........c
    ...c..........c     c..........c
    ....c.........c     c.........c
    .....ccccccccc      .ccccccccc


    ....ccccccc         .ccccccc
    ...c.......c        c.......c
    ..c                 .........c
    .c                  ..........c
    c......ccccc        ccccc......c
    .c.........c        c.........c
    ..c........c        c........c
    ...c.......c        c.......c
    ....ccccccc         .ccccccc


    ...ccccc            .ccccc
    ..c.....c           c.....c
    .c                  .......c
    c....cccc           cccc....c
    .c......c           c......c
    ..c.....c           c.....c
    ...ccccc            .ccccc

    */
}
