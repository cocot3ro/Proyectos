public class Solution {
    public String convert(String s, int numRows) {

        if (numRows == 1) {
            return s;
        }

        String[][] zigzag = new String[numRows][Math.max(s.length() / 2, 1)];

        int row = 0;
        int col = 0;
        boolean b = true;
        for (int i = 0; i < s.length(); i++) {
            if (b) {
                zigzag[row++][col] = String.valueOf(s.charAt(i));
                if (row == numRows) {
                    col++;
                    row-=2;
                    b = false;
                }
            } else {
                zigzag[row--][col++] = String.valueOf(s.charAt(i));
                if (row == 0) {
                    b = true;
                }
            }
        }

        StringBuilder str = new StringBuilder();
        for (String[] string : zigzag) {
            for (String aString : string) {
                str.append((aString == null ? "" : aString));
            }
        }

        return str.toString();
    }
}
