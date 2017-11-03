package upmc.pcg.game.Card;

/**
 * Created by Timothé PARDIEU & Valentin HERVOUET.
 */

import upmc.pcg.game.Player.Player;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Class Card
 * This class is the "mother" class of cards.
 * It's a base for the other cards (Pokemon, Trainer and Energy)
 * All the things in common for those 3 cards are implemented here.
 */
public abstract class Card implements Serializable
{

    private String cardTitle;
    private CardType cardType;
    private int idCard;

    /**
     * Construtor in order to create a new Card
     * @param cardTitle the name of the card
     * @param cardType the type of card (pokemon, trainer, energy)
     * @param idCard the unique id
     */
    public Card(String cardTitle, CardType cardType, int idCard)
    {
        this.cardTitle = cardTitle;
        this.cardType = cardType;
        this.idCard = idCard;
    }

    public Card()
    {
    }



    /**
     * Create a card but in an abstract way.
     * So we can redefine for each card the method's body
     * @param arrayList the data to affect (what's required by the constructor)
     * @param currentPlayer the player who made the action (in order to get the unique id)
     * @return a new Card
     */
    public abstract Card createCard(ArrayList arrayList, Player currentPlayer);

    /**
     * Update a card in the same way as createCard().
     * It's abstract so we can redefine the body
     * @param alUpdate the data to affect
     */
    public abstract void updateCardFromCollec(ArrayList alUpdate);

                /***********
                 * GETTERS *
                 ***********/

    /**
     * @return the card's title
     */
    public String getCardTitle()
    {
        return cardTitle;
    }

    /**
     * @return the id of the card
     */
    public int getID()
    {
        return this.idCard;
    }

    /**
     * @return the type of the card
     */
    public String getTypeCard()
    {
        return this.cardType.toString();
    }


                /*************
                * Overriding *
                **************/
    @Override
    public String toString()
    {
        String card = "*" + String.format("%-14s", "------------").replace(' ', '-') + "*\n"
                + String.format("%-15s", "| " + this.cardTitle + "") + "|\n"
                + String.format("%-15s", "| " + this.getTypeCard() + "") + "|\n"
                + String.format("%-15s", "| " + "") + "|\n"
                + String.format("%-15s", "| " + "") + "|\n"
                + String.format("%-15s", "| " + "") + "|\n"
                + "*" + String.format("%-14s", "------------").replace(' ', '-') + "*\n";
        return card;    }
}