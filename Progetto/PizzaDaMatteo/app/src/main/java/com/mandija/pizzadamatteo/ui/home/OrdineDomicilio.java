package com.mandija.pizzadamatteo.ui.home;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mandija.pizzadamatteo.R;
import com.mandija.pizzadamatteo.databinding.FragmentOrdineDomicilioBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link } factory method to
 * create an instance of this fragment.
 */
public class OrdineDomicilio extends Fragment {

    private FragmentOrdineDomicilioBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentOrdineDomicilioBinding.inflate(inflater, container, false);
        View rootView = binding.getRoot();

        binding.btNextDom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //NavHostFragment.findNavController(OrdineDomicilio.this).navigate(R.id.action);
            }
        });

        return rootView;
    }
}