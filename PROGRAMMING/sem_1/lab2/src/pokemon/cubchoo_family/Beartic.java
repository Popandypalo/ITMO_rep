package src.pokemon.cubchoo_family;

import src.move.*;
import ru.ifmo.se.pokemon.*;
import java.util.ArrayList;
import java.util.List;

public class Beartic extends Cubchoo {

    private List<Move> moves; // Поле для хранения списка атак

    public Beartic(String name, int level) {
        super(name, level);
        setType(Type.ICE);
        setStats(95, 130, 80, 70, 80, 50); 

        this.moves = new ArrayList<>();

        moves.add(new IceBeam());
        moves.add(new FrostBreath());
        moves.add(new Facade());
        moves.add(new Bulldoze());

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
