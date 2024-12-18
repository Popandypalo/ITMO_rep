package src.pokemon.poliwag_family;

import src.move.*;
import ru.ifmo.se.pokemon.*;
import java.util.ArrayList;
import java.util.List;

public class Poliwhirl extends Poliwag {
    private List<Move> moves;

    public Poliwhirl(String name, int level) {
        super(name, level);
        setType(Type.WATER);
        setStats(65, 65, 65, 50, 50, 90);
        
        this.moves = new ArrayList<>();
        moves.add(new Waterfall());
        moves.add(new Rest());
        moves.add(new BodySlam());

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
