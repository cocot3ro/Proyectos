public class App {
    public static void main(String[] args) throws Exception {
        imprimirM(9, '#');
    }

    public static void imprimirM(int n, char c) {
        if ((n > 3 || n < -3) && (n % 2 != 0)) {
            if (n < 0) {n = -n;}

            System.out.print(c);
            for (int i = 0; i < n - 2; i++) {
                System.out.print(" ");
            }
            System.out.println(c);

            for (int i = 0; i < n / 2 - 1; i++) {
                System.out.print(c);
                for (int j = 0; j < 0 + i; j++) {
                    System.out.print(" ");
                }
                System.out.print(c);
                for (int j = 0; j < n - 4 - 2 * i; j++) {
                    System.out.print(" ");
                }
                System.out.print(c);
                for (int j = 0; j < 0 + i; j++) {
                    System.out.print(" ");
                }
                System.out.println(c);
            }

            System.out.print(c);
            for (int i = 0; i < 0.5 * n - 1.5; i++) {
                System.out.print(" ");
            }
            System.out.print(c);
            for (int i = 0; i < 0.5 * n - 1.5; i++) {
                System.out.print(" ");
            }
            System.out.println(c);

            for (int i = 0; i < n / 2; i++) {
                System.out.print(c);
                for (int j = 0; j < n - 2; j++) {
                    System.out.print(" ");
                }
                System.out.println(c);
            }
            
        }
    }

    /*  M
     
    c       c
    cc     cc
    c c   c c
    c  c c  c
    c   c   c
    c       c
    c       c
    c       c
    c       c


    c     c
    cc   cc
    c c c c
    c  c  c
    c     c
    c     c
    c     c


    c   c
    cc cc
    c c c
    c   c
    c   c

    */
}
