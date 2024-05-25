package main;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class CustomPopup {

    public static void showPopup(String title, String header, String content) {
        Stage popupWindow = new Stage();

        // Bloquer les interactions avec la fenêtre principale jusqu'à ce que ce pop-up soit fermé
        popupWindow.initModality(Modality.APPLICATION_MODAL);
        popupWindow.initStyle(StageStyle.UTILITY);
        popupWindow.setTitle(title);

        // Texte du header
        Text headerText = new Text(header);
        headerText.setStyle("-fx-font-weight: bold; -fx-font-size: 16px;");

        // Texte du contenu
        Text contentText = new Text(content);

        // Bouton de fermeture
        Button closeButton = new Button("Fermer");
        closeButton.setOnAction(e -> popupWindow.close());

        // Layout du pop-up
        VBox layout = new VBox(10);
        layout.getChildren().addAll(headerText, contentText, closeButton);
        layout.setAlignment(Pos.CENTER);

        // Créer la scène et l'ajouter à la fenêtre
        Scene scene = new Scene(layout, 300, 200);
        popupWindow.setScene(scene);

        // Afficher la fenêtre et attendre qu'elle soit fermée avant de retourner à la fenêtre principale
        popupWindow.showAndWait();
    }
}
