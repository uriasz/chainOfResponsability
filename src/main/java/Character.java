
public class Character {
    private int health;
    private Handler handlerChain;

    public Character(int health, Handler handlerChain) {
        this.health = health;
        this.handlerChain = handlerChain;
    }

    public int getHealth() {
        return health;
    }

    public void reduceHealth(int amount) {
        this.health = Math.max(0, this.health - amount);
    }

    public void takeDamage(int damage) {
        handlerChain.handle(damage, this);
    }
}
