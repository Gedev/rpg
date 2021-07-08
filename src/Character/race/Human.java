package Character.race;
import Character.Hero;

public class Human extends Hero {

    // NEW Hero has 0 gold and 0 leather

    public Human(int stamina, int strength, int hp, String pseudo) {
        super(stamina, strength, hp, 1, 1, 0, 0, pseudo);
    }
}

