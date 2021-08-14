package com.bryanahusna.golek.belajar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.bryanahusna.golek.R;

public class NgokoLuguActivity extends AppCompatActivity {
    public static ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ngoko_lugu);
        viewPager = findViewById(R.id.ngoko_lugu_viewPager);
        NgokoLuguPageAdapter pageAdapter = new NgokoLuguPageAdapter(getSupportFragmentManager(), 2);
        viewPager.setAdapter(pageAdapter);
    }
}
