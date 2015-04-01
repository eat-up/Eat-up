package com.eatup.android.eatup;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.common.api.GoogleApiClient;


public class LunchTimeActivity extends ActionBarActivity {
    private Button btYes;
    private Button btNotToday;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lunch_time);
        initSettings();
        locateGPS();
    }

    private void locateGPS() {
        GPSTracker gps;
        gps = new GPSTracker(this);


        if(gps.canGetLocation()) {
            double latitude = gps.getLatitude();
            double longitude = gps.getLongitude();

            Toast.makeText(getApplicationContext(),
                    "Your location is -\nLat: " + latitude + "-\nLong: " + longitude, Toast.LENGTH_LONG).show();
        } else {
            gps.showSettingsAlert();
        }
    }

    private void initSettings() {
        btYes = (Button) findViewById(R.id.btYes);
        btNotToday = (Button) findViewById(R.id.btNotToday);

        btYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent yes = new Intent(getApplicationContext(),LunchingActivity.class);
                startActivity(yes);
            }
        });

        btNotToday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent notToday = new Intent(getApplicationContext(), NotLunch.class);
                startActivity(notToday);

            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_lunch_time, menu);
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