package com.mandija.pizzadamatteo.fragment.onlyGet;

import android.os.Bundle;

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

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link getOrdine#newInstance} factory method to
 * create an instance of this fragment.
 */
public class getOrdine extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public getOrdine() {}

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment getOrdine.
     */
    // TODO: Rename and change types and number of parameters
    public static getOrdine newInstance(String param1, String param2) {
        getOrdine fragment = new getOrdine();
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

    private RecyclerView rv;
    private ListaOrdineAdapter adapter;
    private FragmentGetOrdineBinding binding;

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
                getOrdine1(id_ordine);
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

    private void getOrdine1(String id_ordine) {
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

                    if (line.equals("[]"))
                    {
                        handler.post(new Runnable() {
                            @Override
                            public void run() { Toast.makeText(getContext(), "Ordine non trovato", Toast.LENGTH_SHORT).show(); }
                        });
                    } else {
                        Runnable runnable = new Runnable() {
                            @Override
                            public void run() { NavHostFragment.findNavController(getOrdine.this).navigate(R.id.action_getOrdine_to_getOrdine1, bundle); }
                        };
                        handler.post(runnable);
                    }
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
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
                }
            }
        });
    }
}