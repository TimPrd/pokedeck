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

//   http://www.apache.org/licenses/LICENSE-2.0

// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package upmc.pcg.game.main;

import upmc.pcg.controller.Pokedeck;
import upmc.pcg.game.actions.ActionsManager;
import upmc.pcg.game.card.PokemonCard;
import upmc.pcg.game.player.Player;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class Game implements Observer {
    private final Player[] arrayPlayers = new Player[2];
    private Player currentPlayer;
    private ArrayList<Collection> allCollections = new ArrayList<>();
    private Collection currentCollection;
    private Pokedeck controller;
    private int lap = 1;


    private PokemonCard ia = null;
    private ActionsManager actionManager;

    public Game() {
        actionManager = new ActionsManager(this);

    }

    /**
     * Initialize the game
     * it will first create the players and affect a new collection to it
     *
     * @param playersName the players names get by the class LecturePseudo
     */
    public void initialize(ArrayList<String> playersName) {
        Color[] colors = new Color[]{Color.green, Color.RED, Color.blue};
        for (int i = 0; i < arrayPlayers.length; i++) {
            arrayPlayers[i] = new Player(i + 1, playersName.get(i), new Collection(new ArrayList<>(), new ArrayList<>(), playersName.get(i)), colors[i]);
        }

        currentPlayer = arrayPlayers[0];
        System.out.println("The current player is set to : " + currentPlayer.getName());
        this.currentCollection = currentPlayer.getCollection();

        loadGame();
    }

    private void loadGame() {
        for (Player p : arrayPlayers) {


            if (new File(p.getName() + "_collection.json").exists()) {
                try {
                    p.getCollection().loadCollec(p.getName());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (new File(p.getName() + "_deck.json").exists()) {
                try {
                    p.getCollection().loadDeck(p.getName());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    /**
     * Launch a battle if the pokemons have HP
     * Damages are affected (HP minus damages)
     * If the ia is K.O. get a new random
     * Erasing the card from deck when a pokemon is dead
     * @param j1Pkmn the currentplayer's pokemon
     * @param attack the attack chosen (clicked)
     */
    public void play(PokemonCard j1Pkmn, Integer attack) throws InterruptedException {
        singletonEnemy();
        int attackEnnemy = ia.getRandomAttack();

        if (j1Pkmn.getHP() >= 0 && ia.getHP() >= 0 && stillCard(currentPlayer) && stillCard(arrayPlayers[1])) {
            this.controller.printMessage("Tour N° " + String.valueOf(lap));

            ia.setHP(ia.getHP() - attack);
            j1Pkmn.setHP(j1Pkmn.getHP() - attackEnnemy);

            this.controller.setHP(j1Pkmn.getHP());
            this.controller.setEnemyHP(ia.getHP());


            if (ia.getHP() <= 0) {
                this.arrayPlayers[1].getCollection().getAlCardsDeck().remove(ia);

                //If there is still cards in the other deck, we take a new random card + attack
                if (stillCard(arrayPlayers[1])) {
                    ia = (PokemonCard) this.arrayPlayers[1].getCollection().getRandomCard();
                    attackEnnemy = ia.getRandomAttack();
                }
            }

            if (j1Pkmn.getHP() <= 0) {
                this.arrayPlayers[0].getCollection().getAlCardsDeck().remove(j1Pkmn);
                if (stillCard(currentPlayer))
                    this.controller.removeFromView();

                //this.controller.removeFromTable();
            }


            if (!stillCard(arrayPlayers[1])) {
                this.controller.printMessage(this.currentPlayer.getName() + " a gagné !!");

            }

            if (!stillCard(this.currentPlayer)) {
                this.controller.printMessage(arrayPlayers[1].getName() + " a gagné !!");
            }

            lap++;


        }
    }

    /**
     * Check the emptiness of the deck
     *
     * @param player the deck's player
     * @return true if it's equal or greater than 1
     */
    private boolean stillCard(Player player) {
        return player.getCollection().getAlCardsDeck().size() >= 1;
    }

    /**
     * Singleton in order to get if a card is already set or not
     */
    private void singletonEnemy() {
        if (ia == null) {
            ia = (PokemonCard) this.arrayPlayers[1].getCollection().getRandomCard();
            try {
                this.controller.addToViewEnnemy(ia);
                Thread.sleep(200);
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    public Player getCurrentPlayer() {
        return currentPlayer;
    }


    /**
     * Start of the application, describing names
     *
     * @param controller
     */
    public void start(Pokedeck controller) {
        ArrayList<String> names = new ArrayList<>();
        names.add("Tim");
        names.add("Alfred");

        this.controller = controller;
        this.initialize(names);

    }

    /**
     * Switch current player data
     */
    public void switchPlayer() {
        if (currentPlayer.getId() == 1)
            currentPlayer = arrayPlayers[1];
        else
            currentPlayer = arrayPlayers[0];
        currentCollection = currentPlayer.getCollection();
    }


    public ActionsManager manageAction() {
        return actionManager;
    }

    //A better pattern would have been observable/observer
    @Override
    public void update(Observable o, Object arg) {
        System.out.println(arg);
    }
}
