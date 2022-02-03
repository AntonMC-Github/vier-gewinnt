public class Spielfeld {
    public String player1;
    public String player2;

    //Spielvariablen definieren
    public boolean gameRunning = true;
    public int currentPlayer = 1;

    //Spielfeld "generieren"
    public int[][] spielfeld = new int[6][7]; //Erstes x dann y

    public static boolean determineWinner(Spielfeld spielfeld) {
        //Testen ob Spieler gewonnen hat
        for (int i = 0; i <= 2; i++)
            for (int j = 0; j <= 6; j++)
                if (spielfeld.spielfeld[i][j] == spielfeld.currentPlayer && spielfeld.spielfeld[i + 1][j] == spielfeld.currentPlayer && spielfeld.spielfeld[i + 2][j] == spielfeld.currentPlayer && spielfeld.spielfeld[i + 3][j] == spielfeld.currentPlayer)
                    return true;

        for (int i = 0; i <= 5; i++)
            for (int j = 0; j <= 3; j++)
                if (spielfeld.spielfeld[i][j] == spielfeld.currentPlayer && spielfeld.spielfeld[i][j + 1] == spielfeld.currentPlayer && spielfeld.spielfeld[i][j + 2] == spielfeld.currentPlayer && spielfeld.spielfeld[i][j + 3] == spielfeld.currentPlayer)
                    return true;

        for (int i = 0; i <= 2; i++)
            for (int j = 0; j <= 3; j++)
                if (spielfeld.spielfeld[i][j] == spielfeld.currentPlayer && spielfeld.spielfeld[i + 1][j + 1] == spielfeld.currentPlayer && spielfeld.spielfeld[i + 2][j + 2] == spielfeld.currentPlayer && spielfeld.spielfeld[i + 3][j + 3] == spielfeld.currentPlayer)
                    return true;

        return false;
    }
}
