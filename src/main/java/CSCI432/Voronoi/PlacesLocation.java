package CSCI432.Voronoi;

/**
 * Created by thechucklingatom on 11/22/2016.
 * @author thechucklingatom
 */
public class PlacesLocation {
	private Geometry geometry;
	private String name;
	private double drawLat;
	private double drawLng;

	public Geometry getGeometry() {
		return geometry;
	}

	public void setGeometry(Geometry geometry) {
		this.geometry = geometry;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getDrawLat() {
		return drawLat;
	}

	public double getDrawLng() {
		return drawLng;
	}

	public void calculateDraw(double minLat, double maxLat, double minLng, double maxLng){
		drawLat = (500) * (getGeometry().getLocation().getLat() - minLat) / (maxLat - minLat);
		drawLng = (250) * (getGeometry().getLocation().getLng() - minLng) / (maxLng - minLng);

		if(drawLat < 0){
			drawLat =  -drawLat;
		}

		if(drawLng < 0){
			drawLng = -drawLng;
		}
	}
}
