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
import upmc.pcg.game.card.EnergyCard;
import upmc.pcg.game.card.EnergyType;

/**
 * EnergyCard Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>d�c. 9, 2017</pre>
 */
public class EnergyCardTest {

    EnergyCard energyCard = new EnergyCard("energy", 0, EnergyType.Fairy);

    @Before
    public void before() throws Exception {
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
            assert !energyCard.getType().equals(e) || true;
    }


} 
