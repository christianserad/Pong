package pong;


import Java.Game.Game;
import Java.Game.GameStage;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * The main class of the game, which initializes game object positions
 *
 * @author Christian Romar Paul Serad
 */
public class GameApp extends Application {
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        setGame();
        Game.displayStage("stage");
        Scene scene = new Scene(Game.getRoot(), 500, 500);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    /**
     * initialize the position of game objects within the stage
     *
     * @param stage
     */
    public void setGame() throws CloneNotSupportedException {
        //Put all the initialized game objects here
        
        GameStage stage = new GameStage("stage");
        ButtonStart buttonStart = new ButtonStart("button","pong/images/start.png", 200, 230);
        
        stage.addObject(buttonStart);
        
        
        //stage1
        GameStage stage1 = new GameStage("stage1");
        Ball ball = new Ball("ball","pong/images/ball.png", 250, 250);
        
        Rectangle2 rec2 = new Rectangle2("rec1","pong/images/rectangle1.png", 200, 25);
        
        Rectangle1 rec1 = new Rectangle1("rec1","pong/images/rectangle2.png", 200, 475);
        
        stage1.addObject(rec2,rec1,ball);
        
        //stage2
        GameStage stage2 = new GameStage("stage2");
 
        
        ButtonRestart buttonRestart = new ButtonRestart("button", "pong/images/restart.png", 200, 230);
        
        stage2.addObject(buttonRestart);
    }

}
