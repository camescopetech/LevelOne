/**
 * The Biome class is an abstract class that represents a specific type of environment in the game.
 * It contains a 2D array of tiles, each of which can contain entities, items, and blocks.
 */
package main.Biome;

import javafx.scene.control.Label;
import main.*;
import main.Entity.*;
import main.Item.*;

public abstract class Biome {

	//Var
	/**
	 * The height of the biome, in tiles.
	 */
	private int height;

	/**
	 * The width of the biome, in tiles.
	 */
	private int width;

	/**
	 * The ID of the biome, used to differentiate between different types of biomes.
	 */
	private int id;

	/**
	 * The 2D array of tiles that make up the biome.
	 */
	private Tile[][] tiles;

	//Super
	/**
	 * Constructs a new Biome with the specified height, width, and ID.
	 * The tiles are initialized to an empty array, and the initBiome() method is called to populate it.
	 *
	 * @param height the height of the biome, in tiles
	 * @param width the width of the biome, in tiles
	 * @param id the ID of the biome
	 */
	public Biome (int height, int width, int id){
		this.height = height;
		this.width = width;
		this.id = id;
		this.tiles = new Tile[this.height][this.width];
		this.initBiome();
	}

	//GetSet
	/**
	 * Returns the tile at the specified (x, y) coordinates.
	 *
	 * @param i the x-coordinate of the tile
	 * @param j the y-coordinate of the tile
	 * @return the tile at the specified coordinates, or null if the coordinates are out of bounds
	 */
	public Tile getTile(int i, int j) {
		return this.tiles[i][j];
	}

	/**
	 * Sets the tile at the specified (x, y) coordinates to the specified tile.
	 *
	 * @param i the x-coordinate of the tile
	 * @param j the y-coordinate of the tile
	 * @param t the tile to set
	 */
	public void setTile(int i, int j, Tile t) {
		this.tiles[i][j] = t;
	}

	/**
	 * Returns the height of the biome, in tiles.
	 *
	 * @return the height of the biome
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * Returns the width of the biome, in tiles.
	 *
	 * @return the width of the biome
	 */
	public int getWidth() {
		return width;
	}

	//Meth
	/**
	 * Checks if a tile with the given coordinates exists in the biome.
	 *
	 * @param x the x-coordinate of the tile
	 * @param y the y-coordinate of the tile
	 * @return true if the tile exists, false otherwise
	 */
	public boolean isTileExist(int x, int y) {
		if(x < 0 || y < 0) {
			System.out.println("Bloque");
			return false;
		}
		if(x >= this.width || y >= this.height) {
			System.out.println("Bloque");
			return false;
		}
		return true;
	}

	/**
	 * Checks if a move from the current player position to the (x, y) coordinates is possible.
	 *
	 * @param x the x-coordinate of the destination tile
	 * @param y the y-coordinate of the destination tile
	 * @param p the current player
	 * @return true if the move is possible, false otherwise
	 */
	public boolean moveIsPossible(int x, int y, Player p) {
		if(!this.isTileExist(x, y)) {
			System.out.println("Bloque");
			return false;
		}
		if(this.getTile(x, y).getBloc().isSolid()) {
			if(this.getTile(x, y).getBloc().getId() == 3 && p.inventoryContainsItem(Constantes.ITEM_KEY.getId())) {
				this.getTile(x, y).setBloc(Constantes.BLOC_0.deepCopy());
			} else if (this.getTile(x,y).getBloc().getId() == 2 && p.inventoryContainsItem(Constantes.ITEM_TREE.getId())){
				this.getTile(x, y).setBloc(Constantes.BLOC_1.deepCopy());
			} else {
				System.out.println("Bloque");
				return false;
			}

		}
		if(this.getTile(x,y).getPnj() != null || this.getTile(x, y).getPokemon() != null || this.getTile(x, y).getItem() != null) {
			return false;
		}
		if(this.getTile(x, y).getBloc().getId() == Constantes.BLOC_6.getId() && !p.inventoryContainsItem(Constantes.ITEM_SWIM.getId())){
			System.out.println("noyé");
		}
		return true;
	}

	//Abstract
	/**
	 * Initializes the biome by populating the tiles array with the appropriate tiles, entities, and items.
	 */
	public abstract void initBiome();
}
