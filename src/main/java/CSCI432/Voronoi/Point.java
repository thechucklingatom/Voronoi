package CSCI432.Voronoi;

/**
 * Created by Mathew Gostnell on 11/23/2016.
 */
public class Point {
    private static int numPoints = 0;
    private double xPos;
    private double yPos;
    private int id;

    public  Point(double xPos, double yPos) {
        this.xPos = xPos;
        this.yPos = yPos;
        id = numPoints++;
    }
    /*~~~~~~~~~~~GETTER METHODS~~~~~~~~~~~*/
    public double getxPos() { return xPos; }
    public double getyPos() { return yPos; }
    public int getId() { return id; }
    public int getNumPoints() { return numPoints; }

    /*~~~~~~~~~~~SETTER METHODS~~~~~~~~~~~*/
    public void setxPos(double xPos) { this.xPos = xPos; }
    public void setyPos(double yPos) { this.yPos = yPos; }

    /*~~~~~~~~~~~PRINT METHODS~~~~~~~~~~~*/
    @Override
    public String toString() { return "[" + id + "](" + xPos + ", " + yPos + ")";}
    public void printPoint() { System.out.println(toString()); }
}
