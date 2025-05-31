
public class Main {
    public static void main(String[] args) {
        Handler chain = new DamageHandler(5);
        chain.setNext(new ArmorHandler(10))
             .setNext(new ShieldHandler(20))
             .setNext(new FinalHealthHandler());

        Character hero = new Character(100, chain);

        hero.takeDamage(50);
        int finalHealth = hero.getHealth();
    }
}
