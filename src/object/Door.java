package object;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.*;
import javax.imageio.ImageIO;
import javax.swing.*;

public class Door extends SuperObject{

    public BufferedImage openDoor;

    public Door(){
        name = "Door";
        try{
            image = ImageIO.read(getClass().getResourceAsStream("/res/objects/door_closed.png")); //closed door
            openDoor = ImageIO.read(getClass().getResourceAsStream("/res/objects/door_open.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        collision = true;
    }
}
