package CSCI432.Voronoi;

/**
 * Created by Mathew Gostnell on 12/3/2016.
 */



public class VEvent implements Comparable<VEvent> {
    public VPoint point;
    public boolean pe;
    public double y;
    public VParabola arch;

    public VEvent(VPoint p, boolean pev) {
        point = p;
        pe = pev;
        y = p.y;
        arch = null;
    }

    /*
    Convert to Java
    struct CompareEvent : public std::binary_function<VEvent*, VEvent*, bool>
    {
        bool operator() (const VEvent* l. const VEvent* r) const { return (l->y < r->y); }
    };
     */

    public int compareTo(VEvent other) {
        /*
            return < 0  : this is before other
            return > 0  : this is after other
            return = 0  : these have same value
         */
        int value = (int)(this.y - other.y);
        if (value > 0) { // this point is above other
            return -1;
        } else if (value < 0) { // this point is below other
            return 1;
        } else { // they have the same height
            return 0;
        }
    }
};
