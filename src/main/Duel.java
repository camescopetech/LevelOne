package main;

import main.*;
import main.Biome.*;
import main.Item.*;
import main.Entity.*;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.util.List;
import java.util.function.Consumer;

/**
 * Represents a duel between a player and a Pokémon.
 */
public class Duel {
	//VAR
	private Player player;
	private Pokemon pokemon;
	private Scene scene;
	private Stage primaryStage;

	private boolean isClose;
	private boolean isPlayerWin;
	private Consumer<Boolean> closeChangeListener;

	//VarFX;
	private ComboBox<String> itemsBox;
	private Button btnUse;

	private int xDuel;
	private int yDuel;

	/**
	 * Constructs a Duel with the specified stage, player, Pokémon, and duel coordinates.
	 *
	 * @param primaryStage the primary stage for displaying the duel
	 * @param player the player participating in the duel
	 * @param pokemon the Pokémon the player is dueling against
	 * @param xDuel the x-coordinate of the duel
	 * @param yDuel the y-coordinate of the duel
	 */
	public Duel(Stage primaryStage, Player player, Pokemon pokemon, int xDuel, int yDuel) {
		this.primaryStage = primaryStage;
		this.player = player;
		this.pokemon = pokemon;
		this.isClose = false;
		this.xDuel = xDuel;
		this.yDuel = yDuel;

		this.btnUse = new Button("Utiliser");
		this.btnUse.setOnAction(e -> {
			this.handleUseBtn();
		});

		this.scene = new Scene(this.loadDuel(), Constantes.STAGE_HEIGHT, Constantes.STAGE_WIDTH);
		this.primaryStage.setScene(this.scene);
	}

	// Getters and Setters

	/**
	 * Checks if the player has won the duel.
	 *
	 * @return true if the player has won, false otherwise
	 */
	public boolean isPlayerWin() {
		return this.isPlayerWin;
	}

	/**
	 * Gets the Pokémon the player is dueling against.
	 *
	 * @return the Pokémon
	 */
	public Pokemon getPokemon() {
		return this.pokemon;
	}

	/**
	 * Sets the Pokémon the player is dueling against.
	 *
	 * @param p the Pokémon
	 */
	public void setPokemon(Pokemon p) {
		this.pokemon = p;
	}

	/**
	 * Checks if the duel is closed.
	 *
	 * @return true if the duel is closed, false otherwise
	 */
	public boolean getIsClose() {
		return this.isClose;
	}

	/**
	 * Sets whether the duel is closed.
	 *
	 * @param b true to close the duel, false to keep it open
	 */
	public void setIsClose(boolean b) {
		this.isClose = b;
		if (closeChangeListener != null) {
			closeChangeListener.accept(b);
		}
	}

	/**
	 * Gets the x-coordinate of the duel.
	 *
	 * @return the x-coordinate
	 */
	public int getxDuel() {
		return this.xDuel;
	}

	/**
	 * Sets the x-coordinate of the duel.
	 *
	 * @param xDuel the x-coordinate
	 */
	public void setxDuel(int xDuel) {
		this.xDuel = xDuel;
	}

	/**
	 * Gets the y-coordinate of the duel.
	 *
	 * @return the y-coordinate
	 */
	public int getyDuel() {
		return this.yDuel;
	}

	/**
	 * Sets the y-coordinate of the duel.
	 *
	 * @param yDuel the y-coordinate
	 */
	public void setyDuel(int yDuel) {
		this.yDuel = yDuel;
	}

	/**
	 * Sets the listener for when the close status changes.
	 *
	 * @param listener the listener to set
	 */
	public void setCloseChangeListener(Consumer<Boolean> listener) {
		this.closeChangeListener = listener;
	}

	// Methods

	/**
	 * Advances the duel to the next turn.
	 */
	public void nextTurn() {
		if (pokemon.isKo()) {
			System.out.println("Victoire");
			this.isPlayerWin = true;
			this.setIsClose(true);
		} else {
			this.pokemonTurn();

			if (this.player.isKo()) {
				System.out.println("Defaite");
				this.isPlayerWin = false;
				this.setIsClose(true);
			} else {
				System.out.println("\nTour joueur:");
				this.scene.setRoot(this.loadDuel());
				this.primaryStage.setScene(this.scene);
			}
		}
	}

	/**
	 * Executes the Pokémon's turn.
	 */
	private void pokemonTurn() {
		System.out.println("Tour adverse:");
		this.player.receiveDamage(pokemon.getAtk());

		List<Item> inventoryDuel = this.pokemon.getInventoryDuel();

		if (!inventoryDuel.isEmpty()) {
			pokemon.useObject(inventoryDuel.get(0).getName());
		}
	}

	/**
	 * Loads the item box with items usable in duels.
	 */
	private void loadItemBox() {
		ComboBox<String> comboBox = new ComboBox<>();

		if (this.player.getInventory().isEmpty()) {
			comboBox.getItems().add("vide");
		} else {
			for (Item item : this.player.getInventory()) {
				if (item.isUseableInDuel()) {
					comboBox.getItems().add(item.getName());
				}
			}
		}

		this.itemsBox = comboBox;
	}

	/**
	 * Handles the use button action.
	 */
	public void handleUseBtn() {
		if (this.itemsBox.getValue() != null) {
			if (!this.itemsBox.getValue().equals("vide")) {
				if (this.itemsBox.getValue().equals("X") && this.pokemon.getName().equals("B")) {
					this.pokemon.setHp(0);
					this.player.useObject(this.itemsBox.getValue().toString());
					System.out.println("Victoire");
					this.isPlayerWin = true;
					this.setIsClose(true);
				} else {
					this.player.useObject(this.itemsBox.getValue().toString());
					this.scene.setRoot(this.loadDuel());
					this.primaryStage.setScene(this.scene);
				}
			}
		}
	}

	/**
	 * Loads the duel UI components.
	 *
	 * @return the grid pane containing the duel UI
	 */
	public GridPane loadDuel() {
		GridPane gridPane = new GridPane();

		for (int row = 0; row < 3; row++) {
			for (int col = 0; col < 2; col++) {
				Rectangle border = new Rectangle(Constantes.STAGE_WIDTH / 2, Constantes.STAGE_HEIGHT / 3);
				border.setFill(null);
				border.setStroke(Color.BLACK);
				border.setStrokeWidth(1);

				gridPane.add(border, col, row);
			}
		}

		// ImgPokemon
		ImageView img = new ImageView(new Image(this.pokemon.getDuelSpritePath()));
		img.setFitHeight(Constantes.STAGE_HEIGHT / 3);
		img.setFitWidth(Constantes.STAGE_WIDTH / 2);
		gridPane.add(img, 1, 0);

		// ImgPlayer
		ImageView imgPlayer = new ImageView(new Image(this.player.getDuelSpritePath()));
		imgPlayer.setFitHeight(Constantes.STAGE_HEIGHT / 3);
		imgPlayer.setFitWidth(Constantes.STAGE_WIDTH / 2);
		gridPane.add(imgPlayer, 0, 1);

		// HpPokemon
		Label hpPoke = new Label(this.pokemon.getHp() + " / " + this.pokemon.getHpMax());
		gridPane.add(hpPoke, 0, 0);

		// HpPlayer
		Label hpPlayer = new Label(this.player.getHp() + " / " + this.player.getHpMax());
		gridPane.add(hpPlayer, 1, 1);

		// Atk
		Button buttonAtk = new Button("Attaque");
		buttonAtk.setOnAction(e -> {
			if (pokemon.getName().equals("B")){
				pokemon.setHp(0);
				this.nextTurn();
			} else {
				pokemon.receiveDamage(this.player.getAtk());
				this.nextTurn();
			}
		});
		gridPane.add(buttonAtk, 0, 2);

		// UseItem
		GridPane gridItem = new GridPane();
		for (int col = 0; col < 2; col++) {
			Rectangle border = new Rectangle(Constantes.STAGE_WIDTH / 4, Constantes.STAGE_HEIGHT / 3);
			border.setFill(null);
			border.setStroke(Color.BLACK);
			border.setStrokeWidth(1);

			gridItem.add(border, col, 0);
		}

		this.loadItemBox();
		gridItem.add(this.itemsBox, 0, 0);
		gridItem.add(this.btnUse, 1, 0);
		gridPane.add(gridItem, 1, 2);

		return gridPane;
	}
}
