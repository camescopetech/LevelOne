/**
 * The BossBiome class is a concrete subclass of Biome that represents the boss level in the game.
 * It contains a specific arrangement of tiles, blocks, entities, and items.
 */
package main.Biome;

import main.*;
import main.Entity.*;

public class BossBiome extends Biome {

	/**
	 * Constructs a new BossBiome with a fixed size of 11x11 and an ID of 1.
	 */
	public BossBiome() {
		super(11, 11, 1);
	}

	/**
	 * Initializes the BossBiome by populating the tiles array with the appropriate tiles, blocks, entities, and items.
	 * The arrangement includes a water path to the boss room, a friendly NPC who gives a hint, and the boss Pokemon itself.
	 * There is also a teleportation tile that leads to the previous biome.
	 */
	@Override
	public void initBiome() {
		int i, j;
		for (i = 0; i < this.getWidth(); i++) {
			for (j = 0; j < this.getHeight(); j++) {
				this.setTile(i, j, new Tile());
				this.getTile(i, j).setBloc(Constantes.BLOC_GRAYSTONE.deepCopy());
			}
		}

		// Enter
		this.getTile(5, 0).setBloc(Constantes.BLOC_NETHER.deepCopy());
		this.getTile(5, 0).setTpTile(0, 3, 2);

		this.getTile(1, 1).setBloc(Constantes.BLOC_WATER.deepCopy());
		this.getTile(1, 2).setBloc(Constantes.BLOC_WATER.deepCopy());
		this.getTile(1, 3).setBloc(Constantes.BLOC_WATER.deepCopy());
		this.getTile(2, 2).setBloc(Constantes.BLOC_WATER.deepCopy());

		this.getTile(8, 6).setBloc(Constantes.BLOC_WATER.deepCopy());
		this.getTile(9, 6).setBloc(Constantes.BLOC_WATER.deepCopy());
		this.getTile(9, 7).setBloc(Constantes.BLOC_WATER.deepCopy());

		this.getTile(2, 8).setBloc(Constantes.BLOC_WATER.deepCopy());

		// Papi
		String[] dialog = { "A l'aide ... Bat le dracofeu" };
		this.getTile(7, 3).setPnj(new Pnj("main/img/vieux.png", dialog, "vieux", 0));

		// Boss
		this.getTile(5, 7).setPokemon(new Pokemon("main/img/dracofeu2.png", "Dracofeu", 0, 10, 15, "main/img/dracofeu.png"));
		this.getTile(5, 7).getPokemon().getInventory().add(Constantes.ITEM_POTION.deepCopy());

		this.getTile(7, 5).setPokemon(new Pokemon("main/img/slowbro.png", "B", 0, 30, 5, "main/img/slowbro.png"));

		this.getTile(6, 9).setItem(Constantes.ITEM_X.deepCopy());
	}
}
