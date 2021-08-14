package com.bryanhusna.golek;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class NgokoAlusPageAdapter extends FragmentPagerAdapter {
    private int jumlahTab;

    public NgokoAlusPageAdapter(FragmentManager fm, int jumlahTab) {
        super(fm);
        this.jumlahTab = jumlahTab;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new NgokoAlusDefinisiFragment();
            case 1:
                return new NgokoAlusPenggunaanFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return jumlahTab;
    }
}
