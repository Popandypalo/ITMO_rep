package src.move;

import ru.ifmo.se.pokemon.*;

public class BodySlam extends PhysicalMove {
    private static final int BASE_POWER = 85;
    private static final int ACCURACY = 100;
    private static final double PARALYZE_CHANCE = 0.3;
    private String add;

    public BodySlam() {
        super(Type.NORMAL, BASE_POWER, ACCURACY);
        this.add = "";
    }

    @Override
    protected void applyOppEffects(Pokemon target) {
        if (Math.random() <= PARALYZE_CHANCE) {
            Effect.paralyze(target);
            add = ", парализуя противника";
        } else {
            add = ", но парализовать противника не удалось";
        }
    }

    @Override
    protected String describe() {
        return "использует Body Slam" + add;
    }
}
