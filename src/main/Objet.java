package main;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import main.Entity.Player;
import main.Item.Item;

import java.util.function.Consumer;

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

    public boolean getClose() { return close; }

    public void setClose(boolean close) { this.close = close; }

    public void setCloseChangeListener(Consumer<Boolean> listener) {
        this.closeChangeListener = listener;
    }
    public void setUseChangeListener(Consumer<Boolean> listener) {
        this.useChangeListener = listener;
    }

    public void handleCloseBtn() {
        this.close = true;
        if (closeChangeListener != null) {
            closeChangeListener.accept(true);  // Notifier que la vente est fermée
        }
    }


    public void handleUseBtn() {
        this.use = true;
        if (useChangeListener != null) {
            useChangeListener.accept(true);
        }
    }

    public VBox loadObjet() {
        VBox vBox = new VBox();

        // Image de l'objet
        ImageView img = new ImageView(new Image(this.item.getSpritePath()));
        img.setFitHeight(Constantes.STAGE_HEIGHT / 3);
        img.setFitWidth(Constantes.STAGE_WIDTH / 2);

        // Nom de l'objet en gras
        Label nameLabel = new Label(this.item.getName());
        nameLabel.setFont(Font.font("System", FontWeight.BOLD, 20));

        // Description de l'objet
        Label descriptionLabel = new Label(this.item.getDescription());
        descriptionLabel.setWrapText(true);

        Label nUseLabel = new Label("");
        if (this.item.getnUseRemain() > 1){
            nUseLabel.setText("Il reste " + this.item.getnUseRemain() + " utilisations");
        } else if (this.item.getnUseRemain() <= 1){
            nUseLabel.setText("Il reste " + this.item.getnUseRemain() + " utilisation");
        } else {
            nUseLabel.setText("L'objet s'utilise a l'infini.");
        }

        Label priceLabel = new Label("L'objet vaut " + this.item.getPrice() + " coins");


        // Bouton Fermer
        closeBtn.setOnAction(event -> {
            handleCloseBtn();
        });

        useBtn.setDisable(!this.item.isUseableInBiome());
        useBtn.setOnAction(event -> {
            handleUseBtn();
            this.scene.setRoot(this.loadObjet());
            this.primaryStage.setScene(this.scene);
        });

        // Ajouter les éléments au VBox
        vBox.getChildren().addAll(img, nameLabel, descriptionLabel, nUseLabel, priceLabel, closeBtn, useBtn);

        return vBox;
    }

}

