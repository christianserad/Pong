package Java.Game;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.Group;
import javafx.scene.Parent;

/**
 * Root of the Game Application which includes all necessary GameObjects and
 * GameStages
 *
 * @author Christian Romar Paul Serad
 */
public class Game extends Parent {

    private static Group group = new Group();
    private static ArrayList<GameStage> gameStage = new ArrayList<GameStage>();

    /**
     * This method returns a specific GameStage
     *
     * @param name
     * @return a specific GameStage
     */
    public static GameStage getStage(String name) {
        for (int i = 0; i < gameStage.size(); i++) {
            if (gameStage.get(i).getName().equals(name)) {
                return gameStage.get(i);
            }
        }
        return null;
    }

    /**
     * This method return all the GameStages within the game
     *
     * @return
     */
    public static GameStage[] getStages() {
        return gameStage.toArray(new GameStage[gameStage.size()]);
    }

    /**
     * This method adds a GameStage inside the game
     *
     * @param stage
     */
    public static void addStage(GameStage stage) {
        group.getChildren().add(stage);
        gameStage.add(stage);
    }

    /**
     * This method returns the root
     *
     * @return the root
     */
    public static Group getRoot() {
        return group;
    }

    /**
     * This method displays specific GameStage in the window
     *
     * @param stageName
     */
    public static void displayStage(String stageName) {
        for (int i = 0; i < gameStage.size(); i++) {
            if (gameStage.get(i).getName().equals(stageName)) {
                gameStage.get(i).start();
            } else {
                gameStage.get(i).close();
            }
        }
    }

    /**
     * This method return the displayed GameStage
     *
     * @return displayed GameStage
     */
    public static GameStage currentStage() {
        for (int i = 0; i < gameStage.size(); i++) {
            if (gameStage.get(i).isVisible()) {
                return gameStage.get(i);
            }
        }
        return null;
    }

}
