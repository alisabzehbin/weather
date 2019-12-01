package com.weather.sabzehbin.adapters;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DecimalFormat;
import java.util.ArrayList;

import com.weather.sabzehbin.R;
import com.weather.sabzehbin.models.Weather;
import com.weather.sabzehbin.utils.UnitConvertor;

public class TodayWeatherAdapter extends RecyclerView.Adapter<TodayViewHolder> {


    private ArrayList<Weather> todayWeather = new ArrayList<>();
    private Context context;

    ArrayList<Double> averageTemp;

    public TodayWeatherAdapter(Context context, ArrayList<Weather> todayWeather) {

        this.todayWeather.clear();
        this.todayWeather.addAll(todayWeather);
        this.context = context;


    }

    @NonNull
    @Override
    public TodayViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.today_weather_list,parent,false);
        return new TodayViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TodayViewHolder holder, int position) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);

        Weather weather = todayWeather.get(position);
        Typeface weatherFont = Typeface.createFromAsset(context.getAssets(), "fonts/weather.ttf");

        float temp = Float.valueOf(weather.getTemperature());
        float temperature= UnitConvertor.convertTemperature(temp, sp);
        if (sp.getBoolean("temperatureInteger", false)) {
            temperature = Math.round(temperature);
        }
        //holder.todayIcon.setTextColor(Color.parseColor("#ffff00"));
        holder.todayIcon.setTypeface(weatherFont);
        holder.todayIcon.setText(weather.getIcon());
        holder.todayDescription.setText(weather.getDescription());
        holder.todayTemp.setText(new DecimalFormat("0.#").format(temperature) + " " + sp.getString("unit", "°C"));
        holder.todayClock.setText("" + weather.getDate().getHours());
    }

    @Override
    public int getItemCount() {
        return todayWeather.size();
    }
}

class TodayViewHolder extends RecyclerView.ViewHolder{

    TextView todayIcon, todayClock, todayTemp, todayDescription;
    public TodayViewHolder(@NonNull View item) {
        super(item);
        todayIcon = item.findViewById(R.id.weatherIcon);
        todayClock = item.findViewById(R.id.txtClock);
        todayTemp = item.findViewById(R.id.txtTemp);
        todayDescription = item.findViewById(R.id.txtDescription);

    }
}


