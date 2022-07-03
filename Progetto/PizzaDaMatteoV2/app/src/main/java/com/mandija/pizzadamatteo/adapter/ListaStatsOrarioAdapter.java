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
import com.mandija.pizzadamatteo.entity.ListaStatsOrarioElemento;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

public class ListaStatsOrarioAdapter extends RecyclerView.Adapter<ListaStatsOrarioAdapter.StatsViewAdapter> {

    private ArrayList<ListaStatsOrarioElemento> dati;
    private Context context;
    private LayoutInflater inflater;

    public ListaStatsOrarioAdapter(Context context) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        dati = new ArrayList<ListaStatsOrarioElemento>();
    }

    @NonNull
    @Override
    public StatsViewAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = inflater.inflate(R.layout.getstatsorario_row, parent, false);
        StatsViewAdapter vh = new StatsViewAdapter(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull StatsViewAdapter holder, int position) {
        holder.tvStatsOrario.setText(dati.get(position).getStatsOrario());
        holder.tvStatsOrarioNum.setText(Integer.toString(dati.get(position).getStatsOrarioNum()));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("statsOrario", "onClick on: " + dati.get(holder.getAdapterPosition()).getStatsOrario());
            }
        });
    }

    @Override
    public int getItemCount() { return dati.size(); }

    public void aggiungiStatsOrario(JSONArray array) {
        for (int i = 0; i < array.length(); i++) {
            try {
                dati.add(new ListaStatsOrarioElemento(array.getJSONObject(i).getString("orario"), array.getJSONObject(i).getInt("countOrario")));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            notifyDataSetChanged();
        }
    }

    public class StatsViewAdapter extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView tvStatsOrario, tvStatsOrarioNum;

        public StatsViewAdapter(@NonNull View itemView) {
            super(itemView);
            tvStatsOrario = itemView.findViewById(R.id.tvStatsOrario);
            tvStatsOrarioNum = itemView.findViewById(R.id.tvStatsOrarioNum);
        }

        @Override
        public void onClick(View view) { }
    }
}
