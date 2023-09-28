import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Solution {
    public int firstMissingPositive(int[] nums) {

        Arrays.sort(nums);

        List<Integer> list = new LinkedList<>();

        for (int num : nums) {
            if (num > 0 && !list.contains(num)) {
                list.add(num);
            }
        }

        boolean b = true;

        int size = list.size();

        if (size == 1) {
            if (list.contains(1)) {
                return 2;
            } else {
                return 1;
            }
        } else if (size == 0) {
            return 1;
        }

        for (int i = 0; i < size; i++) {
            int getI = list.get(i);
            int getI2 = list.get(Math.min(i + 1, size - 1));
            if (getI > 0) {
                if (b) {
                    if (getI != 1) {
                        return 1;
                    } else if (getI + 1 != getI2) {
                        return getI + 1;
                    }
                    b = false;
                } else if (getI + 1 != getI2) {
                    return getI + 1;
                }
            }
        }

        return (b ? 1 : list.get(size - 1) + 1);
    }
}
