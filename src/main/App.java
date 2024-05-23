package main;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * This class is the starting point for the JavaFX application.
 * It initializes a new Game object when the application is launched.
 */
public class App extends Application {

    @Override
    /**
     * This method is called when the JavaFX application is launched.
     * It initializes a new Game object with the primary stage of the application.
     *
     * @param primaryStage The primary stage of the JavaFX application.
     */
    public void start(Stage primaryStage) {

        new Game(primaryStage);

    }

    /**
     * This is the main method for the JavaFX application.
     * It launches the application using the launch() method from the Application class.
     *
     * @param args The command-line arguments passed to the application (not used in this implementation).
     */
    public static void main(String[] args) {
        launch(args);
    }
}
