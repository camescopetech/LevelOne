package main.Entity;

import main.*;
import main.Biome.*;
import main.Item.*;

import java.util.ArrayList;
import java.util.List;

/**
 * The FightingEntity class is a subclass of Entity that represents a character or an object
 * in the game that can engage in combat. It has a sprite, a name, an inventory of items,
 * as well as hit points (hp), a maximum amount of hit points (hpMax), and an attack power (atk).
 */
public class FightingEntity extends Entity {
	//Var
	/**
	 * The current amount of hit points (hp) of the FightingEntity.
	 */
	private double hp;
	/**
	 * The maximum amount of hit points (hpMax) of the FightingEntity.
	 */
	private double hpMax;
	/**
	 * The attack power (atk) of the FightingEntity.
	 */
	private double atk;
	/**
	 * The file path of the sprite to be used in combat for the FightingEntity.
	 */
	private String duelSpritePath;

	//Cons
	/**
	 * Creates a new FightingEntity with the given sprite path, name, hit points (hp),
	 * attack power (atk), and duel sprite path. The sprite is scaled to fit the constant
	 * CASE_HEIGHT and CASE_WIDTH. The inventory is initialized as an empty ArrayList.
	 *
	 * @param spritePath     the file path of the entity's sprite
	 * @param name           the name of the entity
	 * @param hp             the hit points (hp) of the FightingEntity
	 * @param atk            the attack power (atk) of the FightingEntity
	 * @param duelSpritePath the file path of the sprite to be used in combat for the FightingEntity
	 */
	public FightingEntity(String spritePath, String name, double money, int maxInventory, double hp, double atk, String duelSpritePath) {
		super(spritePath, name, money, maxInventory);
		this.hpMax = hp;
		this.hp = hp;
		this.atk = atk;
		this.setDuelSpritePath(duelSpritePath);
	}

	//GetSet
	/**
	 * Returns the current amount of hit points (hp) of the FightingEntity.
	 *
	 * @return the current amount of hit points (hp) of the FightingEntity
	 */
	public double getHp() {
		return hp;
	}

	/**
	 * Sets the current amount of hit points (hp) of the FightingEntity.
	 *
	 * @param hp the current amount of hit points (hp) of the FightingEntity
	 */
	public void setHp(double hp) {
		this.hp = hp;
	}

	/**
	 * Returns the maximum amount of hit points (hpMax) of the FightingEntity.
	 *
	 * @return the maximum amount of hit points (hpMax) of the FightingEntity
	 */
	public double getHpMax() {
		return hpMax;
	}

	/**
	 * Sets the maximum amount of hit points (hpMax) of the FightingEntity.
	 *
	 * @param hpMax the maximum amount of hit points (hpMax) of the FightingEntity
	 */
	public void setHpMax(double hpMax) {
		this.hpMax = hpMax;
	}

	/**
	 * Returns the attack power (atk) of the FightingEntity.
	 *
	 * @return the attack power (atk) of the FightingEntity
	 */

	public double getAtk() {
		return atk;
	}

	/**
	 * Sets the attack power (atk) of the FightingEntity.
	 *
	 * @param atk the attack power (atk) of the FightingEntity
	 */
	public void setAtk(double atk) {
		this.atk = atk;
	}

	/**
	 * Returns the file path of the sprite to be used in combat for the FightingEntity.
	 *
	 * @return the file path of the sprite to be used in combat for the FightingEntity
	 */
	public String getDuelSpritePath() {
		return duelSpritePath;
	}

	/**
	 * Sets the file path of the sprite to be used in combat for the FightingEntity.
	 *
	 * @param duelSpritePath the file path of the sprite to be used in combat for the FightingEntity
	 */
	public void setDuelSpritePath(String duelSpritePath) {
		this.duelSpritePath = duelSpritePath;
	}

	/**
	 * Inflicts the given amount of damage to the FightingEntity.
	 *
	 * @param damage the amount of damage to inflict on the FightingEntity
	 */
	public void receiveDamage(double damage) {
		System.out.println(this.getName() + " recoit " + damage + " d�gat");
		this.hp = this.hp - damage;
	}

	/**
	 * Heals the FightingEntity by the given amount, up to its maximum amount of hit points (hpMax).
	 *
	 * @param heal the amount of hit points (hp) to heal the FightingEntity
	 */
	public void healing(double heal) {
		double newHp = this.hp + heal;
		if (newHp > this.hpMax) {
			this.hp = hpMax;
		} else {
			this.hp = newHp;
		}
	}

//Meth
	/**
	 * Returns true if the FightingEntity's hit points (hp) are less than or equal to 0,
	 * and false otherwise.
	 *
	 * @return true if the FightingEntity's hit points (hp) are less than or equal to 0,
	 *         and false otherwise
	 */
	public boolean isKo() {
		return this.hp <= 0;
	}

	/**
	 * Returns a list of items in the FightingEntity's inventory that can be used in combat.
	 *
	 * @return a list of items in the FightingEntity's inventory that can be used in combat
	 */
	public List<Item> getInventoryDuel() {

		List<Item> inventoryDuel = new ArrayList<Item>();

		for (Item item : this.getInventory()) {
			if (item.isUseableInDuel()) {
				inventoryDuel.add(item);
			}
		}

		return inventoryDuel;
	}

//Object
	/**
	 * Uses the item with the given name from the FightingEntity's inventory.
	 * If the item is a potion, the FightingEntity's hit points (hp) are healed by 10.
	 *
	 * @param item the name of the item to use from the FightingEntity's inventory
	 */
	public void useObject(String item) {

		System.out.println(this.getName() + " utilise : " + item);
		this.itemDegradation(item);

		if (item.equals(Constantes.ITEM_POTION.getName())) {

			double currentHp = this.getHp();

			this.healing(10);
			System.out.println(currentHp + " -> " + this.getHp());
		}

	}

}