import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// https://www.acmicpc.net/problem/5639
public class Main {
    static class TreeNode {
        int val;
        TreeNode left, right;

        public TreeNode(int value) {
            this.val = value;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        TreeNode root = new TreeNode(Integer.parseInt(br.readLine()));
        while(true) {
            String s = br.readLine();
            if(s == null || s.equals(""))
                break;
            insert(root, Integer.parseInt(s));
        }

        postOrder(root);
    }

    static void insert(TreeNode root, int data) {
        TreeNode ptr = root;
        while(ptr != null) {
            if(data < ptr.val) {
                if(ptr.left == null) {
                    ptr.left = new TreeNode(data);
                    return;
                }
                ptr = ptr.left;
            } else if(data > ptr.val) {
                if(ptr.right == null) {
                    ptr.right = new TreeNode(data);
                    return;
                }
                ptr = ptr.right;
            }
        }
    }

    static void postOrder(TreeNode ptr) {
        if(ptr == null)
            return;
        postOrder(ptr.left);
        postOrder(ptr.right);
        System.out.println(ptr.val);
    }
}