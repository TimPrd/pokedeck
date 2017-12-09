/*
 * Copyright (c)
 *        @author Timothé PARDIEU
 *                 ${PACKAGE_NAME}
 *                 Created on - ${DATE} (${TIME})
 *                 Build for project ${PROJECT_NAME}
 *
 */

// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at

//     http://www.apache.org/licenses/LICENSE-2.0

// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package upmc.pcg.controller;

import upmc.pcg.game.Utils.NetUtils;
import upmc.pcg.game.actions.Act;
import upmc.pcg.game.actions.ActionsManager;
import upmc.pcg.game.card.Card;
import upmc.pcg.game.card.PokemonCard;
import upmc.pcg.game.main.Collection;
import upmc.pcg.game.main.Game;
import upmc.pcg.ui.ihm.frames.Window;

import java.awt.*;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * Controller
 * It's a link between the view and the model.
 * It doesn't know anything about view stuff, just passing data thought
 */
public class Pokedeck {

    private static Window view;
    private static Game model;
    private static Collection modelCollec;
    private PokemonCard mainPkmn = null;
    private Integer attack = null;
    private boolean isCardSet = false;

    public Pokedeck() {
        super();
    }

    public static void main(String[] args) {
        //older version (cli version)
        //GameUI game_ui = new GameUI();
        //game_ui.start();
        Pokedeck contoller = new Pokedeck();

        view = new Window(contoller);
        model = new Game();

        //verify if we got a internet connection
        if (!NetUtils.netIsAvailable())
            view.alertNetwork();

        contoller.startUI();
    }

    /**
     * Init the view by inflating the names/their colors
     */
    private void startUI() {
        view.start(this);
    }

    /**
     * @return the model (Game)
     */
    public Game getModel() {
        return model;
    }


    /**
     * Setting up the card to be used in the battle and passing the info to the view
     *
     * @param pkmnAt the pokemon chosen
     * @throws IOException
     */
    public void chooseCard(Card pkmnAt) throws IOException {
        this.mainPkmn = (PokemonCard) pkmnAt;
        this.isCardSet = true;
        String name = mainPkmn.getCardTitle();
        String hp = String.valueOf(mainPkmn.getHP());
        String cardTitle = mainPkmn.getCardTitle().toLowerCase();
        String energyType = mainPkmn.getEnergy().getImg();
        int attacks = mainPkmn.nbAttackPossible();
        view.pushCardUI(name, hp, cardTitle, energyType, attacks);
    }

    /**
     * @return the pokemon of the current player that have been chosen
     */
    public PokemonCard getMainPkmn() {
        return mainPkmn;
    }

    /**
     * @return the pokemon's attack that have been chosen
     */
    public Integer getAttack() {
        return attack;
    }

    /**
     * Set the attack
     *
     * @param attack the attack chosen
     */
    public void setAttack(int attack) {
        this.attack = attack;
    }

    /**
     * trigger the battle
     *
     * @throws InterruptedException
     */
    public void play() throws InterruptedException {
        model.play(this.mainPkmn, this.attack);
    }

    /**
     * Initialization of the players' names
     */
    public void initNames() {
        model.start(this);
    }

    /**
     * @return the colors of the players
     */
    public Color getMainColor() {
        System.out.println(model.getCurrentPlayer());
        return model.getCurrentPlayer().getColor();
    }

    /**
     * Update the HP of the card
     *
     * @param hp health point to asign
     */
    public void setHP(int hp) {
        view.setHP(hp);
    }


    public void setCard(boolean card) {
        this.isCardSet = card;
    }

    public void removeFromView() {
        view.rem();
    }

    public boolean isCardSet() {
        return isCardSet;
    }

    /**
     * Passing to the view the generated enemy
     *
     * @param ia the pokemon card of the ia
     * @throws IOException
     */
    public void addToViewEnnemy(PokemonCard ia) throws IOException {
        String name = ia.getCardTitle();
        String hp = String.valueOf(ia.getHP());
        String cardTitle = ia.getCardTitle().toLowerCase();
        String energyType = ia.getEnergy().getImg();
        int attacks = ia.nbAttackPossible();
        view.addEnemy(name, hp, cardTitle, energyType, attacks);
    }

    /**
     * This method deals with annotation
     * It will fetch all the annotation in the action manager (aka all the actions available)
     * if it matches with a annotation it will invoke the related method
     *
     * @param actionCommand the annotation to search for
     * @param al            the possible data
     */
    public void fetchAction(String actionCommand, ArrayList<Object> al) {
        Method[] methods = ActionsManager.class.getMethods();
        for (Method m : methods) {
            if (m.isAnnotationPresent(Act.class)) {
                Act a = m.getAnnotation(Act.class);
                if (a.value().equals(actionCommand))
                    try {
                        ActionsManager am = model.manageAction();
                        m.invoke(am, al);
                        update();
                    } catch (IllegalAccessException | InvocationTargetException e1) {
                        e1.printStackTrace();
                    }

            }
        }

    }


    public ArrayList<Card> collec() {
        return model.getCurrentPlayer().getCollection().getAlCardsCollection();

    }

    /**
     * Find a card is trigger, find the desired card
     *
     * @param queryTypeChoice the type (name/card type)
     * @param searchTerm      the input ex : "pikachu", "rondoudou"..
     * @return the result in a list form using java 8 stream
     */
    public List<Card> findCard(String queryTypeChoice, String searchTerm) {
        return model.manageAction().findCard(queryTypeChoice, searchTerm); //pika (JtextField) | name (button)
    }

    /**
     * Just change the current player to the other one
     */
    public void switchPlayer() {
        model.switchPlayer();
        view.setPlayerColor(this.getMainColor());
    }


    /**
     * Updating all the frames which are actives
     */
    private void update() {
        Frame[] frames = Frame.getFrames();
        for (Frame f : frames) {
            f.repaint();
            f.revalidate();
        }
    }

    /**
     * Setting up the enemy HP
     *
     * @param enemyHP the new hp
     */
    public void setEnemyHP(int enemyHP) {
        view.updateEnemy(enemyHP);
    }

    public void changeColor() {
        view.changeColor();
    }

    /**
     * Show the winner
     *
     * @param messages the message
     */
    public void printMessage(String messages) {
        view.showMessage(messages);

    }
}
