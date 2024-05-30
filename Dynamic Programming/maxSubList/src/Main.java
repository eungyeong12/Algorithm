public class Main {
    public static void main(String[] args) {
        double[] a = {2.3, 3.2, -4.5, 2.1, -5.3, 3.6, 4.1, -2.3, 3.5, -4.5};
        System.out.println(maxSubList(a, a.length));
    }

    static double maxSubList(double[] a, int n) {
        double[] b;
        int start = 0;
        int end = 0;
        double max;

        b = new double[n];
        max = a[0];
        b[0] = a[0];
        
        for(int i=1; i<n; i++) {
            b[i] = (b[i-1] <= 0) ? a[i] : b[i-1] + a[i]; 
            if(b[i-1] <= 0) {
                b[i] = a[i];
                start = i;
            } else {
                b[i] = b[i - 1] + a[i];
            }
            if(max < b[i]) {
                max = b[i];
                end = i;
            }
        }

        for(int i=start; i<=end; i++) {
            System.out.print(a[i] + " ");
        }
        System.out.println();

        return max;
    }
}