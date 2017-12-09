/*
 * Copyright (c)
 *        @author Timothé PARDIEU
 *                 ${PACKAGE_NAME}
 *                 Created on - ${DATE} (${TIME})
 *                 Build for project ${PROJECT_NAME}
 *
 */

package test.upmc.pcg.game.main;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.Before;
import org.junit.Test;
import upmc.pcg.game.card.Card;
import upmc.pcg.game.card.EnergyType;
import upmc.pcg.game.card.PokemonCard;
import upmc.pcg.game.main.Collection;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Collection Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>d?c. 9, 2017</pre>
 */
//@Pierre T.
public class CollectionTest {

    Collection collection, collection2;
    ArrayList<Card> colle = new ArrayList<>();
    ArrayList<Card> deck = new ArrayList<>();

    @Before
    public void before() throws Exception {
        collection = new Collection(colle, deck, "player");
    }

    /**
     * Method: addToCollec(Card card)
     */
    @Test
    //@Pierre T.
    public void testAddToCollec() throws Exception {
        int nbInCollecBF = collection.getAlCardsCollection().size();

        Map<String, Integer> map = new HashMap<>();
        map.put("attack", 3);

        PokemonCard pkmn = new PokemonCard("Pikachu", 0, EnergyType.Darkness, map, 50);

        collection.addToCollec(pkmn);
        int nbInCollecAF = collection.getAlCardsCollection().size();

        assert nbInCollecBF < nbInCollecAF;
    }

    /**
     * Method: toJSOn(String name, String collec, ArrayList<Card> alCards)
     */
    //@Pierre T.
    @Test
    public void testToJSOn() throws Exception {
        testAddToCollec();
        Gson objGson = new GsonBuilder().setPrettyPrinting().create();
        String json = objGson.toJson(collection.getAlCardsCollection());
        System.out.println(json);
        assert json != null;
        assert !json.equals("");
    }


    /**
     * Method: saveCollec(String name)
     */
    //@Pierre T.
    @Test
    public void testSaveCollec() throws Exception {
        testAddToCollec();
        File f = new File("testing_collection.json");
        collection.saveCollec("testing");
        assert f.exists();
    }


    /**
     * Method: loadCollec(String name)
     */
    //@Pierre T.
    @Test
    public void testLoadCollec() throws Exception {
        ArrayList<Card> c = new ArrayList<>();
        ArrayList<Card> d = new ArrayList<>();

        collection2 = new Collection(c, d, "p");
        int nbInCollecBF = collection2.getAlCardsCollection().size();
        collection2.loadCollec("testing2");
        int nbInCollecAF = collection2.getAlCardsCollection().size();
        System.out.println("before : " + nbInCollecBF + " |  after : " + nbInCollecAF);
        assert nbInCollecAF > nbInCollecBF;
    }

    /**
     * Method: getRandomCard()
     */
    @Test
    public void testGetRandomCard() throws Exception {
        testLoadCollec();
        this.deck.addAll(this.collection2.getAlCardsCollection());
        PokemonCard card = (PokemonCard) collection.getRandomCard();
        assert card != null;
        System.out.println(card.toStringCardFormat());
    }


}
