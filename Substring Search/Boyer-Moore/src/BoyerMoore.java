public class BoyerMoore {
    private String pat;
    private int[] right;

    public BoyerMoore(String pat) {
        this.pat = pat;
    }

    public int search(String txt) {
        int M = pat.length();
        int N = txt.length();
        int skip;

        int R = 256;
        right = new int[R];
        for(int c=0; c<R; c++)
            right[c] = -1;
        for(int j=0; j<M; j++)
            right[pat.charAt(j)] = j;

        for(int i=0; i<=N-M; i+=skip) {
            skip = 0;
            for(int j=M-1; j>=0; j--) {
                if(pat.charAt(j) != txt.charAt(i+j)) { // Mismatch
                    skip = Math.max(1, j-right[txt.charAt(i+j)]);
                    break;
                }
            }
            if(skip == 0) return i; // found
        }
        return N; // not found
    }

    public static void main(String[] args) {
        BoyerMoore boyerMoore = new BoyerMoore("NEEDLE");
        String txt = "INAHAYSTACKNEEDLEINA";
        int position = boyerMoore.search(txt);
        if (position < txt.length()) {
            System.out.println("Pattern found at index: " + position);
        } else {
            System.out.println("Pattern not found");
        }
    }
}
