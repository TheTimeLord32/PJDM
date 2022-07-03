package com.mandija.pizzadamatteo.fragment.onlyGet;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mandija.pizzadamatteo.R;
import com.mandija.pizzadamatteo.databinding.FragmentGetOrdine1Binding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link getOrdine1#newInstance} factory method to
 * create an instance of this fragment.
 */
public class getOrdine1 extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private FragmentGetOrdine1Binding binding;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public getOrdine1() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment getOrdine1.
     */
    // TODO: Rename and change types and number of parameters
    public static getOrdine1 newInstance(String param1, String param2) {
        getOrdine1 fragment = new getOrdine1();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentGetOrdine1Binding.inflate(inflater, container, false);

        if (this.getArguments() != null) {
            String pizza1 = this.getArguments().getString("pizza1");
            String pizza2 = this.getArguments().getString("pizza2");
            String pizza3 = this.getArguments().getString("pizza3");
            String fritti1 = this.getArguments().getString("fritti1");
            String fritti2 = this.getArguments().getString("fritti2");
            String bibite1 = this.getArguments().getString("bibite1");

            binding.tvPizza1.setText(pizza1);
            binding.tvPizza2.setText(pizza2);
            binding.tvPizza3.setText(pizza3);
            binding.tvFritti1.setText(fritti1);
            binding.tvFritti2.setText(fritti2);
            binding.tvBibite1.setText(bibite1);
        }
        return binding.getRoot();
    }
}