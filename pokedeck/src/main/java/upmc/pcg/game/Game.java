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
import upmc.pcg.game.Card.PokemonCard;
import upmc.pcg.game.Player.Player;
import upmc.pcg.ui.GameUI;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Game
{
    private ArrayList<Collection> allCollections = new ArrayList<>();
    private ArrayList<String> playersName;
    private Collection currentCollection;
    private String choice = "";

    Player currentPlayer;
    Player[] arrayPlayers = new Player[2];

    public Game()
    {
    }

    public void initialize(ArrayList<String> playersName)
    {
        this.playersName = playersName;


        for (int i = 0; i < arrayPlayers.length; i++)
        {
            arrayPlayers[i] = new Player(i+1, playersName.get(i), new Collection(new ArrayList<>(), new ArrayList<>(), playersName.get(i)));
        }

        currentPlayer = arrayPlayers[0];
        System.out.println("The current player is set to : " + currentPlayer);
        this.currentCollection = currentPlayer.getCollection();
    }

    public void play()
    {
        int collectionMenuChoice = -1;

        while (true)
        {
            //choose player
            choice = GameUI.showMenu();
            chooseOption(choice);
        }
    }


    private void chooseOption(String choice)
    {
        switch (choice)
        {
            case "1":
                int choiceType = GameUI.chooseTypeOfCard();
                System.out.flush();
                this.currentCollection.addingCard(choiceType,currentPlayer);
                break;

            case "2":
                this.currentCollection.showCollec();
                int cardChosenToRemove = GameUI.chooseCardFromCollection(this.currentCollection);
                this.currentCollection.removeCardById(cardChosenToRemove);
                break;

            case "3":
                this.currentCollection.showCollec();
                int cardChosenToUpdate = GameUI.chooseCardFromCollection(this.currentCollection);
                this.currentCollection.updateCardById(cardChosenToUpdate);
                break;


            case "4":
                this.currentCollection.showCollec();
                break;

            case "A":
                this.currentCollection.showCollec();
                this.currentCollection.createDeck();
                break;

            case "5":
                String querySearch = GameUI.getQueryFindChoice();
                String queryType = GameUI.getQueryTypeChoice();
                List<Card> list = this.currentCollection.findCard(queryType, querySearch);
                for (Card card1 : list)
                    System.out.println(card1.toString());
                break;

            case "C":
                if (currentPlayer.getId() == 1)
                    currentPlayer = arrayPlayers[1];
                else
                    currentPlayer = arrayPlayers[0];
                currentCollection = currentPlayer.getCollection();
                break;

            case "X":
                this.currentCollection.showDeck();
                break;

        }
    }




}
