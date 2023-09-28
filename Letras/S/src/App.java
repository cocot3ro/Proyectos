public class App {
    public static void main(String[] args) throws Exception {
        imprimirS(17, '#');
    }

    public static void imprimirS(int n, char c) {
        if ((n > 7 || n < -7) && (n % 2 != 0)) {
            //1
            for (int i = 0; i < (int)(n > 0?0.25 * n - 0.25 : 2); i++) {
                System.out.print(" ");
            }
            for (int i = 0; i < (int)(n > 0?n : -n) - (0.25 * (n > 0?n : -n) + 2.25); i++) {
                System.out.print(c);
            }
            System.out.println();

            //2
            for (int i = 0; i < (int)(n > 0?0.25 * n - 1.25 : 1); i++) {
                System.out.print(" ");
            }
            System.out.print(c);
            for (int i = 0; i < (int)(n > 0?n : -n) - (0.25 * (n > 0?n : -n) + 2.25); i++) {
                System.out.print(" ");
            }
            System.out.println(c);

            //3
            for (int i = 0; i < (int)(0.25 * (n > 0?n : -n) - 1.75); i++) {
                for (int j = 0; j < (int)(n > 0?0.25 * n - 2.25 - i : -n - 3 + i); j++) {
                    System.out.print(" ");
                }
                System.out.println(c);
            }

            //4
            for (int i = 0; i < ((n / 2) % 2 == 0? 1 : 0); i++) {
                for (int j = 0; j < -n - 1; j++) {
                    System.out.print(" ");
                }
                System.out.println(c);
            }

            //5
            for (int i = 0; i < (int)(0.25 * (n > 0?n : -n) - 0.75); i++) {
                for (int j = 0; j < (int)(n > 0?((n / 2) % 2 == 0?1 : 0) + i : -n - 2 - i); j++) {
                    System.out.print(" ");
                }
                System.out.println(c);
            }

            //6
            for (int i = 0; i < (int)(0.25 * (n > 0? n : -n) - 0.25); i++) {
                System.out.print(" ");
            }
            for (int i = 0; i < (n > 0?n : -n) / 2 + 1; i++) {
                System.out.print(c);
            }
            System.out.println();

            //7
            for (int i = 0; i < (int)(0.25 * (n > 0?n : -n) - 0.75); i++) {
                for (int j = 0; j < (n > 0?n - (0.25 * n + 0.25) + i : 0.25 * -n - 1.25 - i); j++) {
                    System.out.print(" ");
                }
                System.out.println(c);
            }

            //8
            for (int i = 0; i < ((n / 2) % 2 == 0? 1 : 0); i++) {
                for (int j = 0; j < n - 1; j++) {
                    System.out.print(" ");
                }
                System.out.println(c);
            }

            //9
            for (int i = 0; i < (int)(0.25 * (n > 0?n : -n) - 1.75); i++) {
                for (int j = 0; j < (n > 0? n - 2 - i : ((n / 2) % 2 == 0? 1 : 0) + i); j++) {
                    System.out.print(" ");
                }
                System.out.println(c);
            }

            //10
            for (int i = 0; i < (int)(n > 0? 1 : 0.25 * -n - 1.25); i++) {
                System.out.print(" ");
            }
            System.out.print(c);
            for (int i = 0; i < (int)(n > 0?n : -n) - (0.25 * (n > 0?n : -n) + 2.25); i++) {
                System.out.print(" ");
            }
            System.out.println(c);

            //11
            for (int i = 0; i < (int)(n > 0?2 : 0.25 * -n - 0.25); i++) {
                System.out.print(" ");
            }
            for (int i = 0; i < (int)(n > 0?n : -n) - (0.25 * (n > 0?n : -n) + 2.25); i++) {
                System.out.print(c);
            }
            System.out.println();
        }

    }

    /*                     S          

    1  ....ccccccccccc--   17  ..ccccccccccc----
    2  ...c...........c-       .c...........c---
    3  ..c--------------       ..............c--
    3  .c---------------       ...............c-
    4  c----------------       ................c
    5  .c---------------       ...............c-
    5  ..c--------------       ..............c--
    5  ...c-------------       .............c---
    6  ....ccccccccc----       ....ccccccccc----
    7  .............c---       ...c-------------
    7  ..............c--       ..c--------------
    7  ...............c-       .c---------------
    8  ................c       c----------------
    9  ...............c-       .c---------------
    9  ..............c--       ..c--------------
    10 .c...........c---       ...c...........c-
    11 ..ccccccccccc----       ....ccccccccccc--


    1  ...ccccccccc--    15    ..ccccccccc---
    2  ..c.........c-          .c.........c--
    3  .c------------          ............c-
    3  c-------------          .............c
    5  c-------------          .............c
    5  .c------------          ............c-
    5  ..c-----------          ...........c--
    6  ...cccccccc---          ...cccccccc---
    7  ...........c--          ..c-----------
    7  ............c-          .c------------
    7  .............c          c-------------
    9  .............c          c-------------
    9  ............c-          .c------------
    10 .c.........c--          ..c........c--
    11 ..ccccccccc---          ...cccccccc---


    1  ...cccccccc--     13    ..cccccccc---
    2  ..c........c-           .c........c--
    3  .c-----------           ...........c-
    4  c------------           ............c
    5  .c-----------           ...........c-
    5  ..c----------           ..........c--
    6  ...ccccccc---           ...ccccccc---
    7  ..........c--           ..c----------
    7  ...........c-           .c-----------
    8  ............c           c------------
    9  ...........c-           .c-----------
    10 .c........c--           ..c--------c-
    11..cccccccc---           ...cccccccc--


    1  ..cccccc--      11      ..cccccc--
    2  .c......c-              .c......c-
    3  c---------              .........c
    5  c---------              .........c
    5  .c--------              ........c-
    6  ..cccccc--              ..cccccc--
    7  ........c-              .c--------
    7  .........c              c---------
    9  .........c              c---------
    10 .c......c-              .c......c-
    11 ..cccccc--              ..cccccc--


    1  ..ccccc--       9       ..ccccc--
    2  .c.....c-               .c.....c-
    4  c--------               ........c
    5  .c-------               .......c-
    6  ..ccccc--               ..ccccc--
    7  .......c-               .c-------
    8  ........c               c--------
    10 .c.....c-               .c-----c-
    11 ..ccccc--               ..ccccc--

    */
}
