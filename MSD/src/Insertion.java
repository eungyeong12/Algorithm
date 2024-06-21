public class Insertion {
    public static void sort(String[] a, int lo, int hi, int d) {
        for(int i=lo; i<=hi; i++)
            for(int j=i; j>lo && less(a[j], a[j-1], d); j--)
                exch(a, j, j-1);
    }

    private static boolean less(String v, String w, int d) {
        for(int i=d; i<Math.min(v.length(), w.length()); i++) {
            if(v.charAt(i) < w.charAt(i)) return true;
            if(v.charAt(i) > w.charAt(i)) return false;
        }
        return v.length() < w.length();
    }

    private static void exch(String[] a, int i, int j) {
        String t = a[i]; a[i] = a[j]; a[j] = t;
    }
}
