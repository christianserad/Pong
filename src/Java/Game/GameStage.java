package Java.Game;

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
     * Add game objects into the stage at the start of the game
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
     * Gets the object from the stage
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
        return null;
    }

    /**
     * get all the objects from the stage
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
    public void close(){
        for (int i = 0; i < objectList.size(); i++) {
            objectList.get(i).setDefault();
            objectList.get(i).stop();
        }
        setVisible(false);
    }

}
