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
