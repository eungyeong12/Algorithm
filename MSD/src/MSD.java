import java.util.Arrays;

public class MSD {
    static String[] aux;
    private static int charAt(String s, int d) {
        if(d < s.length()) return s.charAt(d);
        else return -1;
    }

    public static void sort(String[] a) {
        aux = new String[a.length];
        sort(a, 0, a.length-1, 0);
    }

    private static void sort(String[] a, int lo, int hi, int d) {
        int R = 256; // 기수 = R
        if(hi <= lo+15) { // cutoff: M = 15로 설정
            Insertion.sort(a, lo, hi, d);
            return;
        }

        int[] count = new int[R+2]; // 앞에서 저장 & EOS 공간 필요 -> R+2
        for(int i=lo; i<=hi; i++)
            count[charAt(a[i], d)+2]++; // count[1]: EOS

        for(int r=0; r<R+1; r++)
            count[r+1] += count[r];

        for(int i=lo; i<=hi; i++)
            aux[count[charAt(a[i], d)+1]++] = a[i];

        for(int i=lo; i<=hi; i++)
            a[i] = aux[i-lo]; // aux는 전역 배열로 한번만 할당

        for(int r=0; r<R; r++)
            sort(a, lo+count[r], lo+count[r+1]-1, d+1); // R개의 부분배열에 대해 정렬
    }

    public static void main(String[] args) {
        String[] a = {"she", "sells", "seashells", "by", "the", "sea", "shore", "the", "shells", "she", "sells", "are", "surely", "seashells"};
        sort(a);
        System.out.println(Arrays.toString(a));
    }
}
