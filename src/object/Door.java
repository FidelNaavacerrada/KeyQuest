package object;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.*;
import javax.imageio.ImageIO;
import javax.swing.*;

public class Door extends SuperObject{

    public Door(){
        name = "Door";
        collision = true;
        try{
            image = ImageIO.read(getClass().getResourceAsStream("/res/objects/door_closed.png")); //closed door
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
