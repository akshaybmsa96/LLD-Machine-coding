package questions.connectFour;


/**
 * Problem statement:
 * Build the object-oriented design for a two-player Connect Four game.
 * Players take turns dropping discs into a 7-column, 6-row board.
 * The first to align four of their own discs vertically, horizontally, or diagonally wins
 */
public class Main {
    public static void main(String[] args) {

        Player p1 = new Player(1, " AKSHAY ", DiscColor.RED);
        Player p2 = new Player(2, " LAKSHAY ", DiscColor.YELLOW);

        Game connectFourGame = new Game(p1, p2);

        connectFourGame.startGame();
        connectFourGame.dropDisc(0);
        connectFourGame.dropDisc(1);
        connectFourGame.dropDisc(0);
        connectFourGame.dropDisc(1);
        connectFourGame.dropDisc(0);
        connectFourGame.dropDisc(1);
        connectFourGame.dropDisc(0);
        connectFourGame.dropDisc(1);

//        connectFourGame.dropDisc(1);
    }
}

/**
 * Questions?
 * Do we need GUI? how do they tell which column to drop the disc on?
 * are coordinates fine?
 *
 *
 * Is is one game at a time or concurrent games?
 *
 * board size ?
 * do we need undo?
 */




/**
 * Entities:
 *
 * Players
 *  name
 *  color
 * Board
 *  coordinates
 *  Disc
 * Game
 *  Players
 *  Board
 *
 *  Disc
 *   color
 *
 *
 */