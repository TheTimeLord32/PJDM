package com.mandija.pizzadamatteo.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.mandija.pizzadamatteo.R;
import com.mandija.pizzadamatteo.databinding.FragmentHomeBinding;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class home extends Fragment {
    private FragmentHomeBinding binding;
    private Executor executor = Executors.newSingleThreadExecutor();
    private Bundle bundle = new Bundle();
    private String msgConn = "Connessione assente. Riprovare";
    public home() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        binding.btPutOrdine.setOnClickListener(v -> putOrdine());
        binding.btGetOrdine.setOnClickListener(v -> getOrdine());
        binding.btGetStatsOrario.setOnClickListener(v -> getStatsOrario());
        binding.btGetStatsPizze.setOnClickListener(v -> getStatsPizze());
        return binding.getRoot();
    }

    private void putOrdine() { connToDest(getContext().getString(R.string.getOrdine), R.id.action_home_to_putOrdine, "putOrdine"); }
    private void getOrdine() { connToDest(getContext().getString(R.string.getOrdine), R.id.action_home_to_getOrdine, "getOrdine"); }
    private void getStatsOrario() { connToDest(getContext().getString(R.string.getStatsOrario), R.id.action_home_to_getStatsOrario, "getStatsOrario"); }
    private void getStatsPizze() { connToDest(getContext().getString(R.string.getStatsPizze), R.id.action_home_to_getStatsPizze, "getStatsPizze"); }

    public void connToDest(String URI, int NavAction, String bundleToPass) {
        executor.execute(()-> {
            try {
                URL url = new URL(getContext().getString(R.string.hostname) + URI);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setConnectTimeout(3000);
                connection.connect();
                BufferedReader reader = new BufferedReader(new InputStreamReader(url.openConnection().getInputStream()));
                String line = reader.readLine();
                bundle.putString(bundleToPass, line);
                reader.close();
                new Handler(getContext().getMainLooper()).post(() -> NavHostFragment.findNavController(home.this).navigate(NavAction, bundle));
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                new Handler(getContext().getMainLooper()).post(() -> Toast.makeText(getContext(), msgConn, Toast.LENGTH_SHORT).show());
                e.printStackTrace();
            }
        });
    }
}