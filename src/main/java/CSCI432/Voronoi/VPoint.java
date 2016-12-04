package CSCI432.Voronoi;

/**
 * Created by Mathew Gostnell on 12/3/2016.
 */
public class VPoint {

    public double x, y;

    /**
     * @param nx double := x position of point
     * @param ny double := y position of point
     */
    public VPoint(double nx, double ny) {
        x = nx;
        y = ny;
    }

    public String toPrintString() {
        return "(" + x + ", " + y + ")";
    }

    public void printVPoint() {
        System.out.println(this.toPrintString());
    }
}
