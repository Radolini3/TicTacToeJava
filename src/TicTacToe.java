import java.util.*;
public class TicTacToe {
    private String[] Board = {"1", "2", "3", "4", "5", "6", "7", "8", "9"};
    private String WinningLine = "none"; // jest 8 linii wygrywających
    private String whichTurn = null;
    private String Winner = "none";

    private boolean[] isTaken = new boolean[9];

    public void printBoard() { //Wypsiz tablicę
        System.out.println(Board[0] + "\t" + Board[1] + "\t" + Board[2]);
        System.out.println(Board[3] + "\t" + Board[4] + "\t" + Board[5]);
        System.out.println(Board[6] + "\t" + Board[7] + "\t" + Board[8]);
    }
    public String checkWinner() {
        //Pętla sprawdzająca po każdej turze czy nie ma wygranego, czy też jest remis
        for (int i = 0; i < 8; i++) {
            switch (i) {
                case 0:
                    this.WinningLine = this.Board[0] + this.Board[1] + this.Board[2]; break;
                case 1:
                    this.WinningLine = this.Board[3] + this.Board[4] + this.Board[5]; break;
                case 2:
                    this.WinningLine = this.Board[6] + this.Board[7] + this.Board[8]; break;
                case 3:
                    this.WinningLine = this.Board[0] + this.Board[3] + this.Board[6]; break;
                case 4:
                    this.WinningLine = this.Board[1] + this.Board[4] + this.Board[7]; break;
                case 5:
                    this.WinningLine = this.Board[2] + this.Board[5] + this.Board[8]; break;
                case 6:
                    this.WinningLine = this.Board[0] + this.Board[4] + this.Board[8]; break;
                case 7:
                    this.WinningLine = this.Board[2] + this.Board[4] + this.Board[6]; break;
            }
            if (this.WinningLine.equals("XXX")) { //Sprawdź czy utworzona linijka zawiera same X lub O
                return this.Winner = "X";
            } else if (this.WinningLine.equals("OOO")) return this.Winner = "O";
        }

        int i = 9;
        int takenCount = 0;

        while(i != 0){
            if(this.isTaken[i-1]==true){ takenCount++;}
            if(takenCount>8){return this.Winner = "Draw";}
                i--;
        }

        return null;
    }
    public void setStartingTurn() {
        Scanner input = new Scanner(System.in);
        while (!("X".equals(this.whichTurn) || "O".equals(this.whichTurn))) { //kontrola poprawności inputu X albo O można tylko
                System.out.println("Enter which one will play first (X or O)");
                this.whichTurn = input.nextLine();
            }
    }

    public void play(){
        int numInput = 0;
        while (this.Winner == "none") {
            System.out.println("Input position where you want to put " + this.whichTurn);
            Scanner in = new Scanner(System.in);
            try {
                numInput = in.nextInt();
                if (!(numInput > 0 && numInput <= 9)) {// sprawdź czy nie ma z poza zakresu planszy wybranego pola
                    System.out.println("Invalid input; re-enter slot number:");
                    continue;
                }
                if (this.isTaken[numInput-1] == true){ //sprawdź czy nie podano na zajęte pole kolejnego ruchu
                    System.out.println("You put your " + this.whichTurn + " into taken slot!");
                    continue;
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input; re-enter slot number:");
                continue;
            }
            this.isTaken[numInput-1] = true; // Zajmij miejsce

            this.Board[numInput-1] = this.Board[numInput-1].replaceAll(".+",this.whichTurn); // Wstaw X w miejsce podane przez użytkownika

            System.out.printf("You put: " + this.whichTurn + " into " + (numInput) + " place on board\r\n"); // Info
            /*Logika do zmieniania kolei graczy*/
            if(this.whichTurn.equals("X")){
                    this.whichTurn = "O";
            } else if (this.whichTurn.equals("O")) this.whichTurn = "X";

            this.checkWinner(); //logika do tego, kto wygra
            this.printBoard(); //wypisz tablicę
        }
    }
    public static void main(String[] args) {
        TicTacToe game = new TicTacToe();
        System.out.println("Welcome to 3x3 Tic Tac Toe.");
        game.setStartingTurn(); //wybierz literę rozpoczynającą
        game.printBoard(); //wypisz tablicę
        game.play(); //rozpocznij grę

        if(game.Winner.equals("X") || game.Winner.equals("O")){
            System.out.println("Congrats! Player: " + game.Winner + " has won!");
        }else System.out.println("It's draw!");
    }
}
