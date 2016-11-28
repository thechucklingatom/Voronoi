package CSCI432.Voronoi;

import Exceptions.LocationGetException;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.awt.Image;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

/**
 * Created by thechucklingatom on 11/20/2016.
 * @author thechucklingatom
 */
public class MapsAPIAccesor {
	private final String API_KEY = "lol";
	private final String MAP_ENDPOINT = "https://maps.googleapis.com/maps/api/geocode/json?address=";
	private final String PLACES_ENDPOINT = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?";
	private final String MAP_IMAGE_ENDPOINT = "https://maps.googleapis.com/maps/api/staticmap?";
	private OkHttpClient CLIENT = new OkHttpClient();

	/**
	 * Get a location based on a city/address
	 * @param locationToFind city name that you want to find.
	 * @return {@link List} of {@link MapLocation} locations that match that city name.
	 */
	public List<MapLocation> getLocation(String locationToFind){
		Gson desirializer = new GsonBuilder().create();
		String url = String.format("%s%s", MAP_ENDPOINT, locationToFind);
		JsonObject jsonObject = getJsonObject(url, desirializer);
		ArrayList<MapLocation> toReturn = new ArrayList<>();

		for(JsonElement element : jsonObject.get("results").getAsJsonArray()){
			MapLocation toAdd = desirializer.fromJson(element, MapLocation.class);
			toReturn.add(toAdd);
		}

		return toReturn;
	}

	/**
	 * Gets the JsonObject at the passed url
	 * @param url The url we are getting the Json from
	 * @param desirializer The object we are using to desirialize the Json object into one of our
	 * classes.
	 * @return The {@link JsonObject} representation of our data.
	 */
	private JsonObject getJsonObject(String url, Gson desirializer){
		Request request = new Request.Builder().url(url).build();
		Response response;
		try{
			response = CLIENT.newCall(request).execute();
			return desirializer.fromJson(response.body().string(), JsonObject.class);
		} catch (IOException ex){
			throw new LocationGetException(ex);
		}
	}

	/**
	 * Get the locations around a city/address
	 * @param city The {@link MapLocation} that holds the
	 * @param radius The radius around the location that you want to look for places. Max 50,000
	 * @param types The {@link List} of strings that has all the types that the person is searching
	 * for.
	 * @return The {@link List} of {@link PlacesLocation} that are returned by the search.
	 */
	public List<PlacesLocation> getPlaces(MapLocation city, int radius, List<String> types){
		Gson desirializer = new GsonBuilder().create();
		String url = String.format("%s", PLACES_ENDPOINT);
		url += String.format("location=%f,%f", city.getGeometry().getLocation().getLat(),
				city.getGeometry().getLocation().getLng());
		url += String.format("&radius=%d", radius);
		url += "&types=";
		for(String type : types){
			url += type + '|';
		}
		//remote redundant or at the end
		url = new StringBuilder(url).deleteCharAt(url.length() - 1).toString();

		url += String.format("&key=%s", API_KEY);

		JsonObject jsonObject = getJsonObject(url, desirializer);
		ArrayList<PlacesLocation> toReturn = new ArrayList<>();

		for(JsonElement element : jsonObject.get("results").getAsJsonArray()){
			PlacesLocation toAdd = desirializer.fromJson(element, PlacesLocation.class);
			toReturn.add(toAdd);
		}

		return toReturn;
	}

	/**
	 * Gets the map image based on a city that you are looking for.
	 * @param city The city to find the map of.
	 * @return The {@link Image} that was returned by the api.
	 */
	public Image getMapImage(String city){
		try {
			return ImageIO.read(new URL(MAP_IMAGE_ENDPOINT
					+ "center="
					+ city.replaceAll(" ", "+")
					+ "&zoom=10&size=x&scale=1"));
		} catch (MalformedURLException ex){
			return null;
		} catch (IOException ex){
			return null;
		}
	}
}
