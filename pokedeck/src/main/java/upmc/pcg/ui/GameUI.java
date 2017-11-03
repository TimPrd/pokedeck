// Copyright 2017 Pierre Talbot (IRCAM)

// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at

//     http://www.apache.org/licenses/LICENSE-2.0

// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package upmc.pcg.ui;

import upmc.pcg.game.Card.EnergyType;
import upmc.pcg.game.Card.TrainerType;
import upmc.pcg.game.Collection;
import upmc.pcg.game.Game;
import upmc.pcg.game.Player.LecturePseudo;

import java.util.ArrayList;
import java.util.Scanner;

public class GameUI
{
    private static final Scanner keyboardChoice = new Scanner(System.in);
    private final Game game = new Game();
    private final Scanner console = new Scanner(System.in);

    /**
     * @return the EnergyType chosen
     */
    private static EnergyType setPokemonAndEnergyTypeInput()
    {
        EnergyType energyType = null;
        System.out.println("Available Types: ");
        System.out.println("Please select a corresponding number for class.");
        int i = 1;
        for (EnergyType value : EnergyType.values())
        {
            if (!value.toString().equals("Other"))
            {
                System.out.println(i + ": " + value.name());
                i++;
            }
        }
        System.out.print("Your choice :");
        energyType = EnergyType.values()[keyboardChoice.nextInt() - 1];
        return energyType;
    }

    /**
     * @return the type of trainer chosen
     */
    private static TrainerType setTrainerTypeInput()
    {
        TrainerType trainerType = null;
        System.out.println("Available Types: ");
        System.out.println("Please select a cooresponding number for the chosen class.");
        int i = 1;
        for (TrainerType value : TrainerType.values())
        {
            System.out.println(i + ": " + value.name());
            i++;
        }
        System.out.print("Your choice :");
        trainerType = TrainerType.values()[keyboardChoice.nextInt() - 1];
        return trainerType;
    }

    /**
     * @return the Card's type chosen
     */
    public static int chooseTypeOfCard()
    {
        System.out.println("+ (1) Pokémon");
        System.out.println("+ (2) Energy");
        System.out.println("+ (3) Trainer");
        return keyboardChoice.nextInt();
    }

    /**
     * Show the main menu
     * @return the choice
     */
    public static String showMenu()
    {
        System.out.println("-------------------");
        System.out.println("(1) - ADD A CARD");
        System.out.println("(2) - REMOVE A CARD");
        System.out.println("(3) - UPDATE A CARD");
        System.out.println("(4) - SHOW THE COLLECTION");
        System.out.println("(5) - SEARCH");
        System.out.println("-------------------");
        System.out.println("(C) - CHANGING PLAYER");
        System.out.println("(A) - ADD TO DECK ");
        System.out.println("(X) - SHOW DECK");
        System.out.println("-------------------");

        return keyboardChoice.nextLine();
    }

    /**
     * Choose from the collection the card the user want
     * @param collection the player's collection
     * @return the card's id
     */
    public static int chooseCardFromCollection(Collection collection)
    {
        System.out.println("Please select the pokemons numbers you want. (beetween 1 and " + collection.getAlCardsCollection().size() + ")");
        return keyboardChoice.nextInt();
    }

    /**
     * It's the method to choose all the cards we want in our deck
     * @return the string with all the card's id, separated by a dash
     */
    public static String chooseCardsFromCollection()
    {
        System.out.println("Please select the pokemons you want. By their number and separated by a - (a dash).");
        System.out.println("Ex : 1-6-23-3-87-34...");
        System.out.println("NB: Only 60 ! No more, no less ;) ");
        return keyboardChoice.nextLine();
    }

    /**
     * @return the query the user wants (witch word to search for)
     */
    public static String getQueryFindChoice()
    {
        System.out.println("Please, enter the entity you want to search.");
        return keyboardChoice.nextLine();

    }

    /**
     * @return the query the use wants (with type to search for)
     * NAME or TYPE of Card
     */
    public static String getQueryTypeChoice()
    {
        System.out.println("Please, enter the type you want to search for.");
        System.out.println("1) -> Name");
        System.out.println("2) -> Type of card (Trainer, Energy, Pokemon");
        int choice = keyboardChoice.nextInt();

        do
        {
            if (choice == 1)
                return "NAME";
            else
            {
                if (choice == 2)
                    return "TYPE";
                else System.out.println("Wrong choice");
            }
        }
        while (choice >= 1 && choice <= 2);
        return ",";
    }

    /**
     * @return the data chosen by the user to create a pokemon card
     */
    public static ArrayList<Object> inputAttributesPkmn()
    {
        ArrayList alData = new ArrayList();
        System.out.print("Enter name : ");
        alData.add(keyboardChoice.next());
        System.out.flush();

        System.out.print("Type :");
        alData.add(setPokemonAndEnergyTypeInput());
        System.out.flush();

        int i = 0;
        boolean stopped = false;
        String[] attacks = new String[3];
        while (i < 3 && !stopped)
        {
            System.out.print("Add attack " + (i + 1) + " : ");
            attacks[i] = keyboardChoice.next();
            System.out.flush();
            i++;
            System.out.print("Add another attack ? (Y/N)");
            String stop = keyboardChoice.next();
            System.out.flush();
            if (stop.equals("N") || stop.equals("n")) stopped = true;
            System.out.println();
        }

        alData.add(attacks);

        System.out.print("Enter health points :");

        alData.add(keyboardChoice.nextInt());
        System.out.flush();

        return alData;
    }

    /**
     * @return the data chosen by the user to create a trainer card
     */
    public static ArrayList<Object> inputAttributesTrain()
    {
        ArrayList alData = new ArrayList();
        System.out.print("Enter name : ");
        alData.add(keyboardChoice.next());
        System.out.flush();

        System.out.print("Type :");
        alData.add(setTrainerTypeInput());

        System.out.print("Enter the description :");

        alData.add(keyboardChoice.next());
        System.out.flush();

        return alData;
    }

    /**
     * * @return the data chosen by the user to create a Energy card
     */
    public static ArrayList<Object> inputAttributesEner()
    {
        ArrayList alData = new ArrayList();
        System.out.print("Enter name : ");
        alData.add(keyboardChoice.next());
        System.out.flush();

        System.out.print("Type :");
        alData.add(setPokemonAndEnergyTypeInput());
        System.out.flush();

        return alData;
    }

    /**
     * start the game (call the model part)
     */
    public void start()
    {
        ArrayList<String> names = ask_players_names();
        game.initialize(names);
        game.play();

    }

    /**
     * @return the names of the players
     */
    private ArrayList<String> ask_players_names()
    {
        MenuPseudo menuPseudo = new MenuPseudo();
        LecturePseudo lecturePseudo;
        ArrayList<String> alPseudos;
        lecturePseudo = menuPseudo.modeLecturePseudo();
        alPseudos = lecturePseudo.lirePseudo();
        return alPseudos;
    }
}
