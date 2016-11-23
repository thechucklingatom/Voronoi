package CSCI432.Voronoi;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by thechucklingatom on 11/22/2016.
 *
 * @author thechucklingatom
 */

public class TestMapsAPIAccessor {

	@Test
	public void testGetLocation(){
		MapsAPIAccesor mapsAPIAccesor = new MapsAPIAccesor();

		List<MapLocation> mapLocationList = mapsAPIAccesor.getLocation("Bozeman");

		assertTrue(mapLocationList.size() == 1);

		MapLocation mapLocation = mapLocationList.get(0);

		assertTrue(mapLocation.getFormatted_address().equals("Bozeman, MT, USA"));
		assertTrue(mapLocation.getPlace_id().equals("ChIJE4i6T0xERVMRqmA792TQ9WM"));
		assertTrue(mapLocation.getGeometry().getLocation().getLat() == 45.6769979);
		assertTrue(mapLocation.getGeometry().getLocation().getLng() == -111.0429339);

		assertTrue(mapLocation.getGeometry().getBounds().getNortheast().getLat() == 45.72422400000001);
		assertTrue(mapLocation.getGeometry().getBounds().getNortheast().getLng() == -110.983769);

		assertTrue(mapLocation.getGeometry().getBounds().getSouthwest().getLat() == 45.634789);
		assertTrue(mapLocation.getGeometry().getBounds().getSouthwest().getLng() == -111.1180349);

		assertTrue(mapsAPIAccesor.getLocation("Troy").size() > 1);
	}

	@Test
	public void testGetPlaces(){
		MapsAPIAccesor mapsAPIAccesor = new MapsAPIAccesor();
		MapLocation mapLocation = new MapLocation();

		mapLocation.setGeometry(new Geometry());
		mapLocation.getGeometry().setLocation(new Location());
		mapLocation.getGeometry().getLocation().setLat(45.6769979);
		mapLocation.getGeometry().getLocation().setLng(-111.0429339);

		ArrayList<String> typesList = new ArrayList<>();
		typesList.add("food");

		List<PlacesLocation> placesLocationList = mapsAPIAccesor.getPlaces(mapLocation, 500,
				typesList);

		assertTrue(placesLocationList.size() > 1);
	}
}
