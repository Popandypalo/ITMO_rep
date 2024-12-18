package src.move;

import ru.ifmo.se.pokemon.*;

public class FlameCharge extends PhysicalMove {
    private static final int BASE_POWER = 50;
    private static final int ACCURACY = 100; 

    public FlameCharge() {
        super(Type.FIRE, BASE_POWER, ACCURACY);
    }

    @Override
    protected void applySelfEffects(Pokemon self) {
        self.setMod(Stat.SPEED, 1);
    }

    @Override
    protected String describe() {
        return "использует Flame Charge";
    }
}
