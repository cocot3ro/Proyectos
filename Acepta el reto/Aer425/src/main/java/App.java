import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (sc.hasNext()) {
            String palabra = sc.nextLine();

            if (esPalindromo(palabra)) {
                System.out.println(0);
                continue;
            }

            int[] letras = new int[255];
            int impares = 0;

            for (char c : palabra.toCharArray()) {
                letras[c]++;
                impares -= 1 - (letras[c] % 2) * 2;
            }

            if (impares == 0) {
                System.out.println(1);
                continue;
            }

            System.out.println(impares - 1);
        }
    }

    private static boolean esPalindromo(String s) {
        for (int i = 0; i < s.length() / 2; i++) {
            if (s.charAt(i) != (s.charAt(s.length() - i - 1))) {
                return false;
            }
        }

        return true;
    }
}
