package com.example.listaspesa;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class ListaSpesaAdapter extends BaseAdapter {

    private ArrayList<ListElement> dati;
    private Context context;
    private LayoutInflater inflater;

    public ListaSpesaAdapter(Context context) {
        dati = new ArrayList<ListElement>();
        inflater = LayoutInflater.from(context);
        dati.add(new ListElement("Olio", 2));
        dati.add(new ListElement("Pane", 1));
        dati.add(new ListElement("Latte", 5));


        this.context = context;

    }

    public void addElement(String name, int number){
        dati.add(new ListElement(name, number));
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return dati.size();
    }

    @Override
    public Object getItem(int i) {
        return dati.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Log.d("PPLADD", "getView() called with: i = [" + i + "]");
        String name = dati.get(i).getName();
        Integer num = dati.get(i).getNumber();
        View v;
        TextView tvName, tvNum;

        if (view != null) {
            v = view;
            ListaSpesaViewHolder vh = (ListaSpesaViewHolder) v.getTag();
            tvName = vh.tvName;
            tvNum = vh.tvNum;
        }else {
            v = inflater.inflate(R.layout.simple_row, null); //ritorna il constraint layout della radice
            tvName = v.findViewById(R.id.tvName);
            tvNum = v.findViewById(R.id.tvNum);
            ListaSpesaViewHolder vh = new ListaSpesaViewHolder(tvName, tvNum);
            v.setTag(vh);
        }

        tvName.setText(name);
        tvNum.setText(num.toString());

        //TextView tv = new TextView(context);
        //tv.setText(name);

        return v;
    }

    class ListaSpesaViewHolder {
        TextView tvName;
        TextView tvNum;

        public ListaSpesaViewHolder(TextView tvName, TextView tvNum) {
            this.tvName = tvName;
            this.tvNum = tvNum;
        }
    }
}
