package main.Entity;

import main.*;
import main.Biome.*;
import main.Item.*;

/**
 * The Pnj class is a subclass of Entity that represents a non-playable character (NPC)
 * in the game. It has a sprite, a name, and an array of strings (dialog) to represent the
 * NPC's dialogue.
 */
public class Pnj extends Entity {

	/**
	 * The array of strings (dialog) to represent the NPC's dialogue.
	 */
	private String[] dialog;

	//Cons
	/**
	 * Creates a new Pnj with the given sprite path, dialogue (dialog), and name.
	 * The sprite is scaled to fit the constant CASE_HEIGHT and CASE_WIDTH.
	 *
	 * @param spritePath the file path of the entity's sprite
	 * @param dialog      the array of strings (dialog) to represent the NPC's dialogue
	 * @param name        the name of the entity
	 * @param money the money of the pnj
	 */
	public Pnj(String spritePath, String[] dialog, String name, double money) {
		super(spritePath, name, money, 10);
		this.dialog = dialog;
	}

	//Meth
	/**
	 * Returns the array of strings (dialog) to represent the NPC's dialogue.
	 *
	 * @return the array of strings (dialog) to represent the NPC's dialogue
	 */
	public String[] getDialog() {
		return dialog;
	}

}