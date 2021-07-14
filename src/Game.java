import Character.race.*;
import Character.Character;
import Character.Dice;
import Character.Hero;

import Ennemi.*;
import Terrain.*;

import java.util.Random;
import java.util.Scanner;

import Utils.Colors;
import Utils.GameReplies;


public class Game {

    public static void main(String[] args) {
        Random rand = new Random();
        Scanner sc = new Scanner(System.in);

        int[] frolageDeMort = new int[1];
        int[] statistics = new int[5];

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

        int nbElem = 10, currentXPosition = 0, currentYPosition = 0, currentFloor = 0, maxFloorReached = 0;
        char input = 'a';

        // ITEMS
        String emptyCell = "[  ]", character = "[🧛‍]", ladder = "[ ⬆ ]";

        String[][] grid = new String[nbElem][nbElem];

        initGrid(grid, currentYPosition, currentXPosition, rand);

        System.out.println("q. Aller à Gauche ⬅ | d. Aller à Droite ➡ | z. Aller en haut ⬆ | s. Aller en bas ⬇ | C. Quitter |");
        input = sc.nextLine().charAt(0);
        int wallhit = 0;
        boolean victory = true;

        // Tant que l'on a pas quitter (appuyer sur C)
        while (input != 'c') {
            switch (input) {
                case 'q' :
                    if(currentXPosition > 0) {
                        if(grid[currentYPosition][currentXPosition - 1].equals("[" + Colors.RED.getCodeASCII() + "👹" + Colors.RESET.getCodeASCII() + "]")){
                            System.out.println("Ma position : " + currentXPosition + ", " + currentYPosition );
                            System.out.println("grid[currentYPosition][currentXPosition - 1]" + grid[currentYPosition][currentXPosition - 1]);
                            victory = battle(mainCharacter, rand, frolageDeMort, statistics);
                        }
                        currentXPosition--;
                        grid[currentYPosition][currentXPosition+1] = "[  ]";
                    } else {
                        wallhit++;
                        printWallHitReplies(wallhit);
                    }
                    break;
                case 'd' :
                    if(currentXPosition < grid.length-1) {
                        if(grid[currentYPosition][currentXPosition + 1].equals("[" + Colors.RED.getCodeASCII() + "👹" + Colors.RESET.getCodeASCII() + "]")){
                            System.out.println("Ma position : " + currentXPosition + ", " + currentYPosition );
                            System.out.println("grid[currentYPosition][currentXPosition + 1]" + grid[currentYPosition][currentXPosition + 1]);
                            victory = battle(mainCharacter, rand, frolageDeMort, statistics);
                        }
                        currentXPosition++;
                        grid[currentYPosition][currentXPosition-1] = "[  ]";
                    } else {
                        wallhit++;
                        printWallHitReplies(wallhit);
                    }
                    break;
                case 'z':
                    if(currentYPosition > 0) {
                        if(grid[currentYPosition - 1][currentXPosition].equals("[" + Colors.RED.getCodeASCII() + "👹" + Colors.RESET.getCodeASCII() + "]")){
                            System.out.println("Ma position : " + currentXPosition + ", " + currentYPosition );
                            System.out.println("grid[currentYPosition - 1][currentXPosition]" + grid[currentYPosition -1 ][currentXPosition]);
                            victory = battle(mainCharacter, rand, frolageDeMort, statistics);
                        }
                        currentYPosition--;
                        grid[currentYPosition+1][currentXPosition] = "[  ]";
                    } else {
                        wallhit++;
                        printWallHitReplies(wallhit);
                    }
                    break;
                case 's':
                    if(currentYPosition < grid.length-1) {
                        if(grid[currentYPosition + 1][currentXPosition].equals("[" + Colors.RED.getCodeASCII() + "👹" + Colors.RESET.getCodeASCII() + "]")){
                            System.out.println("Ma position : " + currentXPosition + ", " + currentYPosition );
                            System.out.println("grid[currentYPosition + 1][currentXPosition]" + grid[currentYPosition + 1][currentXPosition]);
                            victory = battle(mainCharacter, rand, frolageDeMort, statistics);
                        }
                        currentYPosition++;
                        grid[currentYPosition-1][currentXPosition] = "[  ]";
                    } else {
                        wallhit++;
                        printWallHitReplies(wallhit);
                    }
                    break;
                default: System.out.println("Commande non reconnue.");
            }
            printGrid(grid, currentYPosition, currentXPosition);

            if (victory) {
                input = sc.nextLine().charAt(0);
            } else {
                input = 'c';
            }

        }
    }

    public static void initGrid(String[][] grid, int currentYPosition, int currentXPosition, Random rand) {

        for (int y = 0; y < 10; y++) {
            for (int x = 0; x < 10; x++) {
                int nombreAleatoire = rand.nextInt(3);

                if (currentYPosition == y && currentXPosition == x)
                    grid[y][x] = Colors.PURPLE.getCodeASCII() + "[🧛‍]" + Colors.RESET.getCodeASCII();
                else if (nombreAleatoire == 1)
                        grid[y][x] ="[" + Colors.RED.getCodeASCII() + "👹" + Colors.RESET.getCodeASCII() + "]";
                else
                    grid[y][x] = "[  ]";

                System.out.print(grid[y][x]);
            }
            System.out.print("\n");
        }
    }

    public static void printGrid(String[][] grid, int currentYPosition, int currentXPosition) {

        for (int y = 0; y < 10; y++) {
            for (int x = 0; x < 10; x++) {
                if (currentYPosition == y && currentXPosition == x)
                    grid[y][x] = Colors.PURPLE.getCodeASCII() + "[🧛‍]" + Colors.RESET.getCodeASCII();

                System.out.print(grid[y][x]);
            }
            System.out.print("\n");
        }
    }

    public static void printWallHitReplies(int wallhit) {
        switch (wallhit) {
            case 1 -> System.out.println(GameReplies.wallHitReply_1.getReplies());
            case 2 -> System.out.println(GameReplies.wallHitReply_2.getReplies());
            case 3 -> System.out.println(GameReplies.wallHitReply_3.getReplies());
            case 4 -> System.out.println(GameReplies.wallHitReply_4.getReplies());
            case 8 -> System.out.println(GameReplies.wallHitReply_5.getReplies());
            case 9 -> System.out.println(GameReplies.wallHitReply_6.getReplies());
            case 10 -> System.out.println(GameReplies.wallHitReply_7.getReplies());
        }
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
            System.out.println("Malheureusement, la génétique n'était pas de votre côté." + Colors.YELLOW.getCodeASCII() + "Votre vie est faible." + Colors.RESET.getCodeASCII());
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

    public static boolean battle(Character mainCharacter, Random rand, int[] frolageDeMort, int[] statistics) {
        boolean dead = false;

            // GENERATE ENNEMI
            Ennemi ennemi = createEnnemi(rand);
            System.out.println(Colors.RED.getCodeASCII() + "⚔ Vous rencontrez un " + Colors.RESET.getCodeASCII() + ennemi.name + Colors.BLUE.getCodeASCII() + "  👹 ! ⚔" + Colors.RESET.getCodeASCII());
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
                System.out.println(Colors.GREEN.getCodeASCII() + "Vous infligez " + Colors.RESET.getCodeASCII() + Colors.RED.getCodeASCII() + damageDoneByHero + Colors.RESET.getCodeASCII() + Colors.GREEN.getCodeASCII() + " dégâts à l'ennemi !" + Colors.RESET.getCodeASCII());
                System.out.println("L'ennemi vous inflige \033[41m " + damageDoneByEnnemi + " " + Colors.RESET.getCodeASCII() + " dégâts !");
                System.out.println("Vie Héro : " + mainCharacterHP + " || Vie ennemi : " + ennemiHP + "\n");

            } while (mainCharacterHP >= 1 && ennemiHP > 1);

            if (mainCharacterHP > 0 && mainCharacterHP <= 3)
                frolageDeMort[0]++;

            System.out.println("------------------------------------------------------");
            System.out.println("------------------------------------------------------");

        // CHECK IF DEAD
        if(mainCharacterHP <= 0) {
            dead = true;
            System.out.println(Colors.BLACK_BACKGROUND.getCodeASCII() + "☠" + Colors.RESET.getCodeASCII() + Colors.RED.getCodeASCII() + "Vous êtes mort" + Colors.RESET.getCodeASCII() + Colors.BLACK_BACKGROUND.getCodeASCII() + "☠" + Colors.RESET.getCodeASCII());

            // RECAPITALATUF
            if (frolageDeMort[0] > 0)
                System.out.println("Vous avez frôlé la mort " + frolageDeMort[0] + " fois avant de mourir");

            System.out.println("Vous avez vaincu " + statistics[0] + " orcs !");
            System.out.println("Vous avez vaincu " + statistics[1] + " dragonnets !");
            System.out.println("Vous avez vaincu " + statistics[2] + " loups !");

            return false;
        }

        // STATISTICS
        switch (ennemi.name) {
            case "orc" -> statistics[0]++;
            case "whelp" -> statistics[1]++;
            case "wolf" -> statistics[2]++;
        }


        return true;
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
