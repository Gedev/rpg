import Character.Character;
import Character.race.Elf;
import Character.race.Human;
import Character.race.Dwarf;
import java.util.Scanner;

public class Game {
    public static void main(String[] args) {
        System.out.println("Bienvenue dans ce mini-RPG");
        System.out.println("Choississez votre classe : ");

        Scanner sc = new Scanner(System.in);

        int raceChoosed = Integer.parseInt(sc.nextLine());
        switch (raceChoosed) {
            case 1 :
                Character mainCharacterHuman = new Human(1, 1, 1);
                System.out.println("Vous incarnez désormais un humain");
                printCharacteristics(mainCharacterHuman);
            break;
            case 2 :
                Character mainCharacterElf = new Elf(1, 1, 1);
                System.out.println("Vous incarnez désormais un elfe");
                printCharacteristics(mainCharacterElf);
            break;

            case 3 :
                Character mainCharacter = new Dwarf(1, 1, 1);
                System.out.println("Vous incarnez désormais un nain");
            break;
        }
    }

    public static void printCharacteristics(Character mainCharacter) {
        System.out.println("Hp : " + mainCharacter.getHp());
        System.out.println("Stamina : " + mainCharacter.getStamina());
        System.out.println("Strength : " + mainCharacter.getStrength());
    }
}
