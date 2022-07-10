package com.mandija.pizzadamatteo.fragment.onlyGet;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mandija.pizzadamatteo.adapter.ListaOrdine2Adapter;
import com.mandija.pizzadamatteo.databinding.FragmentGetOrdine2Binding;

import org.json.JSONArray;
import org.json.JSONException;

public class getOrdine2 extends Fragment {
    public getOrdine2() { }

    private RecyclerView rv;
    private ListaOrdine2Adapter adapter;
    private FragmentGetOrdine2Binding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentGetOrdine2Binding.inflate(inflater, container, false);
        rv = binding.rvLista2;
        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        rv.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        adapter = new ListaOrdine2Adapter(getContext());
        rv.setAdapter(adapter);

        if (this.getArguments() != null) {
            String line = this.getArguments().getString("line");
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
