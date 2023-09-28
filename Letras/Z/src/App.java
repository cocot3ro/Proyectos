public class App {
    public static void main(String[] args) {
        imprimirZ(-9, '#');
    }

    public static void imprimirZ(int n, char c) {
        if ((n > 3 || n < -3) && (n % 2 != 0)) {
            for (int i = 0; i < (n > 0 ? n : -n); i++) {
                System.out.print(c);
            }
            System.out.println();

            for (int i = 0; i < (n > 0 ? n : -n) - 2; i++) {
                for (int j = 0; j < (n > 0 ? n - 2 - i : 1 + i); j++) {
                    System.out.print(" ");
                }
                System.out.println(c);
            }

            for (int i = 0; i < (n > 0 ? n : -n); i++) {
                System.out.print(c);
            }
            System.out.println();
        }
    }

    /*          Z
    
    ccccccccc       ccccccccc
    .......c-       .c-------
    ......c--       ..c------
    .....c---       ...c-----
    ....c----       ....c----
    ...c-----       .....c---
    ..c------       ......c--
    .c-------       .......c-
    ccccccccc       ccccccccc


    ccccccc         ccccccc
    .....c-         .c-----
    ....c--         ..c----
    ...c---         ...c---
    ..c----         ....c--
    .c-----         .....c-
    ccccccc         ccccccc


    ccccc           ccccc
    ...c-           .c---
    ..c--           ..c--
    .c---           ...c-
    ccccc           ccccc

    */
}
