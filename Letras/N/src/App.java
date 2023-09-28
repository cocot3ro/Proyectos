public class App {
    public static void main(String[] args) throws Exception {
        imprimirN(11, '#');
    }

    public static void imprimirN(int n, char c) {
        if ((n > 3 || n < -3) && (n % 2 != 0)) {
            System.out.print(c);
            for (int i = 0; i < (n > 0? n : -n) - 2; i++) {
                System.out.print(" ");
            }
            System.out.println(c);

            for (int i = 0; i < (n > 0?n : -n) - 2; i++) {
                System.out.print(c);
                for (int j = 0; j < (n > 0? 0 + i : -n - 3 - i); j++) {
                    System.out.print(" ");
                }
                System.out.print(c);
                for (int j = 0; j < (n > 0? n - 3 - i: 0 + i); j++) {
                    System.out.print(" ");
                }
                System.out.println(c);
            }

            System.out.print(c);
            for (int i = 0; i < (n > 0? n : -n) - 2; i++) {
                System.out.print(" ");
            }
            System.out.println(c);
        }
    }

    /*          N
    
    c       c       c       c
    cc      c       c      cc
    c c     c       c     c c
    c  c    c       c    c  c
    c   c   c       c   c   c
    c    c  c       c  c    c
    c     c c       c c     c
    c      cc       cc      c
    c       c       c       c


    c     c         c     c
    cc    c         c    cc
    c c   c         c   c c
    c  c  c         c  c  c
    c   c c         c c   c
    c    cc         cc    c
    c     c         c     c


    c   c           c   c
    cc  c           c  cc
    c c c           c c c
    c  cc           cc  c
    c   c           c   c

    */
}
