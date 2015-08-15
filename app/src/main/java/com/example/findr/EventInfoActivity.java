package com.example.findr;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.Date;

public class EventInfoActivity extends AppCompatActivity {

    Event event;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_info);

        Intent intent = getIntent();

        event = (Event) intent.getSerializableExtra("event");

        TextView orgby = (TextView) findViewById(R.id.textView);
        orgby.setText("Organized by " + event.hostName);

        TextView time = (TextView) findViewById(R.id.textView2);
        Date date = new Date(event.time[0] * 1000);
        Date endDate = new Date(event.time[1] * 10000);
        time.setText("This event starts at " + date.getHours() + ":" + date.getMinutes() + '\n' +
            "This event ends at " + endDate.getHours() + ":" + endDate.getMinutes());

        TextView reqs = (TextView) findViewById(R.id.textView4);
        reqs.setText("You must be " + event.qualifications);

        TextView nameview = (TextView) findViewById(R.id.textView8);
        nameview.setText(event.name);

        TextView emailview = (TextView) findViewById(R.id.textView7);
        emailview.setText("Their email is " + event.contact);

        TextView latlong = (TextView) findViewById(R.id.textView15);
        latlong.setText("Latitude: " + event.coordinates[0] + " Longitude: " + event.coordinates[1]);

        TextView policy = (TextView) findViewById(R.id.textView11);
        policy.setText("Policy: " + event.policy);

        TextView note = (TextView) findViewById(R.id.textView9);
        note.setText("Note: " + event.description);

        TextView tags = (TextView) findViewById(R.id.textView5);
        tags.setText("Tags: " + event.tags);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_event_info, menu);
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
}
