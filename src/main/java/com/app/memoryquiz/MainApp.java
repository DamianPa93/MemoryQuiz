package com.app.memoryquiz;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class MainApp extends Application {
    //NOT RESIZABLE OPTION . FLAT WINDOW
    /*private double xOffset = 0;
    private double yOffset = 0;*/

    @Override
    public void start(final Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/Scene.fxml"));
        /**/
        Scene scene = new Scene(root);
        stage.setTitle("Memory Quiz v.1");
        stage.setScene(scene);
        stage.show();
        //NOT RESIZABLE OPTION . FLAT WINDOW
        /*Parent root = FXMLLoader.load(getClass().getResource("/fxml/Scene.fxml"));
        
        Scene scene = new Scene(root, Color.TRANSPARENT);
        stage.initStyle(StageStyle.DECORATED.UNDECORATED);
        
        root.setOnMousePressed(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event){
                xOffset = event.getSceneX();
                yOffset = event.getSceneY();
            }
        });
        root.setOnMouseDragged(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event){
                stage.setX(event.getScreenX()-xOffset);
                stage.setY(event.getScreenY()-yOffset);
            }
        }); 
        stage.setTitle("Memory Quiz");
        stage.setScene(scene);
        stage.show(); */
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}
