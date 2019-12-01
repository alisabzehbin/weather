package com.weather.sabzehbin.utils;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import com.weather.sabzehbin.R;
import com.weather.sabzehbin.models.Weather;

public class UI {

    public static void setNavigationBarMode(Activity context, boolean darkTheme, boolean blackTheme) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O_MR1) {
            View content = context.findViewById(android.R.id.content);
            if (!darkTheme && !blackTheme) {
                content.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR);
            }
        }
    }

    public static int getTheme(String themePref) {
        switch (themePref) {
            case "dark":
                return R.style.AppTheme_NoActionBar_Dark;
            case "black":
                return R.style.AppTheme_NoActionBar_Black;
            case "classic":
                return R.style.AppTheme_NoActionBar_Classic;
            case "classicdark":
                return R.style.AppTheme_NoActionBar_Classic_Dark;
            case "classicblack":
                return R.style.AppTheme_NoActionBar_Classic_Black;
            default:
                return R.style.AppTheme_NoActionBar;
        }
    }

    public static void setLocaleOnCreate(Locale locale, Context context) {
        Locale.setDefault(locale);
        Resources res = context.getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();
        conf.locale = locale;
        res.updateConfiguration(conf, dm);
    }

    public static void setLocale(Locale locale, Context context, Activity activity) {
        Locale.setDefault(locale);
        Resources res = context.getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();
        conf.locale = locale;
        res.updateConfiguration(conf, dm);
        activity.recreate();
        //finish();
        //startActivity(getIntent());
    }

    public static void setBackgroundImage(Context context, String weatherId, View layoutView, Weather todayWeather) {

        Date date = new Date();

        /*if (weatherId.equals("800")) {
            setBackground(context, layoutView, R.drawable.cloudy_day);
        }*/
        switch (weatherId.substring(0, 1)) {

            //Thunderstorm
            case "2":

                    setBackground(context, layoutView, R.drawable.tunderstorm_night);

                break;
            //Cloudy
            case "8":

                if (weatherId.equals("800")){

                    if (todayWeather.getSunset().getTime() < Calendar.getInstance().getTimeInMillis() || todayWeather.getSunrise().getTime() > Calendar.getInstance().getTimeInMillis()) {
                        Log.e("weather", "night");
                        setBackground(context, layoutView, R.drawable.clear_night);
                    }else {
                        Log.e("weather", "day");
                        setBackground(context, layoutView, R.drawable.suny_day);
                    }

                }else {

                    if (todayWeather.getSunset().getTime() < Calendar.getInstance().getTimeInMillis() || todayWeather.getSunrise().getTime() > Calendar.getInstance().getTimeInMillis()) {
                        Log.e("weather", "night");
                        setBackground(context, layoutView, R.drawable.cloudy_night);
                    }else {
                        Log.e("weather", "day");
                        setBackground(context, layoutView, R.drawable.cloudy_day);
                    }
                }

                break;
            //Drizzle
            case "3":
                if (todayWeather.getSunset().getTime() < Calendar.getInstance().getTimeInMillis() || todayWeather.getSunrise().getTime() > Calendar.getInstance().getTimeInMillis()) {
                    Log.e("weather", "night");
                    setBackground(context, layoutView, R.drawable.drizzle_night);
                }else {
                    Log.e("weather", "day");
                    setBackground(context, layoutView, R.drawable.drizzle_day);
                }
                break;
                //rainy
            case "5":
                if (todayWeather.getSunset().getTime() < Calendar.getInstance().getTimeInMillis() || todayWeather.getSunrise().getTime() > Calendar.getInstance().getTimeInMillis()) {
                    Log.e("weather", "night");
                    setBackground(context, layoutView, R.drawable.rainy_night);
                }else {
                    Log.e("weather", "day");
                    setBackground(context, layoutView, R.drawable.rainy_day);
                }
                break;
                //snow
            case "6":
                if (todayWeather.getSunset().getTime() < Calendar.getInstance().getTimeInMillis() || todayWeather.getSunrise().getTime() > Calendar.getInstance().getTimeInMillis()) {
                    Log.e("weather", "night");
                    setBackground(context, layoutView, R.drawable.snow_night);
                }else {
                    Log.e("weather", "day");
                    setBackground(context, layoutView, R.drawable.snow_day);
                }
                break;

            case "7":
                if (todayWeather.getSunset().getTime() < Calendar.getInstance().getTimeInMillis() || todayWeather.getSunrise().getTime() > Calendar.getInstance().getTimeInMillis()) {
                    Log.e("weather", "night");
                    setBackground(context, layoutView, R.drawable.fog_night);
                }else {
                    Log.e("weather", "day");
                    setBackground(context, layoutView, R.drawable.fog_day);
                }
                break;
        }
    }

    public static void setBackground(Context context, View view, int drawableId) {
        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), drawableId);
        int width = Resources.getSystem().getDisplayMetrics().widthPixels;
        int height = Resources.getSystem().getDisplayMetrics().heightPixels;
        bitmap = Bitmap.createScaledBitmap(bitmap, width, height, true);
        Bitmap blurredBitmap = BlurBuilder.blur(context, bitmap);
        BitmapDrawable bitmapDrawable = new BitmapDrawable(context.getResources(), blurredBitmap);
        view.setBackground(bitmapDrawable);
    }
}
