// Copyright 2017 Pierre Talbot (IRCAM)

// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at

//   http://www.apache.org/licenses/LICENSE-2.0

// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package upmc.pcg.game;

import upmc.pcg.game.Card.Card;
import upmc.pcg.game.Player.Player;
import upmc.pcg.ui.GameUI;

import java.util.ArrayList;
import java.util.List;

public class Game
{
    private Player currentPlayer;
    private final Player[] arrayPlayers = new Player[2];
    private ArrayList<Collection> allCollections = new ArrayList<>();
    private Collection currentCollection;

    public Game()
    {
    }

    /**
     * Initialize the game
     * it will first create the players and affect a new collection to it
     * @param playersName the players names get by the class LecturePseudo
     * */
    public void initialize(ArrayList<String> playersName)
    {
        ArrayList<String> playersName1 = playersName;

        for (int i = 0; i < arrayPlayers.length; i++)
        {
            arrayPlayers[i] = new Player(i + 1, playersName.get(i), new Collection(new ArrayList<>(), new ArrayList<>(), playersName.get(i)));
        }

        currentPlayer = arrayPlayers[0];
        System.out.println("The current player is set to : " + currentPlayer.toString());
        this.currentCollection = currentPlayer.getCollection();
    }


    /**
     * Launch the game, never ending.
     * true could be replaced with a boolean but it's more visual.
     * Show the menu -> wait for input
     */
    public void play()
    {
        while (true)
        {
            //choose player
            String choice = GameUI.showMenu();
            chooseOption(choice);
        }
    }


    /**
     * The main method
     * It will dispatch in function of the input
     * @param choice the user's input
     */
    private void chooseOption(String choice)
    {
        switch (choice)
        {
            /*ADDING*/
            case "1":
                int choiceType = GameUI.chooseTypeOfCard();
                System.out.flush();
                this.currentCollection.addingCard(choiceType, currentPlayer);
                break;
            /*REMOVE*/
            case "2":
                this.currentCollection.showCollec();
                int cardChosenToRemove = GameUI.chooseCardFromCollection(this.currentCollection);
                this.currentCollection.removeCardById(cardChosenToRemove);
                break;
            /*UPDATE*/
            case "3":
                this.currentCollection.showCollec();
                int cardChosenToUpdate = GameUI.chooseCardFromCollection(this.currentCollection);
                this.currentCollection.updateCardById(cardChosenToUpdate);
                break;
            /*SHOW*/
            case "4":
                this.currentCollection.showCollec();
                break;
            /*FIND*/
            case "5":
                String querySearch = GameUI.getQueryFindChoice();
                String queryType = GameUI.getQueryTypeChoice();
                List<Card> list = this.currentCollection.findCard(queryType, querySearch);
                for (Card card1 : list)
                    System.out.println(card1.toString());
                break;
            /*ADD TO DECK*/
            case "A":
            this.currentCollection.showCollec();
            this.currentCollection.createDeck();
            break;
            /*CHANGING PLAYER*/
            case "C":
                if (currentPlayer.getId() == 1)
                    currentPlayer = arrayPlayers[1];
                else
                    currentPlayer = arrayPlayers[0];
                currentCollection = currentPlayer.getCollection();
                break;
            /*SHOW DECK*/
            case "X":
                this.currentCollection.showDeck();
                break;

        }
    }


}
