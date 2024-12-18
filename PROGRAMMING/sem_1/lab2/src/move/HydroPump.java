package src.move;

import ru.ifmo.se.pokemon.*;

public class HydroPump extends SpecialMove {
    private static final int BASE_POWER = 110;
    private static final int ACCURACY = 80;

    public HydroPump() {
        super(Type.WATER, BASE_POWER, ACCURACY);
    }

    @Override
    protected void applyOppDamage(Pokemon target, double damage) {
        target.setMod(Stat.HP, (int) Math.round(damage));
    }

    @Override
    protected String describe() {
        return "использует Hydro Pump";
    }
}