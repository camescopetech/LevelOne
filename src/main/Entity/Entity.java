package main.Entity;

import javafx.scene.control.Alert;
import main.*;
import main.Biome.*;
import main.Item.*;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.ArrayList;
import java.util.List;

/**
 * The Entity class is used to represent a character or an object in the game.
 * It has a sprite, a name, and an inventory of items.
 */
public class Entity {
	//Var
	/**
	 * The ImageView object that represents the entity's sprite.
	 */
	private ImageView sprite;
	/**
	 * The file path of the entity's sprite.
	 */
	private String spritePath;
	/**
	 * The list of items that the entity has in its inventory.
	 */
	private List<Item> inventory;
	/**
	 * The name of the entity.
	 */
	private String name;
	private double money;
	private int maxInventorySize;

	//Cons
	/**
	 * Creates a new Entity with the given sprite path and name.
	 * The sprite is scaled to fit the constant CASE_HEIGHT and CASE_WIDTH.
	 * The inventory is initialized as an empty ArrayList.
	 *
	 * @param spritePath the file path of the entity's sprite
	 * @param name       the name of the entity
	 * @param money the entity's money
	 * @param maxInventorySize max size of the entity's inventory
	 */
	public Entity(String spritePath, String name, double money, int maxInventorySize) {
		this.sprite = new ImageView(new Image(spritePath));
		this.setSpritePath(spritePath);
		this.sprite.setFitHeight(Constantes.CASE_HEIGHT);
		this.sprite.setFitWidth(Constantes.CASE_WIDTH);
		this.inventory = new ArrayList<Item>();
		this.name = name;
		this.money = money;
		this.maxInventorySize = maxInventorySize;
	}

	//GetSet
	/**
	 * Returns the ImageView object that represents the entity's sprite.
	 *
	 * @return the ImageView object that represents the entity's sprite
	 */
	public ImageView getSprite() {
		return sprite;
	}

	/**
	 * Sets the entity's sprite to the ImageView object created with the given sprite path.
	 * The sprite is scaled to fit the constant CASE_HEIGHT and CASE_WIDTH.
	 *
	 * @param spritePath the file path of the entity's sprite
	 */
	public void setSprite(String spritePath) {
		this.sprite = new ImageView(new Image(spritePath));
		this.setSpritePath(spritePath);
		this.sprite.setFitHeight(Constantes.CASE_HEIGHT);
		this.sprite.setFitWidth(Constantes.CASE_WIDTH);
	}

	/**
	 * Returns the list of items that the entity has in its inventory.
	 *
	 * @return the list of items that the entity has in its inventory
	 */
	public List<Item> getInventory() {
		return inventory;
	}

	/**
	 * Returns the item at the given index in the entity's inventory.
	 *
	 * @param i the index of the item in the entity's inventory
	 * @return the item at the given index in the entity's inventory
	 */
	public Item getInventoryElement(int i) {
		return this.inventory.get(i);
	}

	/**
	 * Sets the entity's inventory to the given list of items.
	 *
	 * @param inventory the list of items that the entity has in its inventory
	 */
	public void setInventory(List<Item> inventory) {
		this.inventory = inventory;
	}

	/**
	 * Returns the file path of the entity's sprite.

	 * @return the file path of the entity's sprite
	 */
	public String getSpritePath() {
		return spritePath;
	}

	/**
	 * Sets the file path of the entity's sprite.
	 *
	 * @param spritePath the file path of the entity's sprite
	 */
	public void setSpritePath(String spritePath) {
		this.spritePath = spritePath;
	}

	/**
	 * Returns the name of the entity.
	 *
	 * @return the name of the entity
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name of the entity.
	 *
	 * @param name the name of the entity
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Returns the money of the entity.
	 *
	 * @return the money of the entity
	 */
	public double getMoney() {
		return money;
	}

	/**
	 * Sets the money of the entity.
	 *
	 * @param money the money of the entity
	 */
	public void setMoney(double money) {
		this.money = money;
	}

	/**
	 * Returns the maximum number of items the inventory can hold.
	 *
	 * @return the maximum number of items the inventory can hold
	 */
	public int getMaxInventorySize() {
		return maxInventorySize;
	}

	/**
	 * Sets the maximum number of items the inventory can hold.
	 *
	 * @param maxInventorySize the maximum number of items the inventory can hold
	 */
	public void setMaxInventorySize(int maxInventorySize) {
		this.maxInventorySize = maxInventorySize;
	}

	//Meth
	/**
	 * Returns true if the entity's inventory contains an item with the given ID,
	 * and false otherwise.
	 *
	 * @param id the ID of the item to search for in the entity's inventory
	 * @return true if the entity's inventory contains an item with the given ID, and false otherwise
	 */
	public boolean inventoryContainsItem(int id) {
		for (Item item : this.getInventory()) {
			if (item.getId() == id) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Decreases the number of uses remaining for the item with the given name in the entity's inventory,
	 * and removes the item from the inventory if the number of uses remaining is less than 1.
	 * If the item is not limited, a message is printed to the console indicating that the item has
	 * an infinite number of uses.
	 *
	 * @param name the name of the item to degrade in the entity's inventory
	 */
	public void itemDegradation(String name) {

		int i = 0;
		while (i < this.getInventory().size()) {
			System.out.println(i);
			if (this.getInventory().get(i).getName().equals(name)) {
				break;
			}
			i++;
		}

		if (this.getInventory().get(i).isLimited()) {
			this.getInventory().get(i).setnUseRemain(this.getInventory().get(i).getnUseRemain() - 1);
			System.out.println(name + " a encore " + this.getInventory().get(i).getnUseRemain() + " utilisation");

			if (this.getInventory().get(i).getnUseRemain() < 1) {
				this.getInventory().remove(i);
			}

		} else {
			System.out.println("L'utilisation de " + name + " est infini");
		}

	}

	/**
	 * Attempts to add the given item to the entity's inventory.
	 * If the inventory is full, a message is printed and the item is not added.
	 *
	 * @param item the item to add to the inventory
	 */
	public void buyObject(Item item) {
		if (this.inventory.size() < this.maxInventorySize) {
			if (this.money >= item.getPrice()) {
				this.money -= item.getPrice();
				this.inventory.add(item);
			} else {
				Alert alert = new Alert(Alert.AlertType.WARNING);
				alert.setTitle("Fond insuffisant");
				alert.setHeaderText(null);
				alert.setContentText("Fond insuffisant");
				alert.showAndWait();
			}
		} else {
			Alert alert = new Alert(Alert.AlertType.WARNING);
			alert.setTitle("Inventaire plein");
			alert.setHeaderText(null);
			alert.setContentText("Votre inventaire est plein");
			alert.showAndWait();
		}
	}

	/**
	 * Sells an item from the inventory and adds its price to the entity's money.
	 *
	 * @param item the item to sell
	 */
	public void sellObject(Item item) {
		if (this.getInventory().contains(item)) {
			this.money += item.getPrice();
			this.getInventory().remove(item);
		} else {
			Alert alert = new Alert(Alert.AlertType.WARNING);
			alert.setTitle("Objet non présent dans l'inventaire");
			alert.setHeaderText(null);
			alert.setContentText("Objet non présent dans l'inventaire");
			alert.showAndWait();
		}
	}

}