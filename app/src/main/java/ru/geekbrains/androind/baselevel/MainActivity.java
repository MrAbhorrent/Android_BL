package ru.geekbrains.androind.baselevel;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.Arrays;


public class MainActivity extends AppCompatActivity {

    private static final String MySettings = "SettingsApp";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Проверяем конфигурационные файлы. Первый ли запуск
        SharedPreferences sp = getSharedPreferences(MySettings, Context.MODE_PRIVATE);
        boolean NotFirstRun = sp.getBoolean("AnotherRun", false);

        if (!NotFirstRun) {
            Toast.makeText(getApplicationContext(),"Пожалуйста, выберите город", Toast.LENGTH_SHORT).show();
            selectCityLocation();
            SharedPreferences.Editor e = sp.edit();
            e.putBoolean("AnotherRun", true);
            e.apply();
        }

    }

    protected void selectCityLocation() {

        final String[] Citys = getResources().getStringArray(R.array.CityLocation);
        AlertDialog.Builder builder = new AlertDialog.Builder(getApplicationContext());
        String titleDialog = getResources().getString(R.string.TitleDialogLocation);
        builder.setTitle(titleDialog)
                .setMessage("Проверка текста");
                //.setIcon(R.drawable.ic_baseline_location_city_24)
/*                .setItems(Citys, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(getApplication(), "Выбран город - " + Citys[i], Toast.LENGTH_SHORT).show();
                    }
                });*/
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}