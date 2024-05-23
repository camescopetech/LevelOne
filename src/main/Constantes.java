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
	public final static Bloc BLOC_TREE = new Bloc(2, "main/img/tree.jpg", true);
	public final static Bloc BLOC_GATE = new Bloc(3, "main/img/gate.jpg", true);
	public final static Bloc BLOC_STONE = new Bloc(4, "main/img/stone.jpg", false);
	public final static Bloc BLOC_NETHER = new Bloc(5, "main/img/nether.jpg", false);
	public final static Bloc BLOC_WATER = new Bloc(6, "main/img/water.jpg", false);
	public final static Bloc BLOC_PLANK = new Bloc(7, "main/img/plank.jpg", false);
	public final static Bloc BLOC_HOUSE = new Bloc(8, "main/img/house.jpg", false);
	public final static Bloc BLOC_CARPET = new Bloc(9, "main/img/carpet.jpg", false);
	public final static Bloc BLOC_DOOR = new Bloc(10, "main/img/door.jpg", false);
	public final static Bloc BLOC_GRAYSTONE = new Bloc(11, "main/img/graystone.jpg", false);
	
	//main.Item
	public final static Item ITEM_POKEBALL = new Item(0,"Pokeball", "main/img/pokeball.png",false, false, false, 0);
	public final static Item ITEM_POTION = new Item(1,"Potion", "main/img/potion.png",true,3, true, true, 30);
	public final static Item ITEM_KGATE = new Item(2,"Cle", "main/img/key.png",false, false, false, 0);
	public final static Item ITEM_SWIM = new Item(3, "Swimmer", "main/img/pokeball.png", false, false, true, 10);
	public final static Item ITEM_X = new Item(4, "X", "main/img/toxic-orb.png", true, 1, false, false, 0);
	public final static Item ITEM_OVER = new Item(5, "over", "main/img/flame.png", false, 1, true, false, 0);
	public final static Item ITEM_KTREE = new Item(6, "keyTree", "main/img/key.png", false, false, false, 0);
	public final static Item ITEM_BOMB = new Item(6, "bomb", "main/img/bomb.png", false, 1,true, false, 0);


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
