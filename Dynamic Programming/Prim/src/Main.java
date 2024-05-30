import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.StringTokenizer;

class Edge {
    int u;
    int v;

    public Edge(int u, int v) {
        this.u = u;
        this.v = v;
    }

    @Override
    public String toString() {
        return "(" + u +
                ", " + v +
                ")";
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        StringTokenizer st = new StringTokenizer(s, " ");
        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());

        int[][] W = new int[V+1][V+1];
        int i;

        for(i=1; i<=E; i++) {
            s = br.readLine();
            st = new StringTokenizer(s, " ");
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            W[u][v] = w;
        }

        ArrayList<Edge> answer = prim(V, W);
        Iterator it = answer.listIterator();
        int weight = 0;
        while(it.hasNext()) {
            Edge e = (Edge) it.next();
            System.out.println(e);
            weight += W[e.u][e.v];
        }
        System.out.println("최소비용: " + weight);
    }

    static ArrayList<Edge> prim(int n, int[][] W) {
        int i;
        int candidate = 0;
        int[] nearest = new int[n+1];
        int min;
        int[] distance = new int[n+1];
        ArrayList<Edge> F = new ArrayList<>();
        Edge e;

        for(i=2; i<=n; i++) {
            nearest[i] = 1;
            distance[i] = W[1][i];
        }

        for(int p=0; p<n-1; p++) {
            min = 1000000;
            for(i=2; i<=n; i++) {
                if(distance[i] > -1000000 && distance[i] < 1000000) {
                    if(distance[i] < min) {
                        min = distance[i];
                        candidate = i;
                    }
                }
            }
            e = new Edge(nearest[candidate], candidate);
            F.add(e);
            distance[candidate] = -1000000;
            for(i=2; i<=n; i++) {
                if(W[candidate][i] < distance[i]) {
                    distance[i] = W[candidate][i];
                    nearest[i] = candidate;
                }
            }
        }
        return F;
    }
}
