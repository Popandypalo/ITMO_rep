package src.move;
import ru.ifmo.se.pokemon.*;

public class FrostBreath extends SpecialMove {
    private static final int BASE_POWER = 60;
    private static final int ACCURACY = 90;
    private static final double CRITICAL_HIT_MULTIPLIER = 1.5;

    public FrostBreath() {
        super(Type.ICE, BASE_POWER, ACCURACY);
    }

    @Override
    protected double calcCriticalHit(Pokemon attacker, Pokemon defender) {
        return CRITICAL_HIT_MULTIPLIER;
    }

    @Override
    protected String describe() {
        return "использует Frost Breath";
    }
}