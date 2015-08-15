package com.example.findr;

import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import org.json.JSONException;
import org.osmdroid.util.ResourceProxyImpl;
import org.osmdroid.views.MapView;

public class MainActivity extends AppCompatActivity
    implements FindrMapView.OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final FindrMapView frag = (FindrMapView) getFragmentManager().findFragmentById(R.id.fragment);

        Event.RetrieveEventsTask task = new Event.RetrieveEventsTask() {
            protected void onPostExecute(Event[] events) {
                Log.println(Log.DEBUG, "MainActivity-Event", "post execute");
                for (Event ev : events) {
                    frag.addEvent(ev);
                }
                frag.startOverlays();
            }
        };

        task.execute();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {
        
    }
}
