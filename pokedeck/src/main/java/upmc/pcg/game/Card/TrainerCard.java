package upmc.pcg.game.Card;

import upmc.pcg.game.Player.Player;

import java.util.ArrayList;

/**
 * Created by timot on 24/10/2017.
 */
public class TrainerCard extends Card
{

    private String description;
    private TrainerType trainerType;
    private String cardTitle;

    /**
     * Constructor in order to create an instantiated TrainerCard
     * @param cardTitle the card's title/name
     * @param idCard id of the card
     * @param trainerType trainer type (stadium, trainer..)
     * @param description description of the card (effect of it)
     */
    private TrainerCard(String cardTitle, int idCard, TrainerType trainerType, String description)
    {
        super(cardTitle, CardType.Trainer, idCard);
        this.trainerType = trainerType;
        this.cardTitle = cardTitle;
        this.description = description;
    }

    public TrainerCard()
    {

    }



    @Override
    public Card createCard(ArrayList arrayList, Player currentPlayer)
    {
        String cardTitle = (String) arrayList.get(0);
        int idCard = currentPlayer.getIdForCard();
        TrainerType trainerType = (TrainerType) arrayList.get(1);
        String description = (String) arrayList.get(2);
        return generateCard(cardTitle, idCard, trainerType, description);//;
    }

    /**
     * Generate a TrainerCard with the data of createdcard()
     * @param cardTitle card's title
     * @param idCard card's id
     * @param trainerType trainer type
     * @param description effect of the card
     * @return a new TrainerCard
     */
    private TrainerCard generateCard(String cardTitle, int idCard, TrainerType trainerType, String description)
    {
        return new TrainerCard(cardTitle, idCard, trainerType, description);
    }

    @Override
    public void updateCardFromCollec(ArrayList alUpdate)
    {
        this.cardTitle = (String) alUpdate.get(0);
        this.trainerType = (TrainerType) alUpdate.get(1);
        this.description = (String) alUpdate.get(2);
    }
}