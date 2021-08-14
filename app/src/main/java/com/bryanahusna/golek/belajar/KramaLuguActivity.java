package com.bryanahusna.golek.belajar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.bryanahusna.golek.R;

public class KramaLuguActivity extends AppCompatActivity {
    public static ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_krama_lugu);
        viewPager = findViewById(R.id.krama_lugu_viewPager);
        KramaLuguPageAdapter pageAdapter = new KramaLuguPageAdapter(getSupportFragmentManager(), 2);
        viewPager.setAdapter(pageAdapter);
    }
}
