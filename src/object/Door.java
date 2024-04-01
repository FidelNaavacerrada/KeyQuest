package object;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.*;
import javax.imageio.ImageIO;
import javax.swing.*;

public class Door extends SuperObject{

    public Door(){
        name = "Door";
        try{
            image = ImageIO.read(getClass().getResourceAsStream("/res/objects/door_closed.png"));
            //image = ImageIO.read(getClass().getResourceAsStream("/res/objects/door_closed.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        collision = true;
    }
}
