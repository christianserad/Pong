/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pong;

import Java.Game.Game;
import Java.Game.GameObject;
import javafx.animation.AnimationTimer;
import javafx.scene.Node;

/**
 *
 * @author Christian Romar Paul Serad
 */
public class Ball extends GameObject {

    private AnimationTimer timer;

    public Ball(String name, Node node) {
        super(name, node);
    }



    public Ball(String name, String imageURL, double coordinateX, double coordinateY) {
        super(name, imageURL, coordinateX, coordinateY);
    }
    

    @Override
    public void run() {
        if (timer == null) {
            timer = new AnimationTimer() {
                int hspeed = 2;
                int vspeed = 2;

                @Override
                public void handle(long now) {
                    if (hspeed > 0 && getX() > 490) {
                        hspeed *= -1;
                    }

                    if (hspeed < 0 && getX() < 0) {
                        hspeed *= -1;
                    }

                    if (vspeed < 0 && topMeet(2) != -1) {
                        vspeed *= -1;
                    }

                    if (vspeed > 0 && bottomMeet(2) != -1) {
                        vspeed *= -1;
                    }
                    
                    if(getY() > Game.currentStage().getScene().getHeight() || getY() < 0 - getHeight()){
                        Game.displayStage("stage2");
                    }

                    moveY(vspeed);
                    moveX(hspeed);
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
