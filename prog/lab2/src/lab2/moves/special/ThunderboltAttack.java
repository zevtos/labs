package lab2.moves.special;

import lab2.MyPokemon;
import ru.ifmo.se.pokemon.*;

public class ThunderboltAttack extends SpecialMove {

    public ThunderboltAttack() {
        super(Type.ELECTRIC, 90, 100);
    }

    @Override
    protected void applyOppEffects(Pokemon p) {
        super.applyOppEffects(p);
        boolean targetImmune = p.hasType(Type.ELECTRIC);
        if (!targetImmune && MyPokemon.chance(10)) {
            Effect.paralyze(p);
        }
    }

    @Override
    protected String describe() {
        return "использует Thunderbolt!";
    }
}