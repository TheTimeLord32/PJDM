package com.mandija.pizzadamatteo.adapter;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mandija.pizzadamatteo.R;
import com.mandija.pizzadamatteo.databinding.FragmentGetOrdine1Binding;
import com.mandija.pizzadamatteo.entity.ListaOrdine1Elemento;
import com.mandija.pizzadamatteo.entity.ListaOrdineElemento;
import com.mandija.pizzadamatteo.fragment.onlyGet.getOrdine;
import com.mandija.pizzadamatteo.fragment.onlyGet.getOrdine1;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class ListaOrdineAdapter extends RecyclerView.Adapter<ListaOrdineAdapter.ORDViewAdapter> {

    private ArrayList<ListaOrdineElemento> dati;
    private Context context;
    private LayoutInflater inflater;

    public ListaOrdineAdapter(Context context) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        dati = new ArrayList<ListaOrdineElemento>();
    }

    @NonNull
    @Override
    public ORDViewAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = inflater.inflate(R.layout.getordine_row, parent, false);
        ORDViewAdapter vh = new ORDViewAdapter(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ORDViewAdapter holder, int position) {
        holder.tvId_ordine.setText(Integer.toString(dati.get(position).getId_ordine()));
        holder.tvCliente.setText(dati.get(position).getNome_cliente());
        holder.tvOrario.setText(dati.get(position).getOrario());
        holder.tvRecapito.setText(dati.get(position).getRecapito());
        holder.tvIndirizzo.setText(dati.get(position).getIndirizzo());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("getOrdine-getOrdine1", "onClick: clicked on: " + dati.get(holder.getAdapterPosition()).getId_ordine());

                /* Bundle bundle = new Bundle();
                bundle.putInt("id_ordine", dati.get(holder.getAdapterPosition()).getId_ordine());
                bundle.putString("pizza1", dati.get(holder.getAdapterPosition()).getPizza1());
                bundle.putString("pizza2", dati.get(holder.getAdapterPosition()).getPizza2());
                bundle.putString("pizza3", dati.get(holder.getAdapterPosition()).getPizza3());
                bundle.putString("fritti1", dati.get(holder.getAdapterPosition()).getFritti1());
                bundle.putString("fritti2", dati.get(holder.getAdapterPosition()).getFritti2());
                bundle.putString("bibite1", dati.get(holder.getAdapterPosition()).getBibite1());

                getOrdine1 go = new getOrdine1();

                AppCompatActivity activity = (AppCompatActivity) view.getContext();
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.fl_lista1, go).addToBackStack(null).commit();
                */
            }
        });
    }

    @Override
    public int getItemCount() { return dati.size(); }

    public void aggiungi(JSONArray array) {
        for (int i = 0; i < array.length(); i++) {
            try {
                dati.add(new ListaOrdineElemento(array.getJSONObject(i).getInt("id_ordine"), array.getJSONObject(i).getString("nomeCliente"), array.getJSONObject(i).getString("orario"), array.getJSONObject(i).getString("recapito"), array.getJSONObject(i).getString("indirizzo")));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            notifyDataSetChanged();
        }
    }

    class ORDViewAdapter extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tvId_ordine, tvCliente, tvOrario, tvRecapito, tvIndirizzo;

        public ORDViewAdapter(@NonNull View itemView) {
            super(itemView);
            tvId_ordine = itemView.findViewById(R.id.tvId_ordine);
            tvCliente = itemView.findViewById(R.id.tvCliente);
            tvOrario = itemView.findViewById(R.id.tvOrario);
            tvRecapito = itemView.findViewById(R.id.tvRecapito);
            tvIndirizzo = itemView.findViewById(R.id.tvIndirizzo);
        }

        @Override
        public void onClick(View view) { }
    }
}
