package com.example.adity.music;

import android.annotation.SuppressLint;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by adity on 8/27/2015.
 */
public class JSONHandler {

    private final String urlString;
    private String artistName="artistName";
    private String trackName="trackName";
    private String collectionName="collectionName";
    public volatile boolean parsingComplete = true;
    private ArrayList<String> list = new ArrayList<String>();

    public ArrayList<String> getCollectList() {
        return collectList;
    }

    public ArrayList<String> getTrackList() {
        return trackList;
    }

    private ArrayList<String> trackList=new ArrayList<String>();
    private ArrayList<String> collectList=new ArrayList<String>();

    public String getArtistName() {
        return artistName;
    }

    public ArrayList<String> getList() {
        Log.d("ArrayList",""+list);
        return list;
    }

    public String getTrackName() {
        return trackName;
    }

    public String getCollectionName() {
        return collectionName;
    }

    public JSONHandler(String finalURL) {
        this.urlString=finalURL;
    }

    public void fetchJSON() {

        Thread thread=new Thread(new Runnable()  {
            @Override
            public void run() {

                URL url= null;
                try {
                    url = new URL(urlString);

                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setReadTimeout(10000 /* milliseconds */);
                    httpURLConnection.setConnectTimeout(15000 /* milliseconds */);
                    httpURLConnection.setRequestMethod("GET");
                    httpURLConnection.setDoInput(true);
                    // Starts the query
                    httpURLConnection.connect();
                    InputStream stream = httpURLConnection.getInputStream();

                    String data = convertStreamToString(stream);

                    readAndParseJSON(data);
                    stream.close();
                }
                catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
        thread.start();
    }

    private String convertStreamToString(InputStream stream) {

        java.util.Scanner s = new java.util.Scanner(stream).useDelimiter("\\A");
        return s.hasNext() ? s.next() : "";
    }


    @SuppressLint("NewApi")
    public void readAndParseJSON(String data)
    {
        try {
            JSONObject jsonObject = new JSONObject(data);
            JSONArray result = jsonObject.getJSONArray("results");
            Log.d("lenght", "" + result.length());
            for (int i = 0; i < result.length(); i++) {
                JSONObject results = result.getJSONObject(i);
                artistName = results.getString("artistName");
                trackName = results.getString("trackName");
                //collectionName = results.getString("collectionName");
                Log.d("artistName", artistName + "\t" + i);
                Log.d("trackName", trackName + "\t" + i);
                Log.d("collectionName", collectionName + "\t" + i);
                if(artistName!=null||trackName!=null||collectionName!=null){
                list.add(i, trackName);
                trackList.add(i, trackName);
                //collectList.add(i,collectionName);
                }
            }
           parsingComplete = false;

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
