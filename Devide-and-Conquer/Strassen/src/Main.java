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
        int m = Integer.parseInt(st.nextToken());

        int[][] a = new int[128][128];
        for(int i=0; i<n; i++) {
            s = br.readLine();
            st = new StringTokenizer(s, " ");
            for(int j=0; j<m; j++) {
                a[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        s = br.readLine();
        st = new StringTokenizer(s, " ");

        m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[][] b = new int[128][128];
        for(int i=0; i<m; i++) {
            s = br.readLine();
            st = new StringTokenizer(s, " ");
            for(int j=0; j<k; j++) {
                b[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int big = Math.max(Math.max(n, k), m);

        int size = 1;
        while(true) {
            if(size >= big) {
                break;
            }
            size *= 2;
        }

        int[][] c = strassen(a, b, size);

        StringBuilder sb = new StringBuilder();

        // 출력
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < k; j++) {
                sb.append(c[i][j] + " ");
            }
            sb.append('\n');
        }

        System.out.println(sb);
    }

    static int[][] matrixmult(int[][] a, int[][] b, int size) {
        int res[][] = new int[size][size];
        for(int i=0; i<size; i++) { // a의 i번째 row
            for (int j = 0; j < size; j++) { // b의 j번째 col
                for (int x = 0; x < size; x++) { // m번 곱해야함
                    res[i][j] += a[i][x] * b[x][j];
                }
            }
        }
        return res;
    }

    static int[][] strassen(int[][] a, int[][] b, int size) {


        int[][] c = new int[size][size];

        if(size <= 16) { // 임계점
            return c = matrixmult(a, b, size);
        }

        int newSize = size / 2;	// 부분행렬에 대한 사이즈

        // A의 부분행렬
        int[][] a11 = subArray(a, 0, 0, newSize);
        int[][] a12 = subArray(a, 0, newSize, newSize);
        int[][] a21 = subArray(a, newSize, 0, newSize);
        int[][] a22 = subArray(a,newSize, newSize, newSize);

        // A의 부분행렬
        int[][] b11 = subArray(b, 0, 0, newSize);
        int[][] b12 = subArray(b, 0, newSize, newSize);
        int[][] b21 = subArray(b, newSize, 0, newSize);
        int[][] b22 = subArray(b, newSize, newSize, newSize);



        // M1 := (A11 + A22) * (B11 + B22)
        int[][] M1 = strassen(add(a11, a22, newSize), add(b11, b22, newSize), newSize); // 더 작은 부분행렬로 나누기 위해 재귀호출

        // M2 := (A21 + A22) * B11
        int[][] M2 = strassen(add(a21, a22, newSize), b11, newSize);

        // M3 := A11 * (B12 - B22)
        int[][] M3 = strassen(a11, sub(b12, b22, newSize), newSize);

        // M4 := A22  * (B21 − B11)
        int[][] M4 = strassen(a22, sub(b21, b11, newSize), newSize);

        // M5 := (A11 + A12) * B22
        int[][] M5 = strassen(add(a11, a12, newSize), b22, newSize);

        // M6 := (A21 - A11) * (B11 + B12)
        int[][] M6 = strassen(sub(a21, a11, newSize), add(b11, b12, newSize), newSize);

        // M7 := (A12 - A22) * (B21−B22)
        int[][] M7 = strassen(sub(a12, a22, newSize), add(b21, b22, newSize), newSize);



        // C11 := M1 + M4 − M5 + M7
        int[][] c11 = add(sub(add(M1, M4, newSize), M5, newSize), M7, newSize);

        // C12 := M3 + M5
        int[][] c12 = add(M3, M5, newSize);

        // C21 := M2 + M4
        int[][] c21 = add(M2, M4, newSize);

        // C22 := M1 − M2 + M3 + M6
        int[][] c22 = add(add(sub(M1, M2, newSize), M3, newSize), M6, newSize);


        // 구해진 C의 부분행렬들 합치기
        merge(c11, c, 0, 0, newSize);
        merge(c12, c, 0, newSize, newSize);
        merge(c21, c, newSize, 0, newSize);
        merge(c22, c, newSize, newSize, newSize);


        return c;
    }

    // 행렬 뺄셈
    static int[][] sub(int[][] a, int[][] b, int size) {

        int[][] c = new int[size][size];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                c[i][j] = a[i][j] - b[i][j];
            }
        }
        return c;
    }

    // 행렬 덧셈
    static int[][] add(int[][] a, int[][] b, int size) {

        int n = size;

        int[][] c = new int[n][n];

        for (int i = 0; i < n; i++) {

            for (int j = 0; j < n; j++) {
                c[i][j] = a[i][j] + b[i][j];
            }
        }
        return c;
    }


    // 부분행렬을 반환하는 메소드
    static int[][] subArray(int[][] src, int row, int col, int size) {

        int[][] dest = new int[size][size];
        for (int dset_i = 0, src_i = row; dset_i < size; dset_i++, src_i++) {
            for (int dest_j = 0, src_j = col; dest_j < size; dest_j++, src_j++) {
                dest[dset_i][dest_j] = src[src_i][src_j];
            }
        }
        return dest;
    }

    // src는 복사할 배열(=부분배열), dest는 합쳐질 배열(= 배열 C)
    static void merge(int[][] src, int[][] dest, int row, int col, int size) {

        for (int src_i = 0, dest_i = row; src_i < size; src_i++, dest_i++) {
            for (int src_j = 0, dest_j = col; src_j < size; src_j++, dest_j++) {

                dest[dest_i][dest_j] = src[src_i][src_j];
            }
        }
    }
}
