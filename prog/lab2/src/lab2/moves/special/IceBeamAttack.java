package lab2.moves.special;

import lab2.MyPokemon;
import ru.ifmo.se.pokemon.*;

public class IceBeamAttack extends SpecialMove {

    public IceBeamAttack() {
        super(Type.ICE, 90, 100);
    }

    @Override
    protected void applyOppEffects(Pokemon p) {
        super.applyOppEffects(p);

        if (MyPokemon.chance(10)) {
            Effect.freeze(p);
        }
    }

    @Override
    protected String describe() {
        return "выпускает мощный ледяной луч Ice Beam!";
    }
}