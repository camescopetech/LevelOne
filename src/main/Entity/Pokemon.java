package main.Entity;

import main.*;
import main.Biome.*;
import main.Item.*;

import java.util.List;

/**
 * The Pokemon class is a subclass of FightingEntity that represents a Pokemon
 * in the game. It has a sprite, a name, hit points (hp), attack power (atk), and a
 * sprite to be used in combat (duelSpritePath). It also has a reward (Item) that the
 * player can receive for defeating it in combat, and a list of rewards (List<Item>) that
 * the player can receive for capturing it.
 */
public class Pokemon extends FightingEntity {
	//Var
	/**
	 * The reward (Item) that the player can receive for defeating the Pokemon in combat.
	 */
	private Item reward;

	/**
	 * The list of rewards (List<Item>) that the player can receive for capturing the Pokemon.
	 */
	private List<Item> listReward;

	//Cons
	/**
	 * Creates a new Pokemon with the given sprite path, name, hit points (hp), attack power (atk),
	 * and sprite to be used in combat (duelSpritePath). The sprite is scaled to fit the constant
	 * CASE_HEIGHT and CASE_WIDTH.
	 *
	 * @param spritePath     the file path of the entity's sprite
	 * @param name           the name of the entity
	 * @param hp             the hit points (hp) of the Pokemon
	 * @param atk            the attack power (atk) of the Pokemon
	 * @param duelSpritePath the file path of the sprite to be used in combat for the Pokemon
	 */
	public Pokemon(String spritePath, String name, double money, double hp, double atk, String duelSpritePath) {
		super(spritePath, name, money, 10, hp, atk, duelSpritePath);

	}

	//GetSet
	/**
	 * Returns the reward (Item) that the player can receive for defeating the Pokemon in combat.
	 *
	 * @return the reward (Item) that the player can receive for defeating the Pokemon in combat
	 */
	public Item getReward() {
		return reward;
	}

	/**
	 * Sets the reward (Item) that the player can receive for defeating the Pokemon in combat.
	 *
	 * @param reward the reward (Item) that the player can receive for defeating the Pokemon in combat
	 */
	public void setReward(Item reward) {
		this.reward = reward;
	}

	/**
	 * Returns the list of rewards (List<Item>) that the player can receive for capturing the Pokemon.
	 *
	 * @return the list of rewards (List<Item>) that the player can receive for capturing the Pokemon
	 */
	public List<Item> getListReward() {
		return listReward;
	}

	/**
	 * Returns the item at the given index in the list of rewards (List<Item>) that the player can
	 * receive for capturing the Pokemon.
	 *
	 * @param i the index of the item in the list of rewards
	 * @return the item at the given index in the list of rewards
	 */
	public Item getListRewardElement(int i) {
		return this.listReward.get(i);
	}

	/**
	 * Sets the list of rewards (List<Item>) that the player can receive for capturing the Pokemon.
	 *
	 * @param inventory the list of rewards (List<Item>) that the player can receive for capturing the Pokemon
	 */
	public void setListReward(List<Item> inventory) {
		this.listReward = inventory;
	}

}
