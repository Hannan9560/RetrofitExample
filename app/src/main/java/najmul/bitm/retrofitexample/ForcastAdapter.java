package najmul.bitm.retrofitexample;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

import static android.R.attr.resource;

/**
 * Created by Hannan Talukder on 4/18/2017.
 */

public class ForcastAdapter extends ArrayAdapter<ForecastWeatherResponse> {
    private Context context;
    private ArrayList<ForecastWeatherResponse> forecasts;

    public ForcastAdapter(@NonNull Context context, ArrayList<ForecastWeatherResponse> forecasts) {
        super(context, resource, forecasts);
        this.context = context;
        this.forecasts = forecasts;
    }
}
