package src.move;

import ru.ifmo.se.pokemon.*;

public class Rest extends StatusMove {
    private static final int HP_RESTORE = Integer.MAX_VALUE;
    private static final int SLEEP_DURATION = 2;

    public Rest() {
        super(Type.PSYCHIC, 0, 0);
    }

    @Override
    protected void applySelfEffects(Pokemon self) {
        self.setMod(Stat.HP, HP_RESTORE);
        Effect sleepEffect = new Effect().turns(SLEEP_DURATION).condition(Status.SLEEP);
        self.addEffect(sleepEffect);
    }
    
    @Override
    protected String describe() {
        return "использует Rest, восстанавливая максимум HP и засыпая!";
    }
}
