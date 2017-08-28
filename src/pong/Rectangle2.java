/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pong;

import Java.Game.Game;
import Java.Game.GameObject;
import Java.Game.KeyPressed;
import javafx.animation.AnimationTimer;
import javafx.scene.Node;
import javafx.scene.input.KeyCode;

/**
 *
 * @author Christian Romar Paul Serad
 */
public class Rectangle2 extends GameObject {

    private AnimationTimer timer;

    public Rectangle2(String name, Node node) {
        super(name, node);
    }

    public Rectangle2(String name, String imageURL, double coordinateX, double coordinateY) {
        super(name, imageURL, coordinateX, coordinateY);
    }
    
    

    @Override
    public void run() {
        if (timer == null) {
            timer = new AnimationTimer() {
                double speed = 0;

                @Override
                public void handle(long now) {
                    if (pressed(KeyCode.D)) {
                        speed = 3;
                    }

                    if (pressed(KeyCode.A)) {
                        speed = -3;
                    }

                    if (!pressed(KeyCode.A) && !pressed(KeyCode.D)) {
                        speed = 0;
                    }

                    if (pressed(KeyCode.D) && getX() > 400) {
                        speed = 0;
                    }

                    if (pressed(KeyCode.A) && getX() < 0) {
                        speed = 0;
                    }
                    moveX(speed);
                }

            };

            timer.start();
        } else {
            timer.start();
        }

    }

    @Override
    public void stop() {
        if(timer != null){
            timer.stop();
            timer = null;
        }
    }

}
