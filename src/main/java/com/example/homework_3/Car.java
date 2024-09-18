package com.example.homework_3;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class Car extends Group {
    private Rectangle body;
    private Rectangle roof;
    private Rectangle frontWindow;
    private Rectangle rearWindow;
    private Circle frontWheel;
    private Circle rearWheel;
    private Circle frontHeadlight;
    private Circle rearHeadlight;

    public Car() {
        // Create the body of the car
        body = new Rectangle(0, 20, 60, 20);
        body.setFill(Color.BLUE);

        // Create the roof of the car
        roof = new Rectangle(10, 10, 40, 10);
        roof.setFill(Color.DARKBLUE);

        // Create the windows of the car
        frontWindow = new Rectangle(12, 12, 16, 8);
        frontWindow.setFill(Color.LIGHTBLUE);
        rearWindow = new Rectangle(32, 12, 16, 8);
        rearWindow.setFill(Color.LIGHTBLUE);

        // Create the wheels of the car
        frontWheel = new Circle(15, 40, 5);
        frontWheel.setFill(Color.BLACK);
        rearWheel = new Circle(45, 40, 5);
        rearWheel.setFill(Color.BLACK);

        // Create the headlights of the car
        frontHeadlight = new Circle(5, 30, 2);
        frontHeadlight.setFill(Color.RED);
        rearHeadlight = new Circle(55, 30, 2);
        rearHeadlight.setFill(Color.YELLOW);

        // Add the shapes to the group
        this.getChildren().addAll(body, roof, frontWindow, rearWindow, frontWheel, rearWheel, frontHeadlight, rearHeadlight);
    }

    public void setPosition(double x, double y) {
        this.setLayoutX(x);
        this.setLayoutY(y);
    }

    public void setRotation(double angle) {
        this.setRotate(angle);
    }

    public void invertHorizontal() {
        //preserve aspect ratio
        this.setScaleY(.5);
        this.setScaleX(-.5);
    }
    public void resetInvertHorizontal() {
        this.setScaleY(.5);
        this.setScaleX(.5);
    }
}