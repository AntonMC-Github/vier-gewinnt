import java.util.Arrays;

public class Main {
    public static void main(String[] args) {

        Spielfeld spielfeld = new Spielfeld();
        spielfeld.spielfeld[1][2] = 1;
        System.out.println(Arrays.deepToString(spielfeld.spielfeld));

        String ausgeben = new Ausgeben().spielfeldausgeben(spielfeld);


    }
}
