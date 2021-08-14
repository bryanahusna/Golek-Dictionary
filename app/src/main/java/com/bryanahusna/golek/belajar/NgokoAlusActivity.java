package com.bryanahusna.golek.belajar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.bryanahusna.golek.R;

public class NgokoAlusActivity extends AppCompatActivity {
    public static ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ngoko_alus);
        viewPager = findViewById(R.id.ngoko_alus_viewPager);
        NgokoAlusPageAdapter pageAdapter = new NgokoAlusPageAdapter(getSupportFragmentManager(), 2);
        viewPager.setAdapter(pageAdapter);
    }
}
