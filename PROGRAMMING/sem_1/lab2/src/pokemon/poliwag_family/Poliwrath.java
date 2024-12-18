package src.pokemon.poliwag_family;

import src.move.*;
import ru.ifmo.se.pokemon.*;
import java.util.ArrayList;
import java.util.List;

public class Poliwrath extends Poliwhirl {
    private List<Move> moves;

    public Poliwrath(String name, int level) {
        super(name, level);
        setType(Type.WATER, Type.FIGHTING);
        setStats(90, 95, 95, 70, 90, 70);
        
        this.moves = new ArrayList<>();
        moves.add(new Waterfall());
        moves.add(new Rest());
        moves.add(new BodySlam());
        moves.add(new DynamicPunch());

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
