package questions.connectFour;

public class Game {
    private final Board board;
    private final Player player1;
    private final Player player2;
    private Player currentPlayer;
    private GameState gameState = GameState.NOT_STARTED;
    private Player winner;

    public Player getWinner() {
        return winner;
    }

    public Game(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
        board = new Board();
    }

    public GameState getGameState() {
        return gameState;
    }

    public void startGame(){
        if(gameState== GameState.IN_PROGRESS ){
            System.out.println("Game Already in Progress");
        } else {
            gameState = GameState.IN_PROGRESS;
            winner = null;
            currentPlayer = player1;
            System.out.println("Game Started");
        }
    }


    public void dropDisc(int column){ // only y coordinate Changes
        //we can handle concurrency here, put synchronized on board itself if correctness is asked in followup required
        if (gameState!=GameState.IN_PROGRESS){
            System.out.println("Start the Game first!!!");
            return;
        }

        int row = board.dropDisc(column, currentPlayer.getPlayerId());
        if(row >= 0){
            System.out.println("Placed disc successfully");
            board.printBoard();

            if(board.checkIfWon(row, column, currentPlayer.getPlayerId())){
                System.out.println("Player WON" + currentPlayer.getName());
                gameState = GameState.WON;
                return;
            } else{
                switchPlayerChance();
            }

        } else {
            System.out.println("Couldn't place, try again");
        }

        if(gameState==GameState.IN_PROGRESS && board.checkIfBoardFilled()){
            System.out.println("GAME DRAWN!");
            gameState = GameState.DRAW;
        }
    }

    private void switchPlayerChance(){
        if(currentPlayer == player1){
            currentPlayer = player2;
        } else {
            currentPlayer = player1;
        }
    }

}
