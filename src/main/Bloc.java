package main;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * This class represents a block in the game.
 * Each block has a unique ID, a sprite, and a boolean indicating whether it is solid or not.
 */
public class Bloc {

	//Var
	/**
	 * A boolean indicating whether the block is solid or not.
	 */
	private boolean isSolid;
	/**
	 * The path to the block's sprite.
	 */
	private String spritePath;
	/**
	 * The block's sprite.
	 */
	private ImageView sprite;
	/**
	 * The block's unique ID.
	 */
	private int id;

	//Cons
	/**
	 * Creates a new block with a given ID, sprite path, and boolean indicating whether it is solid or not.
	 *
	 * @param id The block's unique ID.
	 * @param spritePath The path to the block's sprite.
	 * @param isSolid A boolean indicating whether the block is solid or not.
	 */
	public Bloc(int id, String spritePath, boolean isSolid) {
		this.isSolid = isSolid;
		this.id = id;
		this.spritePath = new String(spritePath);
		this.sprite = new ImageView(new Image (spritePath));
		this.sprite.setFitHeight(Constantes.CASE_HEIGHT);
		this.sprite.setFitWidth(Constantes.CASE_WIDTH);
	}

	/**
	 * Creates a new block with the same properties as an existing block.
	 *
	 * @param b The block to copy.
	 */
	public Bloc(Bloc b){
		this.isSolid = b.isSolid();
		this.id = b.getId();
		this.sprite = b.getSprite();
	}

	//GetSet
	/**
	 * Returns the boolean indicating whether the block is solid or not.
	 *
	 * @return The boolean indicating whether the block is solid or not.
	 */
	public boolean isSolid() {
		return isSolid;
	}

	/**
	 * Sets the boolean indicating whether the block is solid or not.
	 *
	 * @param isSolid The boolean indicating whether the block is solid or not.
	 */
	public void setSolid(boolean isSolid) {
		this.isSolid = isSolid;
	}

	/**
	 * Returns the block's unique ID.
	 *
	 * @return The block's unique ID.
	 */
	public int getId() {
		return id;
	}

	/**
	 * Returns the block's sprite.
	 *
	 * @return The block's sprite.
	 */
	public ImageView getSprite() {
		return sprite;
	}

	/**
	 * Sets the block's sprite.
	 *
	 * @param sprite The block's sprite.
	 */
	public void setSprite(ImageView sprite) {
		this.sprite = sprite;
	}

	//Methode
	/**
	 * Creates and returns a deep copy of the block.
	 *
	 * @return A deep copy of the block.
	 */
	public Bloc deepCopy() {
		return new Bloc(this.id, new String(this.spritePath), this.isSolid);
	}
}
