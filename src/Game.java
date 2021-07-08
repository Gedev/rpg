import Character.race.*;
import Character.Character;
import Character.Dice;

import Terrain.*;

import java.util.Scanner;

public class Game {
    public static void main(String[] args) {

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

        do {

        } while (mainCharacter.getHp() >= 0);

    }
    public static void printMenu(Scanner sc) {
        System.out.println("__-----_________________{]__________________________________________________");
        System.out.println("{&&&&&&&#%%&#%&%&%&%&%#%&|]__________________________________________________\\");
        System.out.println("                        {]");

        System.out.println("Bienvenue dans ce mini-RPG");

        // CHOOSE YOUR NAME
        System.out.println("Quel est votre pseudo : ");
    }

    public static Character createCharacter(int raceChoosed, String pseudo) {
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
                System.out.println(mainCharacterElf.getHp());
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

    public static int applyStatisticsBonus(int stamina) {
        int bonus = 0;
        if(stamina < 5)
            bonus = -1;
        else if (stamina < 10)
            bonus = 0;
        else if (stamina < 15)
            bonus = 1;
        else
            bonus = 2;

        return bonus;
    }

}
