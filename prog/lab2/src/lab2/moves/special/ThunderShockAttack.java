package lab2.moves.special;

import lab2.MyPokemon;
import ru.ifmo.se.pokemon.*;

public class ThunderShockAttack extends SpecialMove {

    public ThunderShockAttack() {
        super(Type.ELECTRIC, 40, 100);
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
        return "запускает маленькую электрическую атаку Thunder Shock!";
    }
}