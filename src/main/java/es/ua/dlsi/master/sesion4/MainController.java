package es.ua.dlsi.master.sesion4;

import es.ua.dlsi.master.sesion4.persistence.User;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @autor drizo
 */
public class MainController implements Initializable {

    @FXML
    BorderPane mainBorderPane;

    @FXML
    Label labelTitle;

    @FXML
    Label labelVersion;

    @FXML
    Label labelIdioma;

    @FXML
    SplitPane splitPane;

    @FXML
    ListView<User> lvUsers;

    private Stage primaryStage;
    private Configuration configuration;
    private Model model;
    private GUIPreferences preferences;
    private static final String DIVIDER_POSITION_PREF = "dividerPosition";
    private static final String LABEL_i18n = "txt.stringLoadedFromConfig";
    private ResourceBundle resourceBundle;


    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        mainBorderPane.setStyle("-fx-background-color: #FFFFEF;"); // en lugar de poner el estilo en el CSS lo ponemos aquí
    }

    private void loadData() {
        // por simplicidad no hemos puesto aquí ningun binding
        lvUsers.getItems().addAll(model.getUsers().getUsers());
        Logger.getLogger(this.getClass().getName()).log(Level.INFO, "Loaded {0} users" , lvUsers.getItems().size());

    }

    public void configure(Configuration configuration, ResourceBundle resourceBundle, GUIPreferences guiPreferences) {
        this.resourceBundle = resourceBundle;
        this.configuration = configuration;
        String stringLoadedFromConfig = resourceBundle.getString(LABEL_i18n);
        labelVersion.setText(configuration.getVersion() + stringLoadedFromConfig);
        labelIdioma.setText(configuration.getLanguage());
        Logger.getLogger(this.getClass().getName()).log(Level.INFO, "Version {0} loaded from configuration file", configuration.getVersion());

        this.preferences = guiPreferences;

        String dividerPosStr = this.preferences.getProperty(DIVIDER_POSITION_PREF);
        if (dividerPosStr != null) {
            this.splitPane.setDividerPositions(Double.parseDouble(dividerPosStr));
        }

        this.splitPane.getDividers().get(0).positionProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                preferences.storeProperty(DIVIDER_POSITION_PREF, newValue.toString());
            }
        });

        model = configuration.getInjector().getInstance(Model.class);
        loadData();
    }
}
