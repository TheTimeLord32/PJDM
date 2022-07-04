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
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
    private Pattern pattern = Pattern.compile("\\d\\d[:]\\d\\d");
    private Matcher matcher;

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
                matcher = pattern.matcher(orario);  // necessario per regex su orario

                if(nome_cliente.isEmpty() || orario.isEmpty() || recapito.isEmpty() || indirizzo.isEmpty()) {
                    Toast.makeText(getContext(), "Riempi tutti i campi", Toast.LENGTH_SHORT).show();
                    binding.etCliente.setError("Riempi il campo");
                    binding.etOrario.setError("Riempi il campo");
                    binding.etRecapito.setError("Riempi il campo");
                    binding.etIndirizzo.setError("Riempi il campo");
                }

                boolean nomeValido = nome_cliente.length() > 50;
                boolean orarioValido = matcher.matches() != true;
                boolean recapitoValido = recapito.length() > 50;
                boolean indirizzoValido = indirizzo.length() > 50;

                if (nomeValido) {
                    Toast.makeText(getContext(), "Nome cliente troppo lungo", Toast.LENGTH_SHORT).show();
                    binding.etCliente.setError("Massimo 50 caratteri");
                }
                if (recapitoValido) {
                    Toast.makeText(getContext(), "Recapito più lungo di 10 caratteri", Toast.LENGTH_SHORT).show();
                    binding.etRecapito.setError("Massimo 10 caratteri");
                }
                if (indirizzoValido) {
                    Toast.makeText(getContext(), "Indirizzo troppo lungo", Toast.LENGTH_SHORT).show();
                    binding.etIndirizzo.setError("Massimo 50 caratteri");
                }
                if (orarioValido) {
                    Toast.makeText(getContext(), "Formato non corretto.", Toast.LENGTH_SHORT).show();
                    binding.etOrario.setError("Formato corretto: hh:mm");
                }
                if (nomeValido == false && orarioValido == false && recapitoValido == false && indirizzoValido == false && matcher.matches() == true) {
                    putOrdine(nome_cliente, orario, recapito, indirizzo);
                    NavHostFragment.findNavController(putOrdine.this).navigate(R.id.action_putOrdine_to_putOrdine1, bundle);
                    Toast.makeText(getContext(), "Ordine inviato", Toast.LENGTH_SHORT).show();
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

                    // main thread
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
