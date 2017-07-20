package com.example.manojpun.weather;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.TimeZone;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {


    public static final String TAG = MainActivity.class.getSimpleName();

    private CurrentWeather mCurrentWeather = new CurrentWeather();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        double latitide= 37.8267;
        double longitude=-122.4233;
        String apiKey="7d5a3877bcb6cf441c201f51900a0746";
        String forecastUrl="https://api.darksky.net/forecast/"+apiKey+"/"+latitide+","+longitude;

        if(isNetworkAvavilable()){

            OkHttpClient client = new OkHttpClient();

            Request request= new Request.Builder()
                    .url(forecastUrl)
                    .build();

            Call call = client.newCall(request);

            call.enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {

                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {

                    try {
                        String jsonData=response.body().string();
                        Log.v(TAG,jsonData);
                        if(response.isSuccessful()){
                            mCurrentWeather = getCurrentDetails(jsonData);
                        }
                        else
                        {
                            alertUserAboutError();


                        }
                    } catch (IOException e) {
                        Log.e(TAG,"Exception Caught : ",e);
                    } catch (JSONException e) {
                        Log.e(TAG,"Exception Caught : ",e);                    }
                }
            });
        }

        else
        {

            Toast.makeText(this, "Network is Not Avavilable.",
                    Toast.LENGTH_LONG).show();
        }




        Log.d(TAG,"Main Asyncronous logging");


    }

    private CurrentWeather getCurrentDetails(String jsonData) throws JSONException {
        JSONObject forecast = new JSONObject(jsonData);
        String timezone = forecast.getString("timezone");
        Log.i(TAG,"Result : "+timezone);

        JSONObject currently = forecast.getJSONObject("currently");

        CurrentWeather currentWeather = new CurrentWeather();

        currentWeather.setHumidity(currently.getDouble("humidity"));
        currentWeather.setTime(currently.getLong("time"));
        currentWeather.setIcon(currently.getString("icon"));
        currentWeather.setPerceipChannel(currently.getDouble("precipProbability"));
        currentWeather.setSummary(currently.getString("summary"));
        currentWeather.setTemperature(currently.getDouble("temperature"));
        currentWeather.setTimeZone(timezone);


        Log.d(TAG,"TIME :::::: "+currentWeather.getFormattedTime());
        return null;



    }

    private boolean isNetworkAvavilable() {

        ConnectivityManager manager = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo networkInfo =manager.getActiveNetworkInfo();
        boolean isAvavilable =false;
        if(networkInfo != null && networkInfo.isConnected()){

            isAvavilable=true;

        }
        return isAvavilable;
    }

    private void alertUserAboutError() {

        AlertDialogFragment dialog = new AlertDialogFragment();
        dialog.show(getFragmentManager(),"error_message");
    }
}
