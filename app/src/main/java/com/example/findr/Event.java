package com.example.findr;

import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import org.javalite.http.Get;
import org.javalite.http.Http;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Ian on 2015-08-15.
 */
public class Event implements Serializable {
    @SerializedName("host-name")
    public String hostName;

    @SerializedName("name")
    public String name;

    @SerializedName("contact")
    public String contact;

    @SerializedName("location")
    public String location;

    @SerializedName("description")
    public String description;

    @SerializedName("tags")
    public String tags;

    @SerializedName("qualifications")
    public String qualifications;

    @SerializedName("time")
    public long[] time;

    @SerializedName("coordinates")
    public double[] coordinates;

    @SerializedName("policy")
    public String policy;

    public static String getHTML(String urlToRead) {
        URL url;
        HttpURLConnection conn;
        BufferedReader rd;
        String line;
        StringBuilder result = new StringBuilder();
        try {
            url = new URL(urlToRead);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            while ((line = rd.readLine()) != null) {
                result.append(line);
            }
            rd.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result.toString();
    }

    public static abstract class RetrieveEventsTask extends AsyncTask<String, Void, Event[]> {

        private Exception exception;

        protected Event[] doInBackground(String... x) {
            String text = getHTML("https://afternoon-castle-4785.herokuapp.com/events");

            Gson gson = new Gson();
            String[] estrs = gson.fromJson(text, String[].class);

            Event[] events = new Event[estrs.length];
            for (int i = 0; i < estrs.length; i++) {
                events[i] = gson.fromJson(estrs[i], Event.class);
            }

            return events;
        }
    }
}

