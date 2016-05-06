import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.io.PrintWriter;
import java.io.FileReader;
import java.net.*;
import java.net.URL;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


import javax.net.ssl.HttpsURLConnection;

public class ReadNews {
  private final String url = "http://api.nytimes.com/svc/topstories/v1/world.json?api-key=63be839b41bb226b895aa719d66a75f9:7:75145082"; 

  public static void main (String[] args) {
    ReadNews readData = new ReadNews(); 
    String s = ""; 
    try {
    s = readData.sendGet();
    //System.out.println(s); 
    JSONParser jsonParser = new JSONParser();
    JSONObject jsonObject = (JSONObject) jsonParser.parse(s);
    JSONArray list = (JSONArray) jsonObject.get("results"); 
    //System.out.println("=========================================="); 
    //System.out.println(list); 
    JSONObject obj = (JSONObject) list.get(0); 
    //System.out.println(obj); 

    for (int i = 0; i < 10; i++) {
      JSONObject obj2 = (JSONObject) list.get(i); 
      String obj3 = (String) obj2.get("title");
      System.out.println(obj3); 
    }
    /*JSONObject obj2 = (JSONObject) obj.get("main"); 
    JSONArray arr = (JSONArray) obj.get("weather"); 
    JSONObject obj3 = (JSONObject) arr.get(0); 
    String desc = (String) obj3.get("description");
    //System.out.println(obj2);
    Double temp = (Double) obj2.get("temp"); 
    Double temp_min = (Double) obj2.get("temp_min"); 
    Double temp_max = (Double) obj2.get("temp_max");
    long newTemp = Math.round(temp - 273.0);
    long newTemp_Min = Math.round(temp_min - 273.0);
    long newTemp_Max = Math.round(temp_max - 273.0);
    System.out.println("Temperature outside today is " + newTemp + " Celcius"); 
    System.out.println("Expecting " + desc); 
    System.out.println("Low for today is " + newTemp_Min + " Celcius"); 
    System.out.println("High for today is " + newTemp_Max + " Celcius"); */
  } catch (Exception e) {
    e.printStackTrace(); 
  }
}

   public String sendGet() throws Exception {
    String s = ""; 
    URL obj = new URL(url); 
    HttpURLConnection con = (HttpURLConnection) obj.openConnection(); 
    con.setRequestMethod("GET");
    BufferedReader rd = new BufferedReader(new InputStreamReader(con.getInputStream())); 
    String line; 
    while ((line = rd.readLine()) != null) {
      s += line; 
    }
    rd.close(); 
    return s; 
  }

}
