package com.bryanhusna.golek;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import static com.bryanhusna.golek.KramaLuguActivity.viewPager;


/**
 * A simple {@link Fragment} subclass.
 */
public class KramaLuguDefinisiFragment extends Fragment {


    public KramaLuguDefinisiFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_krama_lugu_definisi, container, false);
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
