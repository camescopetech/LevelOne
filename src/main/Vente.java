package main;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
 * Represents a selling interaction between a player and a PNJ (non-player character).
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
     * Constructs a Vente with the specified stage, player, and PNJ.
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
     * Gets the PNJ the player is selling with.
     *
     * @return the PNJ
     */
    public Pnj getPnj() {
        return this.pnj;
    }

    /**
     * Sets the PNJ the player is selling with.
     *
     * @param pnj the PNJ
     */
    public void setPnj(Pnj pnj) {
        this.pnj = pnj;
    }

    /**
     * Checks if the sale interaction is closed.
     *
     * @return true if the sale interaction is closed, false otherwise
     */
    public boolean getIsClose() {
        return this.isClose;
    }

    /**
     * Sets whether the sale interaction is closed.
     *
     * @param b true to close the sale interaction, false to keep it open
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

    /**
     * Checks if the sale interaction is closed.
     *
     * @return true if the sale interaction is closed, false otherwise
     */
    public boolean getCloseVente() { return closeVente; }

    /**
     * Sets whether the sale interaction is closed.
     *
     * @param closeVente true to close the sale interaction, false to keep it open
     */
    public void setCloseVente(boolean closeVente) { this.closeVente = closeVente; }

    // Methods

    /**
     * Loads the player's item box with items that can be sold.
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

    /**
     * Loads the PNJ's item box with items that can be sold.
     */
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
     * Handles the close button action.
     */
    public void handleCloseBtn() {
        this.closeVente = true;  // Update the state to indicate that the sale is closed
        if (closeChangeListener != null) {
            closeChangeListener.accept(true);  // Notify that the sale is closed
        }
    }

    /**
     * Handles the buy button action.
     */
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
                    this.pnj.setMoney(this.pnj.getMoney() + selectedItem.getPrice());
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

    /**
     * Handles the sell button action.
     */
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
                this.player.sellObject(selectedItem);  // Perform the sale of the item
                this.pnj.getInventory().add(selectedItem);
                this.pnj.setMoney(this.pnj.getMoney() - selectedItem.getPrice());
                System.out.println(selectedItem.getPrice() + " nouveau money player : " + this.player.getMoney());
                this.scene.setRoot(this.loadVente());
                this.primaryStage.setScene(this.scene);
            }
        }
    }

    /**
     * Loads the sale UI components.
     *
     * @return the grid pane containing the sale UI
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
        Label inventoryPNJ = new Label("Inventaire du PNJ");
        VBox vBoxPnj = new VBox();
        this.loadItemBoxPnj();
        Label itemCoins = new Label("");
        this.itemsBoxPnj.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                // Find the corresponding Item in the inventory list based on the name
                Item selected = null;
                for (Item item : pnj.getInventory()) {
                    if (item.getName().equals(newValue)) {
                        selected = item;
                        break;
                    }
                }
                // Check if the Item was found
                if (selected != null) {
                    // Display the item's price in the console
                    System.out.println("Prix de l'item sélectionné (" + selected.getName() + ") : " + selected.getPrice());
                    itemCoins.setText("Prix de l'item sélectionné : " + selected.getPrice() + " coins");
                }
            }
        });

        vBoxPnj.getChildren().addAll(inventoryPNJ, this.itemsBoxPnj, itemCoins);
        gridPane.add(vBoxPnj, 0, 0);

        // Money Player
        Label moneyPlayer = new Label(this.player.getMoney() + " coins ");
        Label inventoryPlayer = new Label("Inventaire du joueur");
        VBox vBoxPlayer = new VBox();
        this.loadItemBoxPlayer();
        Label itemCoinsPlayer = new Label("");
        this.itemsBoxPlayer.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                // Find the corresponding Item in the inventory list based on the name
                Item selected = null;
                for (Item item : player.getInventory()) {
                    if (item.getName().equals(newValue)) {
                        selected = item;
                        break;
                    }
                }
                // Check if the Item was found
                if (selected != null) {
                    // Display the item's price in the console
                    System.out.println("Prix de l'item sélectionné (" + selected.getName() + ") : " + selected.getPrice());
                    itemCoinsPlayer.setText("Prix de l'item sélectionné : " + selected.getPrice() + " coins");
                }
            }
        });
        vBoxPlayer.getChildren().addAll(moneyPlayer, inventoryPlayer, this.itemsBoxPlayer, itemCoinsPlayer);

        gridPane.add(vBoxPlayer, 1, 1);

        // sell or buy
        Button buyBtn = new Button("Acheter");
        Button sellBtn = new Button("Vendre");
        VBox vBoxSell = new VBox();
        vBoxSell.getChildren().addAll(buyBtn, sellBtn);
        buyBtn.setDisable(this.itemsBoxPnj.getItems().isEmpty() || this.player.getMaxInventorySize() == this.player.getInventory().size());
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
