package com.example.spt516.fides_v2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class SelectCommodityActivity extends AppCompatActivity {

    // The list where we hold our data
    protected List<String> commodities;
    // The list view where we display the data above
    protected ListView commoditiesListView;
    // An adapter for the list view
    protected ArrayAdapter<String> commoditiesListViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_commodity);

        setTitle("...Select Commodity");

        commoditiesListView = (ListView) findViewById(R.id.commoditiesListView);
        commodities = new ArrayList<String>();
        commodities.add("Gold");
        commodities.add("Oil");
        commodities.add("Wood");
        commoditiesListViewAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, commodities);

        commoditiesListView.setAdapter(commoditiesListViewAdapter);

        //when a commodity is selected retrun its name
        commoditiesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long arg3) {
                Intent intent = new Intent();
                intent.putExtra("name", commodities.get(position));
                setResult(RESULT_OK,intent);
                finish();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_select_commodity, menu);
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
