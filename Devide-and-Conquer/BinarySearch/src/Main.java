public class Main {
    static int x;
    static int[] s;

    public static void main(String[] args) {
        x = 10;
        s = new int[]{1, 3, 5, 7, 8, 10, 20, 35, 99, 100};
        System.out.println(binarySearch(0, s.length-1));
    }

    static int binarySearch(int low, int high) {
        int mid;
        if(low > high) return -1;
        else {
            mid = (low+high)/2;
            if(x == s[mid])
                return mid;
            else if(x < s[mid])
                return binarySearch(low, mid-1);
            else
                return binarySearch(mid+1, high);
        }
    }
}

