package CSCI432.Voronoi;

import Exceptions.LocationGetException;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by thechucklingatom on 11/20/2016.
 */
public class MapsAPIAccesor {
	private final String API_KEY = "lol";
	private final String MAP_ENDPOINT = "https://maps.googleapis.com/maps/api/geocode/json?address=";
	private final String PLACES_ENDPOINT = "https://maps.googleapis.com/maps/api/place/";
	private OkHttpClient CLIENT = new OkHttpClient();
	//TODO Add logic for accessing the google maps api for location and pictures.

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
}
