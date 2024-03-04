package com.example.eco_connect.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import com.example.eco_connect.R;

public class rateusFragment extends Fragment {

    private RatingBar ratingBar;
    private Button submitRatingButton;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_rateus, container, false);

        ratingBar = view.findViewById(R.id.ratingBar);
        submitRatingButton = view.findViewById(R.id.submitRatingButton);

        submitRatingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                float userRating = ratingBar.getRating();
                // Handle the user's rating (e.g., send it to a server or save it locally)
                // You can also display a confirmation message.
                displayRatingConfirmation(userRating);
            }
        });

        return view;
    }

    private void displayRatingConfirmation(float userRating) {
        // Example: Display a confirmation message using a Toast
        String message = "Thank you for rating: " + userRating + " stars";
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show();
    }
}
