package com.example.eco_connect.ui.home;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.eco_connect.R;
import com.example.eco_connect.databinding.FragmentHomeBinding;
import com.example.eco_connect.ui.home.HomeViewModel;

import java.util.HashMap;
import java.util.Map;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    private EditText editTextName;
    private EditText editTextCommute;
    private EditText editTextEfficiency;
    private Button calculateButton;
    private Spinner spinnerFuelType;
    private Spinner spinnerEnergySource;
    private Spinner spinnerWasteType;

    private Map<String, Double> fuelEmissionMap = new HashMap<>();
    private Map<String, Double> wasteEmissionMap = new HashMap<>();
    private String[] wasteTypeOptions = {"Organic Waste", "Paper and Cardboard", "Plastics", "Glass", "Metals"};

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.texthome;
        homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        editTextName = root.findViewById(R.id.editTextName);
        editTextCommute = root.findViewById(R.id.editTextCommute);
        editTextEfficiency = root.findViewById(R.id.editTextVehicleEfficiency);
        calculateButton = root.findViewById(R.id.buttonCalculate);
        spinnerFuelType = root.findViewById(R.id.spinnerFuelType);
        spinnerEnergySource = root.findViewById(R.id.spinnerEnergySource);
        spinnerWasteType = root.findViewById(R.id.spinnerWasteType);

        fuelEmissionMap.put("Gasoline", 2.3);
        fuelEmissionMap.put("Diesel", 2.8);
        fuelEmissionMap.put("Electric", 0.0);
        fuelEmissionMap.put("CNG", 2.0);

        wasteEmissionMap.put("Organic Waste", 0.5);
        wasteEmissionMap.put("Paper and Cardboard", 0.3);
        wasteEmissionMap.put("Plastics", 0.8);
        wasteEmissionMap.put("Glass", 0.4);
        wasteEmissionMap.put("Metals", 0.7);

        String[] fuelOptions = {"Gasoline", "Diesel", "Electric", "CNG"};
        ArrayAdapter<String> fuelAdapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_spinner_item, fuelOptions);
        spinnerFuelType.setAdapter(fuelAdapter);

        String[] energySourceOptions = {"Coal", "Natural Gas", "Oil", "Renewables (Wind, Solar, etc.)"};
        ArrayAdapter<String> energySourceAdapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_spinner_item, energySourceOptions);
        spinnerEnergySource.setAdapter(energySourceAdapter);

        ArrayAdapter<String> wasteTypeAdapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_dropdown_item_1line, wasteTypeOptions);
        spinnerWasteType.setAdapter(wasteTypeAdapter);

        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculateEmissions();
            }
        });

        return root;
    }

    private void calculateEmissions() {
        try {
            String commuteDistanceStr = editTextCommute.getText().toString();
            double commuteDistance = Double.parseDouble(commuteDistanceStr);

            String selectedFuel = spinnerFuelType.getSelectedItem().toString();
            double emissionValue = fuelEmissionMap.get(selectedFuel);

            String efficiencyStr = editTextEfficiency.getText().toString();
            double efficiency = Double.parseDouble(efficiencyStr);

            double emissionFactor = emissionValue / efficiency;

            double emissions = emissionFactor * commuteDistance;

            String wasteTypesStr = spinnerWasteType.getSelectedItem().toString();
            String[] wasteTypes = wasteTypesStr.split(",\\s*");

            double wasteEmissions = 0.0;

            for (String wasteType : wasteTypes) {
                if (wasteEmissionMap.containsKey(wasteType)) {
                    double wasteEmissionFactor = wasteEmissionMap.get(wasteType);
                    wasteEmissions += wasteEmissionFactor;
                }
            }

            double totalEmissions = emissions + wasteEmissions;

            String formattedEmissions = String.format("%.3f", totalEmissions);

            String username = editTextName.getText().toString();
            Toast.makeText(requireContext(), username + ", Your Total Carbon Footprint: " + formattedEmissions + " kg CO2e", Toast.LENGTH_SHORT).show();
        } catch (NumberFormatException e) {
            Toast.makeText(requireContext(), "Invalid input. Please enter numeric values for commute distance, efficiency, and waste.", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
