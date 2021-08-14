package com.bryanahusna.golek.belajar;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bryanahusna.golek.R;

import static com.bryanahusna.golek.belajar.NgokoAlusActivity.viewPager;


/**
 * A simple {@link Fragment} subclass.
 */
public class NgokoAlusPenggunaanFragment extends Fragment {


    public NgokoAlusPenggunaanFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_ngoko_alus_penggunaan, container, false);
        ImageView sebelumnya = view.findViewById(R.id.sebelumnya_ngoko_alus);
        sebelumnya.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewPager.setCurrentItem((viewPager.getCurrentItem()-1), true);
            }
        });
        return view;
    }

}
