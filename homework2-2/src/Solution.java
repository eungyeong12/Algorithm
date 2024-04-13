// https://leetcode.com/problems/convert-sorted-list-to-binary-search-tree/description/

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) {
        this.val = val;
    }
    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) {
        this.val = val;
    }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = left.right;;
    }
}

public class Solution {
    public TreeNode sortedListToBST(ListNode head) {

        int N = 0;
        for(ListNode ptr = head; ptr!=null; ptr=ptr.next) {
            N++;
        }
        int[] A = new int[N];
        int i = 0;

        for(ListNode ptr = head; ptr!=null; ptr=ptr.next) {
            A[i] = ptr.val;
            i += 1;
        }

        return arraytoBst(A, 0, N-1);
    }

    private static TreeNode arraytoBst(int[] A, int first, int last) {
        if(first > last)
            return null;
        int middle = (first + last) / 2;
        TreeNode root = new TreeNode(A[middle]);
        root.left = arraytoBst(A, first, middle-1);
        root.right = arraytoBst(A, middle+1, last);
        return root;
    }
}
