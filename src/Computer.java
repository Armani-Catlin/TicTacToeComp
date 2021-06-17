import java.util.Random;

public class Computer {
    static Random rand = new Random();

    public static int guess() {
        return rand.nextInt(9);
    }

    public int move(char[] placements) {
        int guess;
        do {
            guess = guess();
        } while (placements[guess] != ' ');
        return guess;
    }

    public int bestMove(char[] placements) {
        int bestScore = -1000;
        int bestMove = 0;
        for( char i=0; i < placements.length; i++) {
            if (placements[i] == ' ') {
                int score = minimax(i, placements,true);
                if (score > bestScore) {
                    bestScore = score;
                    bestMove = i;
                    System.out.println("BestScore = " + bestScore);
                }
            }
        }
        return bestMove;
    }

    private static int minimax(int placement, char [] placements, boolean isMaximizing) {
        Game game = new Game();
        char[] copyPlacements = recreateGame(game, placements);
        int score = 0;

        if(isMaximizing && game.isWon()){
            return 1;
        } else if(!isMaximizing && game.isWon()) {
            return -1;
        } else if(game.isTie()) {
            return 0;
        }

        if(isMaximizing) {
            game.placeComputerPosition(placement,'O');
            if(game.isWon()) {
                return 1;
            }
            for( char i=0; i < placements.length; i++) {
                if (placements[i] == ' ') {
                    score += minimax(i, copyPlacements, false);
                }
            }
        } else {
            game.placeUserPosition(placement+1,'X');
            if(game.isWon()) {
                return -1;
            }
            for( char i=0; i < placements.length; i++) {
                if (placements[i] == ' ') {
                    score += minimax(i, copyPlacements, true);
                }
            }
        }
        return score;
    }
    private static void possibleMoves() {
    }

    private static char[] recreateGame(Game game, char[] placements) {
        for (char i=0; i < placements.length; i++) {
            if(placements[i] == 'X') {
                game.placeUserPosition(i+1, 'X');
            } else if (placements[i] == 'O') {
                game.placeComputerPosition(i, 'O');
            }
        }
        return game.getPlacements();
    }
}