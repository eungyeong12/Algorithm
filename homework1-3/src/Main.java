import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

// https://www.acmicpc.net/problem/18870
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        int[] A = new int[n];
        int[] B = new int[n];
        for(int i=0; i<n; i++)
            B[i] = A[i] = sc.nextInt();
        sc.close();

        Arrays.sort(B);

        HashMap<Integer, Integer> map = new HashMap<>();
        int prev = B[0] - 1;
        int index = 0;
        for(int i=0; i<n; i++) {
            if(B[i] != prev) {
                map.put(B[i], index);
                prev = B[i];
                index += 1;
            }
        }

        StringBuilder sb = new StringBuilder();
        for(int i=0; i<n; i++)
            sb.append(map.get(A[i])).append(" ");
        System.out.println(sb.toString());
    }
}