package com.mandija.pizzadamatteo.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mandija.pizzadamatteo.R;
import com.mandija.pizzadamatteo.entity.ListaOrdine1Elemento;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

public class ListaOrdine1Adapter extends RecyclerView.Adapter<ListaOrdine1Adapter.ORD1ViewAdapter> {
    private ArrayList<ListaOrdine1Elemento> dati;
    private Context context;
    private LayoutInflater inflater;

    public ListaOrdine1Adapter(Context context) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        dati = new ArrayList<ListaOrdine1Elemento>();
    }

    @NonNull
    @Override
    public ORD1ViewAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = inflater.inflate(R.layout.getordine1_row, parent, false);
        ORD1ViewAdapter vh = new ORD1ViewAdapter(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ListaOrdine1Adapter.ORD1ViewAdapter holder, int position) {
        holder.tvPizza1.setText(dati.get(position).getPizza1());
        holder.tvPizza2.setText(dati.get(position).getPizza2());
        holder.tvPizza3.setText(dati.get(position).getPizza3());
        holder.tvPizza4.setText(dati.get(position).getPizza4());
        holder.tvPizza5.setText(dati.get(position).getPizza5());
        holder.tvFritti1.setText(dati.get(position).getFritti1());
        holder.tvFritti2.setText(dati.get(position).getFritti2());
        holder.tvFritti3.setText(dati.get(position).getFritti3());
        holder.tvFritti4.setText(dati.get(position).getFritti4());
        holder.tvFritti5.setText(dati.get(position).getFritti5());
        holder.tvBibite1.setText(dati.get(position).getBibite1());
        holder.tvBibite2.setText(dati.get(position).getBibite2());
        holder.tvBibite3.setText(dati.get(position).getBibite3());
        holder.tvBibite4.setText(dati.get(position).getBibite4());
        holder.tvBibite5.setText(dati.get(position).getBibite5());
    }

    @Override
    public int getItemCount() { return dati.size(); }

    public void aggiungi1(JSONArray array) throws JSONException {

        for (int i = 0; i < array.length(); i++) {
            try {
                Log.d("aggiungiJSON", "aggiungi1: prima ADD");
                dati.add(new ListaOrdine1Elemento(array.getJSONObject(i).getInt("id_ordine"), array.getJSONObject(i).getString("pizza1"), array.getJSONObject(i).getString("pizza2"), array.getJSONObject(i).getString("pizza3"), array.getJSONObject(i).getString("pizza4"), array.getJSONObject(i).getString("pizza5"), array.getJSONObject(i).getString("fritti1"), array.getJSONObject(i).getString("fritti2"), array.getJSONObject(i).getString("fritti3"), array.getJSONObject(i).getString("fritti4"), array.getJSONObject(i).getString("fritti5"), array.getJSONObject(i).getString("bibite1"), array.getJSONObject(i).getString("bibite2"), array.getJSONObject(i).getString("bibite3"), array.getJSONObject(i).getString("bibite4"), array.getJSONObject(i).getString("bibite5")));
                Log.d("aggiungiJSON", "aggiungi1: dopo ADD");
            } catch (JSONException e) {
                e.printStackTrace();
                Toast.makeText(context, "Errore nella stampa", Toast.LENGTH_SHORT).show();
            }
            notifyDataSetChanged();
        }
    }

    public class ORD1ViewAdapter extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView tvId_ordine, tvPizza1, tvPizza2, tvPizza3, tvPizza4, tvPizza5, tvFritti1, tvFritti2, tvFritti3, tvFritti4, tvFritti5, tvBibite1, tvBibite2, tvBibite3, tvBibite4, tvBibite5;

        public ORD1ViewAdapter(@NonNull View itemView) {
            super(itemView);
            tvId_ordine = itemView.findViewById(R.id.tvId_ordine);
            tvPizza1 = itemView.findViewById(R.id.tvPizza1);
            tvPizza2 = itemView.findViewById(R.id.tvPizza2);
            tvPizza3 = itemView.findViewById(R.id.tvPizza3);
            tvPizza4 = itemView.findViewById(R.id.tvPizza4);
            tvPizza5 = itemView.findViewById(R.id.tvPizza5);
            tvFritti1 = itemView.findViewById(R.id.tvFritti1);
            tvFritti2 = itemView.findViewById(R.id.tvFritti2);
            tvFritti3 = itemView.findViewById(R.id.tvFritti3);
            tvFritti4 = itemView.findViewById(R.id.tvFritti4);
            tvFritti5 = itemView.findViewById(R.id.tvFritti5);
            tvBibite1 = itemView.findViewById(R.id.tvBibite1);
            tvBibite2 = itemView.findViewById(R.id.tvBibite2);
            tvBibite3 = itemView.findViewById(R.id.tvBibite3);
            tvBibite4 = itemView.findViewById(R.id.tvBibite4);
            tvBibite5 = itemView.findViewById(R.id.tvBibite5);
        }

        @Override
        public void onClick(View view) { }
    }
}