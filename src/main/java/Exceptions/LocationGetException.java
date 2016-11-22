package Exceptions;

import java.io.IOException;

/**
 * Created by thechucklingatom on 11/22/2016.
 */
public class LocationGetException extends RuntimeException{
	public LocationGetException(IOException ex){
		super(ex);
	}
}
