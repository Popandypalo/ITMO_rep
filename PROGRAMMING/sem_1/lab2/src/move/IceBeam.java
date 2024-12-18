package src.move;

import ru.ifmo.se.pokemon.*;

public class IceBeam extends SpecialMove {
    private static final int BASE_POWER = 90;
    private static final int ACCURACY = 100;
    private static final double FREEZE_CHANCE = 0.1;
    private String add;

    public IceBeam() {
        super(Type.ICE, BASE_POWER, ACCURACY);
        this.add = "";
    }

    @Override
    protected void applyOppDamage(Pokemon target, double damage) {
        int roundedDamage = (int) Math.round(damage);
        target.setMod(Stat.HP, roundedDamage);
    }

    @Override
    protected void applySelfEffects(Pokemon self) {
        if (Math.random() <= FREEZE_CHANCE) {
            Effect.freeze(self);
            add = ", замораживая противника";
        } else{
            add = ", но не замораживая противника";
        }
        self.setMod(Stat.ACCURACY, 0);
    }

    @Override
    protected String describe() {
        return "применяет Ice Beam" + add;
    }
}
