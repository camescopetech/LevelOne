package main;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * Utility class for displaying a custom popup window.
 */
public class CustomPopup {

    /**
     * Shows a custom popup window with the specified title, header, and content.
     *
     * @param title   the title of the popup window
     * @param header  the header text displayed in the popup window
     * @param content the content text displayed in the popup window
     */
    public static void showPopup(String title, String header, String content) {
        Stage popupWindow = new Stage();

        // Block interactions with the main window until this popup is closed
        popupWindow.initModality(Modality.APPLICATION_MODAL);
        popupWindow.initStyle(StageStyle.UTILITY);
        popupWindow.setTitle(title);

        // Header text
        Text headerText = new Text(header);
        headerText.setStyle("-fx-font-weight: bold; -fx-font-size: 16px;");

        // Content text
        Text contentText = new Text(content);

        // Close button
        Button closeButton = new Button("OK");
        closeButton.setOnAction(e -> popupWindow.close());

        // Popup layout
        VBox layout = new VBox(10);
        layout.getChildren().addAll(headerText, contentText, closeButton);
        layout.setAlignment(Pos.CENTER);

        // Create the scene and add it to the window
        Scene scene = new Scene(layout, 500, 300);
        popupWindow.setScene(scene);

        // Show the window and wait for it to be closed before returning to the main window
        popupWindow.showAndWait();
    }
}
