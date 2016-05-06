package voicefb;

import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Version;
import com.restfb.types.User;


public class FbManager {

	public static String MY_APP_SECRET = "your app secret";
	public static String MY_APP_ID = "your app id";
	
	public static String MY_ACCESS_TOKEN = "your access token";
	public static FacebookClient facebookClient = new DefaultFacebookClient(MY_ACCESS_TOKEN, Version.LATEST);
	
}

