package CSCI432.Voronoi;

/**
 * Created by thechucklingatom on 12/3/2016.
 */
public class PlaceNormalized {
	private String placeName;
	private double lat;
	private double lng;

	public PlaceNormalized(PlacesLocation location){
		placeName = location.getName();
		lat = location.getGeometry().getLocation().getLat();
		lng = location.getGeometry().getLocation().getLng();
		normalize();
	}

	private void normalize(){
		lat = Math.sin(lat);
		lng = Math.sin(lng);
	}

	@Override
	public String toString(){
		return placeName + " Lat:" + lat + " Lng: " + lng;
	}
}
