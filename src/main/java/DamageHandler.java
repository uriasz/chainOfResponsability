
public class DamageHandler extends Handler {
    private int damageReduction;

    public DamageHandler(int damageReduction) {
        this.damageReduction = damageReduction;
    }

    @Override
    public int handle(int damage, Character character) {
        int reduced = Math.max(0, damage - damageReduction);
        if (next != null) {
            return next.handle(reduced, character);
        }
        return reduced;
    }
}
