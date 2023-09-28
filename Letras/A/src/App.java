public class App {
    public static void main(String[] args) throws Exception {
        imprimirA(15, '#');
    }

    public static void imprimirA(int n, char c) {
        if ((n > 3 || n < -3) && (n % 2 != 0)) {
            if (n < 0) {n = -n;}
            
            for (int i = 0; i < n-1; i++) {
                System.out.print(" ");
            }
            System.out.println(c);

            for (int i = 0; i < n / 2; i++) {
                for (int j = 0; j < n - 2 - i; j++) {
                    System.out.print(" ");
                }
                System.out.print(c);
                for (int j = 0; j < 2 * i + 1; j++) {
                    System.out.print(" ");
                }
                System.out.println(c);
            }

            for (int i = 0; i < n / 2 - 1; i++) {
                System.out.print(" ");
            }
            for (int i = 0; i < n + 2; i++) {
                System.out.print(c);
            }
            System.out.println();

            for (int i = 0; i < n / 2 - 1; i++) {
                for (int j = 0; j < n / 2 - 2 - i; j++) {
                    System.out.print(" ");
                }
                System.out.print(c);                
                
                for (int j = 0; j < 2 * i + n + 2 ; j++) {
                    System.out.print(" ");
                }
                System.out.println(c);
            }
        }
    }
    /* A
    ..........c
    .........c.c
    ........c...c
    .......c.....c
    ......c.......c
    .....c.........c
    ....ccccccccccccc
    ...c.............c
    ..c...............c
    .c.................c
    c...................c
    
    
    ........c
    .......c.c
    ......c...c
    .....c.....c
    ....c.......c
    ...ccccccccccc
    ..c...........c
    .c.............c
    c...............c


    ......c
    .....c.c
    ....c...c
    ...c.....c
    ..ccccccccc
    .c.........c
    c...........c


    ....c
    ...c.c
    ..c...c
    .ccccccc
    c.......c
    */
}
