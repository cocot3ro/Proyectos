import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int numMediciones, tolerancia;

        while ((numMediciones = sc.nextInt()) != 0) {
            if (numMediciones == 1) {
                System.out.println(1);
                continue;
            }
            tolerancia = sc.nextInt();

            boolean[] mediciones = new boolean[numMediciones];
            for (int i = 0; i < numMediciones; i++) {
                mediciones[i] = sc.nextInt() == 1;
            }

            int max = 0;
            int curr = 0;
            int contadorFrio = 0;
            for (int i = 0; i < numMediciones; i++) {
                if (mediciones[i]) {
                    contadorFrio = 0;
                    curr++;
                } else {
                    contadorFrio++;
                }
                
                if (contadorFrio > tolerancia) {
                    max = Math.max(max, curr);
                    curr = 0;
                }
            }

            System.out.println(max);
        }

        sc.close();
    }
}
