package com.weather.sabzehbin.activities;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

import com.weather.sabzehbin.R;
import com.weather.sabzehbin.utils.UI;

@SuppressLint("Registered")
public class BaseActivity extends AppCompatActivity {
    protected int theme;
    protected boolean darkTheme;
    protected boolean blackTheme;
    private Locale locale;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        setTheme(theme = UI.getTheme(prefs.getString("theme", "classicdark")));
        darkTheme = theme == R.style.AppTheme_NoActionBar_Dark ||
                theme == R.style.AppTheme_NoActionBar_Classic_Dark;
        blackTheme = theme == R.style.AppTheme_NoActionBar_Black ||
                theme == R.style.AppTheme_NoActionBar_Classic_Black;

        UI.setNavigationBarMode(BaseActivity.this, darkTheme, blackTheme);
        locale = new Locale(PreferenceManager.getDefaultSharedPreferences(this).getString("language",""));
        UI.setLocaleOnCreate(locale,this);
    }
}
