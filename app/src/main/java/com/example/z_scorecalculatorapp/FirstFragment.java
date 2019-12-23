package com.example.z_scorecalculatorapp;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import network.GradesApi;

public class FirstFragment extends Fragment {


    EditText scoreField;
    EditText facultyField ;
    EditText courseField;
    GradesApi gradesApi;
    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        View v =inflater.inflate(R.layout.fragment_first, container, false);
         scoreField = v.findViewById(R.id.score_field);
         facultyField = v.findViewById(R.id.faculty_field);
        courseField = v.findViewById(R.id.courseNumber_field);
        return v;
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        view.findViewById(R.id.calculate_button).setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View view) {
//                 scoreEntered = (EditText)view.findViewById(R.id.score_field);
                String  myScore =(scoreField.getText().toString());
//                 facultyEntered = (EditText)view.findViewById(R.id.faculty_field);
                String theFaculty =  facultyField.getText().toString();
//                courseNumberEntered =(EditText) view.findViewById(R.id.courseNumber_field);
                String courseNumber = (courseField.getText().toString());
                gradesApi = new GradesApi(theFaculty, courseNumber, myScore);


                FirstFragmentDirections.ActionFirstFragmentToSecondFragment action =
                        null;
                double zscore = gradesApi.searchCourseThenGetZscore();
                    action = FirstFragmentDirections.
                            actionFirstFragmentToSecondFragment(zscore);

                NavHostFragment.findNavController(FirstFragment.this)
                        .navigate(action);
            }
        });
    }
}
