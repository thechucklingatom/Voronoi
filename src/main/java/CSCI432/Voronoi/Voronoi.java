package CSCI432.Voronoi;

/**
 * Created by Robert Putnam on 11/20/2016.
 */

import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

public class Voronoi {
    //TODO add logic for making voronoi diagram in java

    List<VPoint> vertices;
    private List<VEdge> edges;
    private List<VPoint> points;    // list of all new points that were created during the algorithm
    private List<VPoint> places;    //
    private List<VEvent> queue;     // holds VEvents with y-pos as its priority
    private List<VEvent> deleted;   // holds set of deleted (false) Events.
    private double width, height;   // height, width of the diagram we are drawing
    private VParabola root;         // used to track the beachline in this algorithm
    double ly;

    public Voronoi() {
        /*
            The only public function for generating a diagram

            input:
                v   : Vertices - places for drawing a diagram
                w   : width of the result (top left corner is (0,0))
                h   : height of the result
            output:
                pointer to list of edges

            All the data structures are managed by this class
         */
        edges = null; // call getEdges() to get the generated diagram
    } // end Voronoi();

    public List<VEdge> getEdges(List<VPoint> v, int w, int h) {
        places = v;
        width = w;
        height = h;
        root = null;

        if (edges == null) {
            edges = new ArrayList<>();
        } else {
            points.clear();
            edges.clear();
        }

        for (VPoint place : places) {
            // queue.push(new VEvent(place, true));
            queue.add(new VEvent(place, true));
            Collections.sort(queue); // sort based on priority handled in compareTo
        }

        VEvent e;
        while (!queue.isEmpty()) {
            e = queue.remove(0); // pop the top of queue
            ly = e.point.y;
            // if(deleted.find(e) != deleted.end()) { delete(e); deleted.erase(e); continue;)
            if (deleted.contains(e) && !e.equals(deleted.get(deleted.size() - 1))) {
                // delete(e);
                deleted.remove(e);
                continue;
            }

            if (e.pe) {
                insertParabola(e.point);
            } else {
                removeParabola(e);
                // delete(e);
            }
        }

        finishEdge(root);

        //
        //for(Edges::iterator i = edges->begin(); i != edges->end(); ++i)
        //{
        //  if( (*i)->neighbour)
        //  {
        //      (*i)->start = (*i)->neighbour->end;
        //      delete (*i)->neighbour;
        //  }
        //}
        //

        for (VEdge edge : edges) {
            if (edge.neighbor != null) {
                edge.start = edge.neighbor.end;
                // delete (*i)->neighbor
            }
        }

        return edges;
    } // end getEdges(...);

    private void insertParabola(VPoint p) {
        if (root == null) {
            root = new VParabola(p);
            return;
        }

        if (root.isLeaf && root.site.y - p.y < 1) {
            VPoint fp = root.site;
            root.isLeaf = false;
            root.setLeft(new VParabola(fp));
            root.setRight(new VParabola(p));
            VPoint s = new VPoint((p.x + fp.x) / 2, height);
            points.add(s);

            if (p.x > fp.x) {
                root.edge = new VEdge(s, fp, p);
            } else {
                root.edge = new VEdge(s, p, fp);
            }
            edges.add(root.edge); // edges.pushback(root->edge)
            return;
        }

        VParabola par = getParabolaByX(p.x);

        if (par.cEvent != null) { // if(par->cEvent)
            deleted.add(par.cEvent); // deleted.insert(par->cEvent)
            par.cEvent = null; // par->cEvent = 0;
        }

        VPoint start = new VPoint(p.x, getY(par.site, p.x));
        points.add(start); // points.pushback(start)

        VEdge e1 = new VEdge(start, par.site, p);
        VEdge er = new VEdge(start, p, par.site);

        e1.neighbor = er;
        edges.add(e1); // edges->push_back(e1)

        par.edge = er;
        par.isLeaf = false;

        VParabola p0 = new VParabola(par.site);
        VParabola p1 = new VParabola(p);
        VParabola p2 = new VParabola(par.site);

        par.setRight(p2);
        par.setLeft(new VParabola());
        par.left().edge = e1; // par->Left()->edge = e1

        par.left().setLeft(p0);
        par.left().setRight(p1);

        checkCircle(p0);
        checkCircle(p2);
    } // end insertParabola(...);

    private void removeParabola(VEvent e) {
        VParabola p1 = e.arch;

        VParabola x1 = VParabola.getLeftParent(p1);
        VParabola xr = VParabola.getRightParent(p1);

        VParabola p0 = VParabola.getLeftChild(x1);
        VParabola p2 = VParabola.getRightChild(xr);

        if (p0.equals(p2)) {
            System.out.println("error - right and left parabola has the same focal point!");
        }

        if (p0.cEvent != null) {
            deleted.add(p0.cEvent); //deleted.insert...
            p0.cEvent = null;
        }

        if (p2.cEvent != null) {
            deleted.add(p2.cEvent); // deleted.insert...
            p2.cEvent = null;
        }

        VPoint p = new VPoint(e.point.x, getY(p1.site, e.point.x));
        points.add(p); // points.push_back(p);

        x1.edge.end = p;
        xr.edge.end = p;

        VParabola higher = p1; // higher; might not be defined error
        VParabola par = p1;

        while (!par.equals(root)) {
            par = par.parent;
            if (par.equals(x1)) {
                higher = x1;
            }
            if (par.equals(xr)) {
                higher = xr;
            }
        }
        higher.edge = new VEdge(p, p0.site, p2.site);
        edges.add(higher.edge); // edges->push_back(higer->edge)

        VParabola gParent = p1.parent.parent;
        if (p1.parent.left().equals(p1)) {
            if (gParent.left().equals(p1.parent)) {
                gParent.setLeft(p1.parent.right());
            }
            if (gParent.right().equals(p1.parent)) {
                gParent.setRight(p1.parent.right());
            }
        } else {
            if (gParent.left().equals(p1.parent)) {
                gParent.setLeft(p1.parent.left());
            }
            if (gParent.right().equals(p1.parent)) {
                gParent.setRight(p1.parent.left());
            }
        }

        // delete p1->parent
        // delete p1;

        checkCircle(p0);
        checkCircle(p2);
    } // end removeParabola(...);

    private void finishEdge(VParabola n) {
        if (n.isLeaf) {
            // delete n;
            return;
        }
        double mx;
        if (n.edge.direction.x > 0.0) {
            mx = Math.max(width, n.edge.start.x + 10); // not sure about this constant
        } else {
            mx = Math.min(0.0, n.edge.start.x - 10); // not sure about this constant
        }

        VPoint end = new VPoint(mx, mx * n.edge.f + n.edge.g);
        n.edge.end = end;
        points.add(end); // points.push_back(end);

        finishEdge(n.left());
        finishEdge(n.right());
        // delete n;
    } // emd finishEdge(...);

    private double getXOfEdge(VParabola par, double y) {
        VParabola left = VParabola.getLeftChild(par);
        VParabola right = VParabola.getRightChild(par);

        VPoint p = left.site;
        VPoint r = right.site;

        double dp = 2.0 * (p.y - y);
        double a1 = 1.0 / dp;
        double b1 = -2.0 * p.x / dp;
        double c1 = y + dp / 4 + p.x * p.x / dp;

        dp = 2.0 * (r.y - y);
        double a2 = 1.0 / dp;
        double b2 = -2.0 * r.x / dp;
        double c2 = ly + dp / 4 + r.x * r.x / dp;

        double a = a1 - a2;
        double b = b1 - b2;
        double c = c1 - c2;

        double disc = b * b - 4 * a * c;
        double x1 = (-b + Math.sqrt(disc)) / (2 * a);
        double x2 = (-b - Math.sqrt(disc)) / (2 * a);

        double ry;
        if (p.y < r.y) {
            ry = Math.max(x1, x2);
        } else {
            ry = Math.min(x1, x2);
        }

        return ry;
    } // end getXOfEdge(...);

    private VParabola getParabolaByX(double xx) {
        VParabola par = root;
        double x = 0.f;

        while (!par.isLeaf) { // I walk the tree until you find a suitable sheet
            x = getXOfEdge(par, ly);
            if (x > xx) {
                par = par.left();
            } else {
                par = par.right();
            }
        }
        return par;
    } // end getParabolaByX(...);

    private double getY(VPoint p, double x) {
        double dp = 2 * (p.y - ly);
        double a1 = 1 / dp;
        double b1 = -2 * p.x / dp;
        double c1 = ly + dp / 4 + p.x * p.x / dp;

        return (a1 * x * x + b1 * x + c1);
    } // end getY(...);

    private void checkCircle(VParabola b) {
        VParabola lp = VParabola.getLeftParent(b);
        VParabola rp = VParabola.getRightParent(b);

        VParabola a = VParabola.getLeftChild(lp);
        VParabola c = VParabola.getRightChild(rp);

        if (a == null || c == null || a.site == c.site) {
            // if(!a || !c || a->site == c->site
            return;
        }
        VPoint s = null;
        s = getEdgeIntersection(lp.edge, rp.edge);
        if (s == null) { // if (s == 0)
            return;
        }

        double dx = a.site.x - s.x;
        double dy = a.site.y - s.y;

        double d = Math.sqrt((dx * dx) + (dy * dy));

        if (s.y - d >= ly) {
            return;
        }

        VEvent e = new VEvent(new VPoint(s.x, s.y - d), false);
        points.add(e.point); // points.push_back(e->point);
        b.cEvent = e;
        e.arch = b;
        queue.add(e);
        Collections.sort(queue);
    } // end checkCircle(...);

    private VPoint getEdgeIntersection(VEdge a, VEdge b) {
        double x = (b.g - a.g) / (a.f - b.f);
        double y = a.f * x + a.g;

        if ((x - a.start.x) / a.direction.x < 0) {
            return null; // return 0;
        }
        if ((y - a.start.y) / a.direction.y < 0) {
            return null; // return 0;
        }
        if ((x - b.start.x) / b.direction.x < 0) {
            return null; // return 0;
        }
        if ((y - b.start.y) / b.direction.y < 0) {
            return null; // return 0;
        }

        VPoint p = new VPoint(x, y);
        points.add(p); // points.push_back(p);
        return p;
    } // end getEdgeIntersection(...);
}