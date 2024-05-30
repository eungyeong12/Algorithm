import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Edge {
    int u;
    int v;
    int w;

    public Edge(int u, int v) {
        this.u = u;
        this.v = v;
    }

    public Edge(int u, int v, int w) {
        this.u = u;
        this.v = v;
        this.w = w;
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
        int E_size = Integer.parseInt(st.nextToken());

        ArrayList<Edge> E = new ArrayList<>();
        int i;

        for(i=1; i<=E_size; i++) {
            s = br.readLine();
            st = new StringTokenizer(s, " ");
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            E.add(new Edge(u,v,w));
        }

        ArrayList<Edge> answer = kruskal(V, E.size(), E);
        Iterator it = answer.listIterator();
        int weight = 0;
        while(it.hasNext()) {
            Edge e = (Edge) it.next();
            System.out.println(e);
            weight += e.w;
        }
        System.out.println(weight);
    }

    static ArrayList<Edge> kruskal(int n, int m, ArrayList<Edge> E) {
        int i,j,p,q;
        Edge e;
        ArrayList<Edge> F = new ArrayList<>();
        int[] parent = new int[n+1];

        Collections.sort(E, new Comparator<Edge>() {
            @Override
            public int compare(Edge o1, Edge o2) {
                return o1.w-o2.w;
            }
        });

        // 부모를 자기 자신으로 초기화
        for(i=1; i<n+1; i++) {
            parent[i] = i;
        }

        while(F.size() < n-1) {
            e = E.remove(0);
            i = e.u;
            j = e.v;

            // 특정 원소가 속한 집합을 찾기
            p = find(parent, i);
            q = find(parent, j);

            // 사이클이 발생하지 않는 경우에만 집합에 포함
            if(p != q) {
                union(parent, p, q);
                F.add(e);
            }
        }
        return F;
    }

    static int find(int[] parent, int x) {
        // 루트 노드를 찾을 때까지 재귀 호출
        if(parent[x] != x)
            parent[x] = find(parent, parent[x]);
        return parent[x];
    }

    // 두 원소가 속한 집합을 합치기
    static void union(int[] parent, int a, int b) {
        a = find(parent, a);
        b = find(parent, b);
        if(a < b)
            parent[b] = a;
        else
            parent[a] = b;
    }
}
