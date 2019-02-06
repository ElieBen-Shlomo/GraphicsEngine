import Geometry.*;
import Maths.Matrix;

import javax.swing.*;
import java.util.Arrays;

public class MainClass {
    public static void main(String args[]) {
        JFrame win = new JFrame();
        win.setSize(1400, 1000);
        win.setTitle("Graphics Engine");
        win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        GraphicsController graph = new GraphicsController();
        win.add(graph);

        win.setVisible(true);
    }
}
