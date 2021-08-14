package com.bryanahusna.golek.belajar;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class NgokoLuguPageAdapter extends FragmentPagerAdapter {
    private int jumlahTab;
    public NgokoLuguPageAdapter(FragmentManager fm, int jumlahTab) {
        super(fm);
        this.jumlahTab = jumlahTab;
    }

    @Override
    public Fragment getItem(int position) {
        switch(position){
            case 0:
                return new NgokoLuguDefinisiFragment();
            case 1:
                return new NgokoLuguPenggunaanFragment();
                default:
                    return null;
        }
    }

    @Override
    public int getCount() {
        return jumlahTab;
    }
}
