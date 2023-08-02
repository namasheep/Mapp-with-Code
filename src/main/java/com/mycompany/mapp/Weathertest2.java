
package com.mycompany.mapp;

// need to download json library for this to run
// Also required to have Wifi
import org.json.*;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

/**
 * handles retrieving weather data
 * @author Group 7
 */
public class Weathertest2 {
    String url = "https://api.openweathermap.org/data/2.5/weather?lat=42.9849&lon=-81.2453&units=metric&appid=7ddfa4963a43424d2db5759da1dcd8dd";
        
        HttpRequest request = HttpRequest.newBuilder().GET().uri(URI.create(url)).build();
        HttpClient client = HttpClient.newBuilder().build();
        String temperature;
        
    /**
     * 
     */
    public Weathertest2(){
        temperature = "unavailable";
        try{
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            String jsonString = response.body();
            JSONObject obj = new JSONObject(jsonString);
            temperature=""+GetTemp(obj);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        
    }
    
    
    // takes in JSON object
    // not sure what it will return if temp not found
    /**
     * returns temperature
     * @param obj stringified JSON resp
     * @return temperature
     */
    static private int GetTemp(JSONObject obj)
    {
        int temp = obj.getJSONObject("main").getInt("temp");
        return temp;
    }
    
    // may return empty string if there is a random error
    /**
     *  gets weather conditions
     * @param obj - stringified JSON resp
     * @return 
     */
    static private String GetWeather(JSONObject obj)
    {
        JSONArray arr = obj.getJSONArray("weather");
        String main = "";
        for (int i = 0; i < arr.length(); i++)
        {
            main = arr.getJSONObject(i).getString("main");
        }
        
        return main;
    }
    
        
    public static void main( String[] args ) throws Exception
    {
        var url = "https://api.openweathermap.org/data/2.5/weather?lat=42.9849&lon=-81.2453&units=metric&appid=7ddfa4963a43424d2db5759da1dcd8dd";
        
        var request = HttpRequest.newBuilder().GET().uri(URI.create(url)).build();
        var client = HttpClient.newBuilder().build();
        var response = client.send(request, HttpResponse.BodyHandlers.ofString());
        
        System.out.println(response.body());  // this prints out the JSON
        
        String jsonString = response.body();
        JSONObject obj = new JSONObject(jsonString);
        
        System.out.format("The temperature is: %d Degrees Celcius \n", GetTemp(obj));
        System.out.format("The Weather condition is: %s \n",GetWeather(obj));
    }
}

// MAIN API LINK
//https://api.openweathermap.org/data/2.5/weather?lat=44.34&lon=10.99&appid=7ddfa4963a43424d2db5759da1dcd8dd
//api key == 7ddfa4963a43424d2db5759da1dcd8dd
// link for example api response = https://openweathermap.org/current#format

// This is the link to the Maven Repository for JSON
// https://mvnrepository.com/artifact/org.json/json/20220924

// ignore this for now dont need it unless emergency, its just another api
//https://weather.visualcrossing.com/VisualCrossingWebServices/rest/services/timeline/Washington,DC/yesterday?key=M9RPD5RYPDWTFEZLKV4RXRRKN
