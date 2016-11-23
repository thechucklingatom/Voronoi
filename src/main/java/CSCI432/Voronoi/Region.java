package CSCI432.Voronoi;

import java.util.List;
import java.util.ArrayList;

/**
 * Created by Mathew Gostnell on 11/23/2016.
 */
public class Region {
    private static int numRegions = 0;
    private List<Point> contains;
    private Site center;
    private int id;

    private double minXPos;
    private double maxXPos;
    private double minYPos;
    private double maxYPos;

    public Region(Site center, ArrayList<Point> contains) {
        this.center = center;
        this.contains = contains;
        id = numRegions++;


    }

    private void updateRegion(Point addition) {
        if (addition.getxPos() < minXPos) {
            minXPos = addition.getxPos();
        }
        if (addition.getxPos() > maxXPos) {
            maxXPos = addition.getxPos();
        }


    }
}
