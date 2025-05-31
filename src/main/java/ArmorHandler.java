
public class ArmorHandler extends Handler {
    private int armorValue;

    public ArmorHandler(int armorValue) {
        this.armorValue = armorValue;
    }

    @Override
    public int handle(int damage, Character character) {
        int absorbed = Math.max(0, damage - armorValue);
        if (next != null) {
            return next.handle(absorbed, character);
        }
        return absorbed;
    }
}
