import java.util.Scanner;

/**
 *
 * @author 
 */
public class TennisKata {

    private int player1Points = 0;
    private int player2Points = 0;
    private final int FOUR = 4;
    private final int THREE = 3;
    private final int TWO = 2;
    private final int ONE = 1;
    private final String PLAYER_1 = "Player 1";
    private final String PLAYER_2 = "Player 2";
    private Scanner scanner;

    public static void main(String args[]) {
        TennisKata kata = new TennisKata();
        kata.getPlayerPoints();
        System.out.println(kata.processPlayerPoints());
    }

    /**
     * Takes input points for Player 1 and Player 2.
     */
    private void getPlayerPoints() {
        scanner = new Scanner(System.in);
        getPlayer1Points();
        getPlayer2Points();
    }

    /**
     * Takes and validates input for Player 1 points.
     */
    private void getPlayer1Points() {
        try {
            System.out.println("Enter player 1 points:");
            player1Points = Integer.parseInt(scanner.next());
        } catch (NumberFormatException e) {
            System.out.println("Please enter valid points");
            getPlayer1Points();
        }
    }

    /**
     * Takes and validates input for Player 2 points.
     */
    private void getPlayer2Points() {
        try {
            System.out.println("Enter player 2 points:");
            player2Points = Integer.parseInt(scanner.next());
        } catch (NumberFormatException e) {
            System.out.println("Please enter valid points");
            getPlayer2Points();
        }
    }

    /**
     * Processes the points depending upon conditions.
     *
     * @return String value stating the result.
     */
    private String processPlayerPoints() {
        String result = getWinnerIfAvailable();
        if (result != null) {
            return (result + " wins");
        }

        result = getDeuceIfAvailable();
        if (result != null) {
            return result;
        }

        result = getAdvantagePlayerIfAvailable();
        if (result != null) {
            return (result + " has advantage");
        }

        if (arePointsEqual()) {
            return (getPointsValue(player1Points) + " all");
        }

        return (getPointsValue(player1Points) + "," + getPointsValue(player2Points));
    }

    /**
     * Returns winner player. If a player has at least 4 points and leads by at
     * least 2 points then he is the winner.
     *
     * @return winner player.
     */
    private String getWinnerIfAvailable() {
        if ((player1Points >= FOUR) && (player1Points >= (player2Points + TWO))) {
            return PLAYER_1;
        }
        if ((player2Points >= FOUR) && (player2Points >= (player1Points + TWO))) {
            return PLAYER_2;
        }
        return null;
    }

    /**
     * Returns player with advantage. If both player has at least 3 points and a
     * player is leading with 1 point then that player has advantage.
     *
     * @return player with advantage.
     */
    private String getAdvantagePlayerIfAvailable() {
        if (player1Points >= THREE && player2Points >= THREE) {
            if (player1Points == player2Points + ONE) {
                return PLAYER_1;
            }
            if (player2Points == player1Points + ONE) {
                return PLAYER_2;
            }
        }
        return null;
    }

    /**
     * Returns deuce if both players have at least 3 points and have same points
     *
     * @return deuce based on condition.
     */
    private String getDeuceIfAvailable() {
        if (player1Points >= THREE && arePointsEqual()) {
            return "Deuce";
        }
        return null;
    }

    /**
     * Returns true if both players have same points.
     *
     * @return true if both players have same points.
     */
    private boolean arePointsEqual() {
        return player1Points == player2Points;
    }

    /**
     * Returns points value in Tennis terminology.
     *
     * @param points
     * @return points value in tennis terminology.
     */
    private String getPointsValue(int points) {
        switch (points) {
            case 0:
                return "Love";
            case ONE:
                return "Fifteen";
            case TWO:
                return "Thirty";
            case THREE:
                return "Forty";
            default:
                System.out.println("Entered value is not legal.");
        }
        return null;
    }
}
