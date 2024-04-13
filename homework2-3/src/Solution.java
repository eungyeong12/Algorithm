import javax.management.ListenerNotFoundException;
import java.util.ArrayDeque;
import java.util.Scanner;

// https://leetcode.com/problems/balanced-binary-tree/description/
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
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        ListNode head = new ListNode(sc.nextInt());
        ListNode ptr = head;
        for(int x=0; x<N-1; x++) {
            ptr.next = new ListNode(sc.nextInt());
            ptr = ptr.next;
        }

        TreeNode root = sortedListToBST(head);
        System.out.println(isBalanced(root));
        ArrayDeque<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);
        while(!queue.isEmpty()) {
            TreeNode item = queue.poll();
            System.out.println(item.val + " ");
            if(item.left != null)
                queue.add(item.left);
            if(item.right != null)
                queue.add(item.right);
        }
        sc.close();
    }

    public static TreeNode sortedListToBST(ListNode head) {
        if(head == null)
            return null;
        if(head.next == null)
            return new TreeNode(head.val);
        ListNode first = head, second = head.next.next;
        while(second != null && second.next != null) {
            first = first.next;
            second = second.next;
        }
        ListNode middle = first.next;
        first.next = null;
        TreeNode root = new TreeNode(middle.val);
        root.left = sortedListToBST(head);
        root.right = sortedListToBST(middle.next);
        return root;
    }

    public static boolean isBalanced(TreeNode root) {
        if(root == null)
            return true;
        return isBalanced(root.left) && isBalanced(root.right) &&
                Math.abs(depth(root.left) - depth(root.right)) <= 1;
    }

    private static int depth(TreeNode root) {
        if(root == null)
            return 0;
        return Math.max(depth(root.left), depth(root.right)) + 1;
    }
}
