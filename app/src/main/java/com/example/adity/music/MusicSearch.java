package com.example.adity.music;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MusicSearch extends Activity {
    private String url="https://itunes.apple.com/search?term=";
    public String name;
    JSONHandler jsonhandler;
    ListView lv,lv1,lv2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_search);
        Log.d("Error","Checkpoint1");
        Intent intent=getIntent();
        String artistName = intent.getStringExtra(MainActivity.URL);
        Log.d("Error","Checkpoint2");
        name=artistName;
        String finalURL=url+name;
        Log.d("Error","Checkpoint3");
        getDetails(finalURL);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_music_search, menu);
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


    public void getDetails(String finalURL) {
        jsonhandler=new JSONHandler(finalURL);
        jsonhandler.fetchJSON();
        Log.d("Error", "Checkpoint4");
//        View view=getLayoutInflater().inflate(R.layout.musiclayout,null);
//        TextView tv1=(TextView)view.findViewById(R.id.artist);
//        TextView tv2=(TextView)view.findViewById(R.id.collection);
//        TextView tv3=(TextView)view.findViewById(R.id.track);
//        while(jsonhandler.parsingComplete);
//        tv1.setText(jsonhandler.getArtistName());
//        tv2.setText(jsonhandler.getCollectionName());
//        tv3.setText(jsonhandler.getTrackName());
        while(jsonhandler.parsingComplete);


        /* ArrayList to get the details of the artistName

        ArrayList<String> arrayData=jsonhandler.getList();
        Log.d("String", "" + arrayData);
        final ArrayAdapter adapter=new ArrayAdapter<String>(getApplication(), android.R.layout.simple_list_item_1,arrayData);
        lv=(ListView)findViewById(R.id.listView);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String item = (String) adapter.getItem(position);
                Toast.makeText(getApplication(),"You have clicked the artistName"+item,Toast.LENGTH_SHORT).show();
            }
        });
        */

        //ArrayList to get the details of tracks
        ArrayList<String> arrayTrackData=jsonhandler.getTrackList();
        Log.d("String", "" + arrayTrackData);
        ArrayAdapter adapter1=new ArrayAdapter<String>(getApplication(), android.R.layout.simple_list_item_1,arrayTrackData);
        lv1=(ListView)findViewById(R.id.listView2);
        lv1.setAdapter(adapter1);

        /* ArrayList to get the details of collectionNames

        ArrayList<String> arrayCollectionData=jsonhandler.getList();
        Log.d("String", "" + arrayCollectionData);
        ArrayAdapter adapter2=new ArrayAdapter<String>(getApplication(), android.R.layout.simple_list_item_1,arrayCollectionData);
        lv2=(ListView)findViewById(R.id.listView);
        lv2.setAdapter(adapter2);
*/
    }


}
