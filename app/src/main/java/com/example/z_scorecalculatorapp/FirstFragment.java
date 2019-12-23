package com.example.z_scorecalculatorapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

public class FirstFragment extends Fragment {


    EditText scoreEntered;
    EditText facultyEntered;
    EditText courseNumberEntered;
    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_first, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        view.findViewById(R.id.calculate_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 scoreEntered = view.findViewById(R.id.score_field);
                Integer myScore = Integer.parseInt(scoreEntered.getText().toString());
                 facultyEntered = view.findViewById(R.id.faculty_field);
                String theFaculty = facultyEntered.getText().toString();
                courseNumberEntered = view.findViewById(R.id.courseNumber_field);
                Integer courseNumber = Integer.parseInt(courseNumberEntered.getText().toString());
                FirstFragmentDirections.ActionFirstFragmentToSecondFragment action =
                        FirstFragmentDirections.
                                actionFirstFragmentToSecondFragment("From FirstFragment");
                NavHostFragment.findNavController(FirstFragment.this)
                        .navigate(action);
            }
        });
    }
}
