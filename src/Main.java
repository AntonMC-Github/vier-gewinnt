import java.util.Arrays;
import java.util.Scanner;

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
        //Mit Pfeiltasten auswählen wo der Spielstein gesetzt werden soll - Mit Pfeil nach unten Setzen

        //Spielrunde startet: Spieler setzen abwechselnd Steine bis einer Gewinnt
        startGame(spielfeld);


    }

    public static void startGame(Spielfeld spielfeld) {
        //Spielvariablen definieren
        boolean gameRunning = true;
        boolean inputRunning = true;
        int currentPlayer = 1;
        Scanner scanner = new Scanner(System.in);

        //So lange das Spiel läuft neue Runden starten
        while (gameRunning) {

            while (inputRunning) {
                //Zuerst Auswählen lassen an welche Stelle der Spieler den Spielstein setzen möchte
                System.out.println("Wo möchtest du den Spielstein setzen?");
                System.out.println("Bitte gib eine Zahl zwischen 1 und 7 ein: ");
                int input = scanner.nextInt();

                //Eingabe überprüfen
                if (!(0 <= input && input <= 7)) {
                    //Fehler
                    //TODO: Fehlermeldung
                } else {
                    //Testen, ob an dieser Stelle noch Platz ist,
                    if (spielfeld.spielfeld[0][input - 1] == 0) {
                        //wenn noch Platz frei ist, Spielstein setzen.
                        for (int x = 5; x >= 0; x--) {
                            if (spielfeld.spielfeld[x][input - 1] == 0) {
                                spielfeld.spielfeld[x][input - 1] = currentPlayer;
                                inputRunning = false;
                                break;
                            }
                        }
                    } else {
                        //Reihe ist bereits voll
                        //TODO: Fehlermeldung und erneute Eingabe
                    }
                }

            }

            //Testen ob Spieler gewonnen hat
            //Fall ja, Spiel beenden
            //Sonst ist der andere Spieler dran
            Ausgeben.spielfeldausgeben(spielfeld);
            inputRunning = true;
            gameRunning = false;

        }
    }
}
