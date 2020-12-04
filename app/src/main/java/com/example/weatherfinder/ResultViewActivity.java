package com.example.weatherfinder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.icu.util.Calendar;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.weatherfinder.api.RetrofitClient;
import com.example.weatherfinder.api.model.request.WeatherApiResponse;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ResultViewActivity extends AppCompatActivity {

    private TextView cityTextView;
    private TextView weatherTextView;
    private TextView temperatureTextView;
    private TextView dayTextView;
    private TextView dateTextView;
    private TextView descriptionTextView;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_view);

        cityTextView = findViewById(R.id.cityTextView);
        weatherTextView = findViewById(R.id.weatherTextView);
        temperatureTextView = findViewById(R.id.temperatureTextView);
        dayTextView = findViewById(R.id.dayTextView);
        dateTextView = findViewById(R.id.dateTextView);
        descriptionTextView = findViewById(R.id.descriptionTextView);
        imageView = findViewById(R.id.imageView);

        Intent intent = getIntent();
        String city = intent.getStringExtra("city").trim();

        RetrofitClient.getWeatherApi().getWeatherApiResponseAt(city).enqueue(new Callback<WeatherApiResponse>() {
            @Override
            public void onResponse(Call<WeatherApiResponse> call, Response<WeatherApiResponse> response) {
                if (response.isSuccessful()) {
                    WeatherApiResponse weatherApiResponse = response.body();
                    cityTextView.setText(city);
                    String temperature = String.format("%.2f", toCelsius(weatherApiResponse.getMain().getTemp()));
                    temperatureTextView.setText(temperature);
                    weatherTextView.setText(String.valueOf(weatherApiResponse.getWeather().get(0).getMain()));
                    descriptionTextView.setText(String.valueOf(weatherApiResponse.getWeather().get(0).getDescription() + " day"));
                    String icon = weatherApiResponse.getWeather().get(0).getIcon();
                    Calendar c = Calendar.getInstance();
                    dateTextView.setText(String.valueOf(c.DAY_OF_MONTH));
                    dayTextView.setText(getDayOfWeek(c.DAY_OF_WEEK));

                    String url = "http://openweathermap.org/img/wn/";
                    String url2 = "@2x.png";

                    new DownloadImageTask(imageView)
                            .execute(url + icon + url2);

                }
            }

            @Override
            public void onFailure(Call<WeatherApiResponse> call, Throwable t) {
                System.out.println("error");
            }
        });



    }

    public String getDayOfWeek(int day) {
        switch (day) {
            case 1:
                return "MON";
            case 2:
                return "TUE";
            case 3:
                return "WED";
            case 4:
                return "THU";
            case 5:
                return "FRI";
            case 6:
                return "SAT";
            case 7:
                return "SUN";
        }
        return "";
    }

    public float toCelsius(float kelvin) {
        return (kelvin - 273.15F);
    }

}

class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
    ImageView bmImage;

    public DownloadImageTask(ImageView bmImage) {
        this.bmImage = bmImage;
    }

    protected Bitmap doInBackground(String... urls) {
        String urldisplay = urls[0];
        Bitmap mIcon11 = null;
        try {
            InputStream in = new java.net.URL(urldisplay).openStream();
            mIcon11 = BitmapFactory.decodeStream(in);
        } catch (Exception e) {
            Log.e("Error", e.getMessage());
            e.printStackTrace();
        }
        return mIcon11;
    }

    protected void onPostExecute(Bitmap result) {
        bmImage.setImageBitmap(result);
    }
}