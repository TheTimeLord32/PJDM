package com.mandija.testnet;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.mandija.testnet.databinding.FragmentFirstBinding;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class FirstFragment extends Fragment {

    private FragmentFirstBinding binding;
    private Executor executor = Executors.newSingleThreadExecutor();

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentFirstBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.buttonFirst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(FirstFragment.this)
                        .navigate(R.id.action_FirstFragment_to_SecondFragment);
            }
        });

        binding.btAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addText();
            }
        });
    }

    private void addText() {
        Log.d("PPLNET", "addText() called Thread: " + Thread.currentThread().getName());

        executor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    Log.d("PPLNET", "run() called" + Thread.currentThread().getName());
                    URL url = new URL("http://pjdm.netgroup.uniroma2.it/test/gen.php");
                    BufferedReader br = new BufferedReader(new InputStreamReader(url.openConnection().getInputStream()));
                    String line = br.readLine();
                    Log.d("PPLNET", "addText() data: " + line);
                    String frase = new JSONObject(line).getString("frase");
                    binding.tvMain.append(frase + "\n\n");

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        
        /*Thread t = new Thread() {
            @Override
            public void run() {
                try {
                    Log.d("PPLNET", "run() called" + Thread.currentThread().getName());
                    URL url = new URL("http://pjdm.netgroup.uniroma2.it/test/gen.php");
                    BufferedReader br = new BufferedReader(new InputStreamReader(url.openConnection().getInputStream()));
                    String line = br.readLine();
                    Log.d("PPLNET", "addText() data: " + line);
                    String frase = new JSONObject(line).getString("frase");
                    binding.tvMain.append(frase + "\n\n");

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };
        t.start();*/
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}