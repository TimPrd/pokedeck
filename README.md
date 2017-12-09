# - Pokedeck | Timothé PARDIEU -

This project is splited into two different projects:
1. The first part is designed to Pierre T.
2. Second part is designed to Philippe E.
___

## Project 1 - Pierre T. (Saving data)

First of all I invite You to take a look at the previous commit which contains all the others functions (implemented in CLI mode).

:warning: __Also, please note that the update function isn't actually implemented in the UI version but in the CLI version - it can be seen by searching  the tag "@Pierre T." in Collection.java__ :warning:

I recomend you to use the JAR file in the previous commit to view all the update function.

In fact in this new version the only remain functions which wasn't implemented was :
 * The saving function 
 * The loading function 
 * Using Gson dependency

All these functions are implemented. Please, take a look in :
- ActionsManager.java
- Collection.java
- CollectionTest.java 

(Use CTRL-F - find "//@Pierre T.") :+1:

:heavy_plus_sign: I also invite You to take a look in the Project 2 in order to see how the program can be used and also which functions are implemented
____



## Project 2 - Philippe E. (Building UI)


You can find here all the Pokedeck UI.

### Tips on the game
* *Collection* : All your cards.
* *Deck* : The hand of cards that can be played (limited to 60)

1. First, You will begin the game with a defined collection | deck
2. To **create** a new card :
	* CTRL+N or Menu>Collection>Créer>Pokemon
	* Fill up the fields
	* Click on "Ajouter!"
3. To **delete** a pokemon 
	* Menu>Collection>Supprimer
	* Select from the drop-down menu 
	* Hit "Supprimer !"
	
4. To **add** a pokemon in your **deck** 
	* Menu>Deck>Ajouter au deck 
	* Choose your card from the drop-down menu
	* Hit "Ajouter !"
	
5. To **display** your **collection**:
	* Menu>Collection>Montrer
	* You will see all the cards of your collection with all the detailed infos (in Table)
	
 		| Nom     | Type    | PV  | Attack 1      | Attack 2       | Attack 3  |
		| --------| --------|-----|:-------------:|:--------------:|:---------:|
        | Tortank | Water   | 100 |Hydrocanon [90]|Vibraqua [40]   |           |
        | Mew     | Psychic | 50  |Psyko [50]     |Aurasphère [100]|Relais [20]|
		|   ...   | ...     | ... |...            |    | |

	* You could also use the **search** function if you've got to many cards
		* At the bottom of the frame fill the TextField and select which kind of search you want to execute
		* Hit enter in the TextField
		* Example : "tank" - select "name" radio button will show the Tortank pokemon (can display several pokemon)
		
6. To **display** your **deck**
	* Just click on the PokemonCard's back image 
	* You can choose a pokemon to fight with 
		* Select it from the new window, it will show up in the main window
7. Do a **battle**
	* You need to select a pokemon from your deck at first
	* Select an attack 
	* Hit "Fight !"
	* At each lap, hit fight if you'd like to continue with the same attack or choose another one and hit again fight
	* if your pokemon is K.O. then select another one in the deck Table window
	* if the pokemon ennemy is K.O a new one will be draw randomly
	* if one of the player has no more cards it will print the winner
8. **Switching** player 
	* If you want to be your oppent then go to Menu>Joueur>Changer joueur



## What I have learned

First I learn to use a MVC design pattern. The code is splitted by View/Model and Controller. To late but I discovered a pattern named Observable which seems to be a good way of updating data from model/view.

I used this project to learn more about Java language. So I try to do my best to use new concept like:

- Lambda

Enables to passing method in arg / references

- Annotation
	 
I have used annotation to get dynamicaly the action. I've created a @Act which call the right method associated. 
```java 
	@Act("AddPkmn")
    public void createCard(ArrayList al) ...
```
- Stream 
I used it to do some query on my List object to create the "find" method 
``` java
 cards = alCardsCollection
       .stream()
       .filter(
       //getting the name of the card and looking if it contains the query
       p -> p.getCardTitle().toLowerCase().contains((search.toLowerCase())))
       .collect(Collectors.toList());
```

- Using GSON
 
I discover how to save/load data in JSON format using Google GSON

- Using ~API

It might be bad to use it, but if you enter a correct pokemon name (in french). The card will automatically fetch an image of the pokemon on the web. 

:heavy_plus_sign: We haven't to carry all the ~700 pokemons' images (heavy data)

:heavy_minus_sign: Might cause conflicts if we have a low connexion, not really 
maintainable (?)

_____
copyright © Timothé PARDIEU - DEC 2017 - JAVA , 
____



(OLDER README..)
# Pokédeck - Timothé PARDIEU et Valentin HERVOUET

Veuillez vous reférez au fichier PDF exceptionnellement (inclusion d'image oblige)

Jeu de carte Pokédeck dans le cadre du cours Java/UPMC

Instructions
============
# Add
To add a card just select 1 and enter the following card you desire. Next complete all informations

# Delete
To delete a card just enter the number associated to it (its id)

# Update
Same as delete just enter the id

# Find

First enter what you want to match like “Pikachu” or whatever. Then enter the category (Name or Type)
A new list will be shown.

# DECKS

You can add at any moment a card from your collection to your deck. To add it just hit A and then it’s all like add to collection. But it can be boring so you can use ‘ - ‘ to separate the cards and do only one operation. All the cards between each dashes will be added
You can also look at your deck so just enter X
Finally if you want to change from user, you can do it by hitting C .

============

Bonus

In case mvn doesn't work a JAR file is available in the root folder.
Use in a shell/cmd :
```
java -jar pokedeck.jar
```

In case you are a developper and want to modify our code, everything is explained under the pdf and in the code (comments)

Le jeu se compile avec la commande `mvn compile`.

Vous pouvez lancer une partie avec `mvn exec:java -Dexec.mainClass="upmc.pcg.Pokedeck"`

L'énoncé et la date de rendu sont indiqués sur le [site du cours](http://hyc.io/teaching/java.html).
