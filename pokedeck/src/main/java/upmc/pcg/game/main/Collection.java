/*
 * Copyright (c)
 *        @author Timothé PARDIEU
 *                 ${PACKAGE_NAME}
 *                 Created on - ${DATE} (${TIME})
 *                 Build for project ${PROJECT_NAME}
 *
 */

package upmc.pcg.game.main;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import upmc.pcg.game.actions.Act;
import upmc.pcg.game.card.Card;
import upmc.pcg.game.card.EnergyCard;
import upmc.pcg.game.card.PokemonCard;
import upmc.pcg.game.card.TrainerCard;
import upmc.pcg.game.player.Player;
import upmc.pcg.ui.cli.GameUI;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * Created by Timothé PARDIEU & Valentin HERVOUET.
 */
public class Collection implements Serializable {
    private final ArrayList<Card> alCardsCollection;
    private ArrayList<Card> alCardsDeck;


    /**
     * Constructor to create a collection
     * A collection is a sort of deck but unlimited.
     * A deck is a hand of cards but limited to 60 (no more, no less!)
     * There is only one collection per player
     *
     * @param alCardsCollection the collection (unlimited card)
     * @param alCardsDeck       the deck (limited to 60)
     * @param player            the player aka the owner
     */
    public Collection(ArrayList<Card> alCardsCollection, ArrayList<Card> alCardsDeck, String player) {
        this.alCardsCollection = alCardsCollection;
        this.alCardsDeck = alCardsDeck;
        String player1 = player;
    }

    /**
     * Enables to put a card in the player's collection
     *
     * @param card the card to add
     */
    public void addToCollec(Card card) {
        this.alCardsCollection.add(card);
    }

    /**
     * Show all the collection
     */
    public void showCollec() {
        for (Card card : alCardsCollection) {
            System.out.println(card.toString());
        }
    }

    /**
     * Retrieve a card by its id
     *
     * @param idCard id of the card to find
     * @return the card
     */
    private Card getCardByID(int idCard) {
        for (Card card : alCardsCollection)
            if (card.getID() == idCard)
                return card;
        return null;
    }

    /**
     * Remove a card by its id
     *
     * @param idCard id of the card to remove
     */
    public void removeCardById(int idCard) {
        Card card = this.getCardByID(idCard);
        this.alCardsCollection.remove(this.alCardsCollection.indexOf(card));
        //if we have put the card also in the deck we remove it
        if (alCardsDeck.contains(card))
            this.alCardsDeck.remove(this.alCardsDeck.indexOf(card));
    }

    /**
     * Update the card by its id
     * @param idCard the id of the card to update
     */
    //#Design to be seen by Pierre T.
    //@Pierre T.
    public void updateCardById(int idCard) {
        Card cardToUpdate = this.getCardByID(idCard);
        ArrayList alUpdate = new ArrayList();
        //compare witch type of card we want to update
        if (cardToUpdate.getClass().getSimpleName().equals("PokemonCard")) alUpdate = GameUI.inputAttributesPkmn();
        if (cardToUpdate.getClass().getSimpleName().equals("EnergyCard")) alUpdate = GameUI.inputAttributesEner();
        if (cardToUpdate.getClass().getSimpleName().equals("TrainerCard")) alUpdate = GameUI.inputAttributesTrain();
        cardToUpdate.updateCardFromCollec(alUpdate);
    }

    /**
     * @return the complete collection
     */
    public ArrayList<Card> getAlCardsCollection() {
        return alCardsCollection;
    }

    /**
     * Add to the deck a card from the collection
     *
     * @param idCard the id of the card to add
     */
    private void addToDeck(int idCard) {
        Card card = this.getCardByID(idCard);
        if (alCardsDeck.size() < 60)
            this.alCardsDeck.add(card);
    }

    /**
     * Show all the deck
     */
    public void showDeck() {
        for (Card anAlCardsDeck : alCardsDeck) {
            System.out.println(anAlCardsDeck.toString());
        }
    }

    /**
     * Find a card or cards by a specific criteria
     *
     * @param queryTypeChoice the type to search for
     * @param searchTerm      the term to search
     * @return a list with all the cards found
     */
    public List<Card> findCard(String queryTypeChoice, String searchTerm) {

        String search = searchTerm.replace("é", "e");
        List<Card> cards = null;

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
     * Adding in the deck the cards.
     */
    public void createDeck() {
        //get all the cards in format "number-number-number"
        String cardChosenToAddInDeck = GameUI.chooseCardsFromCollection();

        Scanner scan = new Scanner(cardChosenToAddInDeck).useDelimiter("-");

        // Printing the tokenized Strings
        while (scan.hasNext() && this.alCardsDeck.size() <= 60) {
            this.addToDeck(scan.nextInt()); //add it to the deck of the player
        }
        scan.close();

    }

    /**
     * Add cards to collec
     *
     * @param choiceType    to redirect to the right data to ask for
     * @param currentPlayer the player who asked it
     */

    @Act("ADD")
    public void addingCard(int choiceType, Player currentPlayer) {
        Card card = createByChoiceCard(choiceType);
        ArrayList<Object> al = new ArrayList<>();
        if (choiceType == 1) al = GameUI.inputAttributesPkmn();
        if (choiceType == 2) al = GameUI.inputAttributesEner();
        if (choiceType == 3) al = GameUI.inputAttributesTrain();

        if (card != null) {
            card = card.createCard(al, currentPlayer);
        }
        this.addToCollec(card);
    }


    /**
     * Create a new card by the user's choicce
     *
     * @param choiceType input of the user
     * @return the card with the right type
     */
    private Card createByChoiceCard(int choiceType) {
        if (choiceType == 1) return new PokemonCard(null, 0, null, null, 0);
        if (choiceType == 2) return new EnergyCard(null, 0, null);
        if (choiceType == 3) return new TrainerCard(null, 0, null, null);
        return null;
    }

    /**
     * Save the collections using JSON
     *
     * @param name the name of the file to named it
     * @throws IOException
     */
    //@Pierre T.
    public void saveCollec(String name) throws IOException {
        toJSOn(name, "_collection", this.alCardsCollection);
        toJSOn(name, "_deck", this.alCardsDeck);
    }

    /**
     * Convert from object to json
     *
     * @param name    the name of the file
     * @param collec  the collection's name to apply
     * @param alCards the collection itself
     * @throws IOException
     */
    //@Pierre T.
    private void toJSOn(String name, String collec, ArrayList<Card> alCards) throws IOException {
        FileWriter fileWriter = new FileWriter(name + collec + ".json");
        Gson objGson = new GsonBuilder().setPrettyPrinting().create();
        String json = objGson.toJson(alCards);
        //System.out.println(json);
        fileWriter.write(json);
        fileWriter.close();
    }


    /**
     * Load the desired collection
     *
     * @param name the name of the file
     * @throws IOException
     */
    //@Pierre T.
    public void loadCollec(String name) throws IOException {
        Gson gson = new Gson();

        PokemonCard[] cards = gson.fromJson(new FileReader(name + "_collection.json"), PokemonCard[].class);
        this.alCardsCollection.removeAll(alCardsCollection);
        this.alCardsCollection.addAll(Arrays.asList(cards));
    }

    /**
     * Load the deck
     *
     * @param name the name of the deck
     * @throws IOException
     */
    //@Pierre T.
    void loadDeck(String name) throws IOException {
        Gson gson = new Gson();

        PokemonCard[] cards = gson.fromJson(new FileReader(name + "_deck.json"), PokemonCard[].class);
        this.alCardsDeck.removeAll(alCardsDeck);
        this.alCardsDeck.addAll(Arrays.asList(cards));

    }

    public ArrayList<Card> getAlCardsDeck() {
        return alCardsDeck;
    }

    /**
     * Fetch from the deck of another player a card, randomly
     *
     * @return the card randomly chosen
     */
    public Card getRandomCard() {
        int nbCards = this.alCardsDeck.size();
        int randomCardIndex = (int) (Math.random() * nbCards);
        //In case we want the name of the attack
        //this.attacks.keySet().toArray()[randomAttack] +"-"+ ;
        return this.alCardsDeck.get(randomCardIndex);
    }
}
