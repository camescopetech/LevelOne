package main;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import main.Entity.Player;
import main.Item.Item;

import java.util.function.Consumer;

/**
 * Represents an object (item) in the game and provides the UI for interacting with it.
 */
public class Objet {
    private Item item;
    private Scene scene;
    private Stage primaryStage;
    private Player player;
    private Consumer<Boolean> closeChangeListener;
    private Consumer<Boolean> useChangeListener;
    private Button closeBtn;
    private Button useBtn;
    private boolean close;
    private boolean use;

    /**
     * Constructs an Objet with the specified stage, item, and player.
     *
     * @param primaryStage the primary stage for displaying the object
     * @param item         the item to be displayed
     * @param player       the player interacting with the item
     */
    public Objet(Stage primaryStage, Item item, Player player) {
        this.primaryStage = primaryStage;
        this.item = item;
        this.player = player;
        this.close = false;
        this.use = false;

        this.closeBtn = new Button("Fermer");
        this.useBtn = new Button("Utiliser");

        this.scene = new Scene(this.loadObjet(), Constantes.STAGE_HEIGHT, Constantes.STAGE_WIDTH);
        this.primaryStage.setScene(this.scene);
    }

    /**
     * Gets the close status of the object.
     *
     * @return true if the object interaction is closed, false otherwise
     */
    public boolean getClose() {
        return close;
    }

    /**
     * Sets the close status of the object.
     *
     * @param close true to close the object interaction, false to keep it open
     */
    public void setClose(boolean close) {
        this.close = close;
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
     * Sets the listener for when the use status changes.
     *
     * @param listener the listener to set
     */
    public void setUseChangeListener(Consumer<Boolean> listener) {
        this.useChangeListener = listener;
    }

    /**
     * Handles the action of the close button.
     */
    public void handleCloseBtn() {
        this.close = true;
        if (closeChangeListener != null) {
            closeChangeListener.accept(true);  // Notify that the interaction is closed
        }
    }

    /**
     * Handles the action of the use button.
     */
    public void handleUseBtn() {
        this.use = true;
        if (useChangeListener != null) {
            useChangeListener.accept(true);
        }
    }

    /**
     * Loads the UI components for the object.
     *
     * @return the VBox containing the object UI
     */
    public VBox loadObjet() {
        VBox vBox = new VBox();

        // Image of the item
        ImageView img = new ImageView(new Image(this.item.getSpritePath()));
        img.setFitHeight(Constantes.STAGE_HEIGHT / 3);
        img.setFitWidth(Constantes.STAGE_WIDTH / 2);

        // Name of the item in bold
        Label nameLabel = new Label(this.item.getName());
        nameLabel.setFont(Font.font("System", FontWeight.BOLD, 20));

        // Description of the item
        Label descriptionLabel = new Label(this.item.getDescription());
        descriptionLabel.setWrapText(true);

        // Remaining uses of the item
        Label nUseLabel = new Label("");
        if (this.item.getnUseRemain() > 1) {
            nUseLabel.setText("Il reste " + this.item.getnUseRemain() + " utilisations");
        } else if (this.item.getnUseRemain() <= 1) {
            nUseLabel.setText("Il reste " + this.item.getnUseRemain() + " utilisation");
        } else {
            nUseLabel.setText("L'objet s'utilise à l'infini.");
        }

        // Price of the item
        Label priceLabel = new Label("L'objet vaut " + this.item.getPrice() + " coins");

        // Close button action
        closeBtn.setOnAction(event -> handleCloseBtn());

        // Use button action
        useBtn.setDisable(!this.item.isUseableInBiome());
        useBtn.setOnAction(event -> {
            if (this.item.getName().equals(Constantes.ITEM_OVER.getName())) {
                handleUseBtn();
                this.primaryStage.setScene(endGameOver());
            } else if(this.item.getName().equals(Constantes.ITEM_1.getName())){

            }
            else {
                handleUseBtn();
                this.scene.setRoot(this.loadObjet());
                this.primaryStage.setScene(this.scene);
            }
        });

        // Add elements to the VBox
        vBox.getChildren().addAll(img, nameLabel, descriptionLabel, nUseLabel, priceLabel, closeBtn, useBtn);

        return vBox;
    }



    /**
     * Loads the game over scene.
     *
     * @return the scene indicating game over
     */
    public Scene endGameOver() {
        Label gameOverLabel = new Label("Game Over");
        gameOverLabel.setFont(new Font("Arial", 40));
        gameOverLabel.setTextFill(Color.RED);
        gameOverLabel.setAlignment(Pos.CENTER);

        Pane root = new Pane(gameOverLabel);
        gameOverLabel.setLayoutX((Constantes.STAGE_WIDTH - gameOverLabel.getWidth()) / 2);
        gameOverLabel.setLayoutY((Constantes.STAGE_HEIGHT - gameOverLabel.getHeight()) / 2);

        return new Scene(root, Constantes.STAGE_WIDTH, Constantes.STAGE_HEIGHT);
    }
}
