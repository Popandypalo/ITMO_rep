package src.move;
import ru.ifmo.se.pokemon.*;

public class Waterfall extends PhysicalMove {
    private static final int BASE_POWER = 80;
    private static final int ACCURACY = 100;
    private static final double FLINCH_CHANCE = 0.2;
    private String add;

    public Waterfall() {
        super(Type.WATER, BASE_POWER, ACCURACY);
        this.add = "";
    }

    @Override
    protected void applyOppEffects(Pokemon target) {
        if (Math.random() < FLINCH_CHANCE) {
            Effect.flinch(target);
            add = ", оглушая противника";
        } else {
            add = ", но не оглушает противника";
        }
    }

    @Override
    protected String describe() {
        return "использует Waterfall" + add;
    }
}
