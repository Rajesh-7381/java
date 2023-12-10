
    import java.util.Scanner;

class TicTacToe {
     char board[][];

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

    void displayboard() {
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

     void placemark(int row, int col, char mark) {
        if (row >= 0 && col <= 2) {
            board[row][col] = mark;
        } else {
            System.out.println("invalid position");
        }

    }

    boolean colwincheck() {
        for (int j = 0; j <= 2; j++) {
            // board[0][j] !=' ' check board is not empty
            if (board[0][j] !=' ' && board[0][j] == board[1][j] && board[1][j] == board[2][j]) {
                return true;
            }
        }
        return false;
    }
    boolean rowwincheck() {
        for (int i = 0; i <= 2; i++) {
            // board[i][0] !=' ' check board is not empty
            if (board[i][0] !=' ' && board[i][0] == board[i][1] && board[i][1] == board[i][2]) {
                return true;
            }
        }
        return false;
    }
    boolean diagonalwincheck() {
        // board[0][0] !=' ' && board[0][2] !=' ' both these condition check board is not empty 
            if (board[0][0] !=' ' && board[0][0] == board[1][1] && board[1][1] == board[2][2] || board[0][2] !=' ' &&  board[0][2] == board[1][1] && board[1][1] == board[2][0]) {
                return true;
            }
        
        return false;
    }
}

public class a {
    public static void main(String[] args) {
        System.out.println("hii");
        TicTacToe t = new TicTacToe();
        t.displayboard();
        // check for column data
        // here we don't give any data bydefault it shown true because empty string == empty string thats why it shown true overcome this problem we provide empty string is equal to not null
        // t.placemark(0, 0, 'x');
        // t.placemark(1, 0, 'x');
        // t.placemark(2, 0, 'x');


        // check for row data
        // here we don't give any data bydefault it shown true because empty string == empty string thats why it shown true overcome this problem we provide empty string is equal to not null
        // t.placemark(0, 0, 'x');
        // t.placemark(0, 1, 'x');
        // t.placemark(0, 2, 'x');


        // check for diagonal data
        // here we don't give any data bydefault it shown true because empty string == empty string thats why it shown true overcome this problem we provide empty string is equal to not null
        t.placemark(0, 0, 'x');
        t.placemark(1, 1, 'x');
        t.placemark(2, 2, 'x');

        t.displayboard();
        // check for column win condition
        System.out.println(t.colwincheck());
        // check for row win condition
        System.out.println(t.rowwincheck());
        // check for row win condition
        System.out.println(t.diagonalwincheck());
        
    }
}


// class HumanPlayer{
//     String name;
//     char mark;
//     public HumanPlayer(String name,char mark){
//         this.mark=mark;
//         this.name=name;
//     }
//     // before moving we need to check is valid input or not
//     void makeMove(){
//         // take user input
//         Scanner scan=new Scanner(System.in);
//         int row;
//         int col;
//         do {
//             row=scan.nextInt();
//             col=scan.nextInt();
//         } while (!isValidMove(row, col));
//         // now we acess placemark function thats why we change the method is static
//         TicTacToe.placemark( row,  col,  mark);
//     }
//     boolean isValidMove(int row,int col){
//         if(row >=0 && row <=2 && col >=0 && col <=2){
//             // now we acess TicTacToe class inside HumanPlayer  and board non static to static
//             if(TicTacToe.board[row][col]==' '){
//                 return true;
//             }
//         }
//         return false;
//     }

// }
    

