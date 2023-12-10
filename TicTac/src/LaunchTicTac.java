
import java.util.Scanner;

class TicTacToe {
    static char board[][];

    public TicTacToe() {
        board = new char[3][3];
        initBoard();
    }

    public void initBoard() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = ' ';
            }
        }
    }

    static void displayboard() {
        System.out.println("------------");
        for (int i = 0; i < board.length; i++) {
            System.out.print("|");
            for (int j = 0; j < board[i].length; j++) {
                System.out.print(board[i][j] + " | ");
            }
            System.out.println();
            System.out.println("------------");
        }
    }

    static void placemark(int row, int col, char mark) {
        if (row >= 0 && col <= 2) {
            board[row][col] = mark;
        } else {
            System.out.println("invalid position");
        }

    }

    static boolean colwincheck() {
        for (int j = 0; j <= 2; j++) {
            // board[0][j] !=' ' check board is not empty
            if (board[0][j] != ' ' && board[0][j] == board[1][j] && board[1][j] == board[2][j]) {
                return true;
            }
        }
        return false;
    }

    static boolean rowwincheck() {
        for (int i = 0; i <= 2; i++) {
            // board[i][0] !=' ' check board is not empty
            if (board[i][0] != ' ' && board[i][0] == board[i][1] && board[i][1] == board[i][2]) {
                return true;
            }
        }
        return false;
    }

    static boolean diagonalwincheck() {
        // board[0][0] !=' ' && board[0][2] !=' ' both these condition check board is
        // not empty
        if (board[0][0] != ' ' && board[0][0] == board[1][1] && board[1][1] == board[2][2]
                || board[0][2] != ' ' && board[0][2] == board[1][1] && board[1][1] == board[2][0]) {
            return true;
        }

        return false;
    }
}

public class LaunchTicTac {
    public static void main(String[] args) {
        System.out.println("hii");
        TicTacToe t = new TicTacToe();
        HumanPlayer p1 = new HumanPlayer("Bob", 'x');
        HumanPlayer p2 = new HumanPlayer("Alice", '0');
        HumanPlayer cp;// curentplayer
        cp = p1;
        while (true) {
            System.out.println(cp.name + " turn::   ");
            // System.out.print("enter row and column ::");
            cp.makeMove();
            TicTacToe.displayboard();
            if (TicTacToe.colwincheck() || TicTacToe.diagonalwincheck() || TicTacToe.rowwincheck()) {
                System.out.println(cp.name + " has won");
                // when player is won we forcefully out of this loop
                break;
            } else {
                if (cp == p1) {
                    cp = p2;
                } else {
                    cp = p1;
                }
            }
        }
       

    }
}
// play with 2 player

class HumanPlayer {
    String name;
    char mark;

    public HumanPlayer(String name, char mark) {
        this.mark = mark;
        this.name = name;
    }

    // before moving we need to check is valid input or not
    void makeMove() {
        // take user input
        System.out.println("enter row and column");
        Scanner scan = new Scanner(System.in);
        int row;
        int col;
        do {
            row = scan.nextInt();
            col = scan.nextInt();
        } while (!isValidMove(row, col));
        // now we acess placemark function thats why we change the method is static
        TicTacToe.placemark(row, col, mark);
    }

    boolean isValidMove(int row, int col) {
        if (row >= 0 && row <= 2 && col >= 0 && col <= 2) {
            // now we acess TicTacToe class inside HumanPlayer and board non static to
            // static
            if (TicTacToe.board[row][col] == ' ') {
                return true;
            }
        }
        return false;
    }

}



