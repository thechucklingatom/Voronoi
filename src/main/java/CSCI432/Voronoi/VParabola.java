package CSCI432.Voronoi;

/**
 * Created by Mathew Gostnell on 12/3/2016.
 */
public class VParabola {
    public boolean isLeaf;      // flag whether the node is Leaf or internal node
    public VPoint site;         // pointer to the focus point of parabola (when it is a parabola)
    public VEdge edge;          // pointer to the edge (when it is an edge)
    public VEvent cEvent;       // pointer to the event, when the arch appears (circle event)
    public VParabola parent;    // pointer to the parent node in tree
    private VParabola left;      // pointer to the left child node in tree
    private VParabola right;     // pointer to the right child node in tree


    public VParabola() {
        site = null; // site = 0;
        isLeaf = false;
        cEvent = null; // cEvent = 0;
        edge = null; // edge = 0;
        parent = null; // parent = 0;
    }

    public VParabola(VPoint s) {
        site = s;
        isLeaf = true;
        cEvent = null;
        edge = null;
        parent = null;
    }

    public VParabola left(){
        return this.left;
    }

    public VParabola right() {
        return this.right;
    }

    public void setLeft(VParabola p) {
        left = p;
        p.parent = this;
    }

    public void setRight(VParabola p) {
        right = p;
        p.parent = this;
    }

    public VParabola getLeft(VParabola p) {
        return getLeftChild(getLeftParent(p));
    }

    public VParabola getRight(VParabola p) {
        return getRightChild(getRightParent(p));
    }

    static VParabola getLeftParent(VParabola p) {
        VParabola par = p.parent;
        VParabola pLast = p;
        while (p.left() == pLast) {
            if (p.parent == null) {
                return null;
            }
            pLast = par;
            par = par.parent;
        }
        return par;
    }

    static VParabola getRightParent(VParabola p) {
        VParabola par = p.parent;
        VParabola pLast = p;
        while (par.right() == pLast) {
            if (p.parent == null) {
                return null;
            }
            pLast = par;
            par = par.parent;
        }
        return par;
    }

    static VParabola getLeftChild(VParabola p) {
        if (p == null) { // if (!p) return 0;
            return null;
        }
        VParabola par = p.left();
        while (!par.isLeaf) {
            par = par.right();
        }
        return par;
    }

    static VParabola getRightChild(VParabola p) {
        if (p == null) {
            return null;
        }
        VParabola par = p.right();
        while (!par.isLeaf) {
            par = par.left();
        }
        return par;
    }
}
