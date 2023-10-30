import java.util.*;

public class Solution {
    public int[] sortByBits(int[] arr) {
        Map<Integer, Map<Integer, Integer>> map = new TreeMap<>();

        for (int i : arr) {
            int count = countBits(i);
            Map<Integer, Integer> subMap = map.get(count);
            if (subMap == null) {
                map.put(count, new TreeMap<>(Map.of(i, 1)));
            } else {
                subMap.put(i, subMap.getOrDefault(i, 0) + 1);
            }
        }

        for (Map.Entry<Integer, Map<Integer, Integer>> subMap : map.entrySet()) {

        }

        return arr;
    }

    private int countBits(int val) {
        return String.valueOf(val).replace("0", "").length();
    }
}
