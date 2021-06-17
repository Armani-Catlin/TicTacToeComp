import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {


    public static void main(String[] args) {

        Game game = new Game();
        Scanner scan = new Scanner(System.in);
        Computer computer = new Computer();


        intro(game);

        while(!game.isWon()) {
            userMove(game, scan);

            if (game.isLastMove()) {
                if (game.isWon()) gameWon(game);
                else gameTie(game);
            } else computerMove(game, computer);

            game.printBoard();
        }

        finalPrint(game);

    }

    public static void intro(Game game) {
        System.out.println("Welcome to TicTakToe!");
        System.out.println("Where would you like to place a piece?");
        System.out.println("place a number 1-9 to place a piece");
        game.showOptions();
    }

    public static void gameTie(Game game) {
        System.out.println("Tie Game!");
        game.printBoard();
        System.exit(0);
    }

    public static void gameWon(Game game) {
        finalPrint(game);
        System.exit(0);
    }

    public static void userMove(Game game, Scanner scan) {

        int userMove = 0;
        while(!(userMove < 10) || !(userMove > 0)) {
            try {
                userMove = scan.nextInt();
            } catch (InputMismatchException ime) {
                System.out.println("Not a Valid Input.");
                System.out.println("Exiting...");
                System.exit(0);
            }

            if ( !(userMove > 0) || !(userMove < 10) ) {
                System.out.println("Please Enter Valid Number(1-9)");
//                game.positionIsTaken(userMove);
            } else if (game.positionIsTaken(userMove)) {
                System.out.println("That move is already taken");
                System.out.println("please choose another spot");
                userMove = 0;
            }

        }

        game.placeUserPosition(userMove, 'X');
    }

    public static void computerMove(Game game, Computer computer) {
        int computerMove = computer.bestMove(game.getPlacements());
        game.placeComputerPosition(computerMove, 'O');
    }

    public static void finalPrint(Game game) {
        game.printBoard();
        System.out.printf("%s won the game!", game.getWinner());
    }

}