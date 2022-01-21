public class Ausgeben {
    public String spielfeldausgeben(Spielfeld spielfeld) {

        for (int x = 0; x < 6; x++) {
            for (int y = 0; y < 7; y++) {
                System.out.print(spielfeld.spielfeld[x][y]);
            }
            System.out.println();
        }

        return "";
    }

}
