package src.battle;

import ru.ifmo.se.pokemon.*;
import src.pokemon.cubchoo_family.*;
import src.pokemon.poliwag_family.*;
import src.pokemon.volcanion_family.*;


public class PokemonBattleSimulator {
    public static void battle() {
        Battle battle = new Battle();

        Pokemon ally1 = new Volcanion("Вулканчик", 1);
        Pokemon ally2 = new Beartic("Косолапыч", 1);
        Pokemon ally3 = new Poliwrath("Медвежонок", 1);

        Pokemon foe1 = new Cubchoo("Терпилыч", 1);
        Pokemon foe2 = new Poliwhirl("Боксер", 1);
        Pokemon foe3 = new Poliwag("Михалыч", 1);

        battle.addAlly(ally2);
        battle.addAlly(ally1);
        battle.addAlly(ally3);

        battle.addFoe(foe1);
        battle.addFoe(foe2);
        battle.addFoe(foe3);

        battle.go();
    }
}

