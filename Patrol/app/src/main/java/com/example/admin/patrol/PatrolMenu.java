package com.example.admin.patrol;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;

public class PatrolMenu extends Activity {

    /* Context for changing activity and buttons to do so */
    private Button record;
    private Button stats;
    private Button map;
    private Button about;
    private final Context context = this;

    public void addListenerOnRecord() {

        record = (Button) findViewById(R.id.action_record);

        record.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                //Intent intent = new Intent(context, StatsPatrol.class);
                //startActivity(intent);

            }

        });

    }

    public void addListenerOnStats() {

        stats = (Button) findViewById(R.id.action_stats);

        stats.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                Intent intent = new Intent(context, StatsPatrol.class);
                startActivity(intent);

            }

        });

    }

    public void addListenerOnMap() {

        map = (Button) findViewById(R.id.action_map);

        map.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                Intent intent = new Intent(context, MapPatrol.class);
                startActivity(intent);

            }

        });

    }

    public void addListenerOnAbout() {

        about = (Button) findViewById(R.id.action_about);

        about.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                Intent intent = new Intent(context, AboutPatrol.class);
                startActivity(intent);

            }

        });

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patrol_menu);

        addListenerOnRecord();
        addListenerOnStats();
        addListenerOnMap();
        addListenerOnAbout();
    }

}
