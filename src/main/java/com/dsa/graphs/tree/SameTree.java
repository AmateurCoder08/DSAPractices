/**
 * Link: https://leetcode.com/problems/same-tree/
 *
 * Time Complexity: O(min(N,M)). N - Number of nodes in tree 1 and M - Number of nodes in tree 2
 * Space Complexity: O(min(H1, H2)). H1 - Height of tree 1 and H2 - Height of tree 2
 */
package com.dsa.graphs.tree;

public class SameTree {

    public static void main(String[] args) {
        TreeNode rootOfR = new TreeNode(2, new TreeNode(4), null);
        TreeNode rootOfP = new TreeNode(1, rootOfR, new TreeNode(3));
        TreeNode rootOfQ = new TreeNode(1, new TreeNode(2), new TreeNode(3));

        if (isSameTree(rootOfP, rootOfQ)) {
            System.out.println("The given trees are the same");
        } else {
            System.out.println("The given trees are not the same");
        }
    }

    public static boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        } else if (p != null && q != null) {
            if (p.val != q.val)
                return false;

            if (!isSameTree(p.left, q.left))
                return false;

            if (!isSameTree(p.right, q.right))
                return false;

            return true;

        } else {
            return false;
        }
    }

}
