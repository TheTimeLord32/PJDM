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
import com.mandija.pizzadamatteo.fragment.onlyPut.putOrdine;

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

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link home#newInstance} factory method to
 * create an instance of this fragment.
 */
public class home extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private FragmentHomeBinding binding;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public home() {}

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment home.
     */
    // TODO: Rename and change types and number of parameters
    public static home newInstance(String param1, String param2) {
        home fragment = new home();
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
        binding = FragmentHomeBinding.inflate(inflater, container, false);

        binding.btPutOrdine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { tryConnPutOrdine(); }
        });

        binding.btGetOrdine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { getOrdine(); }
        });

        binding.btGetStatsOrario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { getStatsOrario(); }
        });

        binding.btGetStatsPizze.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { getStatsPizze(); }
        });

        return binding.getRoot();
    }

    private Executor executor = Executors.newSingleThreadExecutor();
    private Bundle bundle = new Bundle();

    private void tryConnPutOrdine() {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                Boolean connected = tryConn();
                home.this.getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (connected == true) { NavHostFragment.findNavController(home.this).navigate(R.id.action_home_to_putOrdine);
                        } else { Toast.makeText(getContext(), "Inserimento ordine non disponibile", Toast.LENGTH_SHORT).show(); }
                    }
                });
            }
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
        executor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL(getContext().getString(R.string.hostname) + getContext().getString(R.string.getOrdine));
                    BufferedReader reader = new BufferedReader(new InputStreamReader(url.openConnection().getInputStream()));

                    String line = reader.readLine();
                    bundle.putString("line", line);
                    reader.close();

                    // main thread
                    Handler handler = new Handler(getContext().getMainLooper());
                    Runnable runnable = new Runnable() {
                        @Override
                        public void run() { NavHostFragment.findNavController(home.this).navigate(R.id.action_home_to_getOrdine, bundle); }
                    };
                    handler.post(runnable);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (ConnectException e) {
                    new Handler(getContext().getMainLooper()).post(new Runnable() {
                        @Override
                        public void run() { Toast.makeText(getContext(), "Mostra ordine non disponibile", Toast.LENGTH_SHORT).show(); }
                    });
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void getStatsOrario() {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL(getContext().getString(R.string.hostname) + getContext().getString(R.string.getStatsOrario));
                    BufferedReader reader = new BufferedReader(new InputStreamReader(url.openConnection().getInputStream()));

                    String line = reader.readLine();
                    bundle.putString("line", line);
                    Log.d("getStats", "run: " + line);
                    reader.close();

                    // main thread
                    Handler handler = new Handler(getContext().getMainLooper());
                    Runnable runnable = new Runnable() {
                        @Override
                        public void run() { NavHostFragment.findNavController(home.this).navigate(R.id.action_home_to_getStatsOrario, bundle); }
                    };
                    handler.post(runnable);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (ConnectException e) {
                    new Handler(getContext().getMainLooper()).post(new Runnable() {
                        @Override
                        public void run() { Toast.makeText(getContext(), "Statistiche orario non disponibile", Toast.LENGTH_SHORT).show(); }
                    });
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void getStatsPizze() {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL(getContext().getString(R.string.hostname) + getContext().getString(R.string.getStatsPizze));
                    BufferedReader reader = new BufferedReader(new InputStreamReader(url.openConnection().getInputStream()));

                    String line = reader.readLine();
                    bundle.putString("line", line);
                    Log.d("getStats", "run: " + line);
                    reader.close();

                    // main thread
                    Handler handler = new Handler(getContext().getMainLooper());
                    Runnable runnable = new Runnable() {
                        @Override
                        public void run() { NavHostFragment.findNavController(home.this).navigate(R.id.action_home_to_getStatsPizze, bundle); }
                    };
                    handler.post(runnable);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (ConnectException e) {
                    new Handler(getContext().getMainLooper()).post(new Runnable() {
                        @Override
                        public void run() { Toast.makeText(getContext(), "Statistiche pizze non disponibile", Toast.LENGTH_SHORT).show(); }
                    });
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

}