package pong;

import Java.Game.Game;
import Java.Game.GameObject;
import javafx.animation.AnimationTimer;
import javafx.scene.shape.Shape;

/**
 *
 * @author Christian Romar Paul Serad
 */
public class ButtonRestart extends GameObject {

    AnimationTimer timer;

    public ButtonRestart(String name, Shape shape) {
        super(name, shape);
    }

    public ButtonRestart(String name, String imageURL, double coordinateX, double coordinateY) {
        super(name, imageURL, coordinateX, coordinateY);
    }


    @Override
    public void run() {
        if (timer == null) {
            timer = new AnimationTimer() {
                @Override
                public void handle(long now) {
                    if(mouseHover()){
                        setImage("pong/images/restartHover.png");
                    }
                    else{
                        setImage("pong/images/restart.png");
                    }
                    
                    if(mouseClick()){
                        Game.displayStage("stage1");
                    }
                }
            };
            timer.start();
        }
        else{
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
