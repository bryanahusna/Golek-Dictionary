package com.bryanhusna.golek;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import static com.bryanhusna.golek.NgokoLuguActivity.viewPager;


/**
 * A simple {@link Fragment} subclass.
 */
public class NgokoLuguDefinisiFragment extends Fragment {
    private ImageView selanjutnya;


    public NgokoLuguDefinisiFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ngoko_definisi, container, false);
        ImageView selanjutnya = view.findViewById(R.id.selanjutnya_ngoko_lugu);
        selanjutnya.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewPager.setCurrentItem((viewPager.getCurrentItem()+1), true);
            }
        });
        return view;
    }

}