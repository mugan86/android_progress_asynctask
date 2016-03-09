package com.amugika.progressexample;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.amugika.progressexample.server.GetLoadData;

public class MainActivity extends AppCompatActivity {
    private TextView load_dataTextView;
    private LinearLayout progressLinearLayout;
    private RelativeLayout dataRelativeLayout;
    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        load_dataTextView = (TextView) findViewById(R.id.load_dataTextView);
        progressLinearLayout = (LinearLayout) findViewById(R.id.progressLinearLayout);
        dataRelativeLayout = (RelativeLayout) findViewById(R.id.dataRelativeLayout);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Datuak kargatzen...", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                new GetLoadData(MainActivity.this, load_dataTextView, progressLinearLayout, dataRelativeLayout, toolbar).execute();
            }
        });

        new GetLoadData(MainActivity.this, load_dataTextView, progressLinearLayout, dataRelativeLayout, toolbar).execute();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
