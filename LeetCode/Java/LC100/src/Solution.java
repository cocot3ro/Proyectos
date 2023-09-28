public class Solution {
    public boolean isSameTree(TreeNode p, TreeNode q) {
        String pTree = toString(p);
        String qTree = toString(q);
        return pTree.equals(qTree);
    }

    private String toString(TreeNode node) {
        if (node == null) {
            return "";
        }

        String str = "";
        str += toString(node.left) + ";";
        str += node.val + ";";
        str += toString(node.right) + ";";

        return str;
    }
}