package com.bryanhusna.golek;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

public class KartuHarianViewPagerAdapter extends PagerAdapter {
    private Context context;
    private LayoutInflater layoutInflater;
    private String[] daftarKartuHarian;
    private String[] daftarKartuharianIndonesia;
    private String[] daftarKartuharianNgoko;
    private String[] daftarKartuharianKrama;

    public KartuHarianViewPagerAdapter(Context context, String[] data, String[] dataIndonesia, String[] dataNgoko, String[] dataKrama){
        this.context = context;
        this.daftarKartuHarian = data;
        this.daftarKartuharianIndonesia = dataIndonesia;
        this.daftarKartuharianNgoko = dataNgoko;
        this.daftarKartuharianKrama = dataKrama;
    }

    @Override
    public int getCount() {
        return 5;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.kartu_harian, null);
        TextView kartuHarianJudul = view.findViewById(R.id.kartu_harian_judul);
        TextView kartuHarianIndonesia = view.findViewById(R.id.kartu_harian_indonesia);
        TextView kartuHarianNgoko = view.findViewById(R.id.kartu_harian_ngoko);
        TextView kartuHarianKrama = view.findViewById(R.id.kartu_harian_krama);
        kartuHarianJudul.setText(daftarKartuHarian[position]);
        kartuHarianIndonesia.setText(daftarKartuharianIndonesia[position]);
        kartuHarianNgoko.setText(daftarKartuharianNgoko[position]);
        kartuHarianKrama.setText(daftarKartuharianKrama[position]);

        ViewPager vp = (ViewPager)container;
        vp.addView(view, 0);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        ViewPager vp = (ViewPager)container;
        View view = (View)object;
        vp.removeView(view);
    }
}
