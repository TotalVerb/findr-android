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
        time.setText("This event starts at " + date.getHours() + ":" + date.getMinutes());
        time.setText("This event ends at " + endDate.getHours() + ":" + endDate.getMinutes());
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
