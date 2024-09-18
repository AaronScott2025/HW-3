package com.example.homework_3;

import javafx.animation.PathTransition;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * TODO: Add Car Class/Functionality. Tab2 Functions (Buttons and actions).
 */
public class mainApp extends Application {
    //Icons Loaded
    Image carpic = new Image(getClass().getResourceAsStream("/assets/GUI/car.png"));
    ImageView car = new ImageView(carpic);
    Image robotpic = new Image(getClass().getResourceAsStream("/assets/GUI/robot.png"));
    ImageView robo = new ImageView(robotpic);
    Image robotpic2 = new Image(getClass().getResourceAsStream("/assets/GUI/robot.png"));
    ImageView robo2 = new ImageView(robotpic);

    //Buttons for Animation, Car, And Robot (Tab 1)
    Button animation = new Button();
    Button player = new Button();
    Button robot = new Button();

    //Buttons for Animation, Car, And Robot (Tab 2)
    Button animation2 = new Button();
    Button player2 = new Button();
    Button robot2 = new Button();

    //Groups for tabs
    Group g1 = new Group();
    Group g2 = new Group();

    @Override
    public void start(Stage stage) {
        //Mazes Loaded
        Image maze1 = new Image(getClass().getResourceAsStream("/assets/GUI/maze.png")); //609 X 430
        ImageView view1 = new ImageView(maze1); view1.setFitWidth(609);view1.setFitHeight(430);
        Image maze2 = new Image(getClass().getResourceAsStream("/assets/GUI/maze2.png")); //609 X 430
        ImageView view2 = new ImageView(maze2);view2.setFitWidth(609);view2.setFitHeight(430);

        //Buttons for Animation versus playing yourself
        animation.setText("Play Robot"); animation.setLayoutX(100);animation.setLayoutY(430);
        player.setText("Play Car"); player.setLayoutX(300);player.setLayoutY(430);
        robot.setText("Robot Auto"); robot.setLayoutX(500);robot.setLayoutY(430);
        animation2.setText("Play Robot"); animation2.setLayoutX(100);animation2.setLayoutY(430);
        player2.setText("Play Car"); player2.setLayoutX(300);player2.setLayoutY(430);
        robot2.setText("Robot Auto"); robot2.setLayoutX(500);robot2.setLayoutY(430);

        //Create and Initialize TabPane
        g1.getChildren().addAll(view1,player,animation,robot);
        g2.getChildren().addAll(view2,player2,animation2,robot2);
        TabPane tb = new TabPane();
        Tab tb1 = new Tab(); tb1.setText("Maze1"); tb1.setContent(g1);
        Tab tb2 = new Tab(); tb2.setText("Maze2"); tb2.setContent(g2);
        tb.getTabs().add(tb1);tb.getTabs().add(tb2);

        //Innit and show the scene
        Scene scene = new Scene(tb, 619, 485);
        stage.setScene(scene);
        stage.setTitle("Maze Project | Group 5 | Use 'W A S D' To move Up Left Down and Right Respectively");
        stage.show();

        /*
        Path for Automatic Path button on Tab1. Starts at (15,270). Ends at (590,260).
        Initialized here to not waste time on action (Button Press).
         */
        Path p1 = new Path(
                new MoveTo(15,270),
                new LineTo(50,270),
                new LineTo(50,160),
                new LineTo(275,160),
                new LineTo(275,105),
                new LineTo(335,105),
                new LineTo(335,330),
                new LineTo(395,330),
                new LineTo(395,220),
                new LineTo(505,220),
                new LineTo(505,110),
                new LineTo(565,110),
                new LineTo(565,260),
                new LineTo(590,260)
        );

        /*
        Automatic path button for Droid -
        Takes the Path, and runs the Android image through the maze. 15 seconds
         */
        robot.setOnAction(e-> {
            g1.getChildren().removeAll(robo,car,robo2); //Reset page
            g1.getChildren().add(robo2); //Adds robo2
            PathTransition doPath = new PathTransition();
            doPath.setDuration(Duration.seconds(15)); //15 sec animation
            doPath.setNode(robo2);
            doPath.setPath(p1); //Set Path
            doPath.play(); //Plays for 15 seconds
            });

        player.setOnAction(e->{
            g1.getChildren().removeAll(robo,car,robo2); //Reset page
            g1.getChildren().add(car); //Adds car
            //make car smaller preserve aspect ratio
            car.setFitHeight(25);
            car.setFitWidth(25);
            car.setX(15.0); //Starting X
            car.setY(260.0); //Starting Y

            final double[] nextover = new double[3];


            /*
            KeyListener for Tab1 Animation -
            Moves by 2 pixels each press to speed up process. Uses WASD
             */
            scene.setOnKeyPressed(event -> {
                switch (event.getCode()) {
                    case W:
                        nextover[0] = maze1.getPixelReader().getArgb((int) (car.getX()) + 14, (int) (car.getY() - 2)); //Addition to offset an error
                        if (nextover[0] >= -1) {
                            car.setY(car.getY() - 2);
                        }; // Move up
                        break;
                    case S:
                        nextover[0] = maze1.getPixelReader().getArgb((int) (car.getX()) + 16, (int) (car.getY() + 24)); //Addition to offset an error
                        if (nextover[0] >= -1) {
                            car.setY(car.getY() + 2);
                        }; // Move down
                        break;
                    case A:
                        nextover[0] = maze1.getPixelReader().getArgb((int) (car.getX()) + 5, (int) (car.getY()));//Addition to offset an error
                        if (nextover[0] >= -1) {
                            car.setX(car.getX() - 2);
                        };// Move left
                        break;
                    case D:
                        nextover[0] = maze1.getPixelReader().getArgb((int) (car.getX()) + 20, (int) (car.getY() + 24)); //Addition to offset an error
                        if (nextover[0] >= -1) {
                            car.setX(car.getX() + 2);
                        };// Move right
                        break;
                }
            });
        });
        /*
        Animation Action Button -
        Clears board, and sets starting point of robo2. Moves with WASD
         */
        animation.setOnAction(e-> {
            g1.getChildren().removeAll(robo,robo2,car); //Reset Page
            g1.getChildren().add(robo); //Adds robo
            robo.setX(15.0); //Starting X
            robo.setY(260.0); //Starting Y
            final double[] nextover = new double[3];
//            Rectangle rr = new Rectangle();            //VERIFIES LOCATION FOR ROBO. DO NOT USE!!!!
//            rr.setHeight(25);
//            rr.setWidth(25);
//            rr.setX(robo.getX());
//            rr.setY(robo.getY());
//            rr.setFill(Color.BLACK);
//            g1.getChildren().add(rr);

                /*
                KeyListener for Tab1 Animation -
                Moves by 2 pixels each press to speed up process. Uses WASD
                 */
                scene.setOnKeyPressed(event -> {
                    switch (event.getCode()) {
                        case W:
                            nextover[0] = maze1.getPixelReader().getArgb((int) (robo.getX()) + 14, (int) (robo.getY() - 2)); //Addition to offset an error
                            if (nextover[0] >= -1) {
                                robo.setY(robo.getY() - 2);
                            }; // Move up
                            break;
                        case S:
                            nextover[0] = maze1.getPixelReader().getArgb((int) (robo.getX()) + 16, (int) (robo.getY() + 24)); //Addition to offset an error
                            if (nextover[0] >= -1) {
                                robo.setY(robo.getY() + 2);
                            }; // Move down
                            break;
                        case A:
                            nextover[0] = maze1.getPixelReader().getArgb((int) (robo.getX()) + 5, (int) (robo.getY()));//Addition to offset an error
                            if (nextover[0] >= -1) {
                                robo.setX(robo.getX() - 2);
                            };// Move left
                            break;
                        case D:
                            nextover[0] = maze1.getPixelReader().getArgb((int) (robo.getX()) + 20, (int) (robo.getY() + 24)); //Addition to offset an error
                            if (nextover[0] >= -1) {
                                robo.setX(robo.getX() + 2);
                            };// Move right
                            break;
                    }
                });
        });

    }

    /*
    Launch the Program here.
     */
    public static void main(String[] args) {
        launch();
    }
}