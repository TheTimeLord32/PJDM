package com.mandija.pizzadamatteo;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.mandija.pizzadamatteo.databinding.FragmentCompletamentoOrdineBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CompletamentoOrdine#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CompletamentoOrdine extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private FragmentCompletamentoOrdineBinding binding;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public CompletamentoOrdine() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CompletamentoOrdine.
     */
    // TODO: Rename and change types and number of parameters
    public static CompletamentoOrdine newInstance(String param1, String param2) {
        CompletamentoOrdine fragment = new CompletamentoOrdine();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        /*Spinner spinPizza;
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(), R.array.pizze, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinPizza = getView().findViewById(R.id.spinPizza1);
        spinPizza.setAdapter(adapter);*/

        binding = FragmentCompletamentoOrdineBinding.inflate(inflater, container, false);
        View rootView = binding.getRoot();

        binding.btComplOrdineEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // inviare i dati al DB
                Toast.makeText(getContext(), "Ordine inviato", Toast.LENGTH_SHORT).show();
                NavHostFragment.findNavController(CompletamentoOrdine.this).navigate(R.id.action_completamentoOrdine_to_homeFragment);
            }
        });

        binding.btComplOrdineBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavHostFragment.findNavController(CompletamentoOrdine.this).navigate(R.id.action_completamentoOrdine_to_homeFragment);
            }
        });

        return rootView;
    }
}