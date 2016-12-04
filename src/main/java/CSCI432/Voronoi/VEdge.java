package CSCI432.Voronoi;

/**
 * Created by Mathew Gostnell on 12/3/2016.
 */
public class VEdge {

    public VPoint start;
    public VPoint end;
    public VPoint direction;
    public VPoint left;
    public VPoint right;

    public double f;
    public double g;

    public VEdge neighbor;

    public VEdge(VPoint s, VPoint a, VPoint b) {
        start = s;
        left = a;
        right = b;
        neighbor = null;
        end = null;

        f = (b.x - a.x) / (a.y - b.y);
        g = s.y - f * s.x;
        direction = new VPoint(b.y - a.y, -(b.x - a.x));
    }

    public String toPrintString() {
        return "VEdge:\n\tstart:\t" + start.toPrintString() +
                "\n\tend:\t" + end.toPrintString() +
                "\n\tdirct:\t" + direction.toPrintString() +
                "\n\tleft:\t" + left.toPrintString() +
                "\n\tright:\t" + right.toPrintString();
    }

    public void printVEdge() {
        System.out.println(this.toPrintString());
    }

    @Override
    public String toString(){
        return "VEdge:\n\tstart:\t" + start.toPrintString() +
                "\n\tend:\t" + end.toPrintString() +
                "\n\tdirct:\t" + direction.toPrintString() +
                "\n\tleft:\t" + left.toPrintString() +
                "\n\tright:\t" + right.toPrintString();
    }
}
