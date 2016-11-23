package CSCI432.Voronoi;

/**
 * Created by thechucklingatom on 11/22/2016.
 */
public class MapLocation {

	private String formatted_address;
	private String place_id;
	private Geometry geometry;

	public String getFormatted_address() {
		return formatted_address;
	}

	public void setFormatted_address(String formatted_address) {
		this.formatted_address = formatted_address;
	}

	public String getPlace_id() {
		return place_id;
	}

	public void setPlace_id(String place_id) {
		this.place_id = place_id;
	}

	public Geometry getGeometry() {
		return geometry;
	}

	public void setGeometry(Geometry geometry) {
		this.geometry = geometry;
	}
}
