package src.move;

import ru.ifmo.se.pokemon.*;

public class Bulldoze extends PhysicalMove {
    private static final int BASE_POWER = 60;
    private static final int ACCURACY = 100;

    public Bulldoze() {
        super(Type.GROUND, BASE_POWER, ACCURACY);
    }

    @Override
    protected void applyOppEffects(Pokemon target) {
        super.applyOppEffects(target);
        target.addEffect(new Effect().stat(Stat.SPEED, -1));
    }

    @Override
    protected String describe() {
        return "использует Bulldoze";
    }
}