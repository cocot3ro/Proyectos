public class App {
    public static void main(String[] args) throws Exception {
        imprimirP(-9, '#');
    }

    public static void imprimirP(int n, char c) {
        if ((n > 3 || n < -3) && (n % 2 != 0)) {
            for (int i = 0; i < (n > 0?0 : 1); i++) {
                System.out.print(" ");
            }
            for (int i = 0; i < (n > 0?n : -n) - 2; i++) {
                System.out.print(c);
            }
            System.out.println();

            
            for (int i = 0; i < 0.5 * (n > 0?n : -n) - 1.5; i++) {
                System.out.print(c);
                for (int j = 0; j < (n > 0?n : -n) - 3; j++) {
                    System.out.print(" ");
                }
                System.out.println(c);
            }

            for (int i = 0; i < (n > 0?0 : 1); i++) {
                System.out.print(" ");
            }
            for (int i = 0; i < (n > 0?n : -n) - 2; i++) {
                System.out.print(c);
            }
            System.out.println();

            for (int i = 0; i < (n > 0?n : -n) / 2; i++) {
                for (int j = 0; j < -n - 2; j++) {
                    System.out.print(" ");
                }
                System.out.println(c);
            }

        }
    }

    /*         P

    ccccccc         .ccccccc
    c......c        c......c
    c......c        c......c
    c......c        c......c
    ccccccc         .ccccccc
    c               .......c
    c               .......c
    c               .......c
    c               .......c


    ccccc           .ccccc
    c....c          c....c
    c....c          c....c
    ccccc           .ccccc
    c               .....c
    c               .....c
    c               .....c


    ccc             .ccc
    c..c            c..c
    ccc             .ccc
    c               ...c
    c               ...c

    */
}
