package es.ua.dlsi.master.sesion4;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Pair;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EjemploSesion4 extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        GUIPreferences guiPreferences = new GUIPreferences();
        Configuration configuration = null;
        try {
            configuration = loadConfiguration();
        } catch (Sesion4Exception e) {
            new DialogoError().show("Ejemplo sesión 4", "No se ha podido cargar la configuración", e);
            return;
        }

        // languages
        Locale localeForLanguage = Locale.forLanguageTag(configuration.getLanguage());
        Locale.setDefault(localeForLanguage);
        Logger.getLogger(this.getClass().getName()).log(Level.INFO, "Locale {0}", localeForLanguage);
        ResourceBundle resourceBundle = ResourceBundle.getBundle("i18n/messages"); // el sufijo lo cargará java

        // load main controller
        Logger.getLogger(this.getClass().getName()).log(Level.INFO, "Configuration loaded, main application loading");
        Pair<MainController, Parent> mainControllerParentPair = ViewLoader.loadView("/main.fxml", resourceBundle);
        mainControllerParentPair.getKey().setPrimaryStage(primaryStage);
        mainControllerParentPair.getKey().configure(configuration, resourceBundle, guiPreferences);

        Scene scene = new Scene(mainControllerParentPair.getValue());
        scene.getStylesheets().add("/main.css");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private Configuration loadConfiguration() throws Sesion4Exception {
        Configuration configuration = new Configuration();
        return configuration;
    }
}
