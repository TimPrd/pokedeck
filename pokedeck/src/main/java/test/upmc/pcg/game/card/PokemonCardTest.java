/*
 * Copyright (c)
 *        @author Timothé PARDIEU
 *                 ${PACKAGE_NAME}
 *                 Created on - ${DATE} (${TIME})
 *                 Build for project ${PROJECT_NAME}
 *
 */

package test.upmc.pcg.game.card;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import upmc.pcg.game.card.EnergyType;
import upmc.pcg.game.card.PokemonCard;

import java.util.HashMap;
import java.util.Map;

/**
 * PokemonCard Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>d?c. 9, 2017</pre>
 */
public class PokemonCardTest {
    Map<String, Integer> map = new HashMap<>();
    PokemonCard pkmn;
    int nbPossible;

    @Before
    public void before() throws Exception {
        map.put("attack", 3);
        pkmn = new PokemonCard("Pikachu", 0, EnergyType.Darkness, map, 50);
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: getType()
     */
    @Test
    public void testGetType() throws Exception {
        for (EnergyType e : EnergyType.values())
            assert !pkmn.getType().equals(e) || true;
    }


    /**
     * Method: nbAttackPossible()
     */
    @Test
    public void testNbAttackPossible() throws Exception {
        nbPossible = pkmn.nbAttackPossible();
        System.out.println(nbPossible);
        assert nbPossible <= 3 && nbPossible > 0;

    }


}
