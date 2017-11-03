package upmc.pcg.game.Card;

/**
 * Created by timot on 24/10/2017.
 */

import upmc.pcg.game.Player.Player;

import java.util.ArrayList;

/**
 * Class EnergyCard, extend the Class Card
 */
public class EnergyCard extends Card
{

    private EnergyType energyType;
    private String cardTitle;

    public EnergyCard(String cardTitle, int idCard, EnergyType energyType) {
        super(cardTitle, CardType.Energy , idCard);
        this.energyType = energyType;
        this.cardTitle = cardTitle;

    }

    public EnergyCard()
    {

    }

    /**
     * Get the type of energy of the card
     * @return EnergyType of card
     */
    public EnergyType getEnergyType() {
        return energyType;
    }

    /**
     * Set the type of energy of the card
     * @param energyType EnergyType Enum
     */
    public void setEnergyType(EnergyType energyType) {
        this.energyType = energyType;
    }

    @Override
    public Card createCard(ArrayList arrayList, Player currentPlayer)
    {
        String cardTitle = (String) arrayList.get(0);
        int idCard = (int) (currentPlayer.getIdForCard()); //(int) arrayList.get(1);
        EnergyType energyType = (EnergyType) arrayList.get(1);

        return         generateCard(cardTitle,idCard,energyType);//;
    }
    private EnergyCard generateCard(String cardTitle, int idCard, EnergyType energyType)
    {
        return new EnergyCard(cardTitle,idCard,energyType);
    }


    @Override
    public void updateCardFromCollec(ArrayList alUpdate)
    {
        this.cardTitle = (String) alUpdate.get(0);
        this.energyType = (EnergyType) alUpdate.get(1);
    }


    public EnergyType getType()
    {
        return this.energyType;
    }

    @Override
    public String toString()
    {
        return "EnergyCard{" +
                this.cardTitle +
                "energyType=" + energyType +
                '}';
    }
}