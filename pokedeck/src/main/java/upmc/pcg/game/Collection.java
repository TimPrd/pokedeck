package upmc.pcg.game;

import upmc.pcg.game.Card.Card;
import upmc.pcg.game.Card.EnergyCard;
import upmc.pcg.game.Card.PokemonCard;
import upmc.pcg.game.Card.TrainerCard;
import upmc.pcg.game.Player.Player;
import upmc.pcg.ui.GameUI;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * Created by Timothé PARDIEU & Valentin HERVOUET.
 */
public class Collection
{
    private final ArrayList<Card> alCardsCollection;
    private final ArrayList<Card> alCardsDeck;


    /**
     * Constructor to create a collection
     * A collection is a sort of deck but unlimited.
     * A deck is a hand of cards but limited to 60 (no more, no less!)
     * There is only one collection per player
     * @param alCardsCollection the collection (unlimited card)
     * @param alCardsDeck the deck (limited to 60)
     * @param player the player aka the owner
     */
    public Collection(ArrayList<Card> alCardsCollection, ArrayList<Card> alCardsDeck, String player)
    {
        this.alCardsCollection = alCardsCollection;
        this.alCardsDeck = alCardsDeck;
        String player1 = player;
    }

    /**
     * Enables to put a card in the player's collection
     * @param card the card to add
     */
    private void addToCollec(Card card)
    {
        this.alCardsCollection.add(card);
    }

    /**
     * Show all the collection
     */
    public void showCollec()
    {
        for (Card card : alCardsCollection)
        {
            System.out.println(card.toString());
        }
    }

    /**
     * Retrieve a card by its id
     * @param idCard id of the card to find
     * @return the card
     */
    private Card getCardByID(int idCard)
    {
        for (Card card : alCardsCollection)
            if (card.getID() == idCard)
                return card;
        return null;
    }

    /**
     * Remove a card by its id
     * @param idCard id of the card to remove
     */
    public void removeCardById(int idCard)
    {
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
    public void updateCardById(int idCard)
    {
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
    public ArrayList<Card> getAlCardsCollection()
    {
        return alCardsCollection;
    }

    /**
     * Add to the deck a card from the collection
     * @param idCard the id of the card to add
     */
    private void addToDeck(int idCard)
    {
        Card card = this.getCardByID(idCard);
        this.alCardsDeck.add(card);
    }

    /**
     * Show all the deck
     */
    public void showDeck()
    {
        for (int i = 0; i < alCardsDeck.size(); i++)
        {
            System.out.println(alCardsDeck.get(i).toString());
        }
    }

    /**
     * Find a card or cards by a specific criteria
     * @param queryTypeChoice the type to search for
     * @param searchTerm the term to search
     * @return a list with all the cards found
     */
    public List<Card> findCard(String queryTypeChoice, String searchTerm)
    {

        String search = searchTerm.replace("é", "e");
        List<Card> cards = null;

        switch (queryTypeChoice)
        {
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
    public void createDeck()
    {
        //get all the cards in format "number-number-number"
        String cardChosenToAddInDeck = GameUI.chooseCardsFromCollection();

        Scanner scan = new Scanner(cardChosenToAddInDeck).useDelimiter("-");

        // Printing the tokenized Strings
        while (scan.hasNext() && this.alCardsDeck.size() <= 60)
        {
            this.addToDeck(scan.nextInt()); //add it to the deck of the player
        }
        scan.close();

    }

    /**
     * Add cards to collec
     * @param choiceType to redirect to the right data to ask for
     * @param currentPlayer the player who asked it
     */
    public void addingCard(int choiceType, Player currentPlayer)
    {
        Card card = createByChoiceCard(choiceType);
        ArrayList<Object> al = new ArrayList<>();
        if (choiceType == 1) al = GameUI.inputAttributesPkmn();
        if (choiceType == 2) al = GameUI.inputAttributesEner();
        if (choiceType == 3) al = GameUI.inputAttributesTrain();

        if (card != null)
        {
            card = card.createCard(al, currentPlayer);
        }
        this.addToCollec(card);
    }


    /**
     * Create a new card by the user's choicce
     * @param choiceType input of the user
     * @return the card with the right type
     */
    private Card createByChoiceCard(int choiceType)
    {
        if (choiceType == 1) return new PokemonCard();
        if (choiceType == 2) return new EnergyCard();
        if (choiceType == 3) return new TrainerCard();
        return null;
    }
}
