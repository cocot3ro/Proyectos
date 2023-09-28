public class App {
    public static void main(String[] args) throws Exception {
        imprimirH(9, '#');
    }

    public static void imprimirH(int n, char c) {
        if ((n > 1 || n < -1) && (n % 2 != 0)) {
            if (n < 0) {n = -n;}
            
            for (int i = 0; i < n / 2; i++) {
                System.out.print(c);
                for (int j = 0; j < n - 2; j++) {
                    System.out.print(" ");
                }
                System.out.println(c);
            }

            for (int i = 0; i < n; i++) {
                System.out.print(c);
            }
            System.out.println();

            for (int i = 0; i < n / 2; i++) {
                System.out.print(c);
                for (int j = 0; j < n - 2; j++) {
                    System.out.print(" ");
                }
                System.out.println(c);
            }
        }
    }
    /*   H
    
    c.........c
    c.........c
    c.........c
    c.........c
    c.........c
    ccccccccccc
    c.........c
    c.........c
    c.........c
    c.........c
    c.........c


    c.......c
    c.......c
    c.......c
    c.......c
    ccccccccc
    c.......c
    c.......c
    c.......c
    c.......c


    c.....c
    c.....c
    c.....c
    ccccccc
    c.....c
    c.....c
    c.....c


    c...c
    c...c
    ccccc
    c...c
    c...c


    c.c
    ccc
    c.c
    */
}
