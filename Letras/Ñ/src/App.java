public class App {
    public static void main(String[] args) throws Exception {
        imprimirÑ(11, '#');
    }

    public static void imprimirÑ(int n, char c) {
        if ((n > 3 || n < -3) && (n % 2 != 0)) {
            for (int i = 0; i < n; i++) {
                System.out.print(c);
            }
            System.out.println("\n");

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

    /*          Ñ
    

    ccccccccc       ccccccccc

    c       c       c       c
    cc      c       c      cc
    c c     c       c     c c
    c  c    c       c    c  c
    c   c   c       c   c   c
    c    c  c       c  c    c
    c     c c       c c     c
    c      cc       cc      c
    c       c       c       c



    ccccccc         ccccccc

    c     c         c     c
    cc    c         c    cc
    c c   c         c   c c
    c  c  c         c  c  c
    c   c c         c c   c
    c    cc         cc    c
    c     c         c     c


     
    ccccc           ccccc

    c   c           c   c
    cc  c           c  cc
    c c c           c c c
    c  cc           cc  c
    c   c           c   c

    */
}
