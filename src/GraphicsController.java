import Geometry.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;



import static java.lang.Math.*;

public class GraphicsController extends JPanel implements ActionListener {
    private int DELAY = 10;
    private int secondsPassed = 10;
    MyTrapezoid trapezoid = new MyTrapezoid(
            new MyPoint(new double[]{350,650,0}),
            new MyPoint(new double[]{400, 900, 300}),
            new MyPoint(new double[]{1000,600,400}),
            new MyPoint(new double[]{350,550, 350})
    );
    Timer myTimer = new Timer(DELAY, this);

    public GraphicsController() {
        myTimer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==myTimer){
            secondsPassed = (secondsPassed +1)% 10000 ;
            repaint();
        }
    }

    public void paintComponent(Graphics G) {
        Graphics2D G2D = (Graphics2D) G;
        super.paintComponent(G); // clears previous graphics

        double theta = 0.01*secondsPassed;
        double[][] rotMat = new double[][]{
                {1,0,0},
                {0,cos(theta),-sin(theta)},
                {0,sin(theta),cos(theta)}};
        MyShape myTrapezoidShape = new MyShape(trapezoid.lines);
        MyPoint vector = new MyPoint(new double[]{-350, -650, 0});

        MyShape shapeTranslatedToOrigin = MyShape.translateMyShapeByVector(myTrapezoidShape, new MyPoint(new double[]{-350, -650, 0}));
        MyShape transformedShape = MyShape.applyMatrix(rotMat, shapeTranslatedToOrigin);
        MyShape rotatedShapeSentBackToOriginalPosition = MyShape.translateMyShapeByVector(transformedShape, new MyPoint(new double[]{350, 650, 0}));

        MyShape projectedShape = projection(rotatedShapeSentBackToOriginalPosition);
        drawMyShape(projectedShape, G2D);
    }

    public void drawMyShape(MyShape myShape, Graphics2D G2D) {
        for (int i = 0; i < 12; ++i) {
            G2D.drawLine(
                    (int) myShape.lines[i].startCoordinate.position[0], (int) myShape.lines[i].startCoordinate.position[1],
                    (int) myShape.lines[i].endCoordinate.position[0], (int) myShape.lines[i].endCoordinate.position[1]
            );
        }
    }

    public MyShape projection(MyShape myTrapezoid) {
        MyLine[] myShapeMyLines = myTrapezoid.lines;
        MyShape projectedShape = new MyShape(myShapeMyLines.length);

        for (int i = 0; i < myShapeMyLines.length; ++i) {
            MyPoint startCoordinate = myShapeMyLines[i].startCoordinate;
            MyPoint endCoordinate = myShapeMyLines[i].endCoordinate;
            MyPoint projectedStartCoordinate = new MyPoint(
                    new double[]{startCoordinate.position[0], startCoordinate.position[1], 0});
            MyPoint projectedEndCoordinate = new MyPoint(
                    new double[]{endCoordinate.position[0], endCoordinate.position[1], 0});
            projectedShape.lines[i] = new MyLine(projectedStartCoordinate, projectedEndCoordinate);
        }
        return projectedShape;
    }
}
