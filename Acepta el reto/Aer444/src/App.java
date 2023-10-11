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

            StringBuilder sb = new StringBuilder();
            boolean b = false;
            for (int i = 0; i < numMediciones; i++) {
                if (sc.nextInt() == 0) {
                    if (b) {
                        sb.append(0);
                    }
                } else {
                    b = true;
                    sb.append(1);
                }
            }

            while (sb.toString().endsWith("0")) {
                sb.deleteCharAt(sb.length() - 1);
            }

            int max = longestLine(sb.toString(), tolerancia);

            System.out.println(max);

        }

        sc.close();
    }

    private static int longestLine(String s, int tolerancia) {
        //System.err.println("String: " + s);
        int size = s.length();
        if (isValid(s, tolerancia)) {
            return size;
        }
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < i + 1; j++) {
                String subString = s.substring(j, size - i + j);
                //System.err.print("subString: " + subString + " -> ");
                if (isValid(subString, tolerancia)) {
                    //System.err.println(true);
                    return subString.length();
                }
                //System.err.println(false);
            }
        }

        return 0;
    }

    private static boolean isValid(String substring, int tolerancia) {
        StringBuilder sb = new StringBuilder("0");
        for (int i = 0; i < tolerancia; i++) {
            sb.append("0");
        }
        return !substring.contains(sb.toString());
    }
}
