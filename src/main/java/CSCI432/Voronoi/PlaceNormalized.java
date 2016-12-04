package CSCI432.Voronoi;

import static java.lang.StrictMath.abs;
import static java.lang.StrictMath.asin;
import static java.lang.StrictMath.sin;

/**
 * Created by thechucklingatom on 12/3/2016.
 */
public class PlaceNormalized {
	private String placeName;
	private double lat;
	private double lng;

	public PlaceNormalized(PlacesLocation location,
			double maxLat,
			double maxLng,
			double minLat,
			double minLng) {
		placeName = location.getName();
		lat = location.getGeometry().getLocation().getLat();
		lng = location.getGeometry().getLocation().getLng();
		normalize(maxLat, maxLng, minLat, minLng);
	}

	private void normalize(double maxLat, double maxLng, double minLat, double minLng) {
		lat = (lat - minLat) / (maxLat - minLat);
		lng = (lng - minLng) / (maxLng - minLng);
	}

	@Override
	public String toString() {
		return placeName + " Lat:" + lat + " Lng: " + lng;
	}

	public String getPlaceName() {
		return placeName;
	}

	public double getLat() {
		return lat;
	}

	public double getLng() {
		return lng;
	}
}
