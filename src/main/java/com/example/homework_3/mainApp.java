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
    ImageView Autobot = new ImageView(robotpic);
    ImageView robo2 = new ImageView(robotpic);
    ImageView Autobot2 = new ImageView(robotpic);

    // Declare the Car variables
    Car car = new Car();
    Car car2 = new Car();
    Rectangle r = new Rectangle(); //Hitbox for the Car

    //Buttons for Animation, Car, And Robot (Tab 1)
    Button robotButton = new Button();
    Button carButton = new Button();
    Button robotAutomaticButton = new Button();

    //Buttons for Animation, Car, And Robot (Tab 2)
    Button robotButton2 = new Button();
    Button carButton2 = new Button();
    Button robotAutomaticButton2 = new Button();

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
        robotButton.setText("Play Robot"); robotButton.setLayoutX(75);robotButton.setLayoutY(430);
        carButton.setText("Play Car"); carButton.setLayoutX(275);carButton.setLayoutY(430);
        robotAutomaticButton.setText("Robot Auto"); robotAutomaticButton.setLayoutX(475);robotAutomaticButton.setLayoutY(430);
        robotButton2.setText("Play Robot"); robotButton2.setLayoutX(75);robotButton2.setLayoutY(430);
        carButton2.setText("Play Car"); carButton2.setLayoutX(275);carButton2.setLayoutY(430);
        robotAutomaticButton2.setText("Robot Auto"); robotAutomaticButton2.setLayoutX(475);robotAutomaticButton2.setLayoutY(430);

        //Create and Initialize TabPane
        g1.getChildren().addAll(view1, carButton, robotButton, robotAutomaticButton);
        g2.getChildren().addAll(view2, carButton2, robotButton2, robotAutomaticButton2);
        TabPane tb = new TabPane();
        Tab tb1 = new Tab(); tb1.setText("Maze1"); tb1.setContent(g1);
        Tab tb2 = new Tab(); tb2.setText("Maze2"); tb2.setContent(g2);
        tb.getTabs().add(tb1);tb.getTabs().add(tb2);

        //Innit and show the scene
        Scene scene = new Scene(tb, 619, 485);
        System.out.println(mainApp.class.getClassLoader().getResource("styles.css"));
        scene.getStylesheets().add(getClass().getResource("/styles.css").toExternalForm());
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
        Path for Automatic Path button on Tab2. Starts at (40,40). Ends at (575,390).
        Initialized here to not waste time on action (Button Press).
         */
        Path p2 = new Path(
                new MoveTo(40,40),
                new LineTo(40,375),
                new LineTo(250,375),
                new LineTo(250,187),
                new LineTo(425,187),
                new LineTo(425,40),
                new LineTo(575,40),
                new LineTo(575,390)

        );
        /*
        Automatic path button for Droid on Tab2 -
        Unloads all tab 2 objects
        Takes the Path, and runs the Android image through the maze. 10 seconds
         */
        robotAutomaticButton2.setOnAction(e-> {
            g2.getChildren().removeAll(robo2, Autobot2,car2); //Reset page
            g2.getChildren().add(Autobot2); //Adds robo2
            PathTransition doPath = new PathTransition();
            doPath.setDuration(Duration.seconds(10)); //15 sec animation
            doPath.setNode(Autobot2);
            doPath.setPath(p2); //Set Path
            doPath.play(); //Plays for 15 seconds
        });

        /*
        Automatic path button for Droid on Tab1 -
        Unloads all tab 1 objects
        Takes the Path, and runs the Android image through the maze. 15 seconds
         */
        robotAutomaticButton.setOnAction(e-> {
            g1.getChildren().removeAll(robo, Autobot,car); //Reset page
            g1.getChildren().add(Autobot); //Adds robo2
            PathTransition doPath = new PathTransition();
            doPath.setDuration(Duration.seconds(15)); //15 sec animation
            doPath.setNode(Autobot);
            doPath.setPath(p1); //Set Path
            doPath.play(); //Plays for 15 seconds
        });

        /*
        Car Action Button -
        Clears board, and sets starting point of the Car Object from the Car Class on tab 1. Moves with WASD
         */
        carButton.setOnAction(e->{
            g1.getChildren().removeAll(robo, Autobot,car,r); //Reset page
            g1.getChildren().addAll(car,r); //Adds car and hitbox
            car.setScaleX(0.4);
            car.setScaleY(0.4);
            car.setPosition(12.0, 250.0); //Starting position
            r.setFill(Color.TRANSPARENT);
            r.setHeight(15);
            r.setWidth(20);
            r.setLayoutX(car.getLayoutX()+18);
            r.setLayoutY(car.getLayoutY()+18); //Starting position

            final double[] nextover = new double[1];

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
        Car Action Button2 -
        Clears board, and sets starting point of the Car Object from the Car Class On tab2. Moves with WASD
        */
        carButton2.setOnAction(e->{
            g2.getChildren().removeAll(robo2, Autobot2,car2,r); //Reset page
            g2.getChildren().addAll(car2,r); //Adds car and hitbox
            car2.setScaleX(0.4);
            car2.setScaleY(0.4);
            car2.setPosition(15.0, 15.0); //Starting position
            r.setFill(Color.TRANSPARENT);
            r.setHeight(15);
            r.setWidth(20);
            r.setLayoutX(car.getLayoutX()+18);
            r.setLayoutY(car.getLayoutY()+18); //Starting position
            final double[] nextover = new double[1];

            /*
            KeyListener for Tab2 Animation -
            Moves by 2 pixels each press to speed up process. Uses WASD
             */
            scene.setOnKeyPressed(event -> {
                switch (event.getCode()) {
                    case W:
                        r.setLayoutX(car2.getLayoutX()+14);
                        r.setLayoutY(car2.getLayoutY()+18); //Starting position
                        nextover[0] = maze2.getPixelReader().getArgb((int) (r.getLayoutX()) + 16, (int) (r.getLayoutY()+13)); //Addition to offset an error
                        if (nextover[0] != -1.6760833E7)  {
                            car2.setLayoutY(car2.getLayoutY() - 5);
                            car2.setRotation(-90); // Face up

                        // Move up
                        };
                        break;
                    case S:
                        r.setLayoutX(car2.getLayoutX()+14);
                        r.setLayoutY(car2.getLayoutY()+18); //Starting position
                        nextover[0] = maze2.getPixelReader().getArgb((int) (r.getLayoutX()) + 14, (int) (r.getLayoutY()+17)); //Addition to offset an error
                        if (nextover[0] != -1.6760833E7)  {
                            car2.setLayoutY(car2.getLayoutY() + 5);
                            car2.setRotation(90); // Face down
                        };
                        // Move down
                        break;
                    case A:
                        r.setLayoutX(car2.getLayoutX()+14);
                        r.setLayoutY(car2.getLayoutY()+18); //Starting position
                        nextover[0] = maze2.getPixelReader().getArgb((int) (r.getLayoutX()) + 14, (int) (r.getLayoutY()+15));//Addition to offset an error
                        if (nextover[0] != -1.6760833E7)  {
                            car2.setLayoutX(car2.getLayoutX() - 5);
                            car2.setRotation(0);
                            car2.invertHorizontal(); // Face left
                        }
                        // Move left
                        break;
                    case D:
                        r.setLayoutX(car2.getLayoutX()+14);
                        r.setLayoutY(car2.getLayoutY()+18); //Starting position
                        nextover[0] = maze2.getPixelReader().getArgb((int) (r.getLayoutX()) + 18, (int) (r.getLayoutY()+15)); //Addition to offset an error
                        if (nextover[0] != -1.6760833E7)  {
                            car2.setLayoutX(car2.getLayoutX() + 5);
                            car2.setRotation(0);
                            car2.resetInvertHorizontal();// Face right
                        }
                        // Move right
                        break;
                }
            });
        });
        /*
        Animation Action Button -
        Clears board, and sets starting point of robo2. Moves with WASD
        In Tab1
         */
        robotButton.setOnAction(e-> {
            g1.getChildren().removeAll(robo, Autobot,car); //Reset Page
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

        /*
        Animation Action Button -
        Clears board, and sets starting point of robo2. Moves with WASD
        In Tab1
         */
        robotButton2.setOnAction(e->{
            g2.getChildren().removeAll(robo2, Autobot2,car2); //Reset page
            g2.getChildren().addAll(robo2); //Adds robo
            robo2.setLayoutX(25.0); //Starting X
            robo2.setLayoutY(25.0); //add the full functionality of the robot here
            final double[] nextover = new double[1];

            scene.setOnKeyPressed(event -> {
                switch (event.getCode()) {
                    case W:
                        nextover[0] = maze2.getPixelReader().getArgb((int) (robo2.getLayoutX()), (int) (robo2.getLayoutY() - 5)); //Addition to offset an error
                        if (nextover[0] != -1.6760833E7)  {
                            robo2.setLayoutY(robo2.getLayoutY() - 5);
                        }; // Move up
                        break;
                    case S:
                        nextover[0] = maze2.getPixelReader().getArgb((int) (robo2.getLayoutX()), (int) (robo2.getLayoutY() + 5)); //Addition to offset an error
                        if (nextover[0] != -1.6760833E7)  {
                            robo2.setLayoutY(robo2.getLayoutY() + 5);
                        }; // Move down
                        break;
                    case A:
                        nextover[0] = maze2.getPixelReader().getArgb((int) (robo2.getLayoutX() - 25), (int) (robo2.getLayoutY()));//Addition to offset an error
                        if (nextover[0] != -1.6760833E7)  {
                            robo2.setLayoutX(robo2.getLayoutX() - 5);
                        };// Move left
                        break;
                    case D:
                        nextover[0] = maze2.getPixelReader().getArgb((int) (robo2.getLayoutX() + 25), (int) (robo2.getLayoutY())); //Addition to offset an error
                        if (nextover[0] != -1.6760833E7) {
                            robo2.setLayoutX(robo2.getLayoutX() + 5);
                        };// Move right
                        break;
                }
            });
        });
    }

    /*
    Launches the Program here.
     */
    public static void main(String[] args) {
        launch();
    }
}