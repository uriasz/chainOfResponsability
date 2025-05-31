
public class ShieldHandler extends Handler {
    private int shieldPoints;

    public ShieldHandler(int shieldPoints) {
        this.shieldPoints = shieldPoints;
    }

    @Override
    public int handle(int damage, Character character) {
        int leftover = Math.max(0, damage - shieldPoints);
        shieldPoints = Math.max(0, shieldPoints - damage);
        if (next != null) {
            return next.handle(leftover, character);
        }
        return leftover;
    }
}
