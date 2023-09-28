public class App {
    public static void main(String[] args) throws Exception {
        imprimirB(11, '#');
    }

    public static void imprimirB(int n, char c) {
        if ((n > 5 || n < -5) && (n % 2 != 0)) {

            System.out.print(n < 0?"  " : "");
            for (int i = 0; i < (n > 0?n : -n) - 2; i++) {
                System.out.print(c);
            }
            System.out.println();
            
            System.out.print(n < 0?" ":"");
            System.out.print(c);
            for (int j = 0; j < (n > 0?n : -n) - 3; j++) {
                System.out.print(" ");
            }
            System.out.println(c);
            
            for (int i = 0; i < (n > 0?n : -n) * 0.5 - 3.5; i++) {
                System.out.print(c);
                for (int j = 0; j < (n > 0?n : -n) - 2; j++) {
                    System.out.print(" ");
                }
                System.out.println(c);
            }

            System.out.print(n < 0?" " + c : c);
            for (int j = 0; j < (n > 0?n : -n) - 3; j++) {
                System.out.print(" ");
            }
            System.out.println(c);
            
            System.out.print(n < 0?"  " : "");
            for (int i = 0; i < (n > 0?n : -n) - 2; i++) {
                System.out.print(c);
            }
            System.out.println();

            System.out.print(n < 0?" ":"");
            System.out.print(c);
            for (int j = 0; j < (n > 0?n : -n) - 3; j++) {
                System.out.print(" ");
            }
            System.out.println(c);
            
            for (int i = 0; i < (n > 0?n : -n) * 0.5 - 3.5; i++) {
                System.out.print(c);
                for (int j = 0; j < (n > 0? n : -n) - 2; j++) {
                    System.out.print(" ");
                }
                System.out.println(c);
            }

            System.out.print(n < 0?" " + c : c);
            for (int j = 0; j < (n > 0?n : -n) - 3; j++) {
                System.out.print(" ");
            }
            System.out.println(c);

            System.out.print(n < 0?"  " : "");
            for (int i = 0; i < (n > 0?n : -n) - 2; i++) {
                System.out.print(c);
            }
            System.out.println();
        }
    }
    /*              B
    ccccccccccc         ..ccccccccccc
    c..........c        .c..........c
    c...........c       c...........c
    c...........c       c...........c
    c...........c       c...........c
    c..........c        .c..........c
    ccccccccccc         ..ccccccccccc
    c..........c        .c..........c
    c...........c       c...........c
    c...........c       c...........c
    c...........c       c...........c
    c..........c        .c..........c
    ccccccccccc         ..ccccccccccc


    ccccccccc           ..ccccccccc
    c........c          .c........c
    c.........c         c.........c
    c.........c         c.........c
    c........c          .c........c
    ccccccccc           ..ccccccccc
    c........c          .c........c
    c.........c         c.........c
    c.........c         c.........c
    c........c          .c........c
    ccccccccc           ..ccccccccc


    ccccccc             ..ccccccc
    c......c            .c......c
    c.......c           c.......c
    c......c            .c......c
    ccccccc             ..ccccccc
    c......c            .c......c
    c.......c           c.......c
    c......c            .c......c
    ccccccc             ..ccccccc


    ccccc               ..ccccc
    c....c              .c....c
    c....c              .c....c
    ccccc               ..ccccc
    c....c              .c....c
    c....c              .c....c
    ccccc               ..ccccc

    */
}
