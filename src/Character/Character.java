package Character;

public abstract class Character {

    // Endurance
    // Force
    // Points de vie
    private int stamina;
    private int strength;
    public int hp;
    private final int bonusStrength;
    private final int bonusStamina;

    public Character(int stamina, int strength, int hp, int bonusStrength, int bonusStamina) {
        this.stamina = stamina;
        this.strength = strength;
        this.hp = hp;
        this.bonusStrength = bonusStrength;
        this.bonusStamina = bonusStamina;
    }

    public void attack() {

    }

    public int getBonusStrength() {
        return bonusStrength;
    }

    public int getBonusStamina() {
        return bonusStamina;
    }

    public int getStamina() {
        return stamina;
    }

    public void setStamina(int stamina) {
        this.stamina = stamina;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }
}

// Personnage -> Héros [Pseudo] -> Humain / Nain
//               Ennemi [Nom] -> Loup / Orque / Ennemi.Dragonnet
//               Pnj