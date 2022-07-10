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
            String line = this.getArguments().getString("line");
            try {
                JSONArray array = new JSONArray(line);
                adapter.aggiungi(array);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        binding.btNextOrdine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id_ordine = binding.etNumVisual.getText().toString();
                getOrdine2(id_ordine);
            }
        });

        binding.btDelOrdine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id_ordine = binding.etNumVisual.getText().toString();
                delOrdine(id_ordine);
            }
        });

        return binding.getRoot();
    }

    private Executor executor = Executors.newSingleThreadExecutor();
    private Bundle bundle = new Bundle();

    /*private void getOrdine1(String id_ordine) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL(getContext().getString(R.string.hostname) + getContext().getString(R.string.getOrdine1) + "?id_ordine=" + id_ordine); // prendere ID manualmente
                    BufferedReader reader = new BufferedReader(new InputStreamReader(url.openConnection().getInputStream()));
                    String line = reader.readLine();
                    bundle.putString("line", line);
                    reader.close();
                    Handler handler = new Handler(getContext().getMainLooper());

                    Runnable runnable = new Runnable() {
                        @Override
                        public void run() { NavHostFragment.findNavController(getOrdine.this).navigate(R.id.action_getOrdine_to_getOrdine1, bundle); }
                    };
                    handler.post(runnable);

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                    Log.d("getOrdine1", "run: ordine non trovato_MalformedURLException");
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.d("getOrdine1", "run: ordine non trovato_Exception");
                    Handler handler = new Handler(getContext().getMainLooper());
                    handler.post(new Runnable() {
                        @Override
                        public void run() { Toast.makeText(getContext(), "Ordine non trovato", Toast.LENGTH_SHORT).show(); }
                    });
                }
            }
        });
    }*/

    private void getOrdine2(String id_ordine) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL(getContext().getString(R.string.hostname) + getContext().getString(R.string.getOrdine2) + "?id_ordine=" + id_ordine); // prendere ID manualmente
                    BufferedReader reader = new BufferedReader(new InputStreamReader(url.openConnection().getInputStream()));
                    String line = reader.readLine();
                    bundle.putString("line", line);
                    reader.close();
                    Handler handler = new Handler(getContext().getMainLooper());

                    Runnable runnable = new Runnable() {
                        @Override
                        public void run() { NavHostFragment.findNavController(getOrdine.this).navigate(R.id.action_getOrdine_to_getOrdine2, bundle); }
                    };
                    handler.post(runnable);

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                    Log.d("getOrdine2", "run: ordine non trovato_MalformedURLException");
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.d("getOrdine2", "run: ordine non trovato_Exception");
                    Handler handler = new Handler(getContext().getMainLooper());
                    handler.post(new Runnable() {
                        @Override
                        public void run() { Toast.makeText(getContext(), "Ordine non trovato", Toast.LENGTH_SHORT).show(); }
                    });
                }
            }
        });
    }

    private void delOrdine(String id_ordine) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL(getContext().getString(R.string.hostname) + getContext().getString(R.string.getOrdine) + "?id_ordine=" + id_ordine); // prendere ID manualmente
                    BufferedReader reader = new BufferedReader(new InputStreamReader(url.openConnection().getInputStream()));
                    String line = reader.readLine();
                    Handler handler = new Handler(getContext().getMainLooper());
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setDoOutput(true);

                    connection.setRequestMethod("GET");
                    if (line.equals("[]")){
                        handler.post(new Runnable() {
                            @Override
                            public void run() { Toast.makeText(getContext(), "Ordine non trovato", Toast.LENGTH_SHORT).show(); }
                        });
                    } else {
                        Log.d("del", "run: ok");
                        connection.disconnect();
                        connection.setDoOutput(true);
                        connection.setRequestMethod("DELETE");
                        Log.d("del", "delOrdine: " + connection.getResponseCode());
                        connection.connect();

                        Runnable runnable = new Runnable() {
                            @Override
                            public void run() { Toast.makeText(getContext(), "Ordine cancellato", Toast.LENGTH_SHORT).show(); }
                        };
                        handler.post(runnable);
                    }
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.d("getOrdine1", "run: ordine non eliminato_Exception");
                    Handler handler = new Handler(getContext().getMainLooper());
                    handler.post(new Runnable() {
                        @Override
                        public void run() { Toast.makeText(getContext(), "Ordine non trovato", Toast.LENGTH_SHORT).show(); }
                    });
                }
            }
        });
    }
}