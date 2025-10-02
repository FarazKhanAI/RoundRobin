

        /*## Java source: core model & algorithm

        ### `MainApp.java`
        ```java*/
package org.app.roundrobin;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.app.roundrobin.controller.MainController;


public class MainApp extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Round-Robin CPU Scheduling Simulator");
        MainController controller = new MainController();
        BorderPane root = controller.getView();
        Scene scene = new Scene(root, 1000, 600);
        scene.getStylesheets().add(getClass().getResource("/org/app/roundrobin/main.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
