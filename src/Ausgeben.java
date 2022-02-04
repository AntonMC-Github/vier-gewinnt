public class Ausgeben {
    public static void spielfeldausgeben(Spielfeld spielfeld) {
        System.out.println("     1     2     3     4     5     6     7");

        for (int x = 0; x < 6; x++) {

            for (int y = 0; y < 7; y++) {
                System.out.print("  |  ");
                System.out.print(spielfeld.spielfeld[x][y]);

            }
            System.out.print("  |");

           System.out.println();

            System.out.println("  |—————————————————————————————————————————|");



        }


    }

}
