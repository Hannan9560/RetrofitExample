package najmul.bitm.retrofitexample;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Url;

/**
 * Created by Trainer on 4/17/2017.
 */

public interface ForecastServiceApi {
    @GET()
    Call<ForecastWeatherResponse> getForecastWeatherResponse(@Url String stringUrl);
}
