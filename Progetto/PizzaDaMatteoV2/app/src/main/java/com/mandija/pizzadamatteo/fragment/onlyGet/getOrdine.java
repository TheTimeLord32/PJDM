package com.mandija.pizzadamatteo.fragment.onlyGet;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.mandija.pizzadamatteo.R;
import com.mandija.pizzadamatteo.adapter.ListaOrdineAdapter;
import com.mandija.pizzadamatteo.databinding.FragmentGetOrdineBinding;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class getOrdine extends Fragment {
    private RecyclerView rv;
    private ListaOrdineAdapter adapter;
    private FragmentGetOrdineBinding binding;
    private Executor executor = Executors.newSingleThreadExecutor();
    private Bundle bundle = new Bundle();

    public getOrdine() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentGetOrdineBinding.inflate(inflater, container, false);
        rv = binding.rvLista;
        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        rv.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        adapter = new ListaOrdineAdapter(getContext());
        rv.setAdapter(adapter);

        if (this.getArguments() != null) {
            String line = this.getArguments().getString("getOrdine");
            try {
                JSONArray array = new JSONArray(line);
                adapter.aggiungi(array);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        binding.btNextOrdine.setOnClickListener(v -> {
            String id_ordine = binding.etNumVisual.getText().toString();
            getOrdine2(id_ordine);
        });

        binding.btDelOrdine.setOnClickListener(v -> {
            String id_ordine = binding.etNumVisual.getText().toString();
            delOrdine(id_ordine);
        });

        return binding.getRoot();
    }

    private void getOrdine2(String id_ordine) {
        executor.execute(() -> {
            try {
                URL url = new URL(getContext().getString(R.string.hostname) + getContext().getString(R.string.getOrdine2) + "?id_ordine=" + id_ordine); // prendere ID manualmente
                BufferedReader reader = new BufferedReader(new InputStreamReader(url.openConnection().getInputStream()));
                String line = reader.readLine();
                bundle.putString("getOrdine2", line);
                reader.close();
                Handler handler = new Handler(getContext().getMainLooper());

                handler.post(()-> NavHostFragment.findNavController(getOrdine.this).navigate(R.id.action_getOrdine_to_getOrdine2, bundle));

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
                new Handler(getContext().getMainLooper()).post(() -> Toast.makeText(getContext(), "Ordine non trovato", Toast.LENGTH_SHORT).show());
            }
        });
    }

    private void delOrdine(String id_ordine) {
        executor.execute(() -> {
            try {
                URL url = new URL(getContext().getString(R.string.hostname) + getContext().getString(R.string.getOrdine) + "?id_ordine=" + id_ordine); // prendere ID manualmente
                BufferedReader reader = new BufferedReader(new InputStreamReader(url.openConnection().getInputStream()));
                String line = reader.readLine();
                Handler handler = new Handler(getContext().getMainLooper());
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();

                if (line.equals("[]")){
                    handler.post(() -> Toast.makeText(getContext(), "Ordine non trovato", Toast.LENGTH_SHORT).show());
                } else {
                    connection.setDoOutput(true);
                    connection.setRequestMethod("DELETE");
                    connection.getResponseCode();
                    connection.connect();
                    handler.post(() -> Toast.makeText(getContext(), "Ordine cancellato", Toast.LENGTH_SHORT).show());
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
                new Handler(getContext().getMainLooper()).post(() -> Toast.makeText(getContext(), "Ordine non trovato", Toast.LENGTH_SHORT).show());
            }
        });
    }
}