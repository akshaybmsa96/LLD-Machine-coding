package patterns.structural;
import java.util.Objects;

public class Facade {
    public static void main(String[] args) {

        Game ticTacToe = new Game();
        ticTacToe.makaMove(0,0);
        ticTacToe.makaMove(1,0);

        ticTacToe.makaMove(0,1);
        ticTacToe.makaMove(1,1);

        ticTacToe.makaMove(0,2);
        ticTacToe.makaMove(1,1);
    }
}


/**
 *
 */
enum GAME_STATE  {IN_PROGRESS, DRAW, WON}


class Player {
    private final String name;
    private final String symbol;

    public Player(String name, String symbol){
        this.name = name;
        this.symbol = symbol;
    }

    public String getPlayerName(){
        return name;
    }
    public String getPlayerSymbol(){
        return symbol;
    }
}

class Board {

    private final String[][] board = {{"","",""},{"","",""},{"","",""}};
    public void placeMarker(int x, int y, String symbol){

        if(!board[x][y].isBlank()){
            System.out.println("Space already filled");
        }
        board[x][y] = symbol;
        System.out.println("Placed Marker: " + symbol + " at pos: "+ x+","+y);
    }


    public boolean checkIfWon(String SymbolToCheck){

        for (int i = 0; i < 3; i++) {
            // Check row i
            if (Objects.equals(board[i][0], SymbolToCheck) && Objects.equals(board[i][1], SymbolToCheck) && Objects.equals(board[i][2], SymbolToCheck)) {
                return true;
            }
            // Check column i
            if (Objects.equals(board[0][i], SymbolToCheck) && Objects.equals(board[1][i], SymbolToCheck) && Objects.equals(board[2][i], SymbolToCheck)) {
                return true;
            }
        }

        // 2. Check Diagonal (Top-Left to Bottom-Right)
        if (Objects.equals(board[0][0], SymbolToCheck) && Objects.equals(board[1][1], SymbolToCheck) && Objects.equals(board[2][2], SymbolToCheck)) {
            return true;
        }

        // 3. Check Anti-Diagonal (Top-Right to Bottom-Left)
        return Objects.equals(board[0][2], SymbolToCheck) && Objects.equals(board[1][1], SymbolToCheck) && Objects.equals(board[2][0], SymbolToCheck);
    }

    public boolean checkIfBoardFull(){

        for(int i = 0; i<=2; i++){
            for(int j =0; j<=2; j++){
                if(board[i][j].isEmpty()){
                    return false;
                }
            }
        }

        return true;
    }

}

//orchestrator
class Game {

    private final Player player1;
    private final Player players2;
    private Player currentPlayer;
    private final Board gameBoard;
    private  GAME_STATE state;

    public Game(){

        state = GAME_STATE.IN_PROGRESS;
        player1 = new Player("Akshay", "X");
        players2 = new Player("Sam", "O");
        gameBoard = new Board();
        currentPlayer = player1;

    }

    public void makaMove(int x, int y){

        if(state!=GAME_STATE.IN_PROGRESS) return ;

        gameBoard.placeMarker(x, y, currentPlayer.getPlayerSymbol());
        if(gameBoard.checkIfWon(currentPlayer.getPlayerSymbol())){
            System.out.println("Player "+ currentPlayer.getPlayerName()+ " won");
            state = GAME_STATE.WON;
        }
        if(gameBoard.checkIfBoardFull()){
            System.out.println("Game DRAWW");
            state = GAME_STATE.DRAW;
        } else {
            if(currentPlayer == player1)
                currentPlayer = players2;
            else
                currentPlayer = player1;
        }


    }






}