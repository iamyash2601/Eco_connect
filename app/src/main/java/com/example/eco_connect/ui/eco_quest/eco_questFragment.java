package com.example.eco_connect.ui.eco_quest;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import com.example.eco_connect.R;
import com.example.eco_connect.databinding.FragmentEcoQuestBinding;
import com.example.eco_connect.ui.home.HomeViewModel;

public class eco_questFragment extends Fragment {

    private EcoQuestViewModel ecoQuestViewModel;

    private FragmentEcoQuestBinding binding; // Correct the binding class name

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        EcoQuestViewModel ecoQuestViewModel =
                new ViewModelProvider(this).get(EcoQuestViewModel.class);

        binding = FragmentEcoQuestBinding.inflate(inflater, container, false); // Correct the binding class name
        View root = binding.getRoot();

        final TextView textView = binding.textecoquest;
        ecoQuestViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
