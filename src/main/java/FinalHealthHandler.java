
public class FinalHealthHandler extends Handler {
    @Override
    public int handle(int damage, Character character) {
        character.reduceHealth(damage);
        return damage;
    }
}
