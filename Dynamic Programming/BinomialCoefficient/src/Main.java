import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        StringTokenizer st = new StringTokenizer(s, " ");
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        System.out.println(bin(n, k));
    }

    static int bin(int n, int k) {
        int[][] b = new int[n+1][k+1];

        for(int i=0; i<=n; i++)
            for(int j=0; j<=Math.min(i, k); j++) {
                if (j == 0 || j == i)
                    b[i][j] = 1;
                else
                    b[i][j] = b[i-1][j-1] + b[i-1][j];
            }

        return b[n][k];
    }
}