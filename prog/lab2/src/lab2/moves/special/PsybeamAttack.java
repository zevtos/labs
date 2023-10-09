package lab2.moves.special;

import ru.ifmo.se.pokemon.*;
import lab2.MyPokemon;

public class PsybeamAttack extends SpecialMove {

    public PsybeamAttack() {
        super(Type.PSYCHIC, 65, 100);
    }

    @Override
    protected void applyOppEffects(Pokemon p) {
        super.applyOppEffects(p);

        if (MyPokemon.chance(10)) {
            Effect.confuse(p);
        }

    }

    @Override
    protected String describe() {
        return "выпускает мощный психический луч и атакует врагов Psybeam!";
    }
}