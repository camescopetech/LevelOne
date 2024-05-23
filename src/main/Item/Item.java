package main.Item;

import main.*;
import main.Biome.*;
import main.Entity.*;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import main.Constantes;

/**
 * Represents an item with various properties and usage restrictions.
 */
public class Item {
	private String name;
	private String spritePath;
	private ImageView sprite;
	private int id;
	private boolean isUseableInDuel;
	private boolean isLimited;
	private int nUseRemain;
	private boolean isUseableInBiome;
	private boolean isSellable;
	private double price;


	/**
	 * Constructs an Item with the specified properties.
	 *
	 * @param id the unique identifier of the item
	 * @param name the name of the item
	 * @param spritePath the path to the sprite image
	 * @param isUsableInDuel whether the item is usable in duels
	 * @param isUsableInBiome whether the item is usable in biomes
	 */
	public Item(int id, String name, String spritePath, boolean isUsableInDuel, boolean isUsableInBiome, boolean isSellable, double price) {
		super();
		this.id = id;
		this.sprite = new ImageView(new Image(spritePath));
		this.spritePath = spritePath;
		this.name = name;
		this.sprite.setFitHeight(Constantes.CASE_HEIGHT);
		this.sprite.setFitWidth(Constantes.CASE_WIDTH);
		this.isUseableInDuel = isUsableInDuel;
		this.isUseableInBiome = isUsableInBiome;
		this.isSellable = isSellable;
		this.price = price;

		this.isLimited = false;
		this.nUseRemain = 1;
	}

	/**
	 * Constructs a limited-use Item with the specified properties.
	 *
	 * @param id the unique identifier of the item
	 * @param name the name of the item
	 * @param spritePath the path to the sprite image
	 * @param isUsableInDuel whether the item is usable in duels
	 * @param nUse the number of uses remaining
	 * @param isUseableInBiome whether the item is usable in biomes
	 */
	public Item(int id, String name, String spritePath, boolean isUsableInDuel, int nUse, boolean isUseableInBiome, boolean isSellable, double price) {
		super();
		this.id = id;
		this.sprite = new ImageView(new Image(spritePath));
		this.spritePath = spritePath;
		this.name = name;
		this.sprite.setFitHeight(Constantes.CASE_HEIGHT);
		this.sprite.setFitWidth(Constantes.CASE_WIDTH);
		this.isUseableInDuel = isUsableInDuel;
		this.isUseableInBiome = isUseableInBiome;
		this.isSellable = isSellable;
		this.price = price;

		this.isLimited = true;
		this.nUseRemain = nUse;
	}

	/**
	 * Constructs an Item with the specified properties, including usage limitations.
	 *
	 * @param id the unique identifier of the item
	 * @param name the name of the item
	 * @param spritePath the path to the sprite image
	 * @param isUsableInDuel whether the item is usable in duels
	 * @param isLimited whether the item has limited uses
	 * @param nUse the number of uses remaining
	 * @param isUseableInBiome whether the item is usable in biomes
	 */
	public Item(int id, String name, String spritePath, boolean isUsableInDuel, boolean isLimited, int nUse, boolean isUseableInBiome, boolean isSellable, double price) {
		super();
		this.id = id;
		this.sprite = new ImageView(new Image(spritePath));
		this.spritePath = spritePath;
		this.name = name;
		this.sprite.setFitHeight(Constantes.CASE_HEIGHT);
		this.sprite.setFitWidth(Constantes.CASE_WIDTH);
		this.isUseableInDuel = isUsableInDuel;
		this.isUseableInBiome = isUseableInBiome;
		this.isSellable = isSellable;
		this.price = price;

		this.isLimited = isLimited;
		this.nUseRemain = nUse;
	}

	/**
	 * Gets the name of the item.
	 *
	 * @return the name of the item
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name of the item.
	 *
	 * @param name the new name of the item
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the sprite path of the item.
	 *
	 * @return the sprite path of the item
	 */
	public String getSpritePath() {
		return spritePath;
	}

	/**
	 * Sets the sprite path of the item.
	 *
	 * @param spritePath the new sprite path of the item
	 */
	public void setSpritePath(String spritePath) {
		this.spritePath = spritePath;
	}

	/**
	 * Gets the sprite image view of the item.
	 *
	 * @return the sprite image view of the item
	 */
	public ImageView getSprite() {
		return sprite;
	}

	/**
	 * Sets the sprite image view of the item.
	 *
	 * @param sprite the new sprite image view of the item
	 */
	public void setSprite(ImageView sprite) {
		this.sprite = sprite;
	}

	/**
	 * Gets the unique identifier of the item.
	 *
	 * @return the unique identifier of the item
	 */
	public int getId() {
		return id;
	}

	/**
	 * Sets the unique identifier of the item.
	 *
	 * @param id the new unique identifier of the item
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Checks if the item is usable in duels.
	 *
	 * @return true if the item is usable in duels, false otherwise
	 */
	public boolean isUseableInDuel() {
		return isUseableInDuel;
	}

	/**
	 * Sets whether the item is usable in duels.
	 *
	 * @param isUseableInDuel true if the item is usable in duels, false otherwise
	 */
	public void setUseableInDuel(boolean isUseableInDuel) {
		this.isUseableInDuel = isUseableInDuel;
	}

	/**
	 * Checks if the item is usable in biomes.
	 *
	 * @return true if the item is usable in biomes, false otherwise
	 */
	public boolean isUseableInBiome() {
		return isUseableInBiome;
	}

	/**
	 * Sets whether the item is usable in biomes.
	 *
	 * @param isUseableInBiome true if the item is usable in biomes, false otherwise
	 */
	public void setUseableInBiome(boolean isUseableInBiome) {
		this.isUseableInBiome = isUseableInBiome;
	}

	/**
	 * Checks if the item has limited uses.
	 *
	 * @return true if the item has limited uses, false otherwise
	 */
	public boolean isLimited() {
		return isLimited;
	}

	/**
	 * Sets whether the item has limited uses.
	 *
	 * @param isLimited true if the item has limited uses, false otherwise
	 */
	public void setLimited(boolean isLimited) {
		this.isLimited = isLimited;
	}

	/**
	 * Gets the number of uses remaining for the item.
	 *
	 * @return the number of uses remaining for the item
	 */
	public int getnUseRemain() {
		return nUseRemain;
	}

	/**
	 * Sets the number of uses remaining for the item.
	 *
	 * @param nUseRemain the new number of uses remaining for the item
	 */
	public void setnUseRemain(int nUseRemain) {
		this.nUseRemain = nUseRemain;
	}

	public boolean isSellable() {
		return isSellable;
	}

	public void setSellable(boolean sellable) {
		isSellable = sellable;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	/**
	 * Creates a deep copy of the item.
	 *
	 * @return a new Item instance that is a deep copy of the current item
	 */
	public Item deepCopy() {
		return new Item(this.id, new String(this.name), new String(this.spritePath), this.isUseableInDuel, this.isLimited, this.nUseRemain, this.isUseableInBiome, this.isSellable, this.price);
	}
}
