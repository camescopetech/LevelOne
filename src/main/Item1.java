package main;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import main.Biome.Biome;
import main.Entity.Player;
import main.Entity.Pnj;
import main.Item.Item;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class Item1 {
    // Variables
    private Player player;
    private List<Pnj> pnjs;
    private Pnj selectedPnj;
    private Scene scene;
    private Stage primaryStage;

    private boolean isClose;
    private boolean closeVente;
    private Consumer<Boolean> closeChangeListener;

    // JavaFX components
    private ComboBox<String> itemsBoxPnj;
    private ComboBox<String> pnjComboBox;

    private Button btnClose;
    private Label itemCoins;
    private VBox vBoxPnj;

    /**
     * Constructs an Item1 with the specified stage, player, and list of PNJs.
     *
     * @param primaryStage the primary stage for displaying the sell
     * @param player the player participating in the selling
     */
    public Item1(Stage primaryStage, Player player) {
        this.primaryStage = primaryStage;
        this.player = player;
        this.isClose = false;
        this.closeVente = false;

        this.btnClose = new Button("Fermer");
        this.btnClose.setOnAction(e -> {
            this.handleCloseBtn();
        });

        this.scene = new Scene(this.loadItem1(), Constantes.STAGE_HEIGHT, Constantes.STAGE_WIDTH);
        this.primaryStage.setScene(this.scene);
    }

    // Getters and Setters
    public boolean getIsClose() {
        return this.isClose;
    }

    public void setIsClose(boolean b) {
        this.isClose = b;
        if (closeChangeListener != null) {
            closeChangeListener.accept(b);
        }
    }

    public void setCloseChangeListener(Consumer<Boolean> listener) {
        this.closeChangeListener = listener;
    }

    public boolean getCloseVente() { return closeVente; }

    public void setCloseVente(boolean closeVente) { this.closeVente = closeVente; }

    // Methods

    private void handleCloseBtn() {
        this.closeVente = true;
        if (closeChangeListener != null) {
            closeChangeListener.accept(true);
        }
    }

    public Pnj[] checkPnjBiome(){
        List<Pnj> pnjList = new ArrayList<>();
        List<Biome> biomes = new ArrayList<>();
        biomes.add(Constantes.BIOME_BOSS);
        biomes.add(Constantes.BIOME_HOUSE);
        biomes.add(Constantes.BIOME_VILLAGE);
        for (Biome biome : biomes){
            for( int i = 0; i < biome.getWidth(); i++) {
                for(int j = 0; j < biome.getHeight(); j++) {
                    if(biome.getTile(i,j).getPnj() != null && player.hasMetPnj(biome.getTile(i,j).getPnj())){
                        pnjList.add(biome.getTile(i,j).getPnj());
                    }
                }
            }
        }
        return pnjList.toArray(new Pnj[0]);
    }

    private void loadItemBoxPnj() {
        ComboBox<String> comboBox = new ComboBox<>();

        if (this.selectedPnj != null && !this.selectedPnj.getInventory().isEmpty()) {
            for (Item item : this.selectedPnj.getInventory()) {
                if (item.isSellable()) {
                    comboBox.getItems().add(item.getName());
                }
            }
        }

        this.itemsBoxPnj = comboBox;
    }

    private void loadPnjComboBox() {
        ComboBox<String> comboBox = new ComboBox<>();
        String[] str = {"test"};
        Pnj [] pnjs = checkPnjBiome();
        for (Pnj pnj : pnjs) {
            comboBox.getItems().add(pnj.getName());
        }

        comboBox.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                for (Pnj pnj : pnjs) {
                    if (pnj.getName().equals(newValue)) {
                        selectedPnj = pnj;
                        break;
                    }
                }
                updatePnjInventory();
            }
        });

        this.pnjComboBox = comboBox;
    }

    private void updatePnjInventory() {
        this.vBoxPnj.getChildren().clear();
        if (this.selectedPnj != null) {
            String str = "";
            Label inventoryPNJ = new Label("");
            for (int i = 0; i < selectedPnj.getInventory().size(); i++) {
                str += selectedPnj.getInventoryElement(i).getName() +"\n";
            }
            this.vBoxPnj.getChildren().add(inventoryPNJ);
            this.loadItemBoxPnj();
            this.vBoxPnj.getChildren().addAll(this.itemsBoxPnj, this.itemCoins);
        }
    }

    public GridPane loadItem1() {
        GridPane gridPane = new GridPane();

        // Load PNJ ComboBox
        this.loadPnjComboBox();
        gridPane.add(new Label("Sélectionnez un PNJ:"), 0, 0);
        gridPane.add(this.pnjComboBox, 1, 0);

        // PNJ Inventory
        this.vBoxPnj = new VBox();
        this.itemCoins = new Label("");
        gridPane.add(this.vBoxPnj, 0, 1, 2, 1);

        // Close button
        VBox vBoxClose = new VBox();
        vBoxClose.getChildren().add(this.btnClose);
        gridPane.add(vBoxClose, 0, 2, 2, 1);

        return gridPane;
    }
}
