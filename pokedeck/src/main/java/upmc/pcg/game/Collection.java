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
 * Created by timot on 24/10/2017.
 */
public class Collection
{
    private final String player;
    private ArrayList<Card> alCardsCollection;
    private ArrayList<Card> alCardsDeck;


    public Collection(ArrayList<Card> alCardsCollection, ArrayList<Card> alCardsDeck, String player)
    {
        this.alCardsCollection = alCardsCollection;
        this.alCardsDeck = alCardsDeck;
        this.player = player;
    }


    public void addToCollec(Card card)
    {
        this.alCardsCollection.add(card);
    }


    public void showCollec()
    {
        for (int i = 0; i < alCardsCollection.size(); i++)
        {
            System.out.println(alCardsCollection.get(i).toString());
        }
    }

    public Card getCardByID(int idCard)
    {
        for (Card card : alCardsCollection)
            if (card.getID() == idCard)
                return card;
        return null;
    }

    public void removeCardById(int idCard)
    {
        Card card = this.getCardByID(idCard);
        this.alCardsCollection.remove(this.alCardsCollection.indexOf(card));
    }


    public void updateCardById(int idCard)
    {
        Card cardToUpdate = this.getCardByID(idCard);
        ArrayList alUpdate = new ArrayList();
        if (cardToUpdate.getClass().getSimpleName().equals("PokemonCard")) alUpdate = GameUI.inputAttributesPkmn();
        if (cardToUpdate.getClass().getSimpleName().equals("EnergyCard"))  alUpdate = GameUI.inputAttributesEner();
        if (cardToUpdate.getClass().getSimpleName().equals("TrainerCard")) alUpdate = GameUI.inputAttributesTrain();
        cardToUpdate.updateCardFromCollec(alUpdate);
    }

    public ArrayList<Card> getAlCardsCollection()
    {
        return alCardsCollection;
    }

    public void addToDeck(int idCard)
    {
        Card card = this.getCardByID(idCard);
        this.alCardsDeck.add(card);
    }

    public void showDeck()
    {
        for (int i = 0; i < alCardsDeck.size(); i++)
        {
            System.out.println(alCardsDeck.get(i).toString());
        }
    }

    public List<Card> findCard(String queryTypeChoice, String searchTerm)
    {

        String search = searchTerm.replace("é", "e");
        List<Card> card = null;

        switch (queryTypeChoice)
        {
            case "NAME":
                card = alCardsCollection
                        .stream()
                        .filter(p -> p.getCardTitle().toLowerCase().contains((search.toLowerCase())))
                        .collect(Collectors.toList());
                System.out.println(alCardsCollection.stream().filter(p -> p.getCardTitle().toLowerCase().contains(search.toLowerCase())).count() + " results !");

                break;
            case "TYPE":
                card = alCardsCollection
                        .stream()
                        .filter(p -> p.getTypeCard().toLowerCase().contains(search.toLowerCase()))
                        .collect(Collectors.toList());
                System.out.println(alCardsCollection.stream().filter(p -> p.getTypeCard().toLowerCase().contains(search.toLowerCase())).count() + " results !");
                break;
        }

        return card;

    }

    public void createDeck()
    {
        //get all the cards in format "number-number-number"
        String cardChosenToAddInDeck = GameUI.chooseCardsFromCollection();

        Scanner scan = new Scanner(cardChosenToAddInDeck).useDelimiter("-");

        // Printing the tokenized Strings
        while(scan.hasNext() && this.alCardsDeck.size()<=60)
        {
            this.addToDeck(scan.nextInt()); //add it to the deck of the player
        }
        scan.close();

    }

    public void addingCard(int choiceType, Player currentPlayer)
    {
        Card card = createByChoiceCard(choiceType);
        ArrayList<Object> al = new ArrayList<>();
        if (choiceType == 1) al = GameUI.inputAttributesPkmn();
        if (choiceType == 2) al = GameUI.inputAttributesEner();
        if (choiceType == 3) al = GameUI.inputAttributesTrain();

        if (card != null)
        {
            card = card.createCard(al,currentPlayer);
        }
        this.addToCollec(card);
    }


    private Card createByChoiceCard(int choiceType)
    {
        if (choiceType == 1) return new PokemonCard();
        if (choiceType == 2) return new EnergyCard();
        if (choiceType == 3) return new TrainerCard();
        return null;
    }
}
