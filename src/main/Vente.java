package main;

import javafx.scene.control.Alert;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
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
public class Vente {
    //VAR
    private Player player;
    private Pnj pnj;
    private Scene scene;
    private Stage primaryStage;

    private boolean isClose;
    private boolean closeVente;
    private Consumer<Boolean> closeChangeListener;

    //VarFX;
    private ComboBox<String> itemsBoxPlayer;
    private ComboBox<String> itemsBoxPnj;

    private Button btnUse;


    /**
     * Constructs a Vente with the specified stage, player, Pokémon, and duel coordinates.
     *
     * @param primaryStage the primary stage for displaying the sell
     * @param player the player participating in the selling
     * @param pnj the PNJ the player is selling with
     */
    public Vente(Stage primaryStage, Player player, Pnj pnj) {
        this.primaryStage = primaryStage;
        this.player = player;
        this.pnj = pnj;
        this.isClose = false;
        this.closeVente = false;

        this.btnUse = new Button("Fermer");
        this.btnUse.setOnAction(e -> {
            this.handleCloseBtn();
        });

        this.scene = new Scene(this.loadVente(), Constantes.STAGE_HEIGHT, Constantes.STAGE_WIDTH);
        this.primaryStage.setScene(this.scene);
    }

    // Getters and Setters

    /**
     * Gets the Pokémon the player is dueling against.
     *
     * @return the Pokémon
     */
    public Pnj getPnj() {
        return this.pnj;
    }

    /**
     * Sets the Pokémon the player is dueling against.
     *
     * @param pnj the Pokémon
     */
    public void setPnj(Pnj pnj) {
        this.pnj = pnj;
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
     * Sets the listener for when the close status changes.
     *
     * @param listener the listener to set
     */
    public void setCloseChangeListener(Consumer<Boolean> listener) {
        this.closeChangeListener = listener;
    }

    public boolean getCloseVente() { return closeVente; }

    public void setCloseVente(boolean closeVente) { this.closeVente = closeVente; }

    // Methods

    /**
     * Loads the item box with items usable in duels.
     */
    private void loadItemBoxPlayer() {
        ComboBox<String> comboBox = new ComboBox<>();

        if (!this.player.getInventory().isEmpty()) {
            for (Item item : this.player.getInventory()) {
                if (item.isSellable()) {
                    comboBox.getItems().add(item.getName());
                }
            }
        }

        this.itemsBoxPlayer = comboBox;
    }

    private void loadItemBoxPnj() {
        ComboBox<String> comboBox = new ComboBox<>();

        if (!this.pnj.getInventory().isEmpty()) {
            for (Item item : this.pnj.getInventory()) {
                if (item.isSellable()) {
                    comboBox.getItems().add(item.getName());
                }
            }
        }

        this.itemsBoxPnj = comboBox;
    }

    /**
     * Handles the use button action.
     */
    public void handleCloseBtn() {
        this.closeVente = true;  // Mettez à jour l'état pour indiquer que la vente est fermée
        if (closeChangeListener != null) {
            closeChangeListener.accept(true);  // Notifier que la vente est fermée
        }
    }

    public void handleBuyBtn() {
        String selectedItemName = this.itemsBoxPnj.getValue();
        if (selectedItemName != null) {
            Item selectedItem = null;
            for (Item item : this.pnj.getInventory()) {
                if (item.getName().equals(selectedItemName)) {
                    selectedItem = item;
                    break;
                }
            }
            if (selectedItem != null) {
                if (this.player.getMoney() > selectedItem.getPrice()) {
                    this.player.buyObject(selectedItem);
                    this.pnj.getInventory().remove(selectedItem);
                    this.pnj.setMoney(this.pnj.getMoney()+selectedItem.getPrice());
                    System.out.println(selectedItem.getPrice() + " nouveau money player : " + this.player.getMoney());
                    this.scene.setRoot(this.loadVente());
                    this.primaryStage.setScene(this.scene);
                } else {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Alerte de fonds insuffisants");
                    alert.setHeaderText(null);
                    alert.setContentText("Le joueur n'a pas assez de sous.");

                    alert.showAndWait();
                }
            }
        }
    }

    public void handleSellBtn() {
        String selectedItemName = this.itemsBoxPlayer.getValue();
        if (selectedItemName != null) {
            Item selectedItem = null;
            for (Item item : this.player.getInventory()) {
                if (item.getName().equals(selectedItemName)) {
                    selectedItem = item;
                    break;
                }
            }
            if (selectedItem != null) {
                this.player.sellObject(selectedItem);  // Effectue l'achat de l'objet
                this.pnj.getInventory().add(selectedItem);
                this.pnj.setMoney(this.pnj.getMoney()-selectedItem.getPrice());
                System.out.println(selectedItem.getPrice() + " nouveau money player : " + this.player.getMoney());
                this.scene.setRoot(this.loadVente());
                this.primaryStage.setScene(this.scene);
            }
        }
    }


    /**
     * Loads the duel UI components.
     *
     * @return the grid pane containing the duel UI
     */
    public GridPane loadVente() {
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
        ImageView img = new ImageView(new Image(this.pnj.getSpritePath()));
        img.setFitHeight(Constantes.STAGE_HEIGHT / 3);
        img.setFitWidth(Constantes.STAGE_WIDTH / 2);
        gridPane.add(img, 1, 0);

        // ImgPlayer
        ImageView imgPlayer = new ImageView(new Image(this.player.getDuelSpritePath()));
        imgPlayer.setFitHeight(Constantes.STAGE_HEIGHT / 3);
        imgPlayer.setFitWidth(Constantes.STAGE_WIDTH / 2);
        gridPane.add(imgPlayer, 0, 1);

        // Money PNJ
        Label moneyPnj = new Label(this.pnj.getMoney() + " coins");
        VBox vBoxPnj = new VBox();
        this.loadItemBoxPnj();
        vBoxPnj.getChildren().addAll(moneyPnj, this.itemsBoxPnj);
        gridPane.add(vBoxPnj, 0, 0);

        // Money Player
        Label moneyPlayer = new Label(this.player.getMoney() + " coins ");
        VBox vBoxPlayer = new VBox();
        this.loadItemBoxPlayer();
        vBoxPlayer.getChildren().addAll(moneyPlayer, this.itemsBoxPlayer);

        gridPane.add(vBoxPlayer, 1, 1);

        // sell or buy
        Button buyBtn = new Button("Acheter");
        Button sellBtn = new Button("Vendre");
        VBox vBoxSell = new VBox();
        vBoxSell.getChildren().addAll(buyBtn, sellBtn);
        buyBtn.setDisable(this.itemsBoxPnj.getItems().isEmpty());
        buyBtn.setOnAction(e -> {
            handleBuyBtn();
        });
        sellBtn.setDisable(this.itemsBoxPlayer.getItems().isEmpty());
        sellBtn.setOnAction(e -> {
            handleSellBtn();
        });
        gridPane.add(vBoxSell, 0, 2);

        // UseItem
        GridPane gridItem = new GridPane();
        for (int col = 0; col < 2; col++) {
            Rectangle border = new Rectangle(Constantes.STAGE_WIDTH / 4, Constantes.STAGE_HEIGHT / 3);
            border.setFill(null);
            border.setStroke(Color.BLACK);
            border.setStrokeWidth(1);

            gridItem.add(border, col, 0);
        }

        gridItem.add(this.btnUse, 1, 0);
        gridPane.add(gridItem, 1, 2);

        return gridPane;
    }
}
