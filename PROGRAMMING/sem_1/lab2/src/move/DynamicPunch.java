package src.move;

import ru.ifmo.se.pokemon.*;

public class DynamicPunch extends PhysicalMove {
    private static final int BASE_POWER = 100;
    private static final int ACCURACY = 50;
    private static final double ATTACK_CHANCE = 1.0 / 3;
    private static final int ADDITIONAL_DAMAGE = 40;
    private String add;

    public DynamicPunch() {
        super(Type.FIGHTING, BASE_POWER, ACCURACY);
        this.add = "";
    }

    @Override
    protected void applyOppEffects(Pokemon target) {
        int turns = (int) (1 + Math.random() * 5);

        target.confuse();
        Effect additionalEffect = new Effect()
            .chance(ATTACK_CHANCE)
            .attack(ADDITIONAL_DAMAGE)
            .turns(turns);

        if (Math.random() < ATTACK_CHANCE) {
            target.addEffect(additionalEffect);
            add = ", нанося дополнительный урон на " + ADDITIONAL_DAMAGE + " в течение " + turns + " ходов";
        } else {
            add = ", но дополнительный эффект не сработал"; 
        }
    }

    @Override
    protected String describe() {
        return "использует Dynamic Punch" + add;
    }
}
