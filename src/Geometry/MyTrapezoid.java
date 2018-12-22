package Geometry;
import java.util.Arrays;

import com.sun.deploy.util.ArrayUtil;

public class MyTrapezoid  {
    public MyPoint[] corners = new MyPoint[8];
    public MyLine[] lines = new MyLine[12];

    public MyTrapezoid(MyPoint originCorner, MyPoint adjacentCorner1, MyPoint adjacentCorner2, MyPoint adjacentCorner3) {

        MyTrapezium initialTrapezium = new MyTrapezium(originCorner, adjacentCorner1, adjacentCorner2);
        MyTrapezium shiftedTrapezium = new MyTrapezium(
                adjacentCorner3,
                adjacentCorner3.addPoint(adjacentCorner1.addPoint(originCorner.scalePoint(-1))),
                adjacentCorner3.addPoint(adjacentCorner2.addPoint(originCorner.scalePoint(-1)))
        );

        for (int i = 0; i < 4; ++i) {
            lines[i] = initialTrapezium.lines[i];
            lines[i + 4] = shiftedTrapezium.lines[i];
        }

        lines[8] = new MyLine(initialTrapezium.originCorner, shiftedTrapezium.originCorner);
        lines[9] = new MyLine(initialTrapezium.adjacentCorner1, shiftedTrapezium.adjacentCorner1);
        lines[10] = new MyLine(initialTrapezium.adjacentCorner2, shiftedTrapezium.adjacentCorner2);
        lines[11] = new MyLine(initialTrapezium.diametricCorner, shiftedTrapezium.diametricCorner);
    }


}
