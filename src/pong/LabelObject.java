/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pong;

import christian.game2d.GameObject;
import javafx.scene.control.Label;

/**
 *
 * @author Christian Romar Paul Serad
 */
public class LabelObject extends GameObject{

    public LabelObject(String name, Label label) {
        super(name, label);
    }

    public LabelObject(String name, String imageURL, double coordinateX, double coordinateY) {
        super(name, imageURL, coordinateX, coordinateY);
    }

    



    @Override
    public void stop() {
        
    }

    @Override
    public void run() {
        
    }
    
    
    
}
