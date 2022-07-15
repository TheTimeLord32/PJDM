package com.mandija.pizzadamatteo.fragment.onlyPut;

import android.app.TimePickerDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.mandija.pizzadamatteo.R;
import com.mandija.pizzadamatteo.databinding.FragmentPutOrdineBinding;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalTime;
import java.util.Locale;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class putOrdine extends Fragment {
    private FragmentPutOrdineBinding binding;
    private String msgErrore = "Campo vuoto o incorretto, controllare.";
    private Executor executor = Executors.newSingleThreadExecutor();

    public putOrdine() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentPutOrdineBinding.inflate(inflater, container, false);
        Button setTime = binding.btSetTime;

        setTime.setOnClickListener(v -> {
            TimePickerDialog timePickerDialog = new TimePickerDialog(getContext(), (view, hourOfDay, minute) -> {
                int ora = hourOfDay;
                int minuti = minute;
                setTime.setText(String.format(Locale.getDefault(), "%02d:%02d", ora, minuti));
            }, LocalTime.now().getHour(), LocalTime.now().getMinute(), true);
            timePickerDialog.show();
        });

        binding.btBack.setOnClickListener(v -> NavHostFragment.findNavController(putOrdine.this).navigate(R.id.action_putOrdine_to_home));

        binding.btNext.setOnClickListener(v -> {
            String nome_cliente = binding.inputCliente.getText().toString();
            String orario = setTime.getText().toString();
            String recapito = binding.inputRecapito.getText().toString();
            String indirizzo = binding.inputIndirizzo.getText().toString();

            boolean nomeValido = nome_cliente.matches("[a-zA-Z ]{3,50}+");
            boolean orarioValido = orario.matches("[0-9]{2}:[0-9]{2}");
            boolean recapitoValido = recapito.matches("[0-9]{10}+");
            boolean indirizzoValido = indirizzo.matches("[a-zA-Z0-9 ,]{5,50}+");

            if (!nomeValido || nome_cliente.isEmpty()) { binding.inputCliente.setError(msgErrore + "\nInserire solo caratteri alfabetici: minimo 3, massimo 50 caratteri."); }
            if (!orarioValido || orario.equals("Scegli orario")) { Toast.makeText(getContext(), "Inserire orario", Toast.LENGTH_SHORT).show(); }
            if (!recapitoValido || recapito.isEmpty()) { binding.inputRecapito.setError(msgErrore + "\nInserire solo cifre: 10 cifre."); }
            if (!indirizzoValido || indirizzo.isEmpty()) { binding.inputIndirizzo.setError(msgErrore + "\nInserire solo caratteri alfabetici e cifre.\nMinimo 5, massimo 50 caratteri."); }

            if (nomeValido && orarioValido && recapitoValido && indirizzoValido)
                putOrdine(nome_cliente, orario, recapito, indirizzo);
        });
        return binding.getRoot();
    }

    private void putOrdine(String nome_cliente, String orario, String recapito, String indirizzo) {
        executor.execute(() -> {
            try {
                URL url = new URL(getContext().getString(R.string.hostname) + getContext().getString(R.string.getOrdine) + "?nome_cliente=" + nome_cliente + "&orario=" + orario + "&recapito=" + recapito + "&indirizzo=" + indirizzo);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                reader.readLine();
                reader.close();
                new Handler(getActivity().getMainLooper()).post(()-> NavHostFragment.findNavController(putOrdine.this).navigate(R.id.action_putOrdine_to_putOrdine2));
            } catch (MalformedURLException e) { e.printStackTrace();
            } catch (ConnectException e) {
                new Handler(getContext().getMainLooper()).post(() -> Toast.makeText(getContext(), "Connessione assente. Riprovare", Toast.LENGTH_SHORT).show());
                e.printStackTrace();
            } catch (IOException e) {
                getActivity().runOnUiThread(() -> Toast.makeText(getActivity(), "Ordine errato", Toast.LENGTH_SHORT).show());
                e.printStackTrace();
            }
        });
    }
}
