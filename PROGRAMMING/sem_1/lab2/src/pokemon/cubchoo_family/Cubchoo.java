package src.pokemon.cubchoo_family;

import src.move.*;
import ru.ifmo.se.pokemon.*;
import java.util.ArrayList;
import java.util.List;

public class Cubchoo extends Pokemon {
    private List<Move> moves;

    public Cubchoo(String name, int level) {
        super(name, level);
        setType(Type.ICE);
        setStats(55, 70, 40, 60, 40, 40);

        this.moves = new ArrayList<>();

        moves.add(new IceBeam());
        moves.add(new FrostBreath());
        moves.add(new Facade());

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
