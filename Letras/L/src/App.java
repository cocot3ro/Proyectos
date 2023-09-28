public class App {
    public static void main(String[] args) throws Exception {
        imprimirL(-9, '#');
    }
    
    public static void imprimirL(int n, char c) {
        if (n > 1 || n < -1) {
            for (int i = 0; i < (n > 0? n : -n) - 1; i++) {
                for (int j = 0; j < (n > 0? 0 : -n - 1); j++) {
                    System.out.print(" ");
                }
                System.out.println(c);
            }

            for (int i = 0; i < (n > 0? n : -n); i++) {
                System.out.print(c);
            }
            System.out.println();
        }
    }

    /*           L
    
    c               .........c         
    c               .........c         
    c               .........c         
    c               .........c         
    c               .........c         
    c               .........c         
    c               .........c         
    c               .........c         
    cccccccccc      cccccccccc


    c               ......c      
    c               ......c      
    c               ......c      
    c               ......c      
    c               ......c      
    c               ......c      
    ccccccc         ccccccc


    c               ....c
    c               ....c
    c               ....c
    c               ....c
    ccccc           ccccc


    c               ..c
    c               ..c
    ccc             ccc

    */
}
