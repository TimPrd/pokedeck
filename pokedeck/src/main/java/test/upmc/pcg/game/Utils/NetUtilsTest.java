/*
 * Copyright (c)
 *        @author Timothé PARDIEU
 *                 ${PACKAGE_NAME}
 *                 Created on - ${DATE} (${TIME})
 *                 Build for project ${PROJECT_NAME}
 *
 */

package test.upmc.pcg.game.Utils;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import upmc.pcg.game.Utils.NetUtils;

/**
 * NetUtils Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>d?c. 8, 2017</pre>
 */
public class NetUtilsTest {

    @Before
    public void before() throws Exception {
        //SETTING UP THE CONNETION
        //SET WIFI (OFF)
    }

    @After
    public void after() throws Exception {

    }

    /**
     * Method: netIsAvailable()
     */
    @Test
    public void testNetIsAvailableInternetOn() throws Exception {
        boolean b = NetUtils.netIsAvailable();
        assert b;
    }

    @Test
    public void testNetIsAvailableInternetOff() throws Exception {
        boolean b = NetUtils.netIsAvailable();
        assert b;
    }

} 
