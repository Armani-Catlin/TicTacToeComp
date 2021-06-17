public class Game {

    private final char[][] board = {
            {' ','|',' ','|',' '},
            {'-','+','-','+','-'},
            {' ','|',' ','|',' '},
            {'-','+','-','+','-'},
            {' ','|',' ','|',' '}
    };

    private final char[] placements = {' ',' ',' ',' ',' ',' ',' ',' ',' '};

    public String[] winningPositions = new String[8];

    private char winner;

    private void updateWinningPositions() {
        winningPositions[0] = "" + this.placements[0] + this.placements[1] + this.placements[2];
        winningPositions[1] = "" + this.placements[3] + this.placements[4] + this.placements[5];
        winningPositions[2] = "" + this.placements[6] + this.placements[7] + this.placements[8];
        winningPositions[3] = "" + this.placements[0] + this.placements[3] + this.placements[6];
        winningPositions[4] = "" + this.placements[1] + this.placements[4] + this.placements[7];
        winningPositions[5] = "" + this.placements[2] + this.placements[5] + this.placements[8];
        winningPositions[6] = "" + this.placements[0] + this.placements[4] + this.placements[8];
        winningPositions[7] = "" + this.placements[6] + this.placements[4] + this.placements[2];
    }

    public char[][] getBoard() {
        return this.board;
    }

    public char getWinner() {
        return winner;
    }

    public char[] getPlacements() {
        return this.placements;
    }

    public boolean isWon() {

        updateWinningPositions();

        for(String position : winningPositions) {
            if( isPositionWon(position) ) {
                setWinner(position);
                return true;
            }
        }
        return false;
    }

    private static boolean isPositionWon(String position) {
        return position.equals("XXX") || position.equals("OOO");
    }

    public void setWinner(String winner) {
        this.winner = winner.charAt(0);
    }

    public void printBoard() {
        int count = 0;
        for (char[] row : this.board) {
            for(char col : row) {
                if (col == ' ') {
                    System.out.print(this.placements[count]);
                    count++;
                } else {
                    System.out.print(col);
                }
            }
            System.out.println();
        }
    }

    public void showOptions() {
        int count = 1;
        for (char[] row : this.board) {
            for (char col : row) {
                if (col == ' ') {
                    System.out.print(count);
                    count++;
                } else {
                    System.out.print(col);
                }
            }
            System.out.println();
        }
    }

    public boolean isLastMove() {
        for(char placement : placements) {
            if (placement == ' ') {
                return false;
            }
        }
        return true;
    }

    public void placeUserPosition(int position, char XorO) {
        this.placements[position-1] = XorO;
    }
    public void placeComputerPosition(int position, char XorO) {
        this.placements[position] = XorO;
    }

    public boolean positionIsTaken(int position) {
        return this.placements[position-1] != ' ';
    }

    public boolean isTie() {
        for(char placement : this.getPlacements()){
            if(placement == ' ') {
                return false;
            }
        }
        return true;
    }
}