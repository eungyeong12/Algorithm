import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("n번째 피보나치 항 구하기");
        System.out.print("입력: ");
        int n = sc.nextInt();

        System.out.println(fib(n));
    }

    static int fib(int n) {
        int i;
        int[] f = new int[n+1];
        f[0] = 0;
        f[1] = 1;
        for(i=2; i<=n; i++)
            f[i] = f[i-1] + f[i-2];

        return f[n];
    }
}