public class Ausgeben {
    public static void spielfeldausgeben(Spielfeld spielfeld) {

        for (int x = 0; x < 6; x++) {

            for (int y = 0; y < 7; y++) {
                System.out.print("|");
                System.out.print(spielfeld.spielfeld[x][y]);

            }
            System.out.print("|");

           System.out.println();

            System.out.println("———————————————");



        }


    }

}
