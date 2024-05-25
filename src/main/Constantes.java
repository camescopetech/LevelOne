package main;
import main.Biome.*;
import main.Item.*;

import javafx.scene.input.KeyCode;

public class Constantes {
	public final static int NUMBER_OF_ROW = 11;
	public final static int NUMBER_OF_COL = 11;

	public final static int CASE_HEIGHT = 48;
	public final static int CASE_WIDTH = CASE_HEIGHT;
	
	public final static int STAGE_HEIGHT = NUMBER_OF_ROW * CASE_HEIGHT;
	public final static int STAGE_WIDTH = NUMBER_OF_COL * CASE_WIDTH;
	
	public final static KeyCode KEY_INVENTORY = KeyCode.I;
	public final static KeyCode KEY_MOVE_TOP = KeyCode.UP;
	public final static KeyCode KEY_MOVE_BOTTOM = KeyCode.DOWN;
	public final static KeyCode KEY_MOVE_LEFT = KeyCode.LEFT;
	public final static KeyCode KEY_MOVE_RIGHT = KeyCode.RIGHT;
	public final static KeyCode KEY_INTERACTION = KeyCode.F;
	
	public final static int DIRECTION_NORTH = 0;
	public final static int DIRECTION_EAST = 1;
	public final static int DIRECTION_SOUTH = 2;
	public final static int DIRECTION_WEST = 3;
	
	//main.Bloc
	public final static Bloc BLOC_DIRT = new Bloc(0,"main/img/dirt.jpg", false);
	public final static Bloc BLOC_ROUGE = new Bloc(1,"main/img/rouge.jpg", false);
	public final static Bloc BLOC_TREE_SOLID = new Bloc(2, "main/img/tree.jpg", true);
	public final static Bloc BLOC_GATE = new Bloc(3, "main/img/gate.jpg", true);
	public final static Bloc BLOC_STONE = new Bloc(4, "main/img/stone.jpg", false);
	public final static Bloc BLOC_NETHER = new Bloc(5, "main/img/nether.jpg", false);
	public final static Bloc BLOC_WATER = new Bloc(6, "main/img/water.jpg", false);
	public final static Bloc BLOC_PLANK = new Bloc(7, "main/img/plank.jpg", false);
	public final static Bloc BLOC_HOUSE = new Bloc(8, "main/img/house.jpg", false);
	public final static Bloc BLOC_CARPET = new Bloc(9, "main/img/carpet.jpg", false);
	public final static Bloc BLOC_DOOR = new Bloc(10, "main/img/door.jpg", false);
	public final static Bloc BLOC_GRAYSTONE = new Bloc(11, "main/img/graystone.jpg", false);
	public final static Bloc BLOC_TREE_NOTSOLID = new Bloc(12, "main/img/tree.jpg", false);


	//main.Item
	public final static Item ITEM_POKEBALL = new Item(0,"Pokeball", "main/img/pokeball.png",false, false, false, 0, "Pour faire joli :)");
	public final static Item ITEM_POTION = new Item(1,"Potion", "main/img/potion.png",true,1, true, true, 30, "Redonne 10 d'HP au joueur. Peut etre utilise 1 fois maximum.");
	public final static Item ITEM_KGATE = new Item(2,"Cle", "main/img/keyGate.png",false, false, false, 0, "Permet d'ouvrir le portail dans le village pour acceder a la grotte.");
	public final static Item ITEM_SWIM = new Item(3, "Swimmer", "main/img/swimmer.png", false, false, true, 10, "Permet de marcher sur l'eau sans se noyer.");
	public final static Item ITEM_X = new Item(4, "X", "main/img/toxic-orb.png", true, 1, false, false, 0, "Permet de battre le pokemon B directement.");
	public final static Item ITEM_OVER = new Item(5, "over", "main/img/flame.png", false, 1, true, false, 0, "Game over instantané");
	public final static Item ITEM_KTREE = new Item(6, "keyTree", "main/img/keyTree.png", false, false, false, 0, "Permet de marcher a travers les arbres, sinon vous serez bloque.");
	public final static Item ITEM_BOMB_10 = new Item(7, "bomb", "main/img/bomb.png", false, 1,true, false, 0, "Fais tout exploser dans un rayon de 9 cases. \nLe joueur et les pokemons impactes perdent 30HP, les items sont enleves \nmais les PNJ ne sont pas affectes.");
	public final static Item ITEM_TELEPORTATION = new Item(8, "teleportation", "main/img/teleportation.png", false, 15, true, true, 60, "Permet de se teleporter aleatoirement.");
	public final static Item ITEM_MALUS_50 = new Item(9, "malus50", "main/img/malus.png", true, 1, false, true, 60, "Votre attaque est reduite de 50%.");
	public final static Item ITEM_COIN_10 = new Item(10, "coins", "main/img/coins.png", false, 1, true, true, 10, "10 coins");

	//main.Biome
	public final static Biome BIOME_VILLAGE = new VillageBiome();
	public final static Biome BIOME_BOSS = new BossBiome();
	public final static Biome BIOME_HOUSE = new HouseBiome();
	
	
	//EndGame
	public final static int NO_WIN = 0;
	public final static int WIN_DRACO = 1;
	public final static int GAME_OVER = 2;
	public final static int GAME_WIN = 3;
}
