public class Main {
    static int[][] P;

    public static void main(String[] args) {
        int[] data = {0,1,100,1,5,9,0,3,2,100,100,100,0,4,100,100,100,2,0,3,3,100,100,100,0};
        int index = 0;
        int[][] W = new int[6][6];
        for(int i=1; i<=5; i++) {
            for(int j=1; j<=5; j++) {
                W[i][j] = data[index++];
            }
        }

        int[][] D = new int[6][6];
        P = new int[6][6];

        floyd(5, W, D, P);

        System.out.println("5에서 3까지 가는 최단경로");
        System.out.print("v" + 5);
        path(5,3);
        System.out.print(" v" + 3);
    }

    static void floyd(int n, int[][] W, int[][] D, int[][] P) {
        int i,j,k;
        for(i=1; i<=n; i++)
            for(j=1; j<=n; j++)
                P[i][j] = 0;

        D = W;
        for(k=1; k<=n; k++)
            for(i=1; i<=n; i++)
                for(j=1; j<=n; j++)
                    if(D[i][k] + D[k][j] < D[i][j]) {
                        P[i][j] = k;
                        D[i][j] = D[i][k] + D[k][j];
                    }

        /*for(i=1; i<=n; i++) {
            for(j=1; j<=n; j++) {
                System.out.print(W[i][j]  + " ");
            }
            System.out.println();
        }
        System.out.println();
        for(i=1; i<=n; i++) {
            for(j=1; j<=n; j++) {
                System.out.print(D[i][j]  + " ");
            }
            System.out.println();
        }
        System.out.println();
        for(i=1; i<=n; i++) {
            for(j=1; j<=n; j++) {
                System.out.print(P[i][j]  + " ");
            }
            System.out.println();
        }*/
    }

    static void path(int q, int r) {
        if(P[q][r] != 0) {
            path(q, P[q][r]);
            System.out.print(" v" + P[q][r]);
            path(P[q][r], r);
        }
    }
}