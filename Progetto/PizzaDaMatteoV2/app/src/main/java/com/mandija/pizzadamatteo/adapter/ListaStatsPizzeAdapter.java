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
import com.mandija.pizzadamatteo.entity.ListaStatsPizzeElemento;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

public class ListaStatsPizzeAdapter extends RecyclerView.Adapter<ListaStatsPizzeAdapter.StatsPizzeViewAdapter> {

    private ArrayList<ListaStatsPizzeElemento> dati;
    private Context context;
    private LayoutInflater inflater;

    public ListaStatsPizzeAdapter(Context context) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        dati = new ArrayList<ListaStatsPizzeElemento>();
    }

    @NonNull
    @Override
    public ListaStatsPizzeAdapter.StatsPizzeViewAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = inflater.inflate(R.layout.getstatspizze_row, parent, false);
        ListaStatsPizzeAdapter.StatsPizzeViewAdapter vh = new ListaStatsPizzeAdapter.StatsPizzeViewAdapter(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ListaStatsPizzeAdapter.StatsPizzeViewAdapter holder, int position) {
        holder.tvStatsPizze.setText(dati.get(position).getStatsPizze());
        holder.tvStatsPizzeNum.setText(Integer.toString(dati.get(position).getStatsPizzeNum()));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("statsPizze", "onClick on: " + dati.get(holder.getAdapterPosition()).getStatsPizze());
            }
        });
    }

    @Override
    public int getItemCount() { return dati.size(); }

    public void aggiungiStatsPizze(JSONArray array) {
        for (int i = 0; i < array.length(); i++) {
            try {
                dati.add(new ListaStatsPizzeElemento(array.getJSONObject(i).getInt("numPizza"), array.getJSONObject(i).getString("nomePizza")));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    public class StatsPizzeViewAdapter extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tvStatsPizze, tvStatsPizzeNum;

        public StatsPizzeViewAdapter(@NonNull View itemView) {
            super(itemView);
            tvStatsPizze = itemView.findViewById(R.id.tvStatsPizze);
            tvStatsPizzeNum = itemView.findViewById(R.id.tvStatsPizzeNum);
        }

        @Override
        public void onClick(View view) { }
    }
}
