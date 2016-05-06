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

public class ReadData {
  private final String url = "http://api.openweathermap.org/data/2.5/forecast/city?id=4560349&APPID=8814f3135fc5eddf7fc2f755118a2235"; 

  public static void main (String[] args) {
    ReadData readData = new ReadData(); 
    String s = ""; 
    try {
    s = readData.sendGet();
    JSONParser jsonParser = new JSONParser();
    JSONObject jsonObject = (JSONObject) jsonParser.parse(s);
    JSONArray list = (JSONArray) jsonObject.get("list"); 
    //System.out.println(list); 
    JSONObject obj = (JSONObject) list.get(0); 
    JSONObject obj2 = (JSONObject) obj.get("main"); 
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
    System.out.println("High for today is " + newTemp_Max + " Celcius"); 
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