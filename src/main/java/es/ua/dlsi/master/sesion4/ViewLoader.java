package es.ua.dlsi.master.sesion4;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.util.Pair;

import java.io.IOException;
import java.util.ResourceBundle;

/**
 * @author drizo
 */
public class ViewLoader {
    /**
     *
     * @param fxmlNameFile
     * @param resourceBundle May be null
     * @param <T> The class of the controller
     * @return
     * @throws IOException
     */
    public static final <T> Pair<T, Parent> loadView(String fxmlNameFile, ResourceBundle resourceBundle) throws IOException {
        FXMLLoader loader;
        if (resourceBundle == null) {
            loader = new FXMLLoader(ViewLoader.class.getResource(fxmlNameFile));
        } else {
            loader = new FXMLLoader(ViewLoader.class.getResource(fxmlNameFile), resourceBundle);
        }
        Parent sceneMain = loader.load();
        T controller = loader.<T>getController();
        if (controller == null) {
            throw new IOException("The fxml has not its controller property set");
        }
        return new Pair<>(controller, sceneMain);
    }
}
