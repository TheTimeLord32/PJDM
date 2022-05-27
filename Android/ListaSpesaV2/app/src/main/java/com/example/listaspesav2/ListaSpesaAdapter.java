package com.example.listaspesav2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collections;

public class ListaSpesaAdapter extends RecyclerView.Adapter<ListaSpesaAdapter.LSViewHolder> {

    private ArrayList<ListElement> dati;
    private Context context;
    private LayoutInflater inflater;
    public ListaSpesaAdapter(Context context) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        dati = new ArrayList<ListElement>();
        dati.add(new ListElement("Pane", 1));
        dati.add(new ListElement("Vino", 2));
        dati.add(new ListElement("Acqua", 3));

    }

    public void addItem(String name, int num) {
        dati.add(new ListElement(name, num));
        notifyDataSetChanged();
    }

    public void remove(int position) {
        dati.remove(dati.get(position));
        notifyDataSetChanged();
    }

    public void move(int from, int to) {
        Collections.swap(dati, from, to);
        notifyItemMoved(from, to);
    }

    @NonNull
    @Override
    public LSViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = inflater.inflate(R.layout.item_row, parent, false);
        LSViewHolder vh = new LSViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull LSViewHolder holder, int position) {
        holder.tvName.setText(dati.get(position).getName());
        holder.tvNum.setText(Integer.toString(dati.get(position).getNum()));

        if (dati.get(position).isSelected()) {
            holder.ibSel.setImageResource(android.R.drawable.checkbox_on_background);
        } else {
            holder.ibSel.setImageResource(android.R.drawable.checkbox_off_background);
        }
    }

    @Override
    public int getItemCount() {
        return dati.size();
    }

    class LSViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView tvName, tvNum;
        ImageButton ibSel;
        public LSViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            tvNum = itemView.findViewById(R.id.tvNum);
            ibSel = itemView.findViewById(R.id.ibSel);
            ibSel.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            dati.get(position).toggleSelect();
            notifyDataSetChanged();
        }
    }
}
