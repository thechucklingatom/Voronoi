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
	List<PlacesLocation> placesToDraw;
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
		for(PlacesLocation place : placesToDraw){
			double x = place.getDrawLat();
			double y = place.getDrawLng();
			graphics.fillOval((int)x, (int)y, 10, 10);
		}

		for(VEdge edge : edges){
			graphics.drawLine((int)(edge.start.x * 500), (int)(edge.start.y * 500), (int)(edge.end.x * 500), (int)(edge.end.y * 500));
		}

	}

	public void addPlaces(List<PlacesLocation> placesToDraw){
		this.placesToDraw = placesToDraw;
		paintComponent(getGraphics());
	}

	public void addEdges(List<VEdge> edges){
		this.edges = edges;
		paintComponent(getGraphics());
	}
}
