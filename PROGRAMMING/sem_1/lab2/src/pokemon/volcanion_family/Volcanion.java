package src.pokemon.volcanion_family;

import src.move.*;
import ru.ifmo.se.pokemon.*;
import java.util.ArrayList;
import java.util.List;

public class Volcanion extends Pokemon {
    private List<Move> moves;

    public Volcanion(String name, int level) {
        super(name, level);
        setType(Type.FIRE, Type.WATER);
        setStats(80, 110, 120, 130, 90, 70);
        
        this.moves = new ArrayList<>();
        moves.add(new HydroPump());
        moves.add(new Scald());
        moves.add(new FlameCharge());
        moves.add(new FireBlast());

        for (Move move : moves) {
            super.setMove(move);
        }
    }

    public boolean hasMove(Move move) {
        return moves.contains(move);
    }

    public List<Move> getMoves() {
        return new ArrayList<>(moves);
    }
}
