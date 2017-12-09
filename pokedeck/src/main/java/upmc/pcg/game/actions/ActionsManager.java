/*
 * Copyright (c)
 *        @author Timothé PARDIEU
 *                 ${PACKAGE_NAME}
 *                 Created on - ${DATE} (${TIME})
 *                 Build for project ${PROJECT_NAME}
 *
 */

package upmc.pcg.game.actions;

import upmc.pcg.game.card.Card;
import upmc.pcg.game.card.PokemonCard;
import upmc.pcg.game.main.Game;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * This class group all the possible actions
 * To be more dynamic, the actions are related by annotations which describe what method need to be used
 */
public class ActionsManager {

    private Game game;

    public ActionsManager(Game game) {
        this.game = game;
    }

    /**
     * Create a card with the desired data (in al) + save the collections
     * @param al the data listed
     */
    @Act("AddPkmn")
    public void createCard(ArrayList al) {

        Card card = new PokemonCard(null, 0, null, null, 0);
        card = card.createCard(al, game.getCurrentPlayer());
        game.getCurrentPlayer().getCollection().addToCollec(card);
        try {
            //@Pierre T.
            saveCollection();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Adding a card from the main collection to the deck
     *
     * @param al which card to choose
     */
    @Act("AddToDeck")
    public void addToDeck(ArrayList<Integer> al) {
        int index = al.get(0);
        Card card = game.getCurrentPlayer().getCollection().getAlCardsCollection().get(index);
        game.getCurrentPlayer().getCollection().getAlCardsDeck().add(card);
        try {
            //@Pierre T.
            saveCollection();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Deleting a card from collection (and from deck indirectly)
     *
     * @param al the card to be removed
     */
    @Act("DeletePkmn")
    public void deletePkmn(ArrayList<Integer> al) {
        int index = al.get(0);
        Card card = this.game.getCurrentPlayer().getCollection().getAlCardsCollection().get(index);
        this.game.getCurrentPlayer().getCollection().getAlCardsCollection().remove(card);

        //if we have put the card also in the deck we remove it
        if (this.game.getCurrentPlayer().getCollection().getAlCardsDeck().contains(card))
            this.game.getCurrentPlayer().getCollection().getAlCardsDeck().remove(game.getCurrentPlayer().getCollection().getAlCardsDeck().indexOf(card));
        try {
            //@Pierre T.
            deleteCollection();
            saveCollection();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Deleting the JSON
     */
    //@Pierre T.
    private void deleteCollection() {
        File collec = new File(game.getCurrentPlayer() + "_collection" + ".json");
        if (collec.exists() && !collec.isDirectory()) {
            try {
                Files.delete(collec.toPath());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        File deck = new File(game.getCurrentPlayer() + "_deck" + ".json");
        if (deck.exists() && !deck.isDirectory()) {
            try {
                Files.delete(deck.toPath());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * Find a card with determined params
     * Using java 8 stream | lambda
     *
     * @param queryTypeChoice the type of search
     * @param searchTerm      the query ("Pikachu","Rondoudou")..
     * @return the listed cards which are matching the query
     */
    public List<Card> findCard(String queryTypeChoice, String searchTerm) {
        String search = searchTerm.replace("é", "e");
        List<Card> cards = null;
        ArrayList<Card> alCardsCollection = this.game.getCurrentPlayer().getCollection().getAlCardsCollection();
        switch (queryTypeChoice) {
            case "NAME":
                cards = alCardsCollection
                        .stream()
                        .filter(p -> p.getCardTitle().toLowerCase().contains((search.toLowerCase())))
                        .collect(Collectors.toList());
                System.out.println(alCardsCollection.stream().filter(p -> p.getCardTitle().toLowerCase().contains(search.toLowerCase())).count() + " results !");

                break;
            case "TYPE":
                cards = alCardsCollection
                        .stream()
                        .filter(p -> p.getTypeCard().toLowerCase().contains(search.toLowerCase()))
                        .collect(Collectors.toList());
                System.out.println(alCardsCollection.stream().filter(p -> p.getTypeCard().toLowerCase().contains(search.toLowerCase())).count() + " results !");
                break;
        }
        return cards;
    }

    /**
     * Create the JSON
     *
     * @throws IOException
     */
    private void saveCollection() throws IOException {
        game.getCurrentPlayer().getCollection().saveCollec(game.getCurrentPlayer().getName());
    }


}
