import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("입력: ");
        int n = sc.nextInt();

        System.out.println(n + "! = " + factorial(n));
    }

    static int factorial(int n) {
        if(n==1) {
            return 1;
        }
        return factorial(n-1) * n;
    }
}