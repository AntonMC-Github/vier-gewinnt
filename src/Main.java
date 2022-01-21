import java.util.Arrays;

public class Main {
    public static void main(String[] args) {

        //Willkommensnachricht
        System.out.println("""
                __      ___              _____               _             _  \s
                \\ \\    / (_)            / ____|             (_)           | | \s
                 \\ \\  / / _  ___ _ __  | |  __  _____      ___ _ __  _ __ | |_\s
                  \\ \\/ / | |/ _ \\ '__| | | |_ |/ _ \\ \\ /\\ / / | '_ \\| '_ \\| __|
                   \\  /  | |  __/ |    | |__| |  __/\\ V  V /| | | | | | | | |_\s
                    \\/   |_|\\___|_|     \\_____|\\___| \\_/\\_/ |_|_| |_|_| |_|\\__|
                                                                              \s
                                                                              \s""".indent(1));
        System.out.println("Copyright 2021: Alina Korn und Lukas Strutz");

        //Kurz warten
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //Spielfeld generieren
        Spielfeld spielfeld = new Spielfeld();
        spielfeld.spielfeld[1][2] = 1;
        Ausgeben.spielfeldausgeben(spielfeld);

        //Steuerung erklären

        //Spielrunde startet: Spieler setzen abwechselnd Steine bis einer Gewinnt


    }

    public void startGame(Spielfeld spielfeld) {
        //
    }
}
