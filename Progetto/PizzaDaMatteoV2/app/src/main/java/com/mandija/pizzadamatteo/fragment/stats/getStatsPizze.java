package com.mandija.pizzadamatteo.fragment.stats;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mandija.pizzadamatteo.adapter.ListaStatsPizzeAdapter;
import com.mandija.pizzadamatteo.databinding.FragmentGetStatsPizzeBinding;

import org.json.JSONArray;
import org.json.JSONException;

public class getStatsPizze extends Fragment {

    public getStatsPizze() { }

    private RecyclerView rv;
    private ListaStatsPizzeAdapter adapter;
    private FragmentGetStatsPizzeBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentGetStatsPizzeBinding.inflate(inflater, container, false);
        rv=binding.rvStatsPizze;
        rv.setLayoutManager(new androidx.recyclerview.widget.LinearLayoutManager(getActivity()));
        rv.addItemDecoration(new androidx.recyclerview.widget.DividerItemDecoration(getActivity(), androidx.recyclerview.widget.DividerItemDecoration.VERTICAL));
        adapter=new ListaStatsPizzeAdapter(getContext());
        rv.setAdapter(adapter);

        if (this.getArguments() != null) {
            String line = this.getArguments().getString("line");
            try {
                JSONArray array = new JSONArray(line);
                adapter.aggiungiStatsPizze(array);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return binding.getRoot();
    }
}