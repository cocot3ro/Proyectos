import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String s;
        while (!(s  = sc.nextLine()).startsWith(".")) {
            Map<Character, Integer> map = new HashMap<>();
            for (char c : s.substring(0, s.length() - 2).toCharArray()) {
                switch (c) {
                    case 'V': case 'I':
                        map.put(c, (map.get(c) == null ? 1 : map.get(c) + 1));
                        break;
                    case 'A':
                        map.put('V', (map.get('V') == null ? 1 : map.get('V') + 1));
                        map.put('I', (map.get('I') == null ? 1 : map.get('I') + 1));
                        break;
                }
            }

            Integer prendasVerano = map.get('V');
            if (prendasVerano == null) {
                prendasVerano = 0;
            }
            Integer prendasInvierno = map.get('I');
            if (prendasInvierno == null) {
                prendasInvierno = 0;
            }

            if (prendasVerano > prendasInvierno) {
                System.out.println("VERANO");
            } else if (prendasInvierno > prendasVerano) {
                System.out.println("INVIERNO");
            } else {
                System.out.println("EMPATE");
            }
        }
    }
}
