package com.example.findr;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TimePicker;

import java.util.Date;


public class CreateEvent extends AppCompatActivity {
    String[] textfields = {"name","hostName","description","location", "qualifications", "tags"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_event);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_create_event, menu);
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

    public Event create() {
        Event event = new Event();
        event.name = ((EditText)findViewById(R.id.eventName)).getText().toString();
        event.hostName = ((EditText)findViewById(R.id.hostName)).getText().toString();
        event.description = ((EditText)findViewById(R.id.description)).getText().toString();
        event.location = ((EditText)findViewById(R.id.location)).getText().toString();
        event.qualifications = ((EditText)findViewById(R.id.qualifications)).getText().toString();
        event.tags = ((EditText)findViewById(R.id.tags)).getText().toString();
        event.contact = ((EditText)findViewById(R.id.email)).getText().toString();
        double[] coordinates = {2,0}; //todo: implement this
        DatePicker datePicker = ((DatePicker)findViewById(R.id.datePicker));
        TimePicker timePicker = ((TimePicker)findViewById(R.id.timePicker));
        Date date = new Date(datePicker.getYear(), datePicker.getMonth(), datePicker.getDayOfMonth(), timePicker.getCurrentHour(), timePicker.getCurrentMinute(), 0);
        long[] times = {date.getTime(), date.getTime()+3600};
        event.time = times;
        event.policy = ((RadioButton)findViewById(R.id.radioButton)).isSelected() ? "drop_in" : "arrive_at_start";

        return event;
    }


}
