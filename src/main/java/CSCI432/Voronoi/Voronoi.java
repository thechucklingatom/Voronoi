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
        edges = null;
    }

    public List<VEdge> getEdges(List<VPoint> v, int w, int h) {
        places = v;
        width = w;
        height = h;
        root = null;

        if (edges == null) {
            edges = new ArrayList<VEdge>();
        } else {
            points.clear();
            edges.clear();
        }

        for (VPoint place : places) {
            queue.add(new VEvent(place, true));
            Collections.sort(queue); // sort based on priority handled in compareTo
        }

        VEvent e;
        while (!queue.isEmpty()) {
            e = queue.remove(0); // pop the top of queue
            ly = e.point.y;
            if (deleted.contains(e) == true && e != deleted.get(deleted.size() - 1)) {
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

        /*
        for(Edges::iterator i = edges->begin(); i != edges->end(); ++i)
	    {
		    if( (*i)->neighbour)
		    {
			    (*i)->start = (*i)->neighbour->end;
			    delete (*i)->neighbour;
		    }
	    }
         */

        for (VEdge edge : edges) {
            if (edge.neighbor != null) {
                edge.start = edge.neighbor.end;
                // delete (*i)->neighbor
            }
        }

        return edges;
    }
}
