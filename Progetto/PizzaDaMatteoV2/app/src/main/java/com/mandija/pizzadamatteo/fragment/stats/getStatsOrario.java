package com.mandija.pizzadamatteo.fragment.stats;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mandija.pizzadamatteo.adapter.ListaStatsOrarioAdapter;
import com.mandija.pizzadamatteo.databinding.FragmentGetStatsOrarioBinding;

import org.json.JSONArray;
import org.json.JSONException;

public class getStatsOrario extends Fragment {
    public getStatsOrario() { }

    private RecyclerView rv;
    private ListaStatsOrarioAdapter adapter;
    private FragmentGetStatsOrarioBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentGetStatsOrarioBinding.inflate(inflater, container, false);
        rv = binding.rvStatsOrario;
        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        rv.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        adapter = new ListaStatsOrarioAdapter(getContext());
        rv.setAdapter(adapter);

        if (this.getArguments() != null) {
            String line = this.getArguments().getString("getStatsOrario");
            try {
                JSONArray array = new JSONArray(line);
                adapter.aggiungiStatsOrario(array);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return binding.getRoot();
    }
}