public class App {
    public static void main(String[] args) throws Exception {
        imprimirT(7, '#');
    }

    public static void imprimirT(int n, char c) {
        if (n < 0) {n = -n;}
        if ((n > 3) && (n % 2 != 0)) {
            for (int i = 0; i < n; i++) {
                System.out.print(c);
            }
            System.out.println();

            for (int i = 0; i < n - 1; i++) {
                for (int j = 0; j < n / 2; j++) {
                    System.out.print(" ");
                }
                System.out.println(c);
            }
        }
    }
    /*T
    
    ccccccc
    ...c
    ...c
    ...c
    ...c
    ...c
    ...c

    ccccc
    ..c
    ..c
    ..c
    ..c


    ccc
    .c
    .c
    */
}
