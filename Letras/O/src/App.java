public class App {
    public static void main(String[] args) throws Exception {
        imprimirO(11, '#');
    }

    public static void imprimirO(int n, char c) {
        if (n < 0) {n = -n;}
        if ((n > 5) && (n % 2 != 0)) {
            for (int i = 0; i < 2; i++) {
                System.out.print(" ");
            }
            for (int i = 0; i < n - 4; i++) {
                System.out.print(c);
            }
            System.out.println();

            System.out.print(" ");
            System.out.print(c);
            for (int i = 0; i < n - 4; i++) {
                System.out.print(" ");
            }
            System.out.println(c);

            for (int i = 0; i < 0.5 * n - 2.5; i++) {
                System.out.print(c);
                for (int j = 0; j < n - 2; j++) {
                    System.out.print(" ");
                }
                System.out.println(c);
            }

            System.out.print(" ");
            System.out.print(c);
            for (int i = 0; i < n - 4; i++) {
                System.out.print(" ");
            }
            System.out.println(c);

            for (int i = 0; i < 2; i++) {
                System.out.print(" ");
            }
            for (int i = 0; i < n - 4; i++) {
                System.out.print(c);
            }
            System.out.println();
        }
    }

    /*   O 

    ..ccccccc
    .c.......c
    c.........c
    c.........c
    c.........c
    .c.......c
    ..ccccccc
    
    
    ..ccccc
    .c.....c
    c.......c
    c.......c
    .c.....c
    ..ccccc
    
    
    ..ccc
    .c...c
    c.....c
    .c...c
    ..ccc

    */
}
