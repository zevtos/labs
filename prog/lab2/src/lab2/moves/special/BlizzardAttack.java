package lab2.moves.special;

import lab2.MyPokemon;
import ru.ifmo.se.pokemon.*;

public class BlizzardAttack extends SpecialMove {
    public BlizzardAttack() {
        super(Type.ICE, 110, 70);
    }


    @Override
    protected void applyOppEffects(Pokemon p) {
        boolean targetImmune = p.hasType(Type.ICE);

        if (!targetImmune && MyPokemon.chance(10))
            Effect.freeze(p);
    }


    @Override
    protected String describe() {
        return "использует Blizzard!";
    }
}