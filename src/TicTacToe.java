import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class TicTacToe {
    private final int numWin;
    private final char[][] desk;
    private final char[] playerChar;
    public TicTacToe(int h, int w, int players, int numWin) throws Exception {
        if(players > h * w || (numWin < 0 || numWin > Math.max(h, w)))
            throw new Exception("Exception");
        this.numWin = numWin;
        desk = getMatrix(h, w);
        playerChar = getCharPlayers(players);
    }
    public void start(){
        System.out.print("[0] - player vs bot\n[1] - player vs player\n[2] - bot vs bot\n\nAnswer: ");
        int answer = new Scanner(System.in).nextByte();
        for(int i = 0; i < desk.length * desk[0].length; i++){
            int index = i % playerChar.length;
            switch (answer){
                case 0 -> {
                    if(index == 0) playerMove(index);
                    else botMove(index);
                }
                case 1 -> playerMove(index);
                default -> botMove(index);
            }
            print();

            if(findWinner()) break;
        }
    }
    public void playerMove(int index){
        Player player;
        do {
            do {
                System.out.print("[" + playerChar[index] + "]" + "player enter the X and Y: ");
                player = new Player(Arrays.stream(new Scanner(System.in).nextLine().split(" ")).mapToInt(Integer::parseInt).toArray());
            } while (player.x < 0 || player.x >= desk[0].length || player.y < 0 || player.y >= desk.length);
        } while (desk[player.y][player.x] != '.');

        desk[player.y][player.x] = playerChar[index];
    }
    public void botMove(int index){
        Player player;
        System.out.print("[" + playerChar[index] + "]" + "player enter the X and Y: ");
        do { player = new Player(new int[]{new Random().nextInt(0, desk[0].length), new Random().nextInt(0, desk.length)});
        } while (desk[player.y][player.x] != '.');
        System.out.println(player);

        desk[player.y][player.x] = playerChar[index];
    }
    public boolean isWinnerInLine(char ch){
        boolean flag = false;
        for (char[] chars : desk) {
            for (int j = 0; j < chars.length - numWin + 1; j++) {
                int count = 0;
                for (int k = j; k < j + numWin; k++)
                    if (chars[k] == ch) count++;

                if (count == numWin) {
                    flag = true;
                    break;
                }
            }
            if (flag) break;
        }
        return flag;
    }
    public boolean isWinnerInColumn(char ch){
        boolean flag = false;
        for(int i = 0; i < desk[0].length; i++){
            for(int j = 0; j < desk.length - numWin + 1; j++){
                int count = 0;

                for(int k = j; k < j + numWin; k++)
                    if(desk[k][i] == ch) count++;

                if(count == numWin) {
                    flag = true;
                    break;
                }
            }
            if(flag) break;
        }
        return flag;
    }
    public boolean isWinnerInSecondDiagonal(char ch){
        boolean flag = false;
        for(int l = 0; l < desk.length - numWin + 1; l++){ // кількість діагоналей, що потрібно перевірити, які знаходяться і на
            for(int j = 0; j < desk.length - numWin + 1 - l; j++){ // кількість перевірок в поточній діагоналі
                int count = 0;
                for(int k = j; k < j + numWin; k++) // пошук виграшного проміжку
                    if(desk[k + l][k] == ch) count++;

                if(count == numWin) {
                    flag = true;
                    break;
                }
            }
            if(flag) break;
            if(l >= 1){ // кількість діагоналей, що потрібно перевірити, які знаходяться і на
                for(int j = 0, count = 0; j < desk.length - numWin + 1 - l; j++){ // кількість перевірок в поточній діагоналі
                    for(int k = j; k < j + numWin; k++) // пошук виграшного проміжку
                        if(desk[k][k + l] == ch) count++;

                    if(count == numWin) {
                        flag = true;
                        break;
                    }
                }
            }
            if(flag) break;
        }
        return flag;
    }
    public boolean isWinnerInMainDiagonal(char ch){
        boolean flag = false;
        for(int l = 0; l < desk.length - numWin + 1; l++){
            for(int j = 0; j < desk.length - numWin + 1 - l; j++){
                int count = 0;
                for(int k = j; k < j + numWin; k++)
                    if(desk[desk.length - 1 - l - k][k] == ch) count++;

                if(count == numWin) {
                    flag = true;
                    break;
                }
            }
            if(flag) break;

            if(l >= 1){
                for(int j = 0; j < desk.length - numWin + 1 - l; j++){
                    int count = 0;
                    for(int k = j; k < j + numWin; k++)
                        if(desk[desk.length - 1 - k][k + l] == ch) count++;

                    if(count == numWin) {
                        flag = true;
                        break;
                    }
                }
            }
            if(flag) break;
        }
        return flag;
    }
    public boolean findWinner(){
        for (char c : playerChar) {
            if (isWinnerInColumn(c)) {
                System.out.println("Winner is: " + c);
                return true;
            }
            if (isWinnerInLine(c)) {
                System.out.println("Winner is: " + c);
                return true;
            }
            if (isWinnerInSecondDiagonal(c) && (desk.length >= 2 && desk[0].length >= 2)) {
                System.out.println("Winner is: " + c);
                return true;
            }
            if (isWinnerInMainDiagonal(c) && (desk.length >= 2 && desk[0].length >= 2)) {
                System.out.println("Winner is: " + c);
                return true;
            }
        }
        return false;
    }
    private char[][] getMatrix(int h, int w){
        char[][] matrix = new char[h][w];
        for (char[] ch : matrix) Arrays.fill(ch, '.');
        return matrix;
    }
    private char[] getCharPlayers(int players){
        char[] chPlayers = new char[players];
        for(int i = 0; i < players; i++)
            chPlayers[i] = (char)('A' + i % 26);
        return chPlayers;
    }
    public void print(){
        for (char[] chars : desk) {
            for (char ch : chars) System.out.print(ch + " ");
            System.out.println();
        }
    }
}