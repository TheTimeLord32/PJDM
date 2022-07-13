package com.mandija.pizzadamatteo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mandija.pizzadamatteo.R;
import com.mandija.pizzadamatteo.entity.ListaRicettaElemento;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

public class ListaRicettaAdapter extends RecyclerView.Adapter<ListaRicettaAdapter.RICViewAdapter> {
    private ArrayList<ListaRicettaElemento> dati;
    private Context context;
    private LayoutInflater inflater;

    public ListaRicettaAdapter(Context context) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.dati = new ArrayList<ListaRicettaElemento>();
    }

    @Override
    public RICViewAdapter onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = inflater.inflate(R.layout.getricetta_row, parent, false);
        RICViewAdapter vh = new RICViewAdapter(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(RICViewAdapter holder, int position) {
        holder.tvCodicePizza.setText(dati.get(position).getCodicePizza());
        holder.tvCodiceIngrediente.setText(dati.get(position).getCodiceIngrediente());
        holder.tvQuantita.setText(Integer.toString(dati.get(position).getQuantita()));
    }

    @Override
    public int getItemCount() { return dati.size(); }

    public void aggiungi(JSONArray array) {
        for (int i = 0; i < array.length(); i++) {
            try {
                dati.add(new ListaRicettaElemento(array.getJSONObject(i).getString("codice_pizza"), array.getJSONObject(i).getString("codice_ingrediente"), array.getJSONObject(i).getInt("quantita")));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        notifyDataSetChanged();
    }

    public class RICViewAdapter extends RecyclerView.ViewHolder {
        TextView tvCodicePizza, tvCodiceIngrediente, tvQuantita;
        public RICViewAdapter(@NonNull View itemView) {
            super(itemView);
            tvCodicePizza = itemView.findViewById(R.id.tvCodicePizza);
            tvCodiceIngrediente = itemView.findViewById(R.id.tvCodiceIngrediente);
            tvQuantita = itemView.findViewById(R.id.tvQuantita);
        }
    }

}
