package com.mandija.pizzadamatteo.fragment.onlyPut;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.mandija.pizzadamatteo.R;
import com.mandija.pizzadamatteo.databinding.FragmentPutOrdine1Binding;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link putOrdine1#newInstance} factory method to
 * create an instance of this fragment.
 */
public class putOrdine1 extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private FragmentPutOrdine1Binding binding;

    private Spinner spPizza1, spPizza2, spPizza3, spPizza4, spPizza5;
    private ArrayList<String> listaPizze = new ArrayList<>();

    private Spinner spFritti1, spFritti2, spFritti3, spFritti4, spFritti5;
    private ArrayList<String> listaFritti = new ArrayList<>();

    private Spinner spBibite1, spBibite2, spBibite3, spBibite4, spBibite5;
    private ArrayList<String> listaBibite = new ArrayList<>();

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public putOrdine1() { }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment putOrdine1.
     */
    // TODO: Rename and change types and number of parameters
    public static putOrdine1 newInstance(String param1, String param2) {
        putOrdine1 fragment = new putOrdine1();
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
        binding = FragmentPutOrdine1Binding.inflate(inflater, container, false);
        bundle = getArguments();

        spPizza1 = binding.spPizza1;
        spPizza2 = binding.spPizza2;
        spPizza3 = binding.spPizza3;
        spPizza4 = binding.spPizza4;
        spPizza5 = binding.spPizza5;
        addPizzeSpinner();

        spFritti1 = binding.spFritti1;
        spFritti2 = binding.spFritti2;
        spFritti3 = binding.spFritti3;
        spFritti4 = binding.spFritti4;
        spFritti5 = binding.spFritti5;
        addFrittiSpinner();

        spBibite1 = binding.spBibite1;
        spBibite2 = binding.spBibite2;
        spBibite3 = binding.spBibite3;
        spBibite4 = binding.spBibite4;
        spBibite5 = binding.spBibite5;
        addBibiteSpinner();

        binding.btInviaOrdine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (spPizza1.getSelectedItem().toString().equals("") && spPizza2.getSelectedItem().toString().equals("") && spPizza3.getSelectedItem().toString().equals("") && spPizza4.getSelectedItem().toString().equals("") && spPizza5.getSelectedItem().toString().equals("")
                        && spFritti1.getSelectedItem().toString().equals("") && spFritti2.getSelectedItem().toString().equals("") && spFritti3.getSelectedItem().toString().equals("") && spFritti4.getSelectedItem().toString().equals("") && spFritti5.getSelectedItem().toString().equals("")
                        && spBibite1.getSelectedItem().toString().equals("") && spBibite2.getSelectedItem().toString().equals("") && spBibite3.getSelectedItem().toString().equals("") && spBibite4.getSelectedItem().toString().equals("") && spBibite5.getSelectedItem().toString().equals("")) {
                    Toast.makeText(getContext(), "Ordine vuoto.\nInserire almeno un elemento", Toast.LENGTH_SHORT).show();
                } else {
                    putOrdine();
                    putOrdine1(spPizza1, spPizza2, spPizza3, spPizza4, spPizza5, spFritti1, spFritti2, spFritti3, spFritti4, spFritti5, spBibite1, spBibite2, spBibite3, spBibite4, spBibite5);
                    NavHostFragment.findNavController(putOrdine1.this).navigate(R.id.action_putOrdine1_to_home);
                    Toast.makeText(getContext(), "Ordine inviato", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return binding.getRoot();
    }

    private Executor executor = Executors.newSingleThreadExecutor();
    private Bundle bundle = new Bundle();

    private void addPizzeSpinner() {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL(getContext().getString(R.string.hostname) + getContext().getString(R.string.getPizze));
                    BufferedReader reader = new BufferedReader(new java.io.InputStreamReader(url.openStream()));
                    String line = reader.readLine();
                    bundle.putString("pizza", line);
                    reader.close();

                    Handler handler = new Handler(getContext().getMainLooper());
                    Runnable run = new Runnable() {
                        @Override
                        public void run() {
                            try {
                                JSONArray jsonArray = new JSONArray(bundle.getString("pizza"));
                                if (jsonArray.length() > 0) {
                                    for (int i = 0; i < jsonArray.length(); i++) {
                                        listaPizze.add(jsonArray.getJSONObject(i).getString("nome"));
                                    }
                                    ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, listaPizze);
                                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                    spPizza1.setAdapter(adapter);
                                    spPizza2.setAdapter(adapter);
                                    spPizza3.setAdapter(adapter);
                                    spPizza4.setAdapter(adapter);
                                    spPizza5.setAdapter(adapter);
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    };
                    handler.post(run);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void addFrittiSpinner() {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL(getContext().getString(R.string.hostname) + getContext().getString(R.string.getFritti));
                    BufferedReader reader = new BufferedReader(new java.io.InputStreamReader(url.openStream()));
                    String line = reader.readLine();
                    bundle.putString("fritti", line);
                    reader.close();

                    Handler handler = new Handler(getContext().getMainLooper());
                    Runnable run = new Runnable() {
                        @Override
                        public void run() {
                            try {
                                JSONArray jsonArray = new JSONArray(bundle.getString("fritti"));
                                if (jsonArray.length() > 0) {
                                    for (int i = 0; i < jsonArray.length(); i++) {
                                        listaFritti.add(jsonArray.getJSONObject(i).getString("nome"));
                                    }
                                    ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, listaFritti);
                                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                    spFritti1.setAdapter(adapter);
                                    spFritti2.setAdapter(adapter);
                                    spFritti3.setAdapter(adapter);
                                    spFritti4.setAdapter(adapter);
                                    spFritti5.setAdapter(adapter);
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    };
                    handler.post(run);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void addBibiteSpinner() {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL(getContext().getString(R.string.hostname) + getContext().getString(R.string.getBibite));
                    BufferedReader reader = new BufferedReader(new java.io.InputStreamReader(url.openStream()));
                    String line = reader.readLine();
                    bundle.putString("bibite", line);
                    reader.close();

                    Handler handler = new Handler(getContext().getMainLooper());
                    Runnable run = new Runnable() {
                        @Override
                        public void run() {
                            try {
                                JSONArray jsonArray = new JSONArray(bundle.getString("bibite"));
                                if (jsonArray.length() > 0) {
                                    for (int i = 0; i < jsonArray.length(); i++) {
                                        listaBibite.add(jsonArray.getJSONObject(i).getString("nome"));
                                    }
                                    ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, listaBibite);
                                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                    spBibite1.setAdapter(adapter);
                                    spBibite2.setAdapter(adapter);
                                    spBibite3.setAdapter(adapter);
                                    spBibite4.setAdapter(adapter);
                                    spBibite5.setAdapter(adapter);
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    };
                    handler.post(run);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void putOrdine() {
        String nome_cliente = bundle.getString("nome_cliente");
        String orario_convertito = bundle.getString("orario_convertito");
        String recapito = bundle.getString("recapito");
        String indirizzo = bundle.getString("indirizzo");

        executor.execute(() -> {
            try {
                URL url = new URL(getContext().getString(R.string.hostname) + getContext().getString(R.string.getOrdine) + "?nome_cliente=" + nome_cliente + "&orario=" + orario_convertito + "&recapito=" + recapito + "&indirizzo=" + indirizzo);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line = reader.readLine();
                bundle.putString("ordine", line);
                connection.connect();
                reader.close();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    private void putOrdine1(Spinner spPizza1, Spinner spPizza2, Spinner spPizza3, Spinner spPizza4, Spinner spPizza5, Spinner spFritti1, Spinner spFritti2, Spinner spFritti3, Spinner spFritti4, Spinner spFritti5, Spinner spBibite1, Spinner spBibite2, Spinner spBibite3, Spinner spBibite4, Spinner spBibite5) {
        executor.execute(() -> {
            try {
                URL url = new URL(getContext().getString(R.string.hostname) + getContext().getString(R.string.getOrdine1) + spPizza1.getSelectedItem().toString() + "&pizza2=" + spPizza2.getSelectedItem().toString() + "&pizza3=" + spPizza3.getSelectedItem().toString() + "&pizza4=" + spPizza4.getSelectedItem().toString() + "&pizza5=" + spPizza5.getSelectedItem().toString() + "&fritti1=" + spFritti1.getSelectedItem().toString() + "&fritti2=" + spFritti2.getSelectedItem().toString() + "&fritti3=" + spFritti3.getSelectedItem().toString() + "&fritti4=" + spFritti4.getSelectedItem().toString() + "&fritti5=" + spFritti5.getSelectedItem().toString() + "&bibite1=" + spBibite1.getSelectedItem().toString() + "&bibite2=" + spBibite2.getSelectedItem().toString() + "&bibite3=" + spBibite3.getSelectedItem().toString() + "&bibite4=" + spBibite4.getSelectedItem().toString() + "&bibite5=" + spBibite5.getSelectedItem().toString());
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                connection.connect();
                reader.close();
                Handler mainHandler = new Handler(getActivity().getMainLooper());
                Runnable myRunnable = () -> { };
                mainHandler.post(myRunnable);
                reader.close();
            } catch (ProtocolException e) {
                e.printStackTrace();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}