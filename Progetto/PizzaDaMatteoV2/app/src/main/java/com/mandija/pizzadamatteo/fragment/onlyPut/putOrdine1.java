package com.mandija.pizzadamatteo.fragment.onlyPut;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.mandija.pizzadamatteo.R;
import com.mandija.pizzadamatteo.databinding.FragmentPutOrdine1Binding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
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

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public putOrdine1() {
        // Required empty public constructor
    }

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
        String numPizza = bundle.getString("numPizze");
        Log.d("putOrdine1 - numPizza", "onCreateView: " + numPizza);

        spPizza1 = binding.spPizza1;
        spPizza2 = binding.spPizza2;
        spPizza3 = binding.spPizza3;
        spPizza4 = binding.spPizza4;
        spPizza5 = binding.spPizza5;
        addItemSpinner();
        spPizza1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {  }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) { }
        });

        /*spPizza2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String pizza = adapterView.getItemAtPosition(i).toString();
                Toast.makeText(getContext(), pizza, Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) { }
        });

        spPizza3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String pizza = adapterView.getItemAtPosition(i).toString();
                Toast.makeText(getContext(), pizza, Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) { }
        });

        spPizza4.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String pizza = adapterView.getItemAtPosition(i).toString();
                Toast.makeText(getContext(), pizza, Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) { }
        });

        spPizza5.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String pizza = adapterView.getItemAtPosition(i).toString();
                Toast.makeText(getContext(), pizza, Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) { }
        });
*/
        binding.btInviaOrdine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                putOrdine1(spPizza1);
                NavHostFragment.findNavController(putOrdine1.this).navigate(R.id.action_putOrdine1_to_home);
                Toast.makeText(getContext(), "Ordine inviato", Toast.LENGTH_SHORT).show();
            }
        });

        return binding.getRoot();
    }

    private Executor executor = Executors.newSingleThreadExecutor();
    private Bundle bundle = new Bundle();
    private void addItemSpinner() {
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

    private void putOrdine1(Spinner spPizza1) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL(getContext().getString(R.string.hostname) + getContext().getString(R.string.getOrdine1) + "?pizza1=" + spPizza1.getSelectedItem().toString());
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("POST");
                    BufferedReader reader = new BufferedReader(new java.io.InputStreamReader(connection.getInputStream()));
                    String line = reader.readLine();

                    Handler mainHandler = new Handler(getActivity().getMainLooper());
                    Runnable myRunnable = new Runnable() {
                        @Override
                        public void run() { }
                    };
                    mainHandler.post(myRunnable);
                    reader.close();
                } catch (ProtocolException e) {
                    e.printStackTrace();
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}