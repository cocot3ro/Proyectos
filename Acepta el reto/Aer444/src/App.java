import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
/*
6 0
0 1 0 1 0 0
6 0
0 1 0 1 1 0
8 1
1 0 0 1 0 1 0 1
9 1
1 0 0 1 0 1 0 1 0
11 1
1 0 0 1 0 1 0 1 0 0 1
0 0
*/

        int numMediciones, tolerancia;

        while ((numMediciones = sc.nextInt()) != 0) {
            tolerancia = sc.nextInt();

            if (numMediciones == 1) {
                System.out.println(sc.nextInt());
            }

            boolean[] mediciones = new boolean[numMediciones];
            for (int i = 0; i < numMediciones; i++) {
                mediciones[i] = sc.nextInt() == 1;
            }

            int max = getMax(mediciones, tolerancia);

            System.out.println(max);
        }

        sc.close();
    }

    private static int getMax(boolean[] mediciones, int tolerancia) {
        int size = mediciones.length;

        int firstIndex = 0;
        for (int i = 0; i < size; i++) {
            if (mediciones[i]) {
                firstIndex = i;
                break;
            }
        }

        int lastIndex = -1;
        for (int i = 0; i < size; i++) {
            if (mediciones[size - 1 - i]) {
                lastIndex = size - i;
                break;
            }
        }

        int max = 0, curr = 0, contadorFrio = 0;
        for (int i = firstIndex; i < lastIndex; i++) {
            curr++;
            if (mediciones[i]) {
                contadorFrio = 0;
            } else {
                contadorFrio++;
            }

            if (contadorFrio > tolerancia) {
                curr = 0;
            } else {
                max = Math.max(max, curr);
            }
        }
        return max;
    }

}
