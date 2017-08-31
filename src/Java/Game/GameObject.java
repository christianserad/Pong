package Java.Game;

import java.util.ArrayList;
import javafx.animation.Animation;
import javafx.animation.Transition;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;

/**
 * An abstract class that has a a standard property of a game object
 *
 * @author Christian Romar Paul Serad
 */
public abstract class GameObject extends Parent {

    //default properties
    private String defaultImageURL;
    private String[] defaultAnimationURL;
    private double defaultY;
    private double defaultX;

    //current properties
    private String name;
    private Animation animation;
    private String[] imageAnimationURL;
    private String imageURL;

    //Events
    private static boolean keyEventInitialized;
    private static ArrayList<KeyPressed> keyPressedList = new ArrayList<KeyPressed>();
    private boolean mouseHover;
    private boolean mouseClick;

    public GameObject(String name, Node node) {
        this.name = name;
        getChildren().add(node);
        setMouseHoverEvent();
        setMouseClickEvent();
    }

    public GameObject(String name, String imageURL, double coordinateX, double coordinateY) {
        this(name, coordinateX, coordinateY);
        defaultImageURL = imageURL;
        setImage(imageURL);

    }

    public GameObject(String name, String[] imageAnimationURL, double coordinateX, double coordinateY) {
        this(name, coordinateX, coordinateY);
        defaultAnimationURL = imageAnimationURL;
        setAnimation(imageAnimationURL);

    }

    public GameObject(String name, double coordinateX, double coordinateY) {
        //default values
        defaultX = coordinateX;
        defaultY = coordinateY;

        this.name = name;
        setX(coordinateX);
        setY(coordinateY);

        //initializing events
        setMouseHoverEvent();
        setMouseClickEvent();
    }

    /**
     * This method sets the image of the GameObject
     *
     * @param imageURL
     */
    public void setImage(String imageURL) {
        this.imageURL = imageURL;
        getChildren().clear();
        getChildren().add(new ImageView(imageURL));
    }

    /**
     * This method sets the Node of the GameObject
     *
     * @param node
     */
    public void setNode(Node node) {
        getChildren().clear();
        getChildren().add(node);
    }

    /**
     * This method sets the X coordinate of the GameObject
     *
     * @param coordinateX
     */
    public void setX(double coordinateX) {
        setTranslateX(0);
        setLayoutX(coordinateX);
    }

    /**
     * This method sets the Y coordinate of the GameObject
     *
     * @param coordinateY
     */
    public void setY(double coordinateY) {
        setTranslateY(0);
        setLayoutY(coordinateY);
    }

    /**
     * This method returns the X coordinate of the GameObject
     *
     * @return the X coordinate
     */
    public double getX() {
        return getLayoutX() + getTranslateX();
    }

    /**
     * This method returns the Y coordinate of the GameObject
     *
     * @return the Y coordinate
     */
    public double getY() {
        return getLayoutY() + getTranslateY();
    }

    /**
     * This method sets or initializes the animation of the object
     *
     * @param animation
     */
    public void setAnimation(String[] imageAnimationURL) {
        this.imageAnimationURL = imageAnimationURL;
        animation = new Transition() {
            {
                setCycleDuration(Duration.millis(2000));
            }
            int frame = 0;

            @Override
            protected void interpolate(double frac) {
                if (frame >= imageAnimationURL.length) {
                    frame = 0;
                }
                getChildren().clear();
                ImageView imageFrame = new ImageView(new Image(imageAnimationURL[frame]));
                getChildren().add(imageFrame);
                frame++;
            }
        };
        animation.setCycleCount(animation.INDEFINITE);
        animation.play();
    }

    /**
     * This method activates the animation
     */
    public void playAnimation() {
        animation.play();
    }

    /**
     * This method stops the animation
     */
    public void stopAnimation() {
        animation.stop();
    }

    /**
     * This method sets the speed of the animation
     */
    public void setSpeedAnimation(double speed) {
        speed *= 0.001;
        animation.setRate(speed);
    }

    /**
     * This method returns the Height of the GameObject
     *
     * @return return the height of the GameObject
     */
    public double getHeight() {
        return getBoundsInParent().getHeight();
    }

    /**
     * This the method returns the width of the GameObject
     *
     * @return the width of the object
     */
    public double getWidth() {
        return getBoundsInParent().getWidth();
    }

    /**
     * This method moves the GameObject horizontally by the speed
     *
     * @param speed
     */
    public void moveX(double speed) {
        setX(getX() + speed);
    }

    /**
     * This method moves the GameObject vertically by the speed
     *
     * @param speed
     */
    public void moveY(double speed) {
        setY(getY() + speed);
    }

    /**
     * This method returns the name of the GameObject
     *
     * @return the name of the GameObject
     */
    public String getName() {
        return name;
    }

    /**
     * This method determines the distance to the left between this GameObject and other GameObject
     *
     * @param distance
     * @return the distance
     */
    public double leftMeet(int distance) {
        return leftMeet(distance, null);
    }

    public double leftMeet(int distance, GameObject object) {
        ArrayList<GameObject> objects;
        if (object == null) {
            objects = Game.currentStage().getObjects();
        } else {
            objects = new ArrayList<GameObject>();
            objects.add(object);
        }

        int left = (int) getX();
        int top = (int) getY();
        int bottom = (int) (getY() + getHeight());

        for (int i = 0; i < objects.size(); i++) {
            if (this != objects.get(i)) {
                int right2 = (int) (objects.get(i).getX() + objects.get(i).getWidth());
                int top2 = (int) objects.get(i).getY();
                int bottom2 = (int) (objects.get(i).getY() + objects.get(i).getHeight());

                //left detector
                if (left >= right2 && left <= (right2 + distance)) {
                    for (double x = top; x <= bottom; x++) {
                        if (x > top2 && x < bottom2) {
                            return right2;
                        }
                    }
                }
            }
        }
        return -1;
    }

    /**
     * This method determines the distance to the right between this GameObject and other GameObject
     *
     * @param distance
     * @return the distance
     */
    public double rightMeet(int distance) {
        return rightMeet(distance, null);
    }

    public double rightMeet(int distance, GameObject object) {
        ArrayList<GameObject> objects;
        if (object == null) {
            objects = Game.currentStage().getObjects();
        } else {
            objects = new ArrayList<GameObject>();
            objects.add(object);
        }

        int right = (int) (getX() + getWidth());
        int top = (int) getY();
        int bottom = (int) (getY() + getHeight());

        for (int i = 0; i < objects.size(); i++) {
            if (this != objects.get(i)) {
                int left2 = (int) objects.get(i).getX();
                int top2 = (int) objects.get(i).getY();
                int bottom2 = (int) (objects.get(i).getY() + objects.get(i).getHeight());

                //right detector
                if (right <= left2 && right >= (left2 - distance)) {
                    for (double x = top; x <= bottom; x++) {
                        if (x > top2 && x < bottom2) {
                            return left2 - objects.get(i).getWidth();
                        }
                    }
                }
            }
        }
        return -1;

    }

    /**
     * This method determines the distance to the top between this GameObject and other GameObject
     *
     * @param distance
     * @return the distance
     */
    public double topMeet(int distance) {
        return topMeet(distance, null);
    }

    public double topMeet(int distance, GameObject object) {
        ArrayList<GameObject> objects;
        if (object == null) {
            objects = Game.currentStage().getObjects();
        } else {
            objects = new ArrayList<GameObject>();
            objects.add(object);
        }

        int top = (int) getY();
        int left = (int) getX();
        int right = (int) (getX() + getWidth());

        for (int i = 0; i < objects.size(); i++) {
            if (this != objects.get(i)) {
                int left2 = (int) objects.get(i).getX();
                int right2 = (int) (objects.get(i).getX() + objects.get(i).getWidth());
                int bottom2 = (int) (objects.get(i).getY() + objects.get(i).getHeight());

                //top dectector
                if (top >= bottom2 && top <= (bottom2 + 4)) {
                    for (double x = left; x <= right; x++) {
                        if (x > left2 && x < right2) {
                            return bottom2 + objects.get(i).getHeight();
                        }
                    }
                }
            }
        }
        return -1;

    }

    /**
     * This method determines the distance to the bottom between this GameObject and other GameObject
     *
     * @param distance
     * @return the distance
     */
    public double bottomMeet(int distance) {
        return bottomMeet(distance, null);
    }

    public double bottomMeet(int distance, GameObject object) {
        ArrayList<GameObject> objects;
        if (object == null) {
            objects = Game.currentStage().getObjects();
        } else {
            objects = new ArrayList<GameObject>();
            objects.add(object);
        }

        int bottom = (int) (getY() + getHeight());
        int left = (int) getX();
        int right = (int) (getX() + getWidth());

        for (int i = 0; i < objects.size(); i++) {
            if (this != objects.get(i)) {
                int left2 = (int) objects.get(i).getX();
                int right2 = (int) (objects.get(i).getX() + objects.get(i).getWidth());
                int top2 = (int) objects.get(i).getY();

                //bottom detector
                if (bottom <= top2 && bottom >= (top2 - distance)) {
                    for (double x = left; x <= right; x++) {
                        if (x > left2 && x < right2) {
                            return top2 - objects.get(i).getHeight();
                        }
                    }
                }
            }
        }
        return -1;
    }

    /**
     * This method initializes the key press event
     */
    private void setKeyEvent() {
        getScene().setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                for (int i = 0; i < keyPressedList.size(); i++) {
                    if (event.getCode() == keyPressedList.get(i).getCode()) {
                        keyPressedList.get(i).setPressed(true);
                    }
                }
            }
        });

        getScene().setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                for (int i = 0; i < keyPressedList.size(); i++) {
                    if (event.getCode() == keyPressedList.get(i).getCode()) {
                        keyPressedList.get(i).setPressed(false);
                    }
                }
            }
        });

        keyEventInitialized = true;
    }

    /**
     * This method determines if the given key is pressed
     *
     * @param code
     * @return true if the key is pressed otherwise false
     */
    public boolean pressed(KeyCode code) {
        if (!keyEventInitialized) {
            setKeyEvent();
        }

        for (int i = 0; i < keyPressedList.size(); i++) {
            if (code == keyPressedList.get(i).getCode()) {
                return keyPressedList.get(i).isPressed();
            }
        }

        keyPressedList.add(new KeyPressed(code));
        return false;

    }

    /**
     * This method initializes the mouse hover event
     */
    private void setMouseHoverEvent() {
        setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                mouseHover = true;
            }
        });

        setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                mouseHover = false;
            }
        });
    }

    /**
     * This method determines if the mouse is hovering inside the GameObject
     *
     * @return true if mouse hovers, otherwise false
     */
    public boolean mouseHover() {
        return mouseHover;
    }

    /**
     * This method initializes mouse click event
     */
    public void setMouseClickEvent() {
        setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                mouseClick = true;
            }
        });

        setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                mouseClick = false;
            }
        });
    }

    /**
     * This method determines if the mouse clicked this GameObject
     *
     * @return true if the mouse clicked the GameObject, otherwise false
     */
    public boolean mouseClick() {
        return mouseClick;
    }

    /**
     * This method sets the GameObject to its default properties
     */
    public void setDefault() {
        if (defaultImageURL != null) {
            setImage(defaultImageURL);
        }
        if (defaultAnimationURL != null) {
            setAnimation(defaultAnimationURL);
        }
        setX(defaultX);
        setY(defaultY);
    }

    /**
     * Activates the object Override this method to subclasses and add distinct
     * behavioral code with AnimationTimer
     */
    public abstract void run();

    /**
     * Stops the object from executing frame by frame code
     */
    public abstract void stop();

}
