import Character.race.*;
import Character.Character;
import Character.Dice;
import Character.Hero;

import Ennemi.*;
import Terrain.*;

import java.util.Random;
import java.util.Scanner;


public class Game {
    // Regular Colors
    public static final String RESET = "\033[0m";

    public static final String BLACK = "\033[0;30m";   // BLACK
    public static final String RED = "\033[0;31m";     // RED
    public static final String GREEN = "\033[0;32m";   // GREEN
    public static final String YELLOW = "\033[0;33m";  // YELLOW
    public static final String BLUE = "\033[0;34m";    // BLUE
    public static final String PURPLE = "\033[0;35m";  // PURPLE
    public static final String CYAN = "\033[0;36m";    // CYAN
    public static final String WHITE = "\033[0;37m";   // WHITE

    public static final String BLACK_BACKGROUND_BRIGHT = "\033[0;100m";// BLACK
    public static final String BLACK_BACKGROUND = "\033[40m";  // BLACK
    public static final String RED_BACKGROUND = "\033[41m";    // RED
    public static final String WHITE_BACKGROUND_BRIGHT = "\033[0;107m";   // WHITE

    public static void main(String[] args) {

        Random rand = new Random();
        Scanner sc = new Scanner(System.in);

        // MENU
        printMenu(sc);
        String pseudo = sc.nextLine();

        // CHOOSE THE RACE
        System.out.println("Choississez votre race : ");
        System.out.println("1. Humain || 2. Elfe || 3. Nain");
        int raceChoosed = Integer.parseInt(sc.nextLine());
        Character mainCharacter = createCharacter(raceChoosed, pseudo);

        // CHOOSE THE TERRAIN
        System.out.println("Choisissez le terrain que vous désirez explorer : ");
        System.out.println("1. Désert || 2. Forêt || 3. Plaine || 4. Étendues rocailleuses");

        int terrainChoosed = Integer.parseInt(sc.nextLine());

        switch (terrainChoosed) {
            case 1 -> {
                Desert desertTerrain = new Desert();
                System.out.println("Vous décidez d'explorer le désert");
            }
            case 2 -> {
                Forest forestTerrain = new Forest();
                System.out.println("Vous décidez d'explorer la forêt");
            }
            case 3 -> {
                Plain plainTerrain = new Plain();
                System.out.println("Vous décidez d'explorer la plaine");
            }
            case 4 -> {
                Rocky rockyTerrain = new Rocky();
                System.out.println("Vous décidez d'explorer les étendues rocailleuses");
            }
        }
        battle(mainCharacter, rand);
    }

    public static void printMenu(Scanner sc) {
        System.out.println("__-----_________________{]__________________________________________________");
        System.out.println("{&&&&&&&#%%&#%&%&%&%&%#%&|]__________________________________________________\\");
        System.out.println("                        {]");

        System.out.println("Bienvenue dans ce mini-RPG");

        // CHOOSE YOUR NAME
        System.out.println("Quel est votre pseudo : ");
    }

    public static Hero createCharacter(int raceChoosed, String pseudo) {
        int stamina;
        switch (raceChoosed) {
            case 1 -> {
                Human mainCharacterHuman = new Human(stamina = setStaminaStrength(), setStaminaStrength(), setHealth(stamina), pseudo);
                System.out.println("Vous incarnez désormais un humain");
                printCharacteristics(mainCharacterHuman);
                return mainCharacterHuman;
            }
            case 2 -> {
                Elf mainCharacterElf = new Elf(stamina = setStaminaStrength(), setStaminaStrength(), setHealth(stamina), pseudo);
                System.out.println("Vous incarnez désormais un elfe");
                printCharacteristics(mainCharacterElf);
                return mainCharacterElf;
            }
            case 3 -> {
                Dwarf mainCharacterDwarf = new Dwarf(stamina = setStaminaStrength(),setStaminaStrength(), setHealth(stamina), pseudo);
                System.out.println("Vous incarnez désormais un nain");
                printCharacteristics(mainCharacterDwarf);
                return mainCharacterDwarf;
            }
        }
        return null;
    }

    public static void printCharacteristics(Character mainCharacter) {
        System.out.println("Hp : " + mainCharacter.getHp());
        System.out.println("Stamina : " + mainCharacter.getStamina());
        System.out.println("Strength : " + mainCharacter.getStrength());

        if (mainCharacter.getHp() < 12)
            System.out.println("Malheureusement, la génétique n'était pas de votre côté. \033[0;33m Votre vie est faible." + RESET);
    }

    public static int setStaminaStrength() {
        Dice dice = new Dice(6);
        int stamina;
        return stamina = dice.diceRollFourTimes();
    }

    public static int setHealth(int stamina) {
        int health = 0;
        int bonusStamina = applyStatisticsBonus(stamina);
        health = stamina + bonusStamina;
        return health;
    }

    public static int applyStatisticsBonus(int nb) {
        int bonus = 0;
        if(nb < 5)
            bonus = -1;
        else if (nb < 10)
            bonus = 0;
        else if (nb < 15)
            bonus = 1;
        else
            bonus = 2;

        return bonus;
    }

    public static void battle(Character mainCharacter, Random rand) {
        boolean dead = false;
        int frolageDeMort = 0;

        // FIGHT LOOP WHILE NOT DEAD
        do {
            // GENERATE ENNEMI
            Ennemi ennemi = createEnnemi(rand);
            System.out.println(RED + "⚔ Vous rencontrez un " + ennemi.name + BLUE + "  👹 ! ⚔" + RESET);
            System.out.println("------------------------------------------------------");
            System.out.println("Force de l'ennemi = " + ennemi.getStrength());
            System.out.println("Bonus Force Héro = " + mainCharacter.getBonusStrength());

            int mainCharacterHP = mainCharacter.getHp() + mainCharacter.getBonusStamina();
            int ennemiHP = ennemi.getHp();



            // FIGHT LOOP AGAINST 1 ENNEMI
            do {
                // Damages calculation
                int damageDoneByEnnemi = damageDone(ennemi);
                int damageDoneByHero = damageDone(mainCharacter);

                // Apply damages
                mainCharacterHP -= damageDoneByEnnemi;
                ennemiHP -= damageDoneByHero;
                System.out.println(GREEN + "Vous infligez " + RESET + RED + damageDoneByHero + RESET + GREEN + " dégâts à l'ennemi !" + RESET);
                System.out.println("L'ennemi vous inflige \033[41m " + damageDoneByEnnemi + " " + RESET + " dégâts !");
                System.out.println("Vie Héro : " + mainCharacterHP + " || Vie ennemi : " + ennemiHP + "\n");

            } while (mainCharacterHP >= 1 && ennemiHP > 1);
            // CHECK IF DEAD
            if(mainCharacterHP <= 0) {
                dead = true;
                System.out.println(BLACK_BACKGROUND + "☠" + RESET + RED + "Vous êtes dead" + RESET + BLACK_BACKGROUND + "☠" + RESET);
            }

            if (mainCharacterHP > 0 && mainCharacterHP <= 3)
                frolageDeMort++;

            System.out.println("------------------------------------------------------");
            System.out.println("------------------------------------------------------");

        }while(!dead);

        // RECAPITALATUF
        System.out.println("Vous avez frôlé la mort " + frolageDeMort + " fois avant de mourir");
    }

    public static Ennemi createEnnemi(Random rand) {
        int randomNumberToCreateEnnemi = rand.nextInt(3)+1;
        int stamina;
        switch (randomNumberToCreateEnnemi) {
            case 1 -> {
                Orc orc = new Orc(stamina = setStaminaStrength(), setStaminaStrength(), setHealth(stamina));
                return orc;
            }
            case 2 -> {
                Whelp whelp = new Whelp(stamina = setStaminaStrength(), setStaminaStrength(), setHealth(stamina));
                return whelp;
            }
            case 3 -> {
                Wolf wolf = new Wolf(stamina = setStaminaStrength(), setStaminaStrength(), setHealth(stamina));
                return wolf;
            }
        }
        return null;
    }

    public static int damageDone(Character character) {
        Dice dice = new Dice(4);
        int bonus = applyStatisticsBonus(character.getStrength());
        int damage = dice.diceRoll() + bonus;

        return damage;
    }

}
