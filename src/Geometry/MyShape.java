package Geometry;

import Maths.Matrix;

public class MyShape {
    public MyLine[] lines;

    public MyShape(int size) {
        this.lines = new MyLine[size];
    }

    public MyShape(MyLine[] myLines) {
        this.lines = myLines;
    }

    public static MyShape applyMatrix(double[][] M, MyShape shape) {
        MyLine[] lines = shape.lines;
        MyLine[] rotatedLines =  new MyLine[lines.length];

        for (int i = 0; i < lines.length; ++i) {
            MyPoint[] line = new MyPoint[]{lines[i].startCoordinate, lines[i].endCoordinate};

            MyPoint startCoordinate = lines[i].startCoordinate;
            MyPoint endCoordinate = lines[i].endCoordinate;

            MyPoint rotatedStartCoordinate = new MyPoint(Matrix.multiply(M, new double[]{
                    startCoordinate.position[0],
                    startCoordinate.position[1],
                    startCoordinate.position[2]
            }));
            MyPoint rotatedEndCoordinate = new MyPoint(Matrix.multiply(M, new double[]{
                    endCoordinate.position[0],
                    endCoordinate.position[1],
                    endCoordinate.position[2]
            }));

            MyLine rotatedLine = new MyLine(rotatedStartCoordinate, rotatedEndCoordinate);
            rotatedLines[i]  = rotatedLine;
        }
        return new MyShape(rotatedLines);
    }

    public static MyShape translateMyShapeByVector(MyShape myShape, MyPoint vector) {
        MyLine[] lines = myShape.lines;
        MyLine[] translatedLines = new MyLine[lines.length];

        for (int i = 0; i < lines.length; ++i) {
            translatedLines[i] = new MyLine(
                    lines[i].startCoordinate.addPoint(vector),
                    lines[i].endCoordinate.addPoint(vector)
            );
        }
        return new MyShape(translatedLines);
    }
}
