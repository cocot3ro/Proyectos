public class App {
    public static void main(String[] args) throws Exception {
        imprimirD(9, '#');
    }

    public static void imprimirD(int n, char c) {
        if ((n > 3 || n < -3) && (n % 2 != 0)) {
            for (int i = 0; i < 0.5 * -n - 1.5; i++) {
                System.out.print(".");
            }
            for (int index = 0; index < (n > 0?n : -n) - 1; index++) {
                System.out.print(c);
            }
            System.out.println();

            for (int i = 0; i < 0.5 * (n > 0?n : -n) - 2.5; i++) {
                for (int j = 0; j < 0.5 * -n - 2.5 - i; j++) {
                    System.out.print(".");
                }
                System.out.print(c);
                for (int j = 0; j < (n > 0?n : -n) - 2 + i; j++) {
                    System.out.print(".");
                }
                System.out.println(c);
            }

            for (int i = 0; i < 3; i++) {
                System.out.print(c);
                for (int j = 0; j < 1.5 * (n > 0?n : -n) - 4.5; j++) {
                    System.out.print(".");
                }
                System.out.println(c);
            }

            for (int i = 0; i < 0.5 * (n > 0?n : -n) - 2.5; i++) {
                for (int j = 0; j < 1 + i; j++) {
                    System.out.print(".");
                }
                System.out.print(c);
                for (int j = 0; j < 1.5 * (n>0?n : -n) - 5.5 -i; j++) {
                    System.out.print(".");
                }
                System.out.println(c);
            }

            for (int i = 0; i < 0.5 * -n - 1.5; i++) {
                System.out.print(".");
            }
            for (int index = 0; index < (n > 0?n : -n) - 1; index++) {
                System.out.print(c);
            }
            System.out.println();
        }
    }

    /*              D
    cccccccccc          ....cccccccccc
    c.........c         ...c.........c
    c..........c        ..c..........c
    c...........c       .c...........c
    c............c      c............c
    c............c      c............c
    c............c      c............c
    c...........c       .c...........c
    c..........c        ..c..........c
    c.........c         ...c.........c
    cccccccccc          ....cccccccccc


    cccccccc            ...cccccccc
    c.......c           ..c.......c
    c........c          .c........c
    c.........c         c.........c
    c.........c         c.........c
    c.........c         c.........c
    c........c          .c........c
    c.......c           ..c.......c
    cccccccc            ...cccccccc


    cccccc              ..cccccc
    c.....c             .c.....c
    c......c            c......c
    c......c            c......c
    c......c            c......c
    c.....c             .c.....c
    cccccc              ..cccccc
    

    cccc                .cccc
    c...c               c...c
    c...c               c...c
    c...c               c...c
    cccc                .cccc

    */
}
