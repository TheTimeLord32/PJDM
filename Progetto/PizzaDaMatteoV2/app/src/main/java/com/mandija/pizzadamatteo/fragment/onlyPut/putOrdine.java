package com.mandija.pizzadamatteo.fragment.onlyPut;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import java.time.format.DateTimeFormatter;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link putOrdine#newInstance} factory method to
 * create an instance of this fragment.
 */
public class putOrdine extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private FragmentPutOrdineBinding binding;
    private Bundle bundle = new Bundle();
    private Executor executor = Executors.newSingleThreadExecutor();

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public putOrdine() { }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment putOrdine.
     */
    // TODO: Rename and change types and number of parameters
    public static putOrdine newInstance(String param1, String param2) {
        putOrdine fragment = new putOrdine();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentPutOrdineBinding.inflate(inflater, container, false);

        binding.btBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavHostFragment.findNavController(putOrdine.this).navigate(R.id.action_putOrdine_to_home);
            }
        });

        binding.btNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nome_cliente = binding.etCliente.getText().toString();
                String orario = binding.etOrario.getText().toString();
                String recapito = binding.etRecapito.getText().toString();
                String indirizzo = binding.etIndirizzo.getText().toString();

                boolean nomeValido = nome_cliente.matches("[a-zA-Z]{3,50}+");
                boolean recapitoValido = recapito.matches("[0-9]{10}+");
                boolean indirizzoValido = indirizzo.matches("[a-zA-Z0-9]{5,50}+");

                try {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
                    LocalTime time = formatter.parse(orario, LocalTime::from);
                    String orario_convertito = time.toString();

                    if (!nomeValido || nome_cliente.isEmpty()) { binding.etCliente.setError("Nome non valido, inserire solo caratteri alfabetici.\nMinimo 3, massimo 50 caratteri."); }
                    if (!recapitoValido || recapito.isEmpty()) { binding.etRecapito.setError("Recapito non valido, inserire solo numeri.\nMassimo 10 cifre."); }
                    if (!indirizzoValido || indirizzo.isEmpty()) { binding.etIndirizzo.setError("Indirizzo non valido, inserire solo caratteri alfabetici e numeri.\nMinimo 5, massimo 50 caratteri."); }

                    if (nomeValido && orario_convertito.length() == 5 && recapitoValido && indirizzoValido) {
                        putOrdine(nome_cliente, orario_convertito, recapito, indirizzo);
                        NavHostFragment.findNavController(putOrdine.this).navigate(R.id.action_putOrdine_to_putOrdine1, bundle);
                    }
                } catch (Exception e) {
                    binding.etCliente.setError("Campo vuoto o incorretto. Controllare");
                    binding.etOrario.setError("Campo vuoto o incorretto.\nFormato corretto: HH:mm");
                    binding.etRecapito.setError("Campo vuoto o incorretto. Controllare");
                    binding.etIndirizzo.setError("Campo vuoto o incorretto. Controllare");
                    e.printStackTrace();
                }
            }
        });
        return binding.getRoot();
    }

    // passare gli edit text nel URL della servlet
    private void putOrdine(String nome_cliente, String orario, String recapito, String indirizzo) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL(getContext().getString(R.string.hostname) + getContext().getString(R.string.getOrdine) + "?nome_cliente=" + nome_cliente + "&orario=" + orario + "&recapito=" + recapito + "&indirizzo=" + indirizzo);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("POST");
                    BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    String line = reader.readLine();
                    bundle.putString("line", line);

                    Handler mainHandler = new Handler(getActivity().getMainLooper());
                    Runnable myRunnable = new Runnable() {
                        @Override
                        public void run() { }
                    };
                    mainHandler.post(myRunnable);
                    reader.close();
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (ConnectException e) {
                    new Handler(getContext().getMainLooper()).post(new Runnable() {
                        @Override
                        public void run() { Toast.makeText(getContext(), "Connessione non disponibile", Toast.LENGTH_SHORT).show(); }
                    });
                    e.printStackTrace();
                } catch (IOException e) {
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getActivity(), "Ordine errato", Toast.LENGTH_SHORT).show();
                            binding.etCliente.setText("");
                            binding.etOrario.setText("");
                            binding.etRecapito.setText("");
                            binding.etIndirizzo.setText("");
                        }
                    });
                    e.printStackTrace();
                }
            }
        });
    }
}
