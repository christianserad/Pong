package christian.game2d;

import java.util.ArrayList;
import javafx.scene.Parent;

/**
 * This class is used to create stage
 *
 * @author Christian Romar Paul Serad
 */
public class GameStage extends Parent {

    private ArrayList<GameObject> objectList = new ArrayList<GameObject>();
    private String name;

    public GameStage(String name) {
        this.name = name;
        Game.addStage(this);
    }

    /**
     * This method adds GameObjects into the stage
     *
     * @param object
     */
    public void addObject(GameObject... object) {
        for (int i = 0; i < object.length; i++) {
            objectList.add(object[i]);
            getChildren().add(object[i]);
        }

    }

    /**
     * This method returns the GameObject from this stage
     *
     * @param name
     * @return the game object
     */
    public GameObject getObject(String name) {
        for (int i = 0; i < objectList.size(); i++) {
            if (objectList.get(i).getName().equals(name)) {
                return objectList.get(i);
            }
        }
        throw new GameErrorException("GameObject " + name + " not found");
    }

    /**
     * This method returns all the GameObjects from this stage
     *
     * @return the objects from the stage
     */
    public ArrayList<GameObject> getObjects() {
        return objectList;
    }

    /**
     * This method returns the name of the GameStage
     *
     * @return the name of the stage
     */
    public String getName() {
        return name;
    }

    /**
     * This method starts or display the GameStage with running GameObjects
     */
    public void start() {
        for (int i = 0; i < objectList.size(); i++) {
            objectList.get(i).run();
        }
        setVisible(true);
    }

    /**
     * This method pauses or stops the GameObjects inside the GameStage
     */
    public void pause() {
        for (int i = 0; i < objectList.size(); i++) {
            objectList.get(i).run();
        }
        setVisible(true);
    }

    /**
     * This method closes the GameStage while stopping the GameObjects
     */
    public void close() {
        for (int i = 0; i < objectList.size(); i++) {
            objectList.get(i).setDefault();
            objectList.get(i).stop();
        }
        setVisible(false);
    }

}
