package com.mandija.pizzadamatteo.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.mandija.pizzadamatteo.R;
import com.mandija.pizzadamatteo.databinding.FragmentHomeBinding;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class home extends Fragment {
    private FragmentHomeBinding binding;
    private Executor executor = Executors.newSingleThreadExecutor();
    private Bundle bundle = new Bundle();
    private String abConn = "Connessione assente. Riprovare";
    public home() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);

        binding.btPutOrdine.setOnClickListener(v -> tryConnPutOrdine());
        binding.btGetOrdine.setOnClickListener(v -> getOrdine());
        binding.btGetStatsOrario.setOnClickListener(v -> getStatsOrario());
        binding.btGetStatsPizze.setOnClickListener(v -> getStatsPizze());
        return binding.getRoot();
    }

    private void tryConnPutOrdine() {
        executor.execute(() -> {
            Boolean connected = tryConn();
            home.this.getActivity().runOnUiThread(() -> {
                if (connected) { NavHostFragment.findNavController(home.this).navigate(R.id.action_home_to_putOrdine);
                } else { Toast.makeText(getContext(), abConn, Toast.LENGTH_SHORT).show(); }
            });
        });
    }

    private boolean tryConn() {
        try {
            URL url = new URL(getContext().getString(R.string.hostname));
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(3000);
            connection.connect();
            return true;
        } catch (ProtocolException e) {
            e.printStackTrace();
            return false;
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    private void getOrdine() {
        executor.execute(() -> {
            try {
                URL url = new URL(getContext().getString(R.string.hostname) + getContext().getString(R.string.getOrdine));
                BufferedReader reader = new BufferedReader(new InputStreamReader(url.openConnection().getInputStream()));
                String line = reader.readLine();
                bundle.putString("getOrdine", line);
                reader.close();
                new Handler(getContext().getMainLooper()).post(() -> { NavHostFragment.findNavController(home.this).navigate(R.id.action_home_to_getOrdine, bundle);});
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (ConnectException e) {
                new Handler(getContext().getMainLooper()).post(() -> Toast.makeText(getContext(), abConn, Toast.LENGTH_SHORT).show());
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
                new Handler(getContext().getMainLooper()).post(() -> Toast.makeText(getContext(), "Tabella ordine vuota", Toast.LENGTH_SHORT).show());
            }
        });
    }

    private void getStatsOrario() {
        executor.execute(() -> {
            try {
                URL url = new URL(getContext().getString(R.string.hostname) + getContext().getString(R.string.getStatsOrario));
                BufferedReader reader = new BufferedReader(new InputStreamReader(url.openConnection().getInputStream()));
                String line = reader.readLine();
                bundle.putString("line", line);
                reader.close();
                new Handler(getContext().getMainLooper()).post(() -> NavHostFragment.findNavController(home.this).navigate(R.id.action_home_to_getStatsOrario, bundle));
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (ConnectException e) {
                new Handler(getContext().getMainLooper()).post(() -> Toast.makeText(getContext(), abConn, Toast.LENGTH_SHORT).show());
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
                new Handler(getContext().getMainLooper()).post(() -> Toast.makeText(getContext(), "Statistiche orario vuote", Toast.LENGTH_SHORT).show());
            }
        });
    }

    private void getStatsPizze() {
        executor.execute(() -> {
            try {
                URL url = new URL(getContext().getString(R.string.hostname) + getContext().getString(R.string.getStatsPizze));
                BufferedReader reader = new BufferedReader(new InputStreamReader(url.openConnection().getInputStream()));
                String line = reader.readLine();
                bundle.putString("line", line);
                reader.close();
                new Handler(getContext().getMainLooper()).post(() -> NavHostFragment.findNavController(home.this).navigate(R.id.action_home_to_getStatsPizze, bundle));
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (ConnectException e) {
                new Handler(getContext().getMainLooper()).post(() -> Toast.makeText(getContext(), abConn, Toast.LENGTH_SHORT).show());
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
                new Handler(getContext().getMainLooper()).post(() -> Toast.makeText(getContext(), "Statistiche pizze vuote", Toast.LENGTH_SHORT).show());
            }
        });
    }
}