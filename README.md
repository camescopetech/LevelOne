# LvlOne

LvlOne is a game lossely based on Pokemon. You can fight Pokemon, interact with PNJ and have items. 

# How to launch
```java --module-path "{PATH_TO_YOUR_JAVAFX_LIB}" --add-modules javafx.controls,javafx.fxml -jar LvlOne.jar```
You need to be in the folder where your .jar is.

# What can you find ?
## Player
The player move with the arrow on your keyboard : up, down, left and right. 

A player can interact with PNJ, get items in the world, fight with pokemons and explore multiple worlds. 

The player have an HP of 50. The player can loose HP during fights or or using items, but can also gain HP by using Potion.

In case of a game over because the player doesn't have any more HP, the player is spawned at the beginning of the game with his HP set to max.

## Inventory
To access the player's inventory you must press I, to close it you have to press F too.

An item can be used in the world by clicking on it. Some items are not usable in the world, therefore clicking on them won't do anything.

## Items
Items are distributed in the different worlds and can be collected by the player. They appear in his inventory. 
Items can be usable in the world, be sold or used during fight. Some items are one thing and not the other. 
All Items have a price and can be sold/bought with a merchant.

### Items found in the worlds
- Pokeball : just a pokeball. It doesn't do anything. This item is not usable in duel, biome neither can be sold.
- Potion : gives the player 10 points of HP. This item is usable in dual, not usable in biome and can be sold for 30 coins.
- Gate Key : lets the player go through the gate in the Village (2nd world), if the player doesn't have it he's blocked. This item is not usable in duel or in the biome and can't be sold. 
- Swimmer : lets the player walk on the water, if the player doesn't have it the game is instantly over. The item is not usable in duel or in the biome but can be sold for 10 coins.
- X : kills the pokemon B in the Boss (3rd world) instantly if used. This item can be used in duel, can't be used in the biome and can't be sold.
- Over : game over instantly. This item can't be used in duel neither can be sold, but can be used in the biome.
- Tree Key : lets the player walk through trees. This item can't be used in duel and in the biome and can't be sold.
- Bomb 10 : burn everything except doors/gate/.. in a 3 cases radius around the player. This item can't be used in duel but can be used in the biome and can't be sold.

## PNJ
PNJ can "talk" to the player with a dialog. You can interact with them by clicking on F when you are next to them. You can go through them.
PNJ





