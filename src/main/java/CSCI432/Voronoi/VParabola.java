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

    }

    public VParabola(VPoint s) {
        // @ToDo
    }

    public void setLeft(VParabola p) {
        p.left = p;
        p.parent = this;
    }

    public void setRight(VParabola p) {
        p.right = p;
        p.parent = this;
    }

    public VParabola getLeft() {
        return this.left;
    }

    public VParabola getRight() {
        return this.right;
    }

    static VParabola getLeftParent(VParabola p) {

    }

    static VParabola getRighParent(VParabola p) {

    }

    static VParabola getLeftChild(VParabola p) {

    }

    static VParabola getRightChild(VParabola p) {

    }
}
