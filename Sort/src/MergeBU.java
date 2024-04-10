public class MergeBU extends AbstractSort {
    private static void merge(Comparable[] in, Comparable[] out, int lo, int mid, int hi) {
        int i = lo, j = mid+1;
        for(int k=lo; k<=hi; k++) {
            if(i > mid)
                out[k] = in[j++]; // in을 병합하여 out에 저장
            else if(j > hi)
                out[k] = in[i++];
            else if(less(in[j], in[i]))
                out[k] = in[j++];
            else
                out[k] = in[i++];
        }
    }

    public static void sort(Comparable[] a) {
        Comparable[] src = a, dst = new Comparable[a.length], tmp;
        int N = a.length;
        for(int n=1; n<N; n*=2) { // n: 병합할 부분 배열의 크기
            for(int i=0; i<N; i+=2*n) // i: 병합할 부분 배열의 위치
                merge(src, dst, i, i+n-1, Math.min(i+2*n-1, N-1)); // (4,4,3)이 남았을 때? 
            tmp = src; src = dst; dst = tmp;
        }
        if(src != a) System.arraycopy(src, 0, a, 0, N);
    }
}
