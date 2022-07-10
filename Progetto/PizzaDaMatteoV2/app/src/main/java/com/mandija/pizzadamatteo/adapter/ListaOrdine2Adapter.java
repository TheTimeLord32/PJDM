package com.mandija.pizzadamatteo.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mandija.pizzadamatteo.R;
import com.mandija.pizzadamatteo.entity.ListaOrdine2Elemento;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

public class ListaOrdine2Adapter extends RecyclerView.Adapter<ListaOrdine2Adapter.ORD2ViewAdapter> {
    private ArrayList<ListaOrdine2Elemento> dati;
    private Context context;
    private LayoutInflater inflater;

    public ListaOrdine2Adapter(Context context) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.dati = new ArrayList<ListaOrdine2Elemento>();
    }

    @NonNull
    @Override
    public ORD2ViewAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = inflater.inflate(R.layout.getordine2_row, parent, false);
        ORD2ViewAdapter vh = new ORD2ViewAdapter(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ORD2ViewAdapter holder, int position) {
        holder.tvPizza.setText(dati.get(position).getPizza());
        holder.tvFritti.setText(dati.get(position).getFritti());
        holder.tvBibite.setText(dati.get(position).getBibite());
    }



    @Override
    public int getItemCount() { return dati.size(); }

    public void aggiungi(JSONArray array) {
        for (int i = 0; i < array.length(); i++) {
            try {
                dati.add(new ListaOrdine2Elemento(array.getJSONObject(i).getString("pizza"), array.getJSONObject(i).getString("fritti"), array.getJSONObject(i).getString("bibite")));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        notifyDataSetChanged();
    }

    public class ORD2ViewAdapter extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView tvPizza, tvFritti, tvBibite;
        public ORD2ViewAdapter(@NonNull View itemView) {
            super(itemView);
            tvPizza = itemView.findViewById(R.id.tvPizza);
            tvFritti = itemView.findViewById(R.id.tvFritti);
            tvBibite = itemView.findViewById(R.id.tvBibite);
        }

        @Override
        public void onClick(View view) {}
    }
}
