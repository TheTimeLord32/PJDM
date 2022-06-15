package com.mandija.pizzadamatteo.ui.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import com.mandija.pizzadamatteo.R;
import com.mandija.pizzadamatteo.databinding.FragmentDashboardBinding;

import org.w3c.dom.Text;

public class DashboardFragment extends Fragment {

    private FragmentDashboardBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        DashboardViewModel dashboardViewModel = new ViewModelProvider(this).get(DashboardViewModel.class);

        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        binding.btOrdineMostra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavHostFragment.findNavController(DashboardFragment.this).navigate(R.id.action_navigation_dashboard_to_mostraOrdine);
            }
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}