package ru.geekbrains.androind.baselevel;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String MySettings = "SettingsApp";
    TextView cityLocation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cityLocation = findViewById(R.id.CityLocation);
        cityLocation.setOnClickListener(this);

        // Добавляем кнопку назад в ActionBar
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Проверяем конфигурационные файлы. Первый ли запуск
        SharedPreferences sp = getSharedPreferences(MySettings, Context.MODE_PRIVATE);
        boolean NotFirstRun = sp.getBoolean("AnotherRun", false);

        if (!NotFirstRun) {
            Toast.makeText(getApplicationContext(),"Пожалуйста, выберите город", Toast.LENGTH_SHORT).show();

            SharedPreferences.Editor e = sp.edit();
            e.putBoolean("AnotherRun", true);
            e.apply();
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.CityLocation:
                Toast.makeText(getApplicationContext(), "Скоро Вы попадете в меню выбора города", Toast.LENGTH_SHORT).show();
                selectCity();
                break;

        }
    }

    public Dialog selectCity(){

        AlertDialog.Builder builder = new AlertDialog.Builder(getApplicationContext());
        String titleDialog = getResources().getString(R.string.TitleDialogLocation);
        builder.setTitle(titleDialog)
                .setIcon(R.drawable.ic_baseline_location_city_24)
                .setMessage("Заглушка. Выбор города")
                .setPositiveButton("Ок", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(getApplicationContext(), "Нажата кнопка Ок", Toast.LENGTH_SHORT).show();
                    }
                });

        return builder.create();
    }
}