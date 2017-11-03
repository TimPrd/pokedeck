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
    private static Scanner keyboardChoice = new Scanner(System.in);
    private final Game game = new Game();
    private final Scanner console = new Scanner(System.in);

    private static EnergyType setPokemonAndEnergyTypeInput()
    {
        EnergyType energyType = null;
        System.out.println("Available Types: ");
        System.out.println("Please select a cooresponding number for class.");
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

    public static int chooseTypeOfCard()
    {
        System.out.println("+ (1) Pokémon");
        System.out.println("+ (2) Energy");
        System.out.println("+ (3) Trainer");
        return keyboardChoice.nextInt();
    }

    public static String showMenu()
    {
        System.out.println("-------------------");
        System.out.println("(1) - AJOUTER UNE CARTE");
        System.out.println("(2) - SUPPRIMER UNE CARTE");
        System.out.println("(3) - MODIFIER UNE CARTE");
        System.out.println("(4) - CONSULTER COLLECTION");
        System.out.println("(5) - RECHERCHER");
        System.out.println("-------------------");
        System.out.println("(C) - Choisir deck");
        System.out.println("(A) - Ajouter au deck");
        System.out.println("(X) - Show deck");
        System.out.println("-------------------");


        return keyboardChoice.nextLine();
    }

    public static int chooseCardFromCollection(Collection collection)
    {
        System.out.println("Please select the pokemons numbers you want. (beetween 1 and " + collection.getAlCardsCollection().size() + ")");
        return keyboardChoice.nextInt();
    }

    public static String chooseCardsFromCollection()
    {
        System.out.println("Please select the pokemons you want. By their number and separated by a - (a dash).");
        System.out.println("Ex : 1-6-23-3-87-34...");
        System.out.println("NB: Only 60 ! No more, no less ;) ");
        return keyboardChoice.nextLine();
    }

    public static String getQueryFindChoice()
    {
        System.out.println("Please, enter the entity you want to search.");
        return keyboardChoice.nextLine();

    }

    public static String getQueryTypeChoice()
    {
        System.out.println("Please, enter the type you want to search for.");
        System.out.println("1) -> Name");
        System.out.println("2) -> Type of card (Trainer, Energy, Pokemon");
        int choice = keyboardChoice.nextInt();

        do
        {
            if (choice == 1)
            {
                return "NAME";
            } else
            {
                if (choice == 2)
                {
                    return "TYPE";
                } else System.out.println("Wrong choice");
            }

        }
        while (choice >= 1 && choice <= 2);
        return ",";
    }

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
            System.out.print("Ajouter attaque " + (i + 1) + " : ");
            attacks[i] = keyboardChoice.next();
            System.out.flush();
            i++;
            System.out.print("Ajouter une autre attaque ? (Y/N)");
            String stop = keyboardChoice.next();
            System.out.flush();
            if (stop.equals("N") || stop.equals("n")) stopped = true;
            System.out.println();
        }

        alData.add(attacks);

        System.out.print("Entez health points :");

        alData.add(keyboardChoice.nextInt());
        System.out.flush();

        return alData;
    }

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

    public void start()
    {
        ArrayList<String> names = ask_players_names();
        game.initialize(names);
        game.play();

    }

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
