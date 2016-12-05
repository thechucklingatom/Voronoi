package CSCI432.Voronoi;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

/**
 * Created by thechucklingatom on 12/4/2016.
 * @author thechucklingatom
 */
public class VoronoiPanel extends JPanel{
	List<PlaceNormalized> placesToDraw;
	List<VEdge> edges;
	public VoronoiPanel(){
		super();
		placesToDraw = new ArrayList<>();
		edges = new ArrayList<>();
		setOpaque(false);
	}

	@Override
	protected void paintComponent(Graphics graphics){
		super.paintComponent(graphics);
		for(PlaceNormalized place : placesToDraw){
			double x = place.getLatDraw();
			double y = place.getLngDraw();
			graphics.fillOval((int)x, (int)y, 10, 10);
		}

		for(VEdge edge : edges){
			graphics.drawLine((int)edge.start.x, (int)edge.start.y, (int)edge.end.x, (int)edge.end.y);
		}

	}

	public void addPlaces(List<PlaceNormalized> placesToDraw){
		this.placesToDraw = placesToDraw;
		paintComponent(getGraphics());
	}

	public void addEdges(List<VEdge> edges){
		this.edges = edges;
		paintComponent(getGraphics());
	}
}
