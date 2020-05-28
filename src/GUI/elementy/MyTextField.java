package GUI.elementy;

import javax.swing.*;
import java.awt.*;

public class MyTextField extends JTextField {

    public MyTextField(String text){
        super(text);
        setFont(new Font("monospace", Font.PLAIN, 20));
        setBackground(new Color(230,230,230));
        setForeground(new Color(50,50,50));
    }
}
