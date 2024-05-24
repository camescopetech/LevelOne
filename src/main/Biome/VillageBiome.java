/**
 * The VillageBiome class is a concrete subclass of Biome that represents a village in the game.
 * It contains a specific arrangement of tiles, blocks, entities, and items.
 */
package main.Biome;

import main.*;
import main.Entity.*;

public class VillageBiome extends Biome {

	/**
	 * Constructs a new VillageBiome with a fixed size of 22x22 and an ID of 0.
	 */
	public VillageBiome() {
		super(11 * 2, 11 * 2, 0);
	}

	/**
	 * Initializes the VillageBiome by populating the tiles array with the appropriate tiles, blocks, entities, and items.
	 * The arrangement includes a forest area, a water area, a path, a dock, a barrier, and a boss biome teleportation tile.
	 * There is also a friendly NPC who gives a hint and a Pokemon to catch, as well as some lootable items.
	 */
	@Override
	public void initBiome() {
		int i = 0;
		int j = 0;
		for (i = 0; i < this.getWidth(); i++) {
			for (j = 0; j < this.getHeight(); j++) {
				this.setTile(i, j, new Tile());
				this.getTile(i, j).setBloc(Constantes.BLOC_DIRT.deepCopy());
			}
		}

		// Forest
		for (i = 0; i < 4; i++) {
			for (j = 0; j < 2; j++) {
				this.getTile(j, i).setBloc(Constantes.BLOC_TREE_SOLID.deepCopy());
			}
		}
		for (i = 0; i < 4; i++) {
			for (j = 5; j < 7; j++) {
				this.getTile(j, i).setBloc(Constantes.BLOC_TREE_SOLID.deepCopy());
			}
		}
		for (i = 0; i < 2; i++) {
			for (j = 6; j < 22; j++) {
				this.getTile(j, i).setBloc(Constantes.BLOC_TREE_SOLID.deepCopy());
			}
		}
		this.getTile(1, 4).setBloc(Constantes.BLOC_TREE_SOLID.deepCopy());
		this.getTile(2, 4).setBloc(Constantes.BLOC_TREE_SOLID.deepCopy());
		this.getTile(2, 0).setBloc(Constantes.BLOC_TREE_SOLID.deepCopy());
		this.getTile(3, 0).setBloc(Constantes.BLOC_TREE_SOLID.deepCopy());
		this.getTile(4, 0).setBloc(Constantes.BLOC_TREE_SOLID.deepCopy());
		this.getTile(2, 1).setBloc(Constantes.BLOC_TREE_SOLID.deepCopy());
		this.getTile(4, 1).setBloc(Constantes.BLOC_TREE_SOLID.deepCopy());
		this.getTile(4, 4).setBloc(Constantes.BLOC_TREE_SOLID.deepCopy());
		this.getTile(5, 4).setBloc(Constantes.BLOC_TREE_SOLID.deepCopy());

		// Water
		for (i = 0; i < 6; i++) {
			this.getTile(i, 11).setBloc(Constantes.BLOC_WATER.deepCopy());
		}
		for (i = 0; i < 8; i++) {
			this.getTile(i, 12).setBloc(Constantes.BLOC_WATER.deepCopy());
		}
		for (i = 0; i < 9; i++) {
			this.getTile(i, 13).setBloc(Constantes.BLOC_WATER.deepCopy());
		}
		for (i = 0; i < 10; i++) {
			this.getTile(i, 14).setBloc(Constantes.BLOC_WATER.deepCopy());
		}
		for (i = 0; i < 10; i++) {
			this.getTile(i, 15).setBloc(Constantes.BLOC_WATER.deepCopy());
		}
		for (i = 0; i < 10; i++) {
			this.getTile(i, 16).setBloc(Constantes.BLOC_WATER.deepCopy());
		}
		for (i = 0; i < 10; i++) {
			this.getTile(i, 17).setBloc(Constantes.BLOC_WATER.deepCopy());
		}
		for (i = 0; i < 10; i++) {
			this.getTile(i, 18).setBloc(Constantes.BLOC_WATER.deepCopy());
		}
		for (i = 0; i < 10; i++) {
			this.getTile(i, 19).setBloc(Constantes.BLOC_WATER.deepCopy());
		}
		for (i = 0; i < 11; i++) {
			this.getTile(i, 20).setBloc(Constantes.BLOC_WATER.deepCopy());
		}
		for (i = 0; i < 13; i++) {
			this.getTile(i, 21).setBloc(Constantes.BLOC_WATER.deepCopy());
		}

		// Path
		for (i = 7; i < 17; i++) {
			this.getTile(11, i).setBloc(Constantes.BLOC_STONE.deepCopy());
		}
		this.getTile(10, 16).setBloc(Constantes.BLOC_STONE.deepCopy());
		for (i = 3; i < 12; i++) {
			this.getTile(i, 9).setBloc(Constantes.BLOC_STONE.deepCopy());
		}
		for (i = 5; i < 9; i++) {
			this.getTile(3, i).setBloc(Constantes.BLOC_STONE.deepCopy());
		}

		// Dock
		for (i = 4; i < 10; i++) {
			this.getTile(i, 16).setBloc(Constantes.BLOC_PLANK.deepCopy());
		}

		// Barrier
		this.getTile(3, 4).setBloc(Constantes.BLOC_GATE.deepCopy());

		// Boss Biome
		this.getTile(3, 0).setBloc(Constantes.BLOC_NETHER.deepCopy());
		this.getTile(3, 0).setTpTile(1, 5, 1);

		// PNJ
		String[] dialog = { "Pikachu a vole les cle du portail." };
		this.getTile(5, 5).setPnj(new Pnj("main/img/vieux.png", dialog, "vieux", 0));
		this.getTile(5, 5).getPnj().getInventory().add(Constantes.ITEM_POKEBALL.deepCopy());

		// Pokemon
		this.getTile(4, 16).setPokemon(new Pokemon("main/img/pikachu2.png", "Pikachu", 0, 30, 10, "main/img/pikachu.png"));
		this.getTile(4, 16).getPokemon().setReward(Constantes.ITEM_KGATE.deepCopy());
		this.getTile(6, 7).setPokemon(new Pokemon("main/img/pikachu.png", "M", 0, 10, 10, "main/img/pikachu.png"));

		// House
		this.getTile(11, 5).setBloc(Constantes.BLOC_HOUSE.deepCopy());
		this.getTile(11, 5).setTpTile(2, 5, 1);

		this.getTile(9, 7).setItem(Constantes.ITEM_KTREE.deepCopy());
	}
}
