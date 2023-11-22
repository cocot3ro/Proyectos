import java.io.File;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner sc1 = new Scanner(new File("expected.txt"));
        Scanner sc2 = new Scanner(new File("result.txt"));

        int i = 0;
        while (true) {
            int i1 = sc1.nextInt();
            int i2 = sc2.nextInt();
            if (i1 != i2) {
                System.out.println("i1 = " + i1);
                System.out.println("i2 = " + i2);
                System.out.println("i = " + i);
                break;
            } else {
                i++;
            }
        }
    }
}
