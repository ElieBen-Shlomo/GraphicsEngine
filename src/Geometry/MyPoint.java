package Geometry;


public class MyPoint {
    private int DIMENSION = 3;
    public double[] position;

    public MyPoint(double[] position) {
        if (position.length != DIMENSION) {
            throw new ArrayIndexOutOfBoundsException("DIMENSION ERROR IN POINT CONSTRUCTOR");
        }
        this.position = position;
    }

    public MyPoint addPoint(MyPoint point) {
        double x = this.position[0] + point.position[0];
        double y = this.position[1] + point.position[1];
        double z = this.position[2] + point.position[2];
        return new MyPoint(new double[]{x,y,z});
    }

    public MyPoint scalePoint(double scalingFactor) {
        return new MyPoint(new double[]{
                scalingFactor*this.position[0],
                scalingFactor*this.position[1],
                scalingFactor*this.position[2]
        });
    }
}
