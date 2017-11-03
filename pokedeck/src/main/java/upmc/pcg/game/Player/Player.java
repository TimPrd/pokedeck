/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package upmc.pcg.game.Player;

import upmc.pcg.game.Card.Card;
import upmc.pcg.game.Collection;

import java.util.ArrayList;

/**
 * This Class enables you to create the player (IA or User) with a name, a hand (of cards) and a score
 * The game has 3 modes : (Player vs Player / Player vs IA / IA vs IA (aka Statistics))
 * The Player will have many actions like : draw a card / increase score / reset the hand / Checking if the hand is empty (loose the game)
 * In order to have 2 type of Player (IA and Player-user) there is two constructor.
 *
 * @author Timothé Pardieu
 */
public class Player
{
    //NB : all the arrayList are called alXXXX where al means array list (personnal convention to know when we are dealing with an arraylist)
    private String name;
    private int score;
    private ArrayList<Card> alPlayerDeck = new ArrayList<Card>();
    private Collection collection;
    private int id;
    private int idCard ;


    /**
     * Creating a player - Computer
     * @param s
     * @param collection
     */
    public Player(int id, String s, Collection collection)
    {
        this.id = id;
        this.name = s;
        this.collection = collection;
        this.score = 0;
    }



    /**


    public int getId()
    {
        return id;
    }

    public Collection getCollection()
    {
        return collection;
    }

    /**
     * Enables the player to draw a card from the last position of his deck
     *
     * @return the card that have been drawn
     */
    public Card drawCard()
    {
        if (this.alPlayerDeck.size() > 0) //check if we have cards
        {
            Card card = alPlayerDeck.get(alPlayerDeck.size() - 1); //Create a card with the last card of the deck
            alPlayerDeck.remove(alPlayerDeck.get(alPlayerDeck.size() - 1)); //remove it from the player's deck
            return card;
        }
        return null;
    }


    /**
     * Enables to create a player's deck from a main deck
     *
     * @param start   beginning of where we cut the deck
     * @param end     end of deck's cut
     * @param alCards the main deck
     */
    public void createPlayerDeck(int start, int end, ArrayList<Card> alCards)
    {
        for (int i = start; i < end; i++)
            this.alPlayerDeck.add(alCards.get(i));
    }

    /**
     * Increase the player's score
     */
    public void increaseScore()
    {
        this.score++;
    }

    /**
     * @return if the deck is empty (payer has lost the round)
     */
    public boolean isEmpty()
    {
        return this.alPlayerDeck.isEmpty();
    }

    /**
     * Removing all the cards from a deck
     */
    public void resetData()
    {
        this.alPlayerDeck.removeAll(this.alPlayerDeck);
    }

    /**
     * Get the percentage of victory (win rate)
     *
     * @param numberOfGame the number of games the users chose
     * @return the percentage of victory
     */
    public double percentageScore(int numberOfGame)
    {
        if (numberOfGame != 0) // do not divided by 0 !!
            return (double) (((double) this.score / (double) numberOfGame) * 100); //cast to double to get the x.xx
        return 0;
    }

    /**
     * Show some informations (number of remaining cards, score..)
     */
    public String gameInfo()
    {
        return this.name + " a " + this.alPlayerDeck.size() + (this.alPlayerDeck.size() > 1 ? " cartes" : " carte") +
                "\n Score : (" + this.score + ")";
    }

    /**
     * @return the player's score
     */
    public int getScore()
    {
        return score;
    }

    /**
     * @return the player's name
     */
    public String getName()
    {
        return name;
    }

    /**
     * @return the player deck
     */
    public ArrayList<Card> getAlPlayerDeck()
    {
        return alPlayerDeck;
    }


    public void showWinningMsg()
    {
        System.out.println(this.name + " Gagne");
    }

    public void addWonCards(ArrayList<Card> alCardsInGame)
    {
        this.showWinningMsg();
        this.alPlayerDeck.addAll(0, alCardsInGame);
    }


    public Collection getCollection()
    {
        return collection;
    }

    public int getId()
    {
        return id;
    }

    public int getIdForCard()
    {
        this.idCard = ++idCard;
        return idCard;
    }
}
