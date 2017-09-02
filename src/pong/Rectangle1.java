/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pong;

import christian.game2d.Game;
import christian.game2d.GameObject;
import christian.game2d.KeyPressed;
import javafx.animation.AnimationTimer;
import javafx.scene.Node;
import javafx.scene.input.KeyCode;

/**
 *
 * @author Christian Romar Paul Serad
 */
public class Rectangle1 extends GameObject {

    private AnimationTimer timer;

    public Rectangle1(String name, Node node) {
        super(name, node);
    }

    public Rectangle1(String name, String imageURL, double coordinateX, double coordinateY) {
        super(name, imageURL, coordinateX, coordinateY);
    }
    
    public Rectangle1(String name, String spriteSheetURL, int width, int height, double coordinateX, double coordinateY) {
        super(name, spriteSheetURL, width, height, coordinateX, coordinateY);
    }
    
    public Rectangle1(String name, String[] imageAnimationURL, double coordinateX, double coordinateY) {
        super(name, imageAnimationURL, coordinateX, coordinateY);
    }

    @Override
    public void run() {
        if (timer == null) {
            timer = new AnimationTimer() {
                double speed = 0;

                @Override
                public void handle(long now) {
                    if (pressed(KeyCode.RIGHT)) {
                        speed = 3;
                    }

                    if (pressed(KeyCode.LEFT)) {
                        speed = -3;
                    }

                    if (!pressed(KeyCode.LEFT) && !pressed(KeyCode.RIGHT)) {
                        speed = 0;
                    }

                    if (pressed(KeyCode.RIGHT) && getX() > 400) {
                        speed = 0;
                    }

                    if (pressed(KeyCode.LEFT) && getX() < 0) {
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
