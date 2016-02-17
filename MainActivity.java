package com.example.spt516.fides_v2;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    // The list where we hold our data
    protected List<String> commodities;
    // The list view where we display the data above
    protected ListView commoditiesListView;
    // An adapter for the list view
    protected ArrayAdapter<String> commoditiesListViewAdapter;

    // Request code for select commodity activity
    protected final int SELECT_COMMODITY_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        commoditiesListView = (ListView) findViewById(R.id.commoditiesListView);
        commodities = new ArrayList<String>();
        commoditiesListViewAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, commodities);

        commoditiesListView.setAdapter(commoditiesListViewAdapter);

        //set an onClickListener for going to the specific commodity when a users press a list element
        commoditiesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long arg3) {
                Intent intent = new Intent(MainActivity.this, CommodityActivity.class);
                intent.putExtra("name", commodities.get(position));
                startActivity(intent);
            }
        });
    }

    public void addCommodityPressed(View v){

        Intent intent = new Intent(MainActivity.this, SelectCommodityActivity.class);
        startActivityForResult(intent, SELECT_COMMODITY_REQUEST);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == SELECT_COMMODITY_REQUEST &&
                resultCode == Activity.RESULT_OK) {
            commodities.add(data.getStringExtra("name"));
            commoditiesListViewAdapter.notifyDataSetChanged();
        }
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


    private void toast(String sentence){
        Toast toast = Toast.makeText(
                MainActivity.this,
                sentence,
                Toast.LENGTH_LONG); // Toast.LENGTH_SHORT
        toast.setGravity(Gravity.BOTTOM, 0, 0);
        toast.show();
    }
}
