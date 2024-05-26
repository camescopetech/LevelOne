package main;

import main.Biome.*;
import main.Item.*;

import javafx.scene.input.KeyCode;

/**
 * The {@code Constantes} class defines various constants used throughout the game.
 */
public class Constantes {
	/** The number of rows in the game grid. */
	public final static int NUMBER_OF_ROW = 11;

	/** The number of columns in the game grid. */
	public final static int NUMBER_OF_COL = 11;

	/** The height of each cell in the game grid. */
	public final static int CASE_HEIGHT = 48;

	/** The width of each cell in the game grid. */
	public final static int CASE_WIDTH = CASE_HEIGHT;

	/** The height of the game stage. */
	public final static int STAGE_HEIGHT = NUMBER_OF_ROW * CASE_HEIGHT;

	/** The width of the game stage. */
	public final static int STAGE_WIDTH = NUMBER_OF_COL * CASE_WIDTH;

	/** Key code for opening the inventory. */
	public final static KeyCode KEY_INVENTORY = KeyCode.I;

	/** Key code for moving up. */
	public final static KeyCode KEY_MOVE_TOP = KeyCode.UP;

	/** Key code for moving down. */
	public final static KeyCode KEY_MOVE_BOTTOM = KeyCode.DOWN;

	/** Key code for moving left. */
	public final static KeyCode KEY_MOVE_LEFT = KeyCode.LEFT;

	/** Key code for moving right. */
	public final static KeyCode KEY_MOVE_RIGHT = KeyCode.RIGHT;

	/** Key code for interacting. */
	public final static KeyCode KEY_INTERACTION = KeyCode.F;

	/** Direction constant for North. */
	public final static int DIRECTION_NORTH = 0;

	/** Direction constant for East. */
	public final static int DIRECTION_EAST = 1;

	/** Direction constant for South. */
	public final static int DIRECTION_SOUTH = 2;

	/** Direction constant for West. */
	public final static int DIRECTION_WEST = 3;

	// main.Bloc
	/** Block representing dirt. */
	public final static Bloc BLOC_DIRT = new Bloc(0,"main/img/dirt.jpg", false);

	/** Block representing a red block. */
	public final static Bloc BLOC_ROUGE = new Bloc(1,"main/img/rouge.jpg", false);

	/** Block representing a solid tree. */
	public final static Bloc BLOC_TREE_SOLID = new Bloc(2, "main/img/tree.jpg", true);

	/** Block representing a gate. */
	public final static Bloc BLOC_GATE = new Bloc(3, "main/img/gate.jpg", true);

	/** Block representing stone. */
	public final static Bloc BLOC_STONE = new Bloc(4, "main/img/stone.jpg", false);

	/** Block representing nether. */
	public final static Bloc BLOC_NETHER = new Bloc(5, "main/img/nether.jpg", false);

	/** Block representing water. */
	public final static Bloc BLOC_WATER = new Bloc(6, "main/img/water.jpg", false);

	/** Block representing a plank. */
	public final static Bloc BLOC_PLANK = new Bloc(7, "main/img/plank.jpg", false);

	/** Block representing a house. */
	public final static Bloc BLOC_HOUSE = new Bloc(8, "main/img/house.jpg", false);

	/** Block representing a carpet. */
	public final static Bloc BLOC_CARPET = new Bloc(9, "main/img/carpet.jpg", false);

	/** Block representing a door. */
	public final static Bloc BLOC_DOOR = new Bloc(10, "main/img/door.jpg", false);

	/** Block representing gray stone. */
	public final static Bloc BLOC_GRAYSTONE = new Bloc(11, "main/img/graystone.jpg", false);

	/** Block representing a non-solid tree. */
	public final static Bloc BLOC_TREE_NOTSOLID = new Bloc(12, "main/img/tree.jpg", false);

	// main.Item
	/** Item representing a Pokeball. */
	public final static Item ITEM_POKEBALL = new Item(0,"Pokeball", "main/img/pokeball.png",false, false, false, 0, "Pour faire joli :)");

	/** Item representing a Potion. */
	public final static Item ITEM_POTION = new Item(1,"Potion", "main/img/potion.png",true,1, true, true, 30, "Redonne 10 d'HP au joueur. Peut etre utilise 1 fois maximum.");

	/** Item representing a key for the gate. */
	public final static Item ITEM_KGATE = new Item(2,"Cle", "main/img/keyGate.png",false, false, false, 0, "Permet d'ouvrir le portail dans le village pour acceder a la grotte.");

	/** Item allowing swimming without drowning. */
	public final static Item ITEM_SWIM = new Item(3, "Swimmer", "main/img/swimmer.png", false, false, true, 10, "Permet de marcher sur l'eau sans se noyer.");

	/** Item for defeating Pokemon B directly. */
	public final static Item ITEM_X = new Item(4, "X", "main/img/toxic-orb.png", true, 1, false, false, 0, "Permet de battre le pokemon B directement.");

	/** Item causing instant game over. */
	public final static Item ITEM_OVER = new Item(5, "over", "main/img/flame.png", false, 1, true, false, 0, "Game over instantané");

	/** Item allowing passage through trees. */
	public final static Item ITEM_KTREE = new Item(6, "keyTree", "main/img/keyTree.png", false, false, false, 0, "Permet de marcher a travers les arbres, sinon vous serez bloque.");

	/** Item representing a bomb with a 9-cell radius effect. */
	public final static Item ITEM_BOMB_10 = new Item(7, "bomb", "main/img/bomb.png", false, 1,true, false, 0, "Fais tout exploser dans un rayon de 9 cases. \nLe joueur et les pokemons impactes perdent 30HP, les items sont enleves \nmais les PNJ ne sont pas affectes.");

	/** Item allowing random teleportation. */
	public final static Item ITEM_TELEPORTATION = new Item(8, "teleportation", "main/img/teleportation.png", false, 15, true, true, 60, "Permet de se teleporter aleatoirement.");

	/** Item reducing attack by 50%. */
	public final static Item ITEM_MALUS_50 = new Item(9, "malus50", "main/img/malus.png", true, 1, false, true, 60, "Votre attaque est reduite de 50%.");

	/** Item representing 10 coins. */
	public final static Item ITEM_COIN_10 = new Item(10, "coins", "main/img/coins.png", false, 1, true, true, 10, "10 coins");

	// main.Biome
	/** Biome representing a village. */
	public final static Biome BIOME_VILLAGE = new VillageBiome();

	/** Biome representing a boss area. */
	public final static Biome BIOME_BOSS = new BossBiome();

	/** Biome representing a house. */
	public final static Biome BIOME_HOUSE = new HouseBiome();

	// EndGame
	/** Constant representing no win. */
	public final static int NO_WIN = 0;

	/** Constant representing a win with Draco. */
	public final static int WIN_DRACO = 1;

	/** Constant representing game over. */
	public final static int GAME_OVER = 2;

	/** Constant representing game win. */
	public final static int GAME_WIN = 3;
}
