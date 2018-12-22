package Geometry;

public class MyTrapezium {
    public MyLine[] lines = new MyLine[4];
    public MyPoint originCorner, adjacentCorner1, adjacentCorner2, diametricCorner;

    public MyTrapezium(MyPoint originCorner, MyPoint adjacentCorner1, MyPoint adjacentCorner2) {

        this.originCorner = originCorner;
        this.adjacentCorner1 = adjacentCorner1;
        this.adjacentCorner2 = adjacentCorner2;

        // diametricCorner = originCorner + (adjCorner1 - originCorner) + (adjCorner2 - originCorner)
        this. diametricCorner = originCorner.addPoint(
          adjacentCorner1.addPoint(originCorner.scalePoint(-1)).addPoint(
                  adjacentCorner2.addPoint(originCorner.scalePoint(-1))));

        lines[0] = new MyLine(originCorner, adjacentCorner1);
        lines[1] = new MyLine(originCorner, adjacentCorner2);
        lines[2] = new MyLine(adjacentCorner1, diametricCorner);
        lines[3] = new MyLine(adjacentCorner2, diametricCorner);
    }

    public MyLine[] getLines() {
        return lines;
    }
}
