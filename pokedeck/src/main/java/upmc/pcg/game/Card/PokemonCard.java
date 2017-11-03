package upmc.pcg.game.Card;

import upmc.pcg.game.Player.Player;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by timot on 24/10/2017.
 */
public class PokemonCard extends Card
{

    private EnergyType energyType;
    private int hp;
    private String cardTitle;
    private int evolutionStage = 0;
    private PokemonCard evolveFrom = null;
    private String[] attacks = new String[3];

    public PokemonCard()
    {

    }

    /**
     * Constructor to instantiate a new PokemonCard
     * @param cardTitle the card's name
     * @param idCard the card's id
     * @param pokemonType the pokemon's type
     * @param arrayAttacks the attacks. (it's an array of max. 3)
     * @param hp hp for health point so in other words : the pokemon's life
     */
    private PokemonCard(String cardTitle, int idCard, EnergyType pokemonType, String[] arrayAttacks, int hp)
    {
        super(cardTitle, CardType.Pokemon, idCard);
        this.cardTitle = cardTitle;
        this.attacks = arrayAttacks;
        this.energyType = pokemonType;
        this.hp = hp;
    }

    /**
     * @deprecated
     * Another constructor dealing with the evolutions (not implemented yet)
     * @param cardTitle
     * @param idCard
     * @param energyType
     * @param arrayAttacks
     * @param hp
     * @param evolutionStage
     * @param evolveFrom
     */
    public PokemonCard(String cardTitle, int idCard, EnergyType energyType, String[] arrayAttacks, int hp, int evolutionStage, PokemonCard evolveFrom)
    {
        super(cardTitle, CardType.Pokemon, idCard);
        this.energyType = energyType;
        this.hp = hp;
        this.attacks = arrayAttacks;
        this.evolutionStage = evolutionStage;
        this.evolveFrom = evolveFrom;
    }

    /**
     * Generate a new pokmeon card with the data picked by createdCard()
     * @param cardTitle card's title
     * @param idCard card's id
     * @param energyType card's energy
     * @param arrayAttacks attacks of the pokemon
     * @param hp healpoint
     * @return the pokemon card
     */
    private PokemonCard generateCard(String cardTitle, int idCard, EnergyType energyType, String[] arrayAttacks, int hp)
    {
        return new PokemonCard(cardTitle, idCard, energyType, arrayAttacks, hp);
    }

    public EnergyType getType()
    {
        return this.energyType;
    }
    @Override
    public Card createCard(ArrayList arrayList, Player current)
    {
        String cardTitle = (String) arrayList.get(0);
        int idCard = current.getIdForCard(); //get the uid by the player
        EnergyType energyType = (EnergyType) arrayList.get(1);
        String[] arrayAttacks = (String[]) arrayList.get(2);
        int hp = (int) arrayList.get(3);
        return generateCard(cardTitle, idCard, energyType, arrayAttacks, hp);
    }

    @Override
    public void updateCardFromCollec(ArrayList alUpdate)
    {
        
        this.cardTitle = (String) alUpdate.get(0);
        this.energyType = (EnergyType) alUpdate.get(1);
        this.attacks = (String[]) alUpdate.get(2);
        this.hp = (int) alUpdate.get(3);
    }

    @Override
    public String toString()
    {
        String card = "*" + String.format("%-14s", "------------").replace(' ', '-') + "*\n"
                + String.format("%-10s", "| " + this.cardTitle + "") +  String.format("%5s", this.hp)  + "|\n"
                + String.format("%-15s", "| " + this.energyType + "") + "|\n"
                + String.format("%-15s", "| " + "") + "|\n"
                + String.format("%-15s", "| " + "") + "|\n";
        for (String attack : this.attacks)
            card += String.format("%-15s", "| " + attack + "") + "|\n";
        card+=  String.format("%-15s", "| " + "") + "|\n"
                + String.format("%-15s", "| " + "") + "|\n"
                + "*" + String.format("%-14s", "------------").replace(' ', '-') + "*\n";
        return card;
    }
}