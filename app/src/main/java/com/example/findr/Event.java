package com.example.findr;

import android.os.AsyncTask;

import org.javalite.http.Get;
import org.javalite.http.Http;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URL;

/**
 * Created by Ian on 2015-08-15.
 */
public class Event {
    public String hostName, name, contact, location, description;
    public String[] tags, qualifications;
    public Time times;
    public double[] coordinates;
    public Policy policy;

    public static abstract class RetrieveEventsTask extends AsyncTask<String, Void, Event[]> {

        private Exception exception;

        protected Event[] doInBackground(String... x) {
            try {
                Get get = Http.get("https://afternoon-castle-4785.herokuapp.com/events");
                JSONArray array = new JSONArray(get.text());
                Event[] events = new Event[array.length()];
                for (int i = 0; i < array.length(); i++) {
                    JSONObject object = (JSONObject) array.getJSONObject(i);
                    Event event = new Event();
                    event.contact = (String) object.get("contact");
                    event.coordinates = (double[]) object.get("coordinates");
                    event.description = (String) object.get("description");
                    event.hostName = (String) object.get("hostName");
                    event.location = (String) object.get("location");
                    event.name = (String) object.get("name");
                    event.policy = object.get("policy").equals("DROP_IN") ? Policy.DROP_IN : Policy.ARRIVE_AT_START;
                    event.qualifications = (String[]) object.get("qualifications");
                    event.tags = (String[]) object.get("tags");
                    event.times = new Time(((long[]) object.get("times"))[0], ((long[]) object.get("times"))[1]);
                    events[i] = event;
                }
                return events;
            } catch (JSONException e) {
                throw new RuntimeException("JSON problem " + e.getMessage());
            }
        }
    }
}

enum Policy {
    DROP_IN, ARRIVE_AT_START
}

