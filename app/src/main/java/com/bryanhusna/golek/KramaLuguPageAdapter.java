package com.bryanhusna.golek;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class KramaLuguPageAdapter extends FragmentPagerAdapter {
    private int jumlahTab;
    public KramaLuguPageAdapter(FragmentManager fm, int jumlahTab) {
        super(fm);
        this.jumlahTab = jumlahTab;
    }

    @Override
    public Fragment getItem(int position) {
        switch(position){
            case 0:
                return new KramaLuguDefinisiFragment();
            case 1:
                return new KramaLuguPenggunaanFragment();
                default:
                    return null;
        }
    }

    @Override
    public int getCount() {
        return jumlahTab;
    }
}
