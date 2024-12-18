package src.move;

import ru.ifmo.se.pokemon.*;

public class Scald extends SpecialMove {
    private static final int BASE_POWER = 80;
    private static final int ACCURACY = 100;
    private static final double BURN_CHANCE = 0.3;
    private String add;

    public Scald() {
        super(Type.WATER, BASE_POWER, ACCURACY);
        this.add = "";
    }

    @Override
    protected void applyOppEffects(Pokemon target) {
        Effect burnEffect = new Effect().chance(BURN_CHANCE).condition(Status.BURN);
        if (burnEffect.success()) {
            add = ", вызывая ожог";
        } else {
            add = ", но не вызывает ожог";
        }
        target.addEffect(burnEffect);
    }

    @Override
    protected void applyOppDamage(Pokemon target, double damage) {
        int roundedDamage = (int) Math.round(damage);
        target.setMod(Stat.HP, roundedDamage);
    }

    @Override
    protected String describe() {
        return "использует Scald" + add;
    }
}