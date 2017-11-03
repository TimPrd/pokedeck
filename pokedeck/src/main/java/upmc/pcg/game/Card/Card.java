package upmc.pcg.game.Card;

/**
 * Created by timot on 24/10/2017.
 */

import upmc.pcg.game.Player.Player;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Class Card
 */
public abstract class Card implements Serializable
{

    private String cardTitle;
    private CardType cardType;
    private int idCard;


    public Card(String cardTitle, CardType cardType, int idCard)
    {
        this.cardTitle = cardTitle;
        this.cardType = cardType;
        this.idCard = idCard;
    }

    public Card()
    {
    }

    public String getCardTitle()
    {
        return cardTitle;
    }


    @Override
    public String toString()
    {
        return "("  + this.idCard + ")" + "-" + this.cardTitle + " " + this.cardType;
    }



    public abstract Card createCard(ArrayList arrayList, Player currentPlayer);

    public abstract void updateCardFromCollec(ArrayList alUpdate);


    public int getID()
    {
        return this.idCard;
    }


    public String getTypeCard()
    {
        return this.cardType.toString();
    }
}