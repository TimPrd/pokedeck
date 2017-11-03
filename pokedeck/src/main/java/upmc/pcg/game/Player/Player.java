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
 * This Class enables you to create the player with a name, a deck, a collection and a score*
 */
public class Player
{
    private int score;
    private ArrayList<Card> alPlayerDeck = new ArrayList<Card>();
    private Collection collection;
    private int id;
    private int idCard;


    /**
     * Creating a player
     *
     * @param s
     * @param collection
     */
    public Player(int id, String s, Collection collection)
    {
        this.id = id;
        String name = s;
        this.collection = collection;
        this.score = 0;
    }



    /**
     * Increase the player's score
     */
    public void increaseScore()
    {
        this.score++;
    }


    /**
     * @return the player's collection
     */
    public Collection getCollection()
    {
        return collection;
    }

    /**
     * @return the player uid
     */
    public int getId()
    {
        return id;
    }

    /**
     * this method enables to create a uid (unique id)
     * For each card there is an incremental variable.
     * @return the uid
     */
    public int getIdForCard()
    {
        this.idCard = ++idCard;
        return idCard;
    }
}
