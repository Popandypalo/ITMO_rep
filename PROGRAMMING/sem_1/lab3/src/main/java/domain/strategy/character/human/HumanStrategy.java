package main.java.domain.strategy.character.human;

import main.java.domain.character.human.Human;
import main.java.domain.entity.kiosk.Kiosk;
import main.java.domain.strategy.ActionStrategy;

public interface HumanStrategy extends ActionStrategy<Human> {
    public abstract void setKiosk(Kiosk kiosk);
}