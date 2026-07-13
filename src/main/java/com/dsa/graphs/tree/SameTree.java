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
