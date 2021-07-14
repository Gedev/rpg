import Character.race.*;
import Character.Character;
import Character.Dice;
import Character.Hero;

import Ennemi.*;
import Terrain.*;

import java.util.Random;
import java.util.Scanner;

import utils.Colors;


public class Game {
    // Regular Colors


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

        int nbElem = 4, currentXPosition = 0, currentYPosition = 0, currentFloor = 0, maxFloorReached = 0;
        char input = 'a';

        // ITEMS
        String emptyCell = "[  ]", character = "[🧛‍]", ladder = "[ ⬆ ]";

        // Tableau des répliques des collisions contre le mur
        String[] tabReplyHitWalls = {"Vous vous heurtez au mur.", "Vous vous heurtez à nouveau au mur.", "Peut-être devriez-vous changer de lunettes..", "Qu'est-ce que vous ne comprenez pas dans \"Vous vous heurtez contre le mur ?\""};

        // INITIALISATION ÉTAGE
        int[][] grid = new int[nbElem][nbElem];

        // IMPRESSION GRILLE
        int i = 0;
        for (int[] elem : grid) {
            int j = 0;
            for (int val : elem) {
                if (currentXPosition == i && currentYPosition == j)
                    System.out.print("[🧛‍]");
                else
                    System.out.print("[  ]");
                j++;
            }

            System.out.println("");
            i++;
        }

        System.out.println("q. Aller à Gauche ⬅ | d. Aller à Droite ➡ | z. Aller en haut ⬆ | s. Aller en bas ⬇ | C. Quitter |");
        input = sc.nextLine().charAt(0);

        // Tant que l'on a pas quitter (appuyer sur C)
        while (input != 'c') {
            switch (input) {
                case 'q' :
                    if(currentXPosition > 0) {
                        currentXPosition--;
                    } else {
                        System.out.println("Impossible d'aller vers la gauche, vous êtes déjà à la limite.");
                    }
                    break;
                case 'd' :
                    if(currentXPosition < grid.length-1) {
                        currentXPosition++;
                    } else {
                        System.out.println("Impossible d'aller vers la droite, vous êtes déjà à la limite.");
                    }
                    break;
                case 'z':
                    if(currentYPosition > 0) {
                        currentYPosition--;
                    } else {
                        System.out.println("Impossible d'aller vers le haut, vous êtes déjà à la limite.");
                    }
                    break;
                case 's':
                    if(currentYPosition < grid.length-1) {
                        currentYPosition++;
                    } else {
                        System.out.println("Impossible d'aller vers le bas, vous êtes déjà à la limite.");
                    }
                    break;
                default: System.out.println("Commande non reconnue.");
            }

            // LOOP WHILE : IMPRESSION GRILLE
            i = 0;
            for (int[] tab1D : grid) {
                int j = 0;
                for (int val : tab1D) {
                    if (currentXPosition == j && currentYPosition == i)
                        System.out.print("[🧛‍]");
                    else
                        System.out.print("[  ]");
                    j++;

                }
                System.out.println("");
                i++;
            }
            input = sc.nextLine().charAt(0);


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
            System.out.println("Malheureusement, la génétique n'était pas de votre côté." + Colors.YELLOW + "Votre vie est faible." + Colors.RESET);
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
            System.out.println(Colors.RED + "⚔ Vous rencontrez un " + ennemi.name + Colors.BLUE + "  👹 ! ⚔" + Colors.RESET);
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
                System.out.println(Colors.GREEN + "Vous infligez " + Colors.RESET + Colors.RED + damageDoneByHero + Colors.RESET + Colors.GREEN + " dégâts à l'ennemi !" + Colors.RESET);
                System.out.println("L'ennemi vous inflige \033[41m " + damageDoneByEnnemi + " " + Colors.RESET + " dégâts !");
                System.out.println("Vie Héro : " + mainCharacterHP + " || Vie ennemi : " + ennemiHP + "\n");

            } while (mainCharacterHP >= 1 && ennemiHP > 1);
            // CHECK IF DEAD
            if(mainCharacterHP <= 0) {
                dead = true;
                System.out.println(Colors.BLACK_BACKGROUND + "☠" + Colors.RESET + Colors.RED + "Vous êtes mort" + Colors.RESET + Colors.BLACK_BACKGROUND + "☠" + Colors.RESET);
            }

            if (mainCharacterHP > 0 && mainCharacterHP <= 3)
                frolageDeMort++;

            System.out.println("------------------------------------------------------");
            System.out.println("------------------------------------------------------");

        }while(!dead);

        // RECAPITALATUF

        if (frolageDeMort > 0)
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
