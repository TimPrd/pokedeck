/*
 * Copyright (c)
 *        @author Timothé PARDIEU
 *                 ${PACKAGE_NAME}
 *                 Created on - ${DATE} (${TIME})
 *                 Build for project ${PROJECT_NAME}
 *
 */

package test.upmc.pcg.controller;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import upmc.pcg.controller.Pokedeck;
import upmc.pcg.game.actions.Act;
import upmc.pcg.game.actions.ActionsManager;
import upmc.pcg.game.card.Card;
import upmc.pcg.game.main.Game;
import upmc.pcg.game.player.Player;

import java.awt.*;
import java.lang.reflect.Method;
import java.util.ArrayList;

/**
 * Pokedeck Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>d?c. 9, 2017</pre>
 */
public class PokedeckTest {

    ArrayList<Player> players;
    Game game = new Game();

    @Before
    public void before() throws Exception {
        ArrayList<String> names = new ArrayList<>();
        names.add("X");
        names.add("Tim");

        game.initialize(names);
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: main(String[] args)
     */
    @Test
    public void testMain() throws Exception {
    }

    /**
     * Method: getModel()
     */
    @Test
    public void testGetModel() throws Exception {

    }

    /**
     * Method: chooseCard(Card pkmnAt)
     */
    @Test
    public void testChooseCard() throws Exception {
    }

    /**
     * Method: getMainPkmn()
     */
    @Test
    public void testGetMainPkmn() throws Exception {
        Pokedeck pokedeck = new Pokedeck();
        Card pkmn = pokedeck.getMainPkmn();
        assert pkmn.getClass().equals("PokemonCard");
    }


    /**
     * Method: fetchAction(String actionCommand, ArrayList<Object> al)
     */
    @Test
    public void testFetchAction() throws Exception {
        Method[] methods = ActionsManager.class.getMethods();
        for (Method m : methods) {
            if (m.isAnnotationPresent(Act.class)) {
                Act a = m.getAnnotation(Act.class);
                System.out.println(a.value());
            }
        }

    }


    /**
     * Method: findCard(String queryTypeChoice, String searchTerm)
     */
    @Test
    public void testFindCard() throws Exception {
    }

    /**
     * Method: switchPlayer()
     */
    @Test
    public void testSwitchPlayer() throws Exception {
        Player j1 = game.getCurrentPlayer();
        game.switchPlayer();
        Player j2 = game.getCurrentPlayer();

        assert j1 != j2;
    }

    /**
     * Method: changeColor()
     */
    @Test
    public void testChangeColor() throws Exception {
        Color a = game.getCurrentPlayer().getColor();
        System.out.println(a.toString());
        game.switchPlayer();
        Color b = game.getCurrentPlayer().getColor();
        System.out.println(b.toString());
        assert !a.equals(b);
    }

} 


