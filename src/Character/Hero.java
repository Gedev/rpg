package Character;

public abstract class Hero extends Character {

    int stockGold;
    int stockLeather;
    public String pseudo;

    public Hero(int stamina, int strength, int hp, int bonusStrength, int bonusStamina, int stockGold, int stockLeather, String pseudo) {
        super(stamina, strength, hp, bonusStrength, bonusStamina);
        this.stockGold = stockGold;
        this.stockLeather = stockLeather;
        this.pseudo = pseudo;
    }

    public int getStockGold() {
        return stockGold;
    }

    public void setStockGold(int stockGold) {
        this.stockGold = stockGold;
    }

    public int getStockLeather() {
        return stockLeather;
    }

    public void setStockLeather(int stockLeather) {
        this.stockLeather = stockLeather;
    }
}
