package com.example.listaspesav2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.annotation.ElementType;
import java.util.ArrayList;

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
    }

    @Override
    public int getItemCount() {
        return dati.size();
    }

    class LSViewHolder extends RecyclerView.ViewHolder{

        TextView tvName, tvNum;
        ImageButton ibSel;
        public LSViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            tvNum = itemView.findViewById(R.id.tvNum);
            ibSel = itemView.findViewById(R.id.ibSel);
        }
    }
}
