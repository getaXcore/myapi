package com.example.taufanseptaufani.myapi;

import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Taufan Septaufani on 07-Dec-17.
 */

public class RetrieveFeedTask extends AsyncTask<Void, Void, String> {

    private Exception exception;

    String API_URL = "https://api.openweathermap.org/data/2.5/weather";
    String API_KEY = "<api_key>";

    public MainActivity activity;
    public String city;

    public RetrieveFeedTask(MainActivity a,String city){
        this.activity = a;
        this.city = city;
    }

    protected void onPreExecute() {
        ProgressBar progressBar = (ProgressBar)activity.findViewById(R.id.progressBar);
        TextView responseView = (TextView)activity.findViewById(R.id.responseView);

        progressBar.setVisibility(View.VISIBLE);
        responseView.setText("");
    }

    protected String doInBackground(Void... urls) {
        // Do some validation here

        try {
            URL url = new URL(API_URL + "?q="+city+",id&appid=" + API_KEY);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            try {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                StringBuilder stringBuilder = new StringBuilder();
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    stringBuilder.append(line).append("\n");
                }
                bufferedReader.close();
                //return  stringBuilder.toString();

                /*int i;
                JSONArray jsonarray = new JSONArray(stringBuilder.toString());
                String WeatherText = "";
                for (i = 0; i < jsonarray.length(); i++) {
                    JSONObject jsonObject = jsonarray.getJSONObject(i);
                    WeatherText = jsonObject.getString("WeatherText");
                }
                return WeatherText;*/

                JSONObject jsonObject = new JSONObject(stringBuilder.toString());
                return  jsonObject.getJSONArray("weather").toString(2);
            }
            finally{
                urlConnection.disconnect();
            }
        }
        catch(Exception e) {
            Log.e("ERROR", e.getMessage(), e);
            return null;
        }
    }

    protected void onPostExecute(String response) {
        ProgressBar progressBar = (ProgressBar)activity.findViewById(R.id.progressBar);
        TextView responseView = (TextView)activity.findViewById(R.id.responseView);

        if(response == null) {
            response = "THERE WAS AN ERROR";
        }
        progressBar.setVisibility(View.GONE);
        Log.i("INFO", response);
        responseView.setText(response);
    }
}
