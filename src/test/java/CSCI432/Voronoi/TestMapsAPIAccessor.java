package CSCI432.Voronoi;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Created by thechucklingatom on 11/22/2016.
 */

public class TestMapsAPIAccessor {

	@Test
	public void testGetLocation(){
		MapsAPIAccesor mapsAPIAccesor = new MapsAPIAccesor();
		assertTrue(mapsAPIAccesor.getLocation("Bozeman").size() == 1);
	}
}
