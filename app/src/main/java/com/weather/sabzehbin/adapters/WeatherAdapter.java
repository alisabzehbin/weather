package com.weather.sabzehbin.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

import com.weather.sabzehbin.R;
import com.weather.sabzehbin.models.Weather;
import com.weather.sabzehbin.utils.UnitConvertor;

public class WeatherAdapter extends RecyclerView.Adapter<WeatherAdapter.CustomViewHolder> {

    private ArrayList<Weather> tomarrowWeather = new ArrayList<>();
    private ArrayList<Weather> longWeather = new ArrayList<>();

    private ArrayList<Weather> sundyWeather; //--> 0
    private ArrayList<Weather> mondayWeather; //--> 1
    private ArrayList<Weather> tuesdayWeather; //--> 2
    private ArrayList<Weather> wednesdayWeather; //--> 3
    private ArrayList<Weather> thursWeather; //--> 4
    private ArrayList<Weather> fridayWeather; //--> 5
    private ArrayList<Weather> saturWeather; //--> 6
    private int newPosition = 0;
    private Context context;

    ArrayList<Double> averageTemp;

    public WeatherAdapter(Context context, ArrayList<Weather> longWeather, ArrayList<Weather> tomarrowWeather ) {

        this.longWeather.clear();
        this.tomarrowWeather.clear();

        this.longWeather.addAll(longWeather);
        this.tomarrowWeather.addAll(tomarrowWeather);
        this.context = context;

        this.averageTemp = new ArrayList<>();

        this.sundyWeather = new ArrayList<>();
        this.mondayWeather = new ArrayList<>();
        this.tuesdayWeather = new ArrayList<>();
        this.wednesdayWeather = new ArrayList<>();
        this.thursWeather = new ArrayList<>();
        this.fridayWeather = new ArrayList<>();
        this.saturWeather = new ArrayList<>();

        for (int i = 0; i < this.longWeather.size(); i++) {

            switch (this.longWeather.get(i).getDate().getDay()) {

                case 0:
                    this.sundyWeather.add(this.longWeather.get(i));
                    break;
                case 1:
                    this.mondayWeather.add(this.longWeather.get(i));
                    break;
                case 2:
                    this.tuesdayWeather.add(this.longWeather.get(i));
                    break;
                case 3:
                    this.wednesdayWeather.add(this.longWeather.get(i));
                    break;
                case 4:
                    this.thursWeather.add(this.longWeather.get(i));
                    break;
                case 5:
                    this.fridayWeather.add(this.longWeather.get(i));
                    break;
                case 6:
                    this.saturWeather.add(this.longWeather.get(i));
                    break;
            }
        }

/*        ArrayList<Weather> fiveDayWeather = new ArrayList<>();

        double average = 0;
        double sumTemp = 0;

        for (int i =0; i< this.longWeather.size(); i++){
            Date date = this.longWeather.get(i).getDate();
            if (fiveDayWeather.size() == 0 ){
                sumTemp = Double.valueOf(this.longWeather.get(i).getTemperature());
                fiveDayWeather.add(this.longWeather.get(i));
            }else {
                sumTemp += Double.valueOf(this.longWeather.get(i).getTemperature());
                for (Weather weather :fiveDayWeather) {
                    if (this.longWeather.get(i).getDate() != weather.getDate()){
                        average = 0;
                        sumTemp = 0;
                        fiveDayWeather.add(this.longWeather.get(i));
                    }
                }
                average = sumTemp / 8;
            }
        }*/
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.weather_list, parent, false);
        return new CustomViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {

        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        Typeface weatherFont = Typeface.createFromAsset(context.getAssets(), "fonts/weather.ttf");

        Weather weather;
        if (position == 0){
            //weather = tomarrowWeather.get(position);
            float tomorrowTemp =  generateSum(tomarrowWeather) / tomarrowWeather.size();
            float temperature11 = UnitConvertor.convertTemperature(tomorrowTemp, sp);
            if (sp.getBoolean("temperatureInteger", false)) {
                temperature11 = Math.round(temperature11);
            }

            holder.txtTemp.setText(new DecimalFormat("0.#").format(temperature11) + " " + sp.getString("unit", "°C"));
            holder.txtDate.setText(new SimpleDateFormat("EEEE", Locale.ENGLISH).format(tomarrowWeather.get(0).getDate().getTime()));
            holder.txtIcon.setTypeface(weatherFont);
            holder.txtIcon.setText(tomarrowWeather.get(2).getIcon());
            holder.txtDescription.setText(tomarrowWeather.get(2).getDescription());

            newPosition = position + 1;

            return;
        }else {
            weather = longWeather.get(newPosition - 1);
        }

        // Temperature
/*        float temperature = UnitConvertor.convertTemperature(Float.parseFloat(weather.getTemperature()), sp);
        if (sp.getBoolean("temperatureInteger", false)) {
            temperature = Math.round(temperature);
        }*/


        switch (weather.getDate().getDay()) {

            case 0:
                float sunTemp =  generateSum(sundyWeather) / sundyWeather.size();
                float temperature0 = UnitConvertor.convertTemperature(sunTemp, sp);
                if (sp.getBoolean("temperatureInteger", false)) {
                    temperature0 = Math.round(temperature0);
                }

                holder.txtTemp.setText(new DecimalFormat("0.#").format(temperature0) + " " + sp.getString("unit", "°C"));
                holder.txtDate.setText(new SimpleDateFormat("EEEE", Locale.ENGLISH).format(sundyWeather.get(0).getDate().getTime()));
                holder.txtIcon.setTypeface(weatherFont);
                holder.txtIcon.setText(sundyWeather.get(2).getIcon());
                holder.txtDescription.setText(sundyWeather.get(2).getDescription());

                newPosition += sundyWeather.size();


                break;
            case 1:
                float monTemp =  generateSum(mondayWeather) / mondayWeather.size();
                float temperature1 = UnitConvertor.convertTemperature(monTemp, sp);
                if (sp.getBoolean("temperatureInteger", false)) {
                    temperature1 = Math.round(temperature1);
                }
                holder.txtTemp.setText(new DecimalFormat("0.#").format(temperature1) + " " + sp.getString("unit", "°C"));
                holder.txtDate.setText(new SimpleDateFormat("EEEE", Locale.ENGLISH).format(mondayWeather.get(0).getDate().getTime()));
                holder.txtIcon.setTypeface(weatherFont);
                holder.txtIcon.setText(mondayWeather.get(2).getIcon());
                holder.txtDescription.setText(mondayWeather.get(2).getDescription());

                newPosition += mondayWeather.size();

                break;
            case 2:
                float tueTemp =  generateSum(tuesdayWeather) / tuesdayWeather.size();
                float temperature2 = UnitConvertor.convertTemperature(tueTemp, sp);
                if (sp.getBoolean("temperatureInteger", false)) {
                    temperature2 = Math.round(temperature2);
                }

                holder.txtTemp.setText(new DecimalFormat("0.#").format(temperature2) + " " + sp.getString("unit", "°C"));
                holder.txtDate.setText(new SimpleDateFormat("EEEE", Locale.ENGLISH).format(tuesdayWeather.get(0).getDate().getTime()));
                holder.txtIcon.setTypeface(weatherFont);
                holder.txtIcon.setText(tuesdayWeather.get(2).getIcon());
                holder.txtDescription.setText(tuesdayWeather.get(2).getDescription());

                newPosition += tuesdayWeather.size();

                break;
            case 3:
                float wedTemp =  generateSum(wednesdayWeather) / wednesdayWeather.size();
                float temperature3 = UnitConvertor.convertTemperature(wedTemp, sp);
                if (sp.getBoolean("temperatureInteger", false)) {
                    temperature3 = Math.round(temperature3);
                }

                holder.txtTemp.setText(new DecimalFormat("0.#").format(temperature3) + " " + sp.getString("unit", "°C"));
                holder.txtDate.setText(new SimpleDateFormat("EEEE", Locale.ENGLISH).format(wednesdayWeather.get(0).getDate().getTime()));
                holder.txtIcon.setTypeface(weatherFont);
                holder.txtIcon.setText(wednesdayWeather.get(2).getIcon());
                holder.txtDescription.setText(wednesdayWeather.get(2).getDescription());

                newPosition += wednesdayWeather.size();

                break;
            case 4:
                float thuTemp =  generateSum(thursWeather) / thursWeather.size();
                float temperature4 = UnitConvertor.convertTemperature(thuTemp, sp);
                if (sp.getBoolean("temperatureInteger", false)) {
                    temperature4 = Math.round(temperature4);
                }

                holder.txtTemp.setText(new DecimalFormat("0.#").format(temperature4) + " " + sp.getString("unit", "°C"));
                holder.txtDate.setText(new SimpleDateFormat("EEEE", Locale.ENGLISH).format(thursWeather.get(0).getDate().getTime()));
                holder.txtIcon.setTypeface(weatherFont);
                holder.txtIcon.setText(thursWeather.get(2).getIcon());
                holder.txtDescription.setText(thursWeather.get(2).getDescription());

                newPosition += thursWeather.size();

                break;
            case 5:
                float friTemp =  generateSum(fridayWeather) / fridayWeather.size();
                float temperature5 = UnitConvertor.convertTemperature(friTemp, sp);
                if (sp.getBoolean("temperatureInteger", false)) {
                    temperature5 = Math.round(temperature5);
                }

                holder.txtTemp.setText(new DecimalFormat("0.#").format(temperature5) + " " + sp.getString("unit", "°C"));
                holder.txtDate.setText(new SimpleDateFormat("EEEE", Locale.ENGLISH).format(fridayWeather.get(0).getDate().getTime()));
                holder.txtIcon.setTypeface(weatherFont);
                if(fridayWeather.size()>2)
                {
                    holder.txtIcon.setText(fridayWeather.get(2).getIcon());
                    holder.txtDescription.setText(fridayWeather.get(2).getDescription());
                }


                newPosition += fridayWeather.size();
/*                if (position != 0){

                    if (longWeather.get(position).getDate().getDay() == longWeather.get(position -1).getDate().getDay()) {
                        newPosition += fridayWeather.size();
                    }
                }*/
                break;
            case 6:
                float satTemp = generateSum(saturWeather) / saturWeather.size();
                float temperature6 = UnitConvertor.convertTemperature(satTemp, sp);
                if (sp.getBoolean("temperatureInteger", false)) {
                    temperature6 = Math.round(temperature6);
                }

                holder.txtTemp.setText(new DecimalFormat("0.#").format(temperature6) + " " + sp.getString("unit", "°C"));
                holder.txtDate.setText(new SimpleDateFormat("EEEE", Locale.ENGLISH).format(saturWeather.get(0).getDate().getTime()));
                holder.txtIcon.setTypeface(weatherFont);
                holder.txtIcon.setText(saturWeather.get(2).getIcon());
                holder.txtDescription.setText(saturWeather.get(2).getDescription());

                newPosition += saturWeather.size();

/*                if (position != 0){

                    if (longWeather.get(position).getDate().getDay() == longWeather.get(position -1).getDate().getDay()) {
                        newPosition += saturWeather.size();
                    }
                }*/
                break;
        }


    }

    @Override
    public int getItemCount() {
        int sundaySize;
        int mondaySize;
        int tuesdaySize;
        int wednesdaySize;
        int thursdaySize;
        int fridaySize;
        int saturdaySize;

        int tomorrowWeather;
        if (sundyWeather.size() == 0){
             sundaySize = 0;
        }else {
            sundaySize = sundyWeather.size() / sundyWeather.size();
        }

        if (mondayWeather.size() == 0){
            mondaySize = 0;
        }else {
            mondaySize = mondayWeather.size() / mondayWeather.size();
        }

        if (tuesdayWeather.size() == 0){
            tuesdaySize = 0;
        }else {
            tuesdaySize = tuesdayWeather.size() / tuesdayWeather.size();
        }

        if (wednesdayWeather.size() == 0){
            wednesdaySize = 0;
        }else {
            wednesdaySize = wednesdayWeather.size() / wednesdayWeather.size();
        }

        if (thursWeather.size() == 0){
            thursdaySize = 0;
        }else {
            thursdaySize = thursWeather.size() / thursWeather.size();
        }

        if (fridayWeather.size() == 0){
            fridaySize = 0;
        }else {
            fridaySize = fridayWeather.size()/ fridayWeather.size();
        }

        if (saturWeather.size() == 0){
            saturdaySize = 0;
        }else {
            saturdaySize = saturWeather.size() / saturWeather.size();
        }

        if (tomarrowWeather.size() == 0){
            tomorrowWeather = 0;
        }else {
            tomorrowWeather = tomarrowWeather.size()/tomarrowWeather.size();
        }



        int size = tomorrowWeather + sundaySize + mondaySize + tuesdaySize + wednesdaySize + thursdaySize + fridaySize + saturdaySize;

        Log.e("size" , "" + size);

        return size;
    }



    class CustomViewHolder extends RecyclerView.ViewHolder {

        private TextView txtIcon, txtDescription, txtTemp, txtDate;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            txtIcon = itemView.findViewById(R.id.weatherIcon);
            txtDescription = itemView.findViewById(R.id.txtDescription);
            txtTemp = itemView.findViewById(R.id.txtTemp);
            txtDate = itemView.findViewById(R.id.txtDate);

        }
    }

    public float generateSum (ArrayList<Weather> weather){
        float tempSum = 0;
        for (Weather w : weather) {
            tempSum += Float.valueOf(w.getTemperature());
        }
        return tempSum;
    }
}
