import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Game game = new Game();
        Scanner scan = new Scanner(System.in);
        Computer computer = new Computer();
        intro(game);

        while (!game.isWon()) {
            userMove(game, scan);
            if (game.isLastMove()) {
                if (game.isWon()) gameWon(game);
                else gameTie(game);
            } else computerMove (game, computer);
            game.printBoard();
        }
        finalPrint(game);
    }

    public static void intro (Game game) {
        System.out.println("Welcome to TicTacToe!");
        System.out.println("Please select a number 1-9 to place a piece on the corresponding spot of the board.");
        game.showOptions();
    }

    public static void gameTie (Game game) {
        System.out.println("Catscratch!");
        game.printBoard();
        System.exit(0);
    }

    public static void gameWon (Game game) {
        finalPrint(game);
        System.exit(0);
    }

    public static void userMove (Game game, Scanner scan) {
        int userMove = 0;
        while (!(userMove < 10) || !(userMove > 0)) {
            try {
                userMove = scan.nextInt();
            } catch (InputMismatchException ime) {
                System.out.println("Not a Valid Input.");
                System.out.println("Exiting the game...");
                System.exit(0);
//                System.out.println("Randomly selecting an open space....");
//                Random rand = new Random();
//                userMove = rand.nextInt(8);
//                if (game.positionIsTaken(userMove)) {
//                    while (game.positionIsTaken(userMove)) {
//                        userMove++;
//                    }
//                }
            }

            if (!(userMove > 0) || !(userMove < 10)) {
                System.out.println("Please Enter a Valid Number(1-9)");
            } else if (game.positionIsTaken(userMove)) {
                System.out.println("That spot is already taken, ");
                System.out.println("please choose an available spot.");
                userMove = 0;
            }
        }
        game.placeUserPosition(userMove, 'X');
    }

    public static void computerMove (Game game, Computer computer) {
        int computerMove = computer.bestMove(game.getPlacements());
        game.placeComputerPosition(computerMove, 'O');
    }

    public static void finalPrint (Game game) {
        game.printBoard();
        System.out.printf("%s won the game!", game.getWinner());
    }
}