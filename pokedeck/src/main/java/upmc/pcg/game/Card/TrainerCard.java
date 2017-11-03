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


    public TrainerCard(String cardTitle, int idCard, TrainerType trainerType, String description) {
        super(cardTitle, CardType.Trainer , idCard);
        this.trainerType = trainerType;
        this.cardTitle = cardTitle;
        this.description = description;
    }

    public TrainerCard()
    {

    }

    /**
     * Get the trainer type of the card
     * @return TrainerType of card
     */
    public TrainerType getTrainerType() {
        return trainerType;
    }

    /**
     * Set the trainer type of the card
     * @param trainerType TrainerType Enum
     */
    public void setTrainerType(TrainerType trainerType) {
        this.trainerType = trainerType;
    }

    @Override
    public Card createCard(ArrayList arrayList, Player currentPlayer)
    {
        String cardTitle = (String) arrayList.get(0);
        int idCard = (int) (currentPlayer.getIdForCard());
        TrainerType trainerType = (TrainerType) arrayList.get(1);
        String description = (String) arrayList.get(2);
        return   generateCard(cardTitle,idCard,trainerType,description);//;
    }

    private TrainerCard generateCard(String cardTitle, int idCard, TrainerType trainerType, String description)
    {
        return new TrainerCard(cardTitle,idCard,trainerType,description);
    }

    @Override
    public void updateCardFromCollec(ArrayList alUpdate)
    {
        this.cardTitle = (String) alUpdate.get(0);
        this.trainerType = (TrainerType) alUpdate.get(1);
        this.description = (String) alUpdate.get(2);
    }
}