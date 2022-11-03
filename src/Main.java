import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        System.out.print("Enter h: ");
        int h = new Scanner(System.in).nextByte();
        System.out.print("Enter w: ");
        int w = new Scanner(System.in).nextByte();
        System.out.print("Enter p: ");
        int p = new Scanner(System.in).nextByte();
        System.out.print("Enter n: ");
        int n = new Scanner(System.in).nextByte();
        TicTacToe ticTacToe = new TicTacToe(h, w, p, n);
        ticTacToe.start();
    }
}