package src.move;

import ru.ifmo.se.pokemon.*;

public class Facade extends PhysicalMove {
    private static final int BASE_POWER = 70;
    private static final int ACCURACY = 100;
    private String add;

    public Facade() {
        super(Type.NORMAL, BASE_POWER, ACCURACY);
        this.add = "";
    }

    @Override
    protected void applyOppDamage(Pokemon target, double damage) {
        if (isStatusEffected(target)) {
            damage *= 2;
            add = ", нанося удвоенный урон";
        }
        super.applyOppDamage(target, damage);
    }

    private boolean isStatusEffected(Pokemon p) {
        Status status = p.getCondition();
        return status.equals(Status.BURN) || status.equals(Status.POISON) || status.equals(Status.PARALYZE);
    }
    
    @Override
    protected String describe() {
        return "использует Facade" + add;
    }
}
