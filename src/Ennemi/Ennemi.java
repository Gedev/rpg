package Ennemi;
import Character.Character;

public abstract class Ennemi extends Character {

    public String name;

    public Ennemi(String name, int stamina, int strength, int hp, int bonusStrength, int bonusStamina) {
        super(stamina, strength, hp, bonusStrength, bonusStamina);
        this.name = name;
    }
}
