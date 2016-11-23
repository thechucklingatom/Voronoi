package CSCI432.Voronoi;

/**
 * Created by Mathew Gostnell on 11/23/2016.
 */
public class Line {
    private static int numLines = 0;
    private Point start;
    private Point end;
    private double slope;
    private int id;

    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
        id = numLines++;

        calculateSlope();
    }

    /* Helper method called to update slope before accessing it. */
    private void calculateSlope() { slope = (end.getyPos() - start.getyPos()) / (end.getxPos() - start.getxPos()); }

    /*~~~~~~~~~~~GETTER METHODS~~~~~~~~~~~*/
    public Point getStart() { return start; }
    public Point getEnd() { return end; }
    public double getSlope() { return slope; }

    /*~~~~~~~~~~~SETTER METHODS~~~~~~~~~~~*/
    public void setStart(Point start) { this.start = start; calculateSlope(); }
    public void setEnd(Point end) { this.end = end; calculateSlope(); }

    /*~~~~~~~~~~~PRINT METHODS~~~~~~~~~~~*/
    public String toString() { return "{" + id + "}[" + start.toString() + ", " + end.toString() + "]"; }
    public void printLine() { System.out.println(toString()); }
}
