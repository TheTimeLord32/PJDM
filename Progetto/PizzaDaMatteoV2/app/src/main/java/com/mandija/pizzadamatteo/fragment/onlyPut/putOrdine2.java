package com.mandija.pizzadamatteo.fragment.onlyPut;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.mandija.pizzadamatteo.R;
import com.mandija.pizzadamatteo.databinding.FragmentPutOrdine2Binding;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class putOrdine2 extends Fragment {
    private FragmentPutOrdine2Binding binding;
    private Executor executor = Executors.newSingleThreadExecutor();
    private Bundle bundle = new Bundle();
    private int lastID_global = 0;

    private Spinner spPizza, spFritti, spBibite;
    private ArrayList<String> listaPizze = new ArrayList<>();
    private ArrayList<String> listaFritti = new ArrayList<>();
    private ArrayList<String> listaBibite = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentPutOrdine2Binding.inflate(inflater, container, false);

        spPizza = binding.spPizza;
        spFritti = binding.spFritti;
        spBibite = binding.spBibite;

        addPizzeSpinner();
        addFrittiSpinner();
        addBibiteSpinner();

        binding.btAdd.setOnClickListener(view -> check() );
        binding.btClose.setOnClickListener(view -> {
            if (check())
                NavHostFragment.findNavController(putOrdine2.this).navigate(R.id.action_putOrdine2_to_home);
        });
        return binding.getRoot();
    }

    private boolean check() {
        if(spPizza.getSelectedItem().toString().equals("") && spFritti.getSelectedItem().toString().equals("") && spBibite.getSelectedItem().toString().equals("")){
            Toast.makeText(getContext(), "Ordine vuoto.\nInserire almeno un elemento", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            putOrdine2(spPizza, spFritti, spBibite);
            Toast.makeText(getContext(), "Ordine aggiunto", Toast.LENGTH_SHORT).show();
            return true;
        }
    }

    private void putOrdine2(Spinner spPizza, Spinner spFritti, Spinner spBibite) {
        executor.execute(() -> {
            try {
                URL url = new URL(getContext().getString(R.string.hostname) + getContext().getString(R.string.getLastOrdine));
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                lastID_global = Integer.parseInt(reader.readLine());
                connection.connect();
                reader.close();
                connection.disconnect();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        executor.execute(() -> {
            try {
                URL url = new URL(getContext().getString(R.string.hostname) + getContext().getString(R.string.getOrdine2) + "?id_ordine=" + lastID_global + "&pizza=" + spPizza.getSelectedItem().toString() + "&fritti=" + spFritti.getSelectedItem().toString() + "&bibite=" + spBibite.getSelectedItem().toString());
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                BufferedReader reader2 = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                connection.connect();
                reader2.readLine();
                reader2.close();
            } catch (MalformedURLException e) { e.printStackTrace();
            } catch (IOException e) { e.printStackTrace(); }
        });
    }

    private void addPizzeSpinner() {
        executor.execute(() -> {
            try {
                URL url = new URL(getContext().getString(R.string.hostname) + getContext().getString(R.string.getPizze));
                BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
                String line = reader.readLine();
                bundle.putString("spinnerPizza", line);
                reader.close();

                new Handler(getContext().getMainLooper()).post(()-> {
                    try {
                        JSONArray jsonArray = new JSONArray(bundle.getString("spinnerPizza"));
                        if (jsonArray.length() > 0) {
                            for (int i = 0; i < jsonArray.length(); i++)
                                listaPizze.add(jsonArray.getJSONObject(i).getString("nome"));

                            ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, listaPizze);
                            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            spPizza.setAdapter(adapter);
                        }
                        } catch (JSONException e) { e.printStackTrace();
                    }
                });
            } catch (MalformedURLException e) { e.printStackTrace();
            } catch (IOException e) { e.printStackTrace(); }
        });
    }

    private void addFrittiSpinner() {
        executor.execute(() -> {
            try {
                URL url = new URL(getContext().getString(R.string.hostname) + getContext().getString(R.string.getFritti));
                BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
                String line = reader.readLine();
                bundle.putString("spinnerFritti", line);
                reader.close();

                new Handler(getContext().getMainLooper()).post(()-> {
                    try {
                        JSONArray jsonArray = new JSONArray(bundle.getString("spinnerFritti"));
                        if (jsonArray.length() > 0) {
                            for (int i = 0; i < jsonArray.length(); i++)
                                listaFritti.add(jsonArray.getJSONObject(i).getString("nome"));

                            ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, listaFritti);
                            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            spFritti.setAdapter(adapter);
                        }
                    } catch (JSONException e) { e.printStackTrace(); }
                });
            } catch (MalformedURLException e) { e.printStackTrace();
            } catch (IOException e) { e.printStackTrace(); }
        });
    }

    private void addBibiteSpinner() {
        executor.execute(() -> {
            try {
                URL url = new URL(getContext().getString(R.string.hostname) + getContext().getString(R.string.getBibite));
                BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
                String line = reader.readLine();
                bundle.putString("spinnerBibite", line);
                reader.close();

                new Handler(getContext().getMainLooper()).post(()-> {
                    try {
                        JSONArray jsonArray = new JSONArray(bundle.getString("spinnerBibite"));
                        if (jsonArray.length() > 0) {
                            for (int i = 0; i < jsonArray.length(); i++)
                                listaBibite.add(jsonArray.getJSONObject(i).getString("nome"));

                            ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, listaBibite);
                            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            spBibite.setAdapter(adapter);
                        }
                    } catch (JSONException e) { e.printStackTrace(); }
                });
            } catch (MalformedURLException e) { e.printStackTrace();
            } catch (IOException e) { e.printStackTrace(); }
        });
    }
}
