package object;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.*;
import javax.imageio.ImageIO;
import javax.swing.*;

public class Key extends SuperObject{

    public Key(){
        name = "Key";
        try{
            image = ImageIO.read(getClass().getResourceAsStream("/res/objects/key.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
