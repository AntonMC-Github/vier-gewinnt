/*
TODO: Farben statt 1 und 2
TODO: Gewinnerbestimmung
TODO: Java Maskottchen bei Gewinnfenster weg
TODO: Anleitung richtig anzeigen

Vier Gewinnt von Alina und Lukas

Ziel des Spieles ist es, vier eigene Spielsteine in Reihe zu haben, sie können dabei direkt nebeneinander, aufeinander oder diagonal liegen.

Eigenanteile:
Alina:
- Schriftarten
- Anleitung
- Spielfelddesign und Spielfeldausgabe

Lukas:
- Gewinnmechanik
- Startmechanik
- GUI

Erklärung der Funktionen:
 */


import javax.swing.*;
import java.awt.*;
import java.util.Scanner;

//Hauptklasse
public class Main {

    //Switch für die Konsolenbasierte Version des Spiels
    public static boolean debugMode = false;

    //Spielfeld
    public static Spielfeld spielfeld = new Spielfeld();

    //Main Klasse die je nach Auswahl die GUI oder Konsolenversion startet
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
        System.out.println("Copyright 2022: Alina Korn und Lukas Strutz");

        if (debugMode) startScreen();
        else startGUIScreen();
    }

    //GUI Version: wir erstellen schonmal das Fenster damit wir von überall auf das Fenster zugreifen können
    public static JFrame meinFrame = new JFrame("Vier Gewinnt");

    //Der Startscreen - Wir initialisieren zuerst das JFrame Fenster, erstellen eine Menübar und geben dem Spieler die Optionen eine Anleitung anzuzeigen oder das Spiel zu starten
    public static void startGUIScreen() {
        //Grundlegende Fensterinitialisierung
        meinFrame.setSize(800, 600);
        meinFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel startScreen = new JPanel(new BorderLayout());

        //Zusätzliche Menübar mit der sich bei Bedarf ein neues Spiel starten lässt oder der Spieler zurpck zum Hauptmenü gehen kann
        JMenuBar menu = new JMenuBar();
        JMenu option = new JMenu("Spiel");
        JMenuItem mainmenu = new JMenuItem("Zurück zum Hauptmenü");
        mainmenu.addActionListener(e -> {
            resetGame();
            startGUIScreen();
        });
        option.add(mainmenu);
        JMenuItem neu = new JMenuItem("Neues Spiel starten");
        neu.addActionListener(e -> {
            resetGame();
            startGUIGame();
        });
        option.add(neu);
        menu.add(option);
        meinFrame.setJMenuBar(menu);

        //Startscreen - Spieler begrüßen und Button zum Starten des Spiels und Anleitung anzeigen
        JLabel willkommen = new JLabel("Willkommen bei Vier Gewinnt", SwingConstants.CENTER);
        willkommen.setFont(new Font("Monospaced", Font.BOLD, 34));
        startScreen.add(willkommen, BorderLayout.CENTER);
        JButton anleitung = new JButton("Anleitung");
        anleitung.setFont(new Font("Monospaced", Font.BOLD, 30));

        anleitung.setPreferredSize(new Dimension(256, 150));
        anleitung.addActionListener(e -> {
            startScreen.setVisible(false);
            anleitung();
        });
        startScreen.add(anleitung, BorderLayout.NORTH);
        JButton startGameButton = new JButton("Spiel starten");
        startGameButton.setFont(new Font("Monospaced", Font.BOLD, 30));
        startGameButton.setPreferredSize(new Dimension(256, 150));
        startGameButton.addActionListener(e -> {
            startScreen.setVisible(false);
            startGUIGame();
        });
        startScreen.add(startGameButton, BorderLayout.PAGE_END);

        //Fenster sichtbar machen
        meinFrame.setContentPane(startScreen);
        meinFrame.setVisible(true);

    }

    //Der Spieler möchte die Anleitung sehen, wir zeigen ihm also die Anleitung
    public static void anleitung() {
        //Wir erstellen ein neues Panel
        JPanel containerFenster = new JPanel(new BorderLayout());

        //Hier wird die Anleitung erstellt
        JPanel labelPanel = new JPanel();
        someText.setText("\" Hey Leute, so könnt ihr „Vier gewinnt“ spielen:\\n\\n\n" +
                "Über dem Spielfeld wird euch angegeben welcher Spieler an der Reihe ist.\\n\n" +
                "Anschließend muss der Spieler, der an der Reihe ist, auf die entsprechende Spalte klicken, indem er den Spielstein setzen möchte.\\n\\n\n" +
                "Viel Spaß beim spielen!!!");



        someText.setFont(new Font("Calibri", Font.PLAIN, 34));
        labelPanel.add(someText);
        containerFenster.add(labelPanel, BorderLayout.CENTER);

        //Zurück zum Hauptmenü
        JButton anleitung = new JButton("Hauptmenü");
        anleitung.setPreferredSize(new Dimension(256, 150));
        anleitung.addActionListener(e -> {
            containerFenster.setVisible(false);
            startGUIScreen();
        });
        containerFenster.add(anleitung, BorderLayout.NORTH);


        //Spiel starten
        JButton startGameButton = new JButton("Spiel starten");
        startGameButton.setFont(new Font("Calibri", Font.BOLD, 34));
        startGameButton.setPreferredSize(new Dimension(256, 150));
        startGameButton.addActionListener(e -> {
            containerFenster.setVisible(false);
            startGUIGame();
        });
        containerFenster.add(startGameButton, BorderLayout.PAGE_END);

        //Die Anleitung im Fenster sichtbar machen
        meinFrame.setContentPane(containerFenster);
    }

    //Erstellung der notwendigen Variablen für das Spiel
    public static JButton[][] grid = new JButton[6][7];
    public static JLabel someText = new JLabel();

    //Start des eigentlichen Spiels - Hier werden alle Dinge fürs Spiel vorbereitet - Das eigentliche Spiel läuft dann über den EventListener
    public static void startGUIGame() {

        //Spielfeldfenster initialisieren
        JPanel containerFenster = new JPanel(new BorderLayout());

        //Den Infotext initialisieren
        JPanel labelPanel = new JPanel();
        someText.setText("Spieler 1 ist dran");
        someText.setFont(new Font("Calibri", Font.BOLD, 34));
        labelPanel.add(someText);

        //Das Spielfeld initialisieren
        JPanel spiefeldFenster = new JPanel(new GridLayout(6, 7));
        ButtonListener bl = new ButtonListener();
        for (int x = 0; x < 6; x++) {
            for (int y = 0; y < 7; y++) {
                grid[x][y] = new JButton(" ");
                if (x != 0) grid[x][y].setEnabled(false);
                grid[x][y].addActionListener(bl);
                grid[x][y].setFont(new Font("Calibri", Font.BOLD, 34));
                spiefeldFenster.add(grid[x][y]);
            }
        }

        //Die beiden Elemente zum Fenster hinzufügen
        containerFenster.add(spiefeldFenster, BorderLayout.CENTER);
        containerFenster.add(labelPanel, BorderLayout.NORTH);

        //Die Elemente im Fenster sichtbar machen
        meinFrame.setContentPane(containerFenster);
        meinFrame.setVisible(true);
    }

    //Wenn ein Button gedrückt wurde, also ein Spieler etwas eingetippt hat überprüfen wir die Eingabe und setzen den Spielstein und pberprüfen ob der SPieler gewonnen hat
    static class ButtonListener implements java.awt.event.ActionListener {
        public void actionPerformed(java.awt.event.ActionEvent e) {
            //Schauen welcher Button gedrückt wurde
            for (int x = 0; x < 6; x++) {
                for (int y = 0; y < 7; y++) {
                    if (e.getSource() == grid[x][y]) {
                        if (spielfeld.spielfeld[0][y] == 0) {
                            //wenn noch Platz frei ist, Spielstein setzen.
                            for (int b = 5; b >= 0; b--) {
                                if (spielfeld.spielfeld[b][y] == 0) {
                                    //Wenn die Reihe voll gemacht wurde Button deaktivieren
                                    if (b == 0) {
                                        grid[b][y].setEnabled(false);
                                    }

                                    //Neuen Spielstein einspeichern
                                    spielfeld.spielfeld[b][y] = spielfeld.currentPlayer;
                                    grid[b][y].setText(String.valueOf(spielfeld.currentPlayer));

                                    //Prüfen ob jemand gewonnen hat
                                    if (Spielfeld.determineWinner(spielfeld)) {
                                        JOptionPane.showMessageDialog(null, "Spieler " + spielfeld.currentPlayer + " hat gewonnen!");
                                        resetGame();
                                    }

                                    //Spieler wechseln
                                    switch (spielfeld.currentPlayer) {
                                        case 1 -> spielfeld.currentPlayer = 2;
                                        case 2 -> spielfeld.currentPlayer = 1;
                                    }

                                    //In der Infoanzeige anzeigen, wer dran ist
                                    someText.setText("Spieler " + spielfeld.currentPlayer + " ist dran");
                                    break;
                                }
                            }
                        } else {
                            //Wenn die Reihe bereits voll ist Infotext anzeigen und Spieler erneut eingeben lassen
                            someText.setText("Reihe ist bereits voll");
                        }

                    }
                }

            }
        }
    }

    //Funktion um das Spielfeld zurückzusetzen - Es wird jedes Feld geleert und die Buttons wieder freigeschaltet
    public static void resetGame() {
        for (int x = 0; x < 6; x++) {
            for (int y = 0; y < 7; y++) {
                grid[x][y].setText("");
                if (x != 0) grid[x][y].setEnabled(false);
                else grid[x][y].setEnabled(true);
                spielfeld.spielfeld[x][y] = 0;
            }
        }
    }


    //Konsolenbasierte Spielversion

    //Startscreen
    public static void startScreen() {
        //Spielfeld generieren
        Spielfeld spielfeld = new Spielfeld();
        spielfeld.spielfeld[1][2] = 1;
        Ausgeben.spielfeldausgeben(spielfeld);

        //Alina Steuerung Anleitung
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
        startGame();
    }

    //Eigentliches Spiel
    public static void startGame() {
        Scanner scanner = new Scanner(System.in);

        //Solange das Spiel läuft neue Runden starten
        while (spielfeld.gameRunning) {
            //Zuerst Spieler wechseln
            switch (spielfeld.currentPlayer) {
                case 1 -> spielfeld.currentPlayer = 2;
                case 2 -> spielfeld.currentPlayer = 1;
            }

            boolean inputRunning = true;

            //Solange der Spieler noch keine richtige Eingabe gegeben hat Spieler Eingabe geben lassen
            while (inputRunning) {
                //Auswählen lassen an welche Stelle der Spieler den Spielstein setzen möchte
                System.out.println("\n\nSpieler " + spielfeld.currentPlayer + " Wo möchtest du den Spielstein setzen?");
                System.out.println("Bitte gib eine Zahl zwischen 1 und 7 ein: ");
                int input = scanner.nextInt();

                //Eingabe überprüfen
                if (!(1 <= input && input <= 7)) {
                    //Fehler
                    System.out.println("\n\n\nHey!\n\n Du hast einen Spielbereich ausgewählt," +
                                       " der nicht besetzt werden kann.\n " +
                                       "Bitte wähle einen Bereich zwischen 0-7 in der waagerechten und 0-6 in der senkrechten. ");

                } else {
                    //Testen, ob an dieser Stelle noch Platz ist,
                    if (spielfeld.spielfeld[0][input - 1] == 0) {
                        //wenn noch Platz frei ist, Spielstein setzen.
                        for (int x = 5; x >= 0; x--) {
                            if (spielfeld.spielfeld[x][input - 1] == 0) {
                                spielfeld.spielfeld[x][input - 1] = spielfeld.currentPlayer;
                                inputRunning = false;
                                break;
                            }
                        }
                    } else {
                        //Reihe ist bereits voll
                        System.out.println("\n\n\nHey!\n\n Du hast einen Spielbereich ausgewählt, " +
                                           "der schon mit Spielsteinen voll besetzt ist!\n Bitte wähle einen anderen Bereich aus.");
                    }
                }
            }

            //Gewinnerbestimmung
            if (Spielfeld.determineWinner(spielfeld)) spielfeld.gameRunning = false;

            //Falls ja, Spiel beenden
            //Sonst ist der andere Spieler dran
            Ausgeben.spielfeldausgeben(spielfeld);
        }

        System.out.println("Herzlichen Glückwunsch Spieler " + spielfeld.currentPlayer);
    }
}
