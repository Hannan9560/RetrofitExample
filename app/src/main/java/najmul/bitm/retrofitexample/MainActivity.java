package najmul.bitm.retrofitexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.media.CamcorderProfile.get;

public class MainActivity extends AppCompatActivity {

    private String tempUnit = "metric";
    private String city = "dhaka";
    private int dayCount = 7;

    private static final String BASE_URL = "https://api.openweathermap.org/data/2.5/forecast/";
    private String urlString;
    private ForecastServiceApi forecastServiceApi;

    private TextView showTV;

    //int ---> %d
    //String ---> %s
    //double/float -->%f


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        showTV = (TextView) findViewById(R.id.showTV);

        /*urlString = String.format("daily?q=%s&mode=json&units=%s&cnt=%d&appid=%s",city,tempUnit,dayCount,getString(R.string.weather_api_key));*/
        urlString = String.format("daily?id=524901&appid=b1b15e88fa797225412429c1c50c122a1");

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        forecastServiceApi = retrofit.create(ForecastServiceApi.class);

        Call<ForecastWeatherResponse>forecastWeatherResponseCall =
                forecastServiceApi.getForecastWeatherResponse(urlString);
        forecastWeatherResponseCall.enqueue(new Callback<ForecastWeatherResponse>() {
            @Override
            public void onResponse(Call<ForecastWeatherResponse> call, Response<ForecastWeatherResponse> response) {
                Toast.makeText(MainActivity.this, "success", Toast.LENGTH_SHORT).show();
                if(response.code() == 200){
                    ForecastWeatherResponse forecastWeatherResponse = response.body();
                    List<ForecastWeatherResponse.ForecastList> lists = forecastWeatherResponse.getList();
                    String cloud = lists.get(0).getClouds().toString();
                    double tem = lists.get(5).getSpeed().doubleValue();
                    showTV.setText((int) tem);
                }
            }
            @Override
            public void onFailure(Call<ForecastWeatherResponse> call, Throwable t) {

                Toast.makeText(MainActivity.this, ""+t.getMessage(), Toast.LENGTH_LONG).show();
                Log.e("weather", "onFailure: "+t.getMessage() );
            }
        });
    }
}
