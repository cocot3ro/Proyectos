import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (sc.hasNext()) {
            sc.nextInt();
            sc.nextInt();
            int humanosAnalizados = sc.nextInt();
            int humanosIncorrectos = sc.nextInt();
            sc.nextInt();
            sc.nextInt();

            double tasaErrorHumanos = (double) humanosIncorrectos / humanosAnalizados;

            if (tasaErrorHumanos < 0.10) {
                System.out.println("APRUEBA");
            } else {
                System.out.println("SUSPENDE");
            }
        }

        sc.close();
    }
}
