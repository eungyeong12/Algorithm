public class KMP {
    private int R;
    private int M;
    private String pat;
    private int[][] dfa;

    public KMP(String pat) { // 생성자에서 DFA 작성
        this.R = 256;
        this.pat = pat;
        this.M = pat.length();

        dfa = new int[R][M];
        dfa[pat.charAt(0)][0] = 1; // 초기 상태의 match condition
        for(int X=0, j=1; j<M; j++) {
            for(int c=0; c<R; c++)
                dfa[c][j] = dfa[c][X]; // 이전 X 상태의 모든 이동규칙들을 복사
            dfa[pat.charAt(j)][j] = j + 1; // match일 경우에는 다음으로 이동
            X = dfa[pat.charAt(j)][X];
        }
    }
    public int search(String txt) {
        int i, j, N = txt.length();
        for(i=0, j=0; i<N && j<M; i++)
            j = dfa[txt.charAt(i)][j]; // no backup
        if(j == M) return i-M; // pattern이 시작되는 text의 위치
        else return N; // not found
    }

    public static void main(String[] args) {
        KMP kmp = new KMP("NEEDLE");
        String txt = "INAHAYSTACKNEEDLEINA";
        int position = kmp.search(txt);
        if (position < txt.length()) {
            System.out.println("Pattern found at index: " + position);
        } else {
            System.out.println("Pattern not found");
        }
    }
}
