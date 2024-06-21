public class BruteForce {
    public static int search(String pat, String txt) {
        int M = pat.length();
        int N = txt.length();
        for(int i=0; i<= N-M; i++) {
            int j;
            for(j=0; j<M; j++)
                if(txt.charAt(i+j) != pat.charAt(j))
                    break;
            if(j == M) return i; // pattern이 시작되는 text의 위치
        }
        return N; // not found
    }

    public static void main(String[] args) {
        String pat = "NEEDLE";
        String txt = "INAHAYSTACKNEEDLEINA";
        System.out.println(search(pat, txt));
    }
}
