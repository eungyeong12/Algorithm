import javax.swing.*;
import java.math.BigInteger;
import java.util.Random;

public class RabinKarp {
    private String pat;
    private long patHash;
    private int M, R = 256;
    private long Q;
    private long RM;

    public RabinKarp(String pat) {
        this.pat = pat;
        M = pat.length();
        Q = longRandomPrime(); // 실제 해시 테이블을 만들지 않으므로 매우 큰 수로..

        RM = 1;
        for(int i=1; i<=M-1; i++)
            RM = (R * RM) % Q;
        patHash = hash(pat, M);
    }

    private long longRandomPrime() {
        BigInteger prime = BigInteger.probablePrime(31, new Random());
        return prime.longValue();
    }

    private long hash(String key, int M) {
        long h = 0;
        for(int j=0; j<M; j++)
            h = (R * h + key.charAt(j)) % Q;
        return h;
    }

    public int search(String txt) {
        int N = txt.length();
        if(N < M) return N;
        long txtHash = hash(txt, M);

        if(txtHash == patHash && check(txt, 0)) return 0;

        for(int i=M; i<N; i++) {
            txtHash = (txtHash + Q - RM * txt.charAt(i-M) % Q) % Q;
            txtHash = (txtHash * R + txt.charAt(i)) % Q;
            if(patHash == txtHash && check(txt, i-M+1)) return i-M+1; // match
        }

        return N; // no match
    }

    private boolean check(String txt, int i) { // Las Vegas 방식
        for(int j=0; j<M; j++)
            if(pat.charAt(j) != txt.charAt(i+j))
                return false; // Monte Carlo 방식의 경우, 항상 return true;
        return true;
    }

    public static void main(String[] args) {
        RabinKarp rabinKarp = new RabinKarp("NEEDLE");
        String txt = "INAHAYSTACKNEEDLEINA";
        int position = rabinKarp.search(txt);
        if (position < txt.length()) {
            System.out.println("Pattern found at index: " + position);
        } else {
            System.out.println("Pattern not found");
        }
    }
}
