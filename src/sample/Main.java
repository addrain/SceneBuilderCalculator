/*
    File:   Main.java
    Name:   Adrian Kwok
    Date:   8/21/2018
    Desc:   This file launches a Stage containing a scene layout of a Calculator (sample.fxml) built using Scene Builder.
            The Calculator can multiply, divide, add, and subtract doubles implemented in Controller.java.

            Other files required: Controller.java, sample.fxml
 */

package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
