package main;

import main.*;
import main.Biome.*;
import main.Item.*;
import main.Entity.*;

/**
 * Represents a tile in the game world, which can contain various elements
 * such as a block, NPC, Pokémon, or item. A tile can also be a teleport tile.
 */
public class Tile {
	// Variables
	private Bloc bloc;
	private Pnj pnj;
	private Pokemon pokemon;
	private Item item;

	private boolean isTpTile;
	private int spawnX;
	private int spawnY;
	private int idTpBiome;

	/**
	 * Constructs a Tile with default values.
	 * By default, the tile is not a teleport tile.
	 */
	public Tile() {
		super();
		this.isTpTile = false;
	}

	// Getters and Setters

	/**
	 * Gets the block on this tile.
	 *
	 * @return the block on this tile
	 */
	public Bloc getBloc() {
		return bloc;
	}

	/**
	 * Sets the block on this tile.
	 *
	 * @param bloc the block to set
	 */
	public void setBloc(Bloc bloc) {
		this.bloc = bloc;
	}

	/**
	 * Gets the NPC on this tile.
	 *
	 * @return the NPC on this tile
	 */
	public Pnj getPnj() {
		return pnj;
	}

	/**
	 * Sets the NPC on this tile.
	 *
	 * @param pnj the NPC to set
	 */
	public void setPnj(Pnj pnj) {
		this.pnj = pnj;
	}

	/**
	 * Gets the Pokémon on this tile.
	 *
	 * @return the Pokémon on this tile
	 */
	public Pokemon getPokemon() {
		return pokemon;
	}

	/**
	 * Sets the Pokémon on this tile.
	 *
	 * @param pokemon the Pokémon to set
	 */
	public void setPokemon(Pokemon pokemon) {
		this.pokemon = pokemon;
	}

	/**
	 * Checks if this tile is a teleport tile.
	 *
	 * @return true if this tile is a teleport tile, false otherwise
	 */
	public boolean isTpTile() {
		return isTpTile;
	}

	/**
	 * Sets whether this tile is a teleport tile.
	 *
	 * @param isTpTile true to make this tile a teleport tile, false to make it a normal tile
	 */
	public void setTpTile(boolean isTpTile) {
		this.isTpTile = isTpTile;
	}

	/**
	 * Gets the X coordinate for teleportation.
	 *
	 * @return the X coordinate for teleportation
	 */
	public int getSpawnX() {
		return spawnX;
	}

	/**
	 * Sets the X coordinate for teleportation.
	 *
	 * @param spawnX the X coordinate for teleportation
	 */
	public void setSpawnX(int spawnX) {
		this.spawnX = spawnX;
	}

	/**
	 * Gets the Y coordinate for teleportation.
	 *
	 * @return the Y coordinate for teleportation
	 */
	public int getSpawnY() {
		return spawnY;
	}

	/**
	 * Sets the Y coordinate for teleportation.
	 *
	 * @param spawnY the Y coordinate for teleportation
	 */
	public void setSpawnY(int spawnY) {
		this.spawnY = spawnY;
	}

	/**
	 * Gets the ID of the biome to teleport to.
	 *
	 * @return the ID of the biome to teleport to
	 */
	public int getIdTpBiome() {
		return idTpBiome;
	}

	/**
	 * Sets the ID of the biome to teleport to.
	 *
	 * @param idTpBiome the ID of the biome to teleport to
	 */
	public void setIdTpBiome(int idTpBiome) {
		this.idTpBiome = idTpBiome;
	}

	/**
	 * Gets the item on this tile.
	 *
	 * @return the item on this tile
	 */
	public Item getItem() {
		return item;
	}

	/**
	 * Sets the item on this tile.
	 *
	 * @param item the item to set
	 */
	public void setItem(Item item) {
		this.item = item;
	}

	// Methods

	/**
	 * Sets this tile as a teleport tile with the specified biome ID and coordinates.
	 *
	 * @param idBiome the ID of the biome to teleport to
	 * @param x the X coordinate to teleport to
	 * @param y the Y coordinate to teleport to
	 */
	public void setTpTile(int idBiome, int x, int y) {
		this.idTpBiome = idBiome;
		this.spawnX = x;
		this.spawnY = y;
		this.isTpTile = true;
	}
}
