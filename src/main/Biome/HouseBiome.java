/**
 * The HouseBiome class is a concrete subclass of Biome that represents a house in the game.
 * It contains a specific arrangement of tiles, blocks, entities, and items.
 */
package main.Biome;

import main.*;
import main.Entity.*;

public class HouseBiome extends Biome {

	/**
	 * Constructs a new HouseBiome with a fixed size of 11x11 and an ID of 2.
	 */
	public HouseBiome() {
		super(Constantes.NUMBER_OF_ROW, Constantes.NUMBER_OF_COL, 2);
	}

	/**
	 * Initializes the HouseBiome by populating the tiles array with the appropriate tiles, blocks, entities, and items.
	 * The arrangement includes a room with walls and a door, two NPCs who give hints and items, and some lootable items.
	 * There is also a teleportation tile that leads to the previous biome.
	 */
	@Override
	public void initBiome() {
		int i = 0;
		int j = 0;
		for (i = 0; i < this.getWidth(); i++) {
			for (j = 0; j < this.getHeight(); j++) {
				this.setTile(i, j, new Tile());
				this.getTile(i, j).setBloc(Constantes.BLOC_CARPET.deepCopy());
			}
		}

		for (i = 1; i < 11; i++) {
			this.getTile(5, i).setBloc(Constantes.BLOC_PLANK.deepCopy());
		}
		for (i = 0; i < 11; i++) {
			this.getTile(i, 5).setBloc(Constantes.BLOC_PLANK.deepCopy());
		}
		this.getTile(5, 0).setBloc(Constantes.BLOC_DOOR.deepCopy());
		this.getTile(5, 0).setTpTile(0, 11, 6);

		// PNJ
		String[] dialog = { "Aide mon ami bloque dans la grotte... \nTu peux y acceder en sortant de la maison peut etre.." };
		this.getTile(1, 2).setPnj(new Pnj("main/img/vieux.png", dialog, "vieux", 0));

		String[] dialogNew = { "Bonne journee" };
		this.getTile(7, 6).setPnj(new Pnj("main/img/voleur.png", dialogNew, "voleur", 0));
		this.getTile(7, 6).getPnj().getInventory().add(Constantes.ITEM_X);

		// Loot
		this.getTile(8, 2).setItem(Constantes.ITEM_POTION.deepCopy());

		this.getTile(9, 3).setItem(Constantes.ITEM_SWIM.deepCopy());

		this.getTile(5, 3).setItem(Constantes.ITEM_OVER.deepCopy());

		this.getTile(6, 7).setPokemon(new Pokemon("main/img/magneton.png", "M", 0, 10, 10, "main/img/magneton.png"));

		this.getTile(4, 3).setPnj(new Pnj("main/img/marchand.png", dialogNew, "Marchand", 10));
		this.getTile(4, 3).getPnj().getInventory().add(Constantes.ITEM_POTION);

		this.getTile(2,3).setItem(Constantes.ITEM_BOMB_10.deepCopy());

		this.getTile(8,9).setItem(Constantes.ITEM_TELEPORTATION.deepCopy());
		this.getTile(9,10).setItem(Constantes.ITEM_MALUS_50.deepCopy());
	}
}
