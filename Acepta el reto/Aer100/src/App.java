import java.math.BigInteger;
import java.util.Arrays;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        outer:
        for (int i = sc.nextInt(); i > 0; i--) {
            String num = String.format("%04d", sc.nextInt());
            char[] chars = num.toCharArray();
            Arrays.sort(chars);
            num = new String(chars);

            BigInteger b1 = new BigInteger(new StringBuilder(num).reverse().toString());
            BigInteger b2 = new BigInteger(num);

            BigInteger b3;
            int ans = 0;
            while (!(b3 = b1.subtract(b2)).equals(new BigInteger("6174"))) {
                if (b3.equals(new BigInteger("0"))) {
                    System.out.println(8);
                    continue outer;
                }
                ans++;

                num = String.format("%04d", Integer.parseInt(b3.toString()));
                chars = num.toCharArray();
                Arrays.sort(chars);
                num = new String(chars);

                b1 = new BigInteger(new StringBuilder(num).reverse().toString());
                b2 = new BigInteger(num);
            }
            System.out.println(ans != 0 ? ans + 1 : ans);
        }

        sc.close();
    }
}
