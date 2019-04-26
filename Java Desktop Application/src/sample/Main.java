package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("resources/sample.fxml"));
        primaryStage.setTitle("Travel Experts");
        //primaryStage.initStyle(StageStyle.UNDECORATED);
        Scene scene = new Scene(root, 1152, 648);
        scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();
        root.setStyle("-fx-text-fill:  #ffffff ;");


    }


    public static void main(String[] args) {
        launch(args);
    }
}
