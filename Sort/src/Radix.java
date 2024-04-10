public class Radix {
    public static void sort(int[] A) {
        int i,m = A[0], exp = 1, n = A.length;
        int[] B = new int[n]; // 각 기수의 정렬 결과를 임시로 저장할 배열

        for(i=1; i<n; i++)
            if(A[i] > m) m = A[i]; // 가장 큰 수를 검색: 이 수에 따라 기수의 수 d가 결정

        while(m/exp > 0) { // exp가 1이므로, 1의 자리부터 시작 -> LSD
            int[] C = new int[10]; // 버킷 할당
            for(i=0; i<n; i++) C[(A[i]/exp)%10]++; // 낮은 자리부터: LSD
            for(i=1; i<10; i++) C[i] += C[i-1];
            for(i=n-1; i>=0; i--) B[--C[(A[i]/exp)%10]] = A[i];
            for(i=0; i<n; i++)
                A[i] = B[i];
            exp *= 10; // 다음 높은 자리로 이동
        }
    }

    public static void main(String[] args) {
        int[] A = {427, 103, 54, 207, 9, 125};
        Radix.sort(A);
        for(int i=0; i<A.length; i++)
            System.out.print(A[i] + " ");
        System.out.println();
    }
}
