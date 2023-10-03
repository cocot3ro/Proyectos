public class Solution {
    public String reverseOnlyLetters(String s) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0, j = s.length(); i < s.length(); i++) {
            if (Character.isLetter(s.charAt(i))) {
                sb.append(s.charAt(nextIndex(s, j)));
            } else {
                sb.append(s.charAt(i));
            }
        }

        return sb.toString();
    }

    private int nextIndex(String s, int currIndex) {
        while (!Character.isLetter(s.charAt(--currIndex)));
        return currIndex;
    }
}
