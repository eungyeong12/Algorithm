// https://leetcode.com/problems/insertion-sort-list/description

import java.sql.SQLOutput;
import java.util.Scanner;

class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}
public class Solution {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("데이터의 수? ");
        int n = sc.nextInt();
        System.out.println(n + "개의 정수 데이터? ");
        ListNode head = null, rear = null;
        while(n > 0) {
            int data = sc.nextInt();
            if(head == null) {
                head = new ListNode(data);
                rear = head;
            } else {
                rear.next = new ListNode(data);
                rear = rear.next;
            }
            n -= 1;
        }
        Solution2 sol = new Solution2();
        head = sol.insertionSortList(head);
        printList(head);
        sc.close();
    }

    static void printList(ListNode head) {
        System.out.println("[ ");
        for(ListNode ptr = head; ptr != null; ptr = ptr.next)
            System.out.println(ptr.val + " ");
    }
}

class Solution2 {
    public ListNode insertionSortList(ListNode head) {
        ListNode rear = null, ptr; // rear: 역순으로 정렬
        while(head != null) {
            ListNode next = head.next;
            if(rear == null) {
                head.next = null;
                rear = head;
            } else if(head.val >= rear.val) {
                head.next = rear;
                rear = head;
            } else {
                for(ptr = rear; ptr.next != null; ptr = ptr.next) {
                    if(head.val >= ptr.next.val) {
                        head.next = ptr.next;
                        ptr.next = head;
                        break;
                    }
                }
                if(ptr.next == null) {
                    head.next = null;
                    ptr.next = head;
                }
            }
            head = next;

        }
        return invert(rear);
    }

    private ListNode invert(ListNode lead) {
        ListNode middle = null, tail;
        while(lead != null) {
            tail = middle;
            middle = lead;
            lead = lead.next;
            middle.next = tail;
        }
        return middle;
    }
}


