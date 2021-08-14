package com.bryanhusna.golek;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

public class KramaAlusActivity extends AppCompatActivity {
    public static ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_krama_alus);
        viewPager = findViewById(R.id.krama_alus_viewPager);
        KramaAlusPageAdapter pageAdapter = new KramaAlusPageAdapter(getSupportFragmentManager(), 2);
        viewPager.setAdapter(pageAdapter);
    }
}
