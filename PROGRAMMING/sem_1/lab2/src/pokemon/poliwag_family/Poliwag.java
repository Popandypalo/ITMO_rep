package src.pokemon.poliwag_family;

import src.move.*;
import ru.ifmo.se.pokemon.*;
import java.util.ArrayList;
import java.util.List;

public class Poliwag extends Pokemon {
    private List<Move> moves;

    public Poliwag(String name, int level) {
        super(name, level);
        setType(Type.WATER);
        setStats(40, 50, 40, 40, 40, 90);
        
        this.moves = new ArrayList<>();
        moves.add(new Waterfall());
        moves.add(new Rest());

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
