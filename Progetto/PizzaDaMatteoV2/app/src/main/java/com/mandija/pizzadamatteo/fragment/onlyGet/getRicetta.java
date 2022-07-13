package com.mandija.pizzadamatteo.fragment.onlyGet;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mandija.pizzadamatteo.adapter.ListaRicettaAdapter;
import com.mandija.pizzadamatteo.databinding.FragmentGetRicettaBinding;

import org.json.JSONArray;
import org.json.JSONException;

public class getRicetta extends Fragment {
    private FragmentGetRicettaBinding binding;
    private RecyclerView rv;
    private ListaRicettaAdapter adapter;

    public getRicetta() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentGetRicettaBinding.inflate(inflater, container, false);
        rv = binding.rvRicetta;
        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        rv.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        adapter = new ListaRicettaAdapter(getContext());
        rv.setAdapter(adapter);

        if (this.getArguments() != null) {
            String line = this.getArguments().getString("getRicetta");
            try {
                JSONArray array = new JSONArray(line);
                adapter.aggiungi(array);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return binding.getRoot();
    }
}