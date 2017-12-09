/*
 * Copyright (c)
 *        @author Timothé PARDIEU
 *                 ${PACKAGE_NAME}
 *                 Created on - ${DATE} (${TIME})
 *                 Build for project ${PROJECT_NAME}
 *
 */

package upmc.pcg.game.card;

import upmc.pcg.game.player.Player;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Class card
 * This class is the "mother" class of cards.
 * It's a base for the other cards (Pokemon, Trainer and Energy)
 * All the things in common for those 3 cards are implemented here.
 */
public abstract class Card implements Serializable {

    private CardType cardType;
    private String cardTitle;
    private int idCard;

    /**
     * Construtor in order to create a new card
     *
     * @param cardTitle the name of the card
     * @param cardType  the type of card (pokemon, trainer, energy)
     * @param idCard    the unique id
     */
    Card(String cardTitle, CardType cardType, int idCard) {
        this.cardTitle = cardTitle;
        this.cardType = cardType;
        this.idCard = idCard;
    }


    /**
     * Create a card but in an abstract way.
     * So we can redefine for each card the method's body
     *
     * @param arrayList     the data to affect (what's required by the constructor)
     * @param currentPlayer the player who made the action (in order to get the unique id)
     * @return a new card
     */
    public abstract Card createCard(ArrayList arrayList, Player currentPlayer);

    /**
     * Update a card in the same way as createCard().
     * It's abstract so we can redefine the body
     *
     * @param alUpdate the data to affect
     */
    public abstract void updateCardFromCollec(ArrayList alUpdate);

    /**
     * @return the card's title
     */
    public String getCardTitle() {
        return cardTitle;
    }

    /*************
     * Overriding *
     **************/
   /* @Override
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

*/

    void setCardTitle(String s) {
        this.cardTitle = s;
    }

    /**
     * @return the id of the card
     */
    public int getID() {
        return this.idCard;
    }

    /**
     * @return the type of the card
     */
    public String getTypeCard() {
        return this.cardType.toString();
    }

    @Override
    public String toString() {
        return " - " + cardTitle + " [" + cardType + "]";
    }
}