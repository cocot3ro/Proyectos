import java.util.LinkedList;
import java.util.List;

public class Solution {
    public List<Integer> rightSideView(TreeNode root) {
        if (root == null) {
            return new LinkedList<>();
        }

        List<Integer> ans = new LinkedList<>();

        while (root != null) {
            ans.add(root.val);
            root = root.right;
        }

        return ans;
    }
}