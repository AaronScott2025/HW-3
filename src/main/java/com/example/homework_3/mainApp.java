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
import javafx.scene.paint.Color;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class mainApp extends Application {
    //Icons Loaded
    Image robotpic = new Image(getClass().getResourceAsStream("/assets/GUI/robot.png"));
    ImageView robo = new ImageView(robotpic);
    Image robotpic2 = new Image(getClass().getResourceAsStream("/assets/GUI/robot.png"));
    ImageView robo2 = new ImageView(robotpic);
    Rectangle r = new Rectangle();

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

    // Declare the Car variable
    Car car = new Car();
    Car car2 = new Car();

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
            g1.getChildren().removeAll(robo,robo2,car); //Reset page
            g1.getChildren().add(robo2); //Adds robo2
            PathTransition doPath = new PathTransition();
            doPath.setDuration(Duration.seconds(15)); //15 sec animation
            doPath.setNode(robo2);
            doPath.setPath(p1); //Set Path
            doPath.play(); //Plays for 15 seconds
        });

        /*
        Car Action Button -
        Clears board, and sets starting point of the Car Object from the Car Class. Moves with WASD
         */
        player.setOnAction(e->{
            g1.getChildren().removeAll(robo,robo2,car,r); //Reset page
            g1.getChildren().addAll(car,r); //Adds car and hitbox
            car.setScaleX(0.4);
            car.setScaleY(0.4);
            car.setPosition(12.0, 250.0); //Starting position
            r.setFill(Color.TRANSPARENT);
            r.setHeight(15);
            r.setWidth(20);
            r.setLayoutX(car.getLayoutX()+18);
            r.setLayoutY(car.getLayoutY()+18); //Starting position

            final double[] nextover = new double[3];

            /*
            KeyListener for Tab1 Animation -
            Moves by 2 pixels each press to speed up process. Uses WASD
             */
            scene.setOnKeyPressed(event -> {
                switch (event.getCode()) {
                    case W:
                        r.setLayoutX(car.getLayoutX()+14);
                        r.setLayoutY(car.getLayoutY()+18); //Starting position
                        nextover[0] = maze1.getPixelReader().getArgb((int) (r.getLayoutX()) + 12, (int) (r.getLayoutY()+10)); //Addition to offset an error
                        if (nextover[0] >= -1) {
                            car.setLayoutY(car.getLayoutY() - 2);
                            car.setRotation(-90);
                            // Face up
                        }; // Move up
                        break;
                    case S:
                        r.setLayoutX(car.getLayoutX()+14);
                        r.setLayoutY(car.getLayoutY()+18); //Starting position
                        nextover[0] = maze1.getPixelReader().getArgb((int) (r.getLayoutX()) + 12, (int) (r.getLayoutY()+14)); //Addition to offset an error
                        if (nextover[0] >= -1) {
                            car.setLayoutY(car.getLayoutY() + 2);
                            car.setRotation(90); // Face down
                        }; // Move down
                        break;
                    case A:
                        r.setLayoutX(car.getLayoutX()+14);
                        r.setLayoutY(car.getLayoutY()+18); //Starting position
                        nextover[0] = maze1.getPixelReader().getArgb((int) (r.getLayoutX()) + 10, (int) (r.getLayoutY()+12));//Addition to offset an error
                        if (nextover[0] >= -1) {
                            car.setLayoutX(car.getLayoutX() - 2);
                            car.setRotation(0);
                            car.invertHorizontal(); // Face left
                        };// Move left
                        break;
                    case D:
                        r.setLayoutX(car.getLayoutX()+14);
                        r.setLayoutY(car.getLayoutY()+18); //Starting position
                        nextover[0] = maze1.getPixelReader().getArgb((int) (r.getLayoutX()) + 14, (int) (r.getLayoutY()+12)); //Addition to offset an error
                        if (nextover[0] >= -1) {
                            car.setLayoutX(car.getLayoutX() + 2);
                            car.setRotation(0);
                            car.resetInvertHorizontal();// Face right
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
            final double[] nextover = new double[1];

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

        animation2.setOnAction(e->{
            g2.getChildren().removeAll(robo,robo2); //Reset page
            g2.getChildren().add(robo); //Adds robo
            robo.setX(15.0); //Starting X
            robo.setY(260.0); //add the full functionality of the robot here
            final double[] nextover = new double[3];

            scene.setOnKeyPressed(event -> {
                switch (event.getCode()) {
                    case W:
                        nextover[0] = maze2.getPixelReader().getArgb((int) (robo.getX()) + 14, (int) (robo.getY() - 2)); //Addition to offset an error
                        if (nextover[0] >= -1) {
                            robo.setY(robo.getY() - 2);
                        }; // Move up
                        break;
                    case S:
                        nextover[0] = maze2.getPixelReader().getArgb((int) (robo.getX()) + 16, (int) (robo.getY() + 24)); //Addition to offset an error
                        if (nextover[0] >= -1) {
                            robo.setY(robo.getY() + 2);
                        }; // Move down
                        break;
                    case A:
                        nextover[0] = maze2.getPixelReader().getArgb((int) (robo.getX()) + 5, (int) (robo.getY()));//Addition to offset an error
                        if (nextover[0] >= -1) {
                            robo.setX(robo.getX() - 2);
                        };// Move left
                        break;
                    case D:
                        nextover[0] = maze2.getPixelReader().getArgb((int) (robo.getX()) + 20, (int) (robo.getY() + 24)); //Addition to offset an error
                        if (nextover[0] >= -1) {
                            robo.setX(robo.getX() + 2);
                        };// Move right
                        break;
                }
            });
        });
        final double[] nextover = new double[3];

    }

    /*
    Launch the Program here.
     */
    public static void main(String[] args) {
        launch();
    }
}