public class App {
    public static void main(String[] args) throws Exception {
        imprimirY(9, '#');
    }

    public static void imprimirY(int n, char c) {
        if (n < 0) {n = -n;}
        if ((n > 1) && (n % 2 != 0)) {
            for (int i = 0; i < n / 2; i++) {
                for (int j = 0; j < 0 + i; j++) {
                    System.out.print(" ");
                }
                System.out.print(c);
                for (int j = 0; j < n - 2 - 2 * i; j++) {
                    System.out.print(" ");
                }
                System.out.println(c);
            }

            for (int i = 0; i < n / 2 + 1; i++) {
                for (int j = 0; j < n / 2; j++) {
                    System.out.print(" ");
                }
                System.out.println(c);
            }
        }
    }

    /*  Y
    
    c-------c
    .c-----c-
    ..c---c--
    ...c-c---
    ....c----
    ....c----
    ....c----
    ....c----
    ....c----


    c-----c
    .c---c-
    ..c-c--
    ...c---
    ...c---
    ...c---
    ...c---


    c---c
    .c-c-
    ..c--
    ..c--
    ..c--


    c-c
    .c-
    .c-

    */
}
