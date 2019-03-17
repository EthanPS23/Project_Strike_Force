package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("resources/sample.fxml"));
        primaryStage.setTitle("Travel Experts");
        //primaryStage.initStyle(StageStyle.UNDECORATED);
        Scene scene = new Scene(root, 1152, 648);
        /*scene.getStylesheets().add("styles/stylesheet.css");*/
        //scene.getStylesheets().add(getClass().getResource("resources/stylesheet.css").toExternalForm());
        //scene.getStylesheets().add(getClass().getClassLoader().getResource("css/style.css").toExternalForm());
        //getClass().getClassLoader().getResource("css/style.css").toExternalForm();
        scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();
        root.setStyle("-fx-text-fill:  #ffffff ;");


    }


    public static void main(String[] args) {
        launch(args);
    }
}
