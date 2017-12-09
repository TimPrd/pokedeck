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

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by timot on 24/10/2017.
 */
public class PokemonCard extends Card {

    private EnergyType energyType;
    private int hp;
    private Map<String, Integer> attacks = new LinkedHashMap<>();
    private int HP;


    /**
     * Constructor to instantiate a new PokemonCard
     *
     * @param cardTitle   the card's name
     * @param idCard      the card's id
     * @param pokemonType the pokemon's type
     * @param hp          hp for health point so in other words : the pokemon's life
     */
    public PokemonCard(String cardTitle, int idCard, EnergyType pokemonType, Map<String, Integer> attacks, int hp) {
        super(cardTitle, CardType.Pokemon, idCard);
        this.attacks = attacks;
        this.energyType = pokemonType;
        this.hp = hp;
    }

    /**
     * @param cardTitle
     * @param idCard
     * @param energyType
     * @param hp
     * @param evolutionStage
     * @param evolveFrom
     * @deprecated Another constructor dealing with the evolutions (not implemented yet)
     */
    @Deprecated
    public PokemonCard(String cardTitle, int idCard, EnergyType energyType, Map<String, Integer> attacks, int hp, int evolutionStage, PokemonCard evolveFrom) {
        super(cardTitle, CardType.Pokemon, idCard);
        this.energyType = energyType;
        this.hp = hp;
        this.attacks = attacks;
        int evolutionStage1 = evolutionStage;
        PokemonCard evolveFrom1 = evolveFrom;
    }

    /**
     * Generate a new pokmeon card with the data picked by createdCard()
     *
     * @param cardTitle  card's title
     * @param idCard     card's id
     * @param energyType card's energy
     * @param hp         healpoint
     * @return the pokemon card
     */
    private PokemonCard generateCard(String cardTitle, int idCard, EnergyType energyType, Map<String, Integer> attacks, int hp) {
        return new PokemonCard(cardTitle, idCard, energyType, attacks, hp);
    }

    public EnergyType getType() {
        return this.energyType;
    }


    @Override
    public Card createCard(ArrayList arrayList, Player current) {
        String cardTitle = (String) arrayList.get(0);
        int idCard = current.getIdForCard(); //get the uid by the player
        EnergyType energyType = (EnergyType) arrayList.get(1);
        Map<String, Integer> attacks = (Map<String, Integer>) arrayList.get(2);
        int hp = (int) arrayList.get(3);
        return generateCard(cardTitle, idCard, energyType, attacks, hp);
    }

    @Override
    public void updateCardFromCollec(ArrayList alUpdate) {
        this.setCardTitle((String) alUpdate.get(0));
        this.energyType = (EnergyType) alUpdate.get(1);
        this.attacks = (Map<String, Integer>) alUpdate.get(2);
        this.hp = (int) alUpdate.get(3);
    }

    @Override
    public String toString() {
        return " - " + getCardTitle() + " [" + getEnergy() + "]";
    }

    public String toStringCardFormat() {
        StringBuilder card = new StringBuilder("  ##### " + this.getID() + " #####" + "\n"
                + "*" + String.format("%-14s", "------------").replace(' ', '-') + "*\n"
                + String.format("%-10s", "| " + getCardTitle() + "") + String.format("%5s", this.hp) + "|\n"
                + String.format("%-15s", "| " + this.energyType + "") + "|\n"
                + String.format("%-15s", "| " + "") + "|\n"
                + String.format("%-15s", "| " + "") + "|\n");
        for (Map.Entry<String, Integer> entry : this.attacks.entrySet()) {
            card.append(String.format("%-10s", "| " + entry.getKey() + " -> ")).append(entry.getValue()).append("|\n");
        }
        card.append(String.format("%-15s", "| " + "")).append("|\n").append(String.format("%-15s", "| " + "")).append("|\n").append("*").append(String.format("%-14s", "------------").replace(' ', '-')).append("*\n");
        return card.toString();
    }

    public int getHP() {
        return this.hp;
    }

    public void setHP(int hp) {
        this.hp = hp;
    }

    public EnergyType getEnergy() {
        return this.energyType;
    }

    /**
     * Retrieve a precis attack
     * @param index the index of the attack between 0 and 2
     * @return the attack selected by its index
     */
    public String getAttack(int index) {
        if (this.attacks.size() >= index + 1)
            return this.attacks.keySet().toArray()[index] + " [" + this.attacks.values().toArray()[index] + "]";
        return "";
    }

    /**
     * @return retrieve a random attack from all the possible attack of a card
     */
    public Integer getRandomAttack() {
        int nbAttacks = nbAttackPossible();
        int randomAttack = (int) (Math.random() * nbAttacks);

        //In case we want the name of the attack
        //this.attacks.keySet().toArray()[randomAttack] +"-"+ ;

        return (Integer) this.attacks.values().toArray()[randomAttack];

    }

    /**
     * @return the number of pokemon's attacks
     */
    public int nbAttackPossible() {
        int cpt = 0;
        for (Map.Entry<String, Integer> entry : this.attacks.entrySet()) {
            if (entry.getValue() != null)
                cpt++;
        }
        return cpt;
    }

    public Map<String, Integer> getAttacks() {
        return attacks;
    }
}