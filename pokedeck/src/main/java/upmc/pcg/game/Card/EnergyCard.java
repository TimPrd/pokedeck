package upmc.pcg.game.Card;

/**
 * Created by timot on 24/10/2017.
 */

import upmc.pcg.game.Player.Player;
import java.util.ArrayList;

/**
 * Class EnergyCard, extend the Class Card
 * This class enables to create an EnergyCard
 * It's based on the class Card to get all the shared features
 */
public class EnergyCard extends Card
{

    private EnergyType energyType;
    private String cardTitle;

    /**
     * Constructor to create the energy card
     * @param cardTitle the name of the card
     * @param idCard the id of the card
     * @param energyType the type of the card
     */
    private EnergyCard(String cardTitle, int idCard, EnergyType energyType)
    {
        super(cardTitle, CardType.Energy, idCard); //calling the Card class
        this.energyType = energyType;
        this.cardTitle = cardTitle;

    }

    public EnergyCard()
    {

    }

    /**
     * Generate a card with the data collected by createdCard()
     * @param cardTitle card's title
     * @param idCard card's id
     * @param energyType card's type
     * @return a new Energy Card with the right parameters
     */
    private EnergyCard generateCard(String cardTitle, int idCard, EnergyType energyType)
    {
        return new EnergyCard(cardTitle, idCard, energyType);
    }


    /**
     * @return EnergyType of the card
     */
    public EnergyType getType()
    {
        return this.energyType;
    }


    @Override
    public Card createCard(ArrayList arrayList, Player currentPlayer)
    {
        //retrieve from the arraylist the data (+parsing it)
        String cardTitle = (String) arrayList.get(0);
        int idCard = currentPlayer.getIdForCard();
        EnergyType energyType = (EnergyType) arrayList.get(1);

        return generateCard(cardTitle, idCard, energyType);
    }



    @Override
    public void updateCardFromCollec(ArrayList alUpdate)
    {
        this.cardTitle = (String) alUpdate.get(0);
        this.energyType = (EnergyType) alUpdate.get(1);
    }


    @Override
    public String toString()
    {
        String card = "  ##### " + this.getID() +" #####" + "\n"
                + "*" + String.format("%-14s", "------------").replace(' ', '-') + "*\n"
                + String.format("%-15s", "| " + this.cardTitle + "")  + "|\n"
                + String.format("%-15s", "| " + "") + "|\n"
                + String.format("%-15s", "| " + "") + "|\n"
                + String.format("%-15s", "| " + "") + "|\n"
                + String.format("%-15s", "| " + this.energyType + "") + "|\n"
                + String.format("%-15s", "| " + "") + "|\n"
                + String.format("%-15s", "| " + "") + "|\n"
                + "*" + String.format("%-14s", "------------").replace(' ', '-') + "*\n";
        return card;
    }
}