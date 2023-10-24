import java.util.LinkedList;
import java.util.List;

public class Solution {
    public List<Integer> largestValues(TreeNode root) {
        if (root == null) {
            return null;
        }

        List<Integer> ans = new LinkedList<>(List.of(root.val));


        return ans;
    }
}