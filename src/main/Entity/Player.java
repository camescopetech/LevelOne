package main.Entity;

import main.*;
import main.Biome.*;
import main.Item.*;

/**
 * The Player class is a subclass of FightingEntity that represents the player character
 * in the game. It has a sprite, a name, an inventory of items, hit points (hp), a maximum
 * amount of hit points (hpMax), and an attack power (atk), as well as a direction, and
 * coordinates (posX, posY) to represent its position in the game world.
 */
public class Player extends FightingEntity {
	//Var
	/**
	 * The direction that the player is facing.
	 */
	private int direction;
	/**
	 * The x-coordinate of the player's position in the game world.
	 */
	private int posX;
	/**
	 * The y-coordinate of the player's position in the game world.
	 */
	private int posY;

	//Cons
	/**
	 * Creates a new Player with the default sprite path, name, hit points (hp), attack power (atk),
	 * and duel sprite path. The sprite is scaled to fit the constant CASE_HEIGHT and CASE_WIDTH.
	 * The inventory is initialized as an empty ArrayList.
	 */
	public Player() {
		super("main/img/player_south.png", "Sacha", 50, 50, 10, "main/img/duel_player.png");

	}

	//GetSet
	/**
	 * Returns the x-coordinate of the player's position in the game world.
	 *
	 * @return the x-coordinate of the player's position in the game world
	 */
	public int getPosX() {
		return posX;
	}

	/**
	 * Sets the x-coordinate of the player's position in the game world.
	 *
	 * @param posX the x-coordinate of the player's position in the game world
	 */
	public void setPosX(int posX) {
		this.posX = posX;
	}

	/**
	 * Returns the y-coordinate of the player's position in the game world.
	 *
	 * @return the y-coordinate of the player's position in the game world
	 */
	public int getPosY() {
		return posY;
	}

	/**
	 * Sets the y-coordinate of the player's position in the game world.
	 *
	 * @param posY the y-coordinate of the player's position in the game world
	 */
	public void setPosY(int posY) {
		this.posY = posY;
	}

	/**
	 * Returns the direction that the player is facing.
	 *
	 * @return the direction that the player is facing
	 */
	public int getDirection() {
		return this.direction;
	}

	//Meth
	/**
	 * Sets the direction that the player is facing and updates the sprite to match.
	 *
	 * @param i the direction that the player is facing, as an integer
	 *           representing one of the following constants:
	 *           Constantes.DIRECTION_NORTH, Constantes.DIRECTION_EAST,
	 *           Constantes.DIRECTION_SOUTH, Constantes.DIRECTION_WEST
	 */
	public void setDirection(int i) {

		this.direction = i;
		switch (i) {
			case Constantes.DIRECTION_NORTH:
				this.setSprite("main/img/player_north.png");
				break;
			case Constantes.DIRECTION_EAST:
				this.setSprite("main/img/player_east.png");
				break;
			case Constantes.DIRECTION_SOUTH:
				this.setSprite("main/img/player_south.png");
				break;
			case Constantes.DIRECTION_WEST:
				this.setSprite("main/img/player_west.png");
				break;
			default:
				break;
		}
	}

	//meth
	/**
	 * Returns a string representation of the player, including its coordinates (posX, posY)
	 * in the game world.
	 *
	 * @return a string representation of the player
	 */
	@Override
	public String toString() {
		return "X: " + this.getPosX() + ",Y :" + this.getPosY();
	}

}