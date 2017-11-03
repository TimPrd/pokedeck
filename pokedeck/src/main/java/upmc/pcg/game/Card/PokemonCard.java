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
    private String[] attacks =new String[3];

    public PokemonCard(){

    }


    public PokemonCard(String cardTitle, int idCard, EnergyType pokemonType, String[] arrayAttacks, int hp) {
        super(cardTitle, CardType.Pokemon, idCard);
        this.cardTitle = cardTitle;
        this.energyType = pokemonType;
        this.hp = hp;
    }


    public PokemonCard(String cardTitle, int idCard, EnergyType energyType, String[] arrayAttacks, int hp, int evolutionStage, PokemonCard evolveFrom) {
        super(cardTitle, CardType.Pokemon , idCard);
        this.energyType = energyType;
        this.hp = hp;
        this.evolutionStage = evolutionStage;
        this.evolveFrom = evolveFrom;
    }

    @Override
    public Card createCard(ArrayList arrayList,Player current)
    {
        String cardTitle = (String) arrayList.get(0);
        int idCard = (int) (current.getIdForCard()); //(int) arrayList.get(1);
        EnergyType energyType = (EnergyType) arrayList.get(1);
        String[] arrayAttacks = (String[]) arrayList.get(2);
        int hp = (int) arrayList.get(3);
        return         generateCard(cardTitle,idCard,energyType,arrayAttacks,hp);//;
    }

    @Override
    public void updateCardFromCollec(ArrayList alUpdate)
    {
        this.cardTitle = (String) alUpdate.get(0);
        this.energyType = (EnergyType) alUpdate.get(1);
        this.attacks = (String[]) alUpdate.get(2);
        this.hp = (int) alUpdate.get(3);
    }

    private PokemonCard generateCard(String cardTitle, int idCard, EnergyType energyType, String[] arrayAttacks, int hp)
    {
        return new PokemonCard(cardTitle,idCard,energyType,arrayAttacks,hp);
    }

    public EnergyType getType()
    {
        return this.energyType;
    }

    @Override
    public String toString()
    {
        return "PokemonCard{" +
                "energyType=" + energyType +
                ", hp=" + hp +
                ", cardTitle='" + cardTitle + '\'' +
                ", evolutionStage=" + evolutionStage +
                ", evolveFrom=" + evolveFrom +
                ", attacks=" + Arrays.toString(attacks) +
                '}';
    }
}