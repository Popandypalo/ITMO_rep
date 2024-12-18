package src.move;

import ru.ifmo.se.pokemon.*;

public class FireBlast extends SpecialMove {
    private static final int BASE_POWER = 110;
    private static final int ACCURACY = 85;
    private static final double BURN_CHANCE = 0.1;
    private String add;

    public FireBlast() {
        super(Type.FIRE, BASE_POWER, ACCURACY);
        this.add = "";
    }

    @Override
    protected void applyOppEffects(Pokemon target) {
        if (Math.random() < BURN_CHANCE) {
            Effect.burn(target);
            add = ", вызывая ожог";
        } else {
            add = ", но не вызывает ожог";
        }
    }

    @Override
    protected String describe() {
        return "использует Fire Blast" + add;
    }
}
