package Character;

public class Character {

    // Endurance
    // Force
    // Points de vie
    private int stamina;
    private int strength;
    private int hp;

    public Character(int stamina, int strength, int hp) {
        this.stamina = stamina;
        this.strength = strength;
        this.hp = hp;
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


// 4 Questions

// - Comment implémenter des classes ? Via l'héritage ou autre chose ? Pourquoi ?
//    -> Une classe est un type de personnage
// - Est-ce que "Héros" doit être implémenter ? Quelle est la différence avec un Personnage ? Est-ce qu'un Personnage se divisera entre "Héros" et "Pnj" ?
// - Comment structurer tout ça ?
// - Créer une classe pour chaque type de monstre ? Ou plutôt donner le type du monstre en paramètre à l'instanciation d'un ennemi ?

// Personnage -> Héros [Pseudo] -> Humain / Nain
//               ennemi [Nom] -> Loup / Orque / Ennemi.Dragonnet
//               Pnj