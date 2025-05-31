
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DamageHandlerTest {

    private Character setupCharacter() {
        Handler chain = new DamageHandler(5);
        chain.setNext(new ArmorHandler(10))
             .setNext(new ShieldHandler(20))
             .setNext(new FinalHealthHandler());

        return new Character(100, chain);
    }

    @Test
    public void testDamageLessThanDefenses() {
        Character hero = setupCharacter();
        hero.takeDamage(10); // 10-5=5, 5-10=0, shield untouched
        assertEquals(100, hero.getHealth());
    }

    @Test
    public void testDamageExactlyDefenses() {
        Character hero = setupCharacter();
        hero.takeDamage(35); // 35-5=30, 30-10=20, 20-20=0
        assertEquals(100, hero.getHealth());
    }

    @Test
    public void testDamageMoreThanDefenses() {
        Character hero = setupCharacter();
        hero.takeDamage(50); // 50-5=45, 45-10=35, 35-20=15
        assertEquals(85, hero.getHealth());
    }

    @Test
    public void testMultipleAttacksDepletingShield() {
        Character hero = setupCharacter();
        hero.takeDamage(30); // shield left with 20-15=5
        assertEquals(100, hero.getHealth());
        hero.takeDamage(10); // shield left with 5-5=0
        assertEquals(100, hero.getHealth());
        hero.takeDamage(20); // no shield, 20-5=15, 15-10=5, health down by 5
        assertEquals(95, hero.getHealth());
    }

    @Test
    public void testOverkillDamage() {
        Character hero = setupCharacter();
        hero.takeDamage(200); // 200-5=195, 195-10=185, 185-20=165, health -165
        assertEquals(0, hero.getHealth());
    }
}
