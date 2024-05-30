import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[] W;
    static int[] V;
    static int[][] P;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        StringTokenizer st = new StringTokenizer(s, " ");
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        W = new int[N+1];
        V = new int[N+1];
        P = new int[N+1][K+1];

        for(int i=1; i<=N; i++) {
            s = br.readLine();
            st = new StringTokenizer(s, " ");
            W[i] = Integer.parseInt(st.nextToken());
            V[i] = Integer.parseInt(st.nextToken());
        }

        System.out.println(knapsack(N, K));
    }

    static int knapsack(int i, int K) {
        if(i < 1) return 0;
        if(W[i] <= K)
            return Math.max(knapsack(i-1, K), V[i] + knapsack(i-1, K-W[i]));
        else
            return knapsack(i-1, K);
    }
}
