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
	public VoronoiPanel(){
		super();
		placesToDraw = new ArrayList<>();
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
	}

	public void addPlaces(List<PlaceNormalized> placesToDraw){
		this.placesToDraw = placesToDraw;
		paintComponent(getGraphics());
	}
}
