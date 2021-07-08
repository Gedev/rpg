package Character;

import java.util.Random;

public class Dice {
    Random rand = new Random();
    int nbFaces;

    public Dice(int nbFaces) {
        this.nbFaces = nbFaces;
    }

    public int diceRollFourTimes() {
        int[] diceRolled = new int[4];
        int min = 0;
        int sommeTotale = 0;

        for (int i = 0; i < 4; i++) {
            diceRolled[i] = diceRoll();

            // SEARCH MIN DICE
            if (i == 0 || diceRolled[i] < min){
                min = diceRolled[i];
            }

            sommeTotale += diceRolled[i];
        }
        sommeTotale -= min;

        return sommeTotale;
    }

    public int diceRoll() {
        int nb = rand.nextInt(nbFaces)+1;
        return nb;
    }
}
