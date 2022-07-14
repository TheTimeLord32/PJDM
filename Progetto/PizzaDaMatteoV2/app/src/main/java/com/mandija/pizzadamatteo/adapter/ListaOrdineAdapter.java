package com.mandija.pizzadamatteo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mandija.pizzadamatteo.R;
import com.mandija.pizzadamatteo.entity.ListaOrdineElemento;

import org.json.JSONArray;
import org.json.JSONException;

import java.text.BreakIterator;
import java.util.ArrayList;

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
        holder.tvConto.setText(dati.get(position).getConto() + " €");
    }

    @Override
    public int getItemCount() { return dati.size(); }

    public void aggiungi(JSONArray array) {
        for (int i = 0; i < array.length(); i++) {
            try {
                dati.add(new ListaOrdineElemento(array.getJSONObject(i).getInt("id_ordine"), array.getJSONObject(i).getString("nomeCliente"), array.getJSONObject(i).getString("orario"), array.getJSONObject(i).getString("recapito"), array.getJSONObject(i).getString("indirizzo"), array.getJSONObject(i).getString("conto")));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            notifyDataSetChanged();
        }
    }

    class ORDViewAdapter extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tvId_ordine, tvCliente, tvOrario, tvRecapito, tvIndirizzo, tvConto;

        public ORDViewAdapter(@NonNull View itemView) {
            super(itemView);
            tvId_ordine = itemView.findViewById(R.id.tvId_ordine);
            tvCliente = itemView.findViewById(R.id.tvCliente);
            tvOrario = itemView.findViewById(R.id.tvOrario);
            tvRecapito = itemView.findViewById(R.id.tvRecapito);
            tvIndirizzo = itemView.findViewById(R.id.tvIndirizzo);
            tvConto = itemView.findViewById(R.id.tvConto);
        }

        @Override
        public void onClick(View view) { }
    }
}
