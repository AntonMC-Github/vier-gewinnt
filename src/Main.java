import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    //Switch für die Konsolenbasierte Version des Spiels
    public static boolean debugMode = false;

    public static void main(String[] args) {
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

        if (debugMode) startScreen();
        else startGUIScreen();
    }

    public static void startGUIScreen() {
        //Grundlegende Fensterinitialisierung
        JFrame meinFrame = new JFrame("Vier Gewinnt");
        meinFrame.setSize(800, 600);
        meinFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel startScreen = new JPanel(new BorderLayout());

        //Zusätzliche Menübar mit der sich bei Bedarf ein neues Spiel starten lässt
        JMenuBar menu = new JMenuBar();
        JMenu option = new JMenu("Spiel");
        JMenuItem neu = new JMenuItem("Neues Spiel starten");
        option.add(neu);
        menu.add(option); //setJMenuBar(menu);
        meinFrame.setJMenuBar(menu);

        //Startscreen - Spieler begrüßen und Button zum Starten des Spiels
        startScreen.add(new JLabel("Willkommen bei Vier Gewinnt", SwingConstants.CENTER), BorderLayout.CENTER);
        JButton startGameButton = new JButton("Spiel starten");
        startGameButton.setPreferredSize(new Dimension(256, 150));
        startGameButton.addActionListener(e -> {
            startScreen.setVisible(false);
            startGUIGame(new Spielfeld());
        });
        startScreen.add(startGameButton, BorderLayout.PAGE_END);

        //Fenster anzeigen
        meinFrame.setContentPane(startScreen);
        meinFrame.setVisible(true);

    }

    public static void startGUIGame(Spielfeld spielfeld) {
        //Als erstes Spiel
    }


    //Konsolenbasierte Spielversion

    //Startscreen
    public static void startScreen() {
        //Spielfeld generieren
        Spielfeld spielfeld = new Spielfeld();
        spielfeld.spielfeld[1][2] = 1;
        Ausgeben.spielfeldausgeben(spielfeld);

        //Alina Steuuerung Anleitung
        System.out.println(" Wie Ihr das Spiel steuert:\n\n" +
                           "Der Spieler (x) steuert die Spielsteine indem er die Pfeiltasten betätigt.\n" +
                           "Um den Stein nach rechts zu setzten, muss die reschte Pfeil-Taste genuzt werde.\n Um den Stein nach links zu setzten muss die linke Pfeiltaste betätigt werden.\n" +
                           "Um den Speilzug zu bestätigen muss die Pfeiltaste die nach unten zeigt genuzt werden.\n" +
                           "" +
                           "\nDer Spieler (o) steuert die Spielsteine in dem die Tasten \"A\" , \"S\" und \"D\" verwendet werden.\n" +
                           "\"A\" wird betätigt um den Stein nach links zu setzen und \"D\" um den Stein nach rechts zu setzen.\n" +
                           " Um den Spielzug zu bestätigen, muss \"S\" gedrückt werden. " +
                           "\n\n Viel vergnügen beim Spielen!");

        //Steuerung erklären
        //Mit Pfeiltasten auswählen wo der Spielstein gesetzt werden soll - Mit Pfeil nach unten Setzen

        //Spielrunde startet: Spieler setzen abwechselnd Steine bis einer Gewinnt
        startGame(spielfeld);
    }

    //Eigentliches Spiel
    public static void startGame(Spielfeld spielfeld) {
        //Spielvariablen definieren
        boolean gameRunning = true;
        int currentPlayer = 2;
        Scanner scanner = new Scanner(System.in);

        //Solange das Spiel läuft neue Runden starten
        while (gameRunning) {
            //Zuerst Spieler wechseln
            switch (currentPlayer) {
                case 1 -> currentPlayer = 2;
                case 2 -> currentPlayer = 1;
            }

            boolean inputRunning = true;

            //Solange der Spieler noch keine richtige Eingabe gegeben hat Spieler Eingabe geben lassen
            while (inputRunning) {
                //Auswählen lassen an welche Stelle der Spieler den Spielstein setzen möchte
                System.out.println("\n\nSpieler " + currentPlayer + " Wo möchtest du den Spielstein setzen?");
                System.out.println("Bitte gib eine Zahl zwischen 1 und 7 ein: ");
                int input = scanner.nextInt();

                //Eingabe überprüfen
                if (!(1 <= input && input <= 7)) {
                    //Fehler
                    //(TODO:) Fehlermeldung
                    System.out.println("\n\n\nHey!\n\n Du hast einen Spielbereich ausgewählt," +
                                       " der nicht besetzt werden kann.\n " +
                                       "Bitte wähle einen Bereich zwischen 0-7 in der waagerechten und 0-6 in der senkrechten. ");


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
                        //(TODO:) Fehlermeldung und erneute Eingabe
                        System.out.println("\n\n\nHey!\n\n Du hast einen Spielbereich ausgewählt, " +
                                           "der schon mit Spielsteinen voll besetzt ist!\n Bitte wähle einen anderen Bereich aus.");
                    }
                }
            }

            //Testen ob Spieler gewonnen hat
            for (int i = 0; i <= 2; i++)
                for (int j = 0; j <= 6; j++)
                    if (spielfeld.spielfeld[i][j] == currentPlayer && spielfeld.spielfeld[i + 1][j] == currentPlayer && spielfeld.spielfeld[i + 2][j] == currentPlayer && spielfeld.spielfeld[i + 3][j] == currentPlayer) {
                        gameRunning = false;
                        break;
                    }

            for (int i = 0; i <= 5; i++)
                for (int j = 0; j <= 3; j++)
                    if (spielfeld.spielfeld[i][j] == currentPlayer && spielfeld.spielfeld[i][j + 1] == currentPlayer && spielfeld.spielfeld[i][j + 2] == currentPlayer && spielfeld.spielfeld[i][j + 3] == currentPlayer) {
                        gameRunning = false;
                        break;
                    }

            for (int i = 0; i <= 2; i++)
                for (int j = 0; j <= 3; j++)
                    if (spielfeld.spielfeld[i][j] == currentPlayer && spielfeld.spielfeld[i + 1][j + 1] == currentPlayer && spielfeld.spielfeld[i + 2][j + 2] == currentPlayer && spielfeld.spielfeld[i + 3][j + 3] == currentPlayer) {
                        gameRunning = false;
                        break;
                    }

            //Falls ja, Spiel beenden
            //Sonst ist der andere Spieler dran
            Ausgeben.spielfeldausgeben(spielfeld);
        }

        System.out.println("Herzlichen Glückwunsch Spieler " + currentPlayer);
    }
}
