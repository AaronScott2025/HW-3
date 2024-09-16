package com.example.homework_3;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;

public class mainApp extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        //Mazes Loaded
        Image maze1 = new Image("GUIElements/maze.png"); //609 X 430
        ImageView view1 = new ImageView(maze1); view1.setFitWidth(609);view1.setFitHeight(430);
        Image maze2 = new Image("GUIElements/maze2.png"); //609 X 430
        ImageView view2 = new ImageView(maze2);view2.setFitWidth(609);view2.setFitHeight(430);
        //Icon Loaded
        Image car = new Image("GUIElements/car.png");
        Image robot = new Image("GUIElements/robot.png");
        //Create and Initialize TabPane
        TabPane tb = new TabPane();
        Tab tb1 = new Tab(); tb1.setText("Maze1");

    }

    public static void main(String[] args) {

        launch();
    }
}