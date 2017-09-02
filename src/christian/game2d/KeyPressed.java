package christian.game2d;

import javafx.scene.input.KeyCode;

/**
 * This class is used to create key objects
 *
 * @author Christian Romar Paul Serad
 */
public class KeyPressed {

    private final KeyCode code;
    private boolean pressed;

    /**
     * This method initializes the KeyCode of this key
     *
     * @param code
     */
    public KeyPressed(KeyCode code) {
        this.code = code;
    }

    /**
     * This method returns the KeyCode of this key
     *
     * @return the KeyCode
     */
    public KeyCode getCode() {
        return code;
    }

    /**
     * This method determines if the key is pressed
     *
     * @return true if pressed, otherwise false
     */
    public boolean isPressed() {
        return pressed;
    }

    /**
     * This method set the key to pressed
     *
     * @param pressed
     */
    public void setPressed(boolean pressed) {
        this.pressed = pressed;
    }

}
