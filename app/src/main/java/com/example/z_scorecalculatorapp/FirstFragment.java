package com.example.z_scorecalculatorapp;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import java.math.BigDecimal;
import java.math.RoundingMode;

import network.GradesApi;

public class FirstFragment extends Fragment {


    private EditText scoreField;
    private EditText facultyField ;
    private EditText courseField;
    private GradesApi gradesApi;
    private View v;
    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
         v =inflater.inflate(R.layout.fragment_first, container, false);
         scoreField = v.findViewById(R.id.score_field);
         facultyField = v.findViewById(R.id.faculty_field);
        courseField = v.findViewById(R.id.courseNumber_field);
        return v;
    }

    public void onViewCreated(@NonNull final View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        view.findViewById(R.id.calculate_button).setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View view) {
//                 scoreEntered = (EditText)view.findViewById(R.id.score_field);
                try{String  myScore =(scoreField.getText().toString());

//                 facultyEntered = (EditText)view.findViewById(R.id.faculty_field);
                String theFaculty =  facultyField.getText().toString();
//                courseNumberEntered =(EditText) view.findViewById(R.id.courseNumber_field);
                String courseNumber = (courseField.getText().toString());
                gradesApi = new GradesApi(theFaculty, courseNumber, myScore);}
                catch (Exception e) {
                    errorPopup();
                }


                FirstFragmentDirections.ActionFirstFragmentToSecondFragment action =
                        null;
                double zscore = 0;
                double classAverage = 0;

                try {
                    zscore = gradesApi.searchCourseThenGetZscore();
                    classAverage = gradesApi.searchCourseAndGetAverage();
                } catch (Exception e) {
                   errorPopup();

                }
                zscore = round(zscore, 2);
                classAverage = round(classAverage, 2);
                Bundle args = new Bundle();
                args.putDouble("zscore", zscore);
                args.putDouble("classAverage", classAverage);
                    action = FirstFragmentDirections.
                            actionFirstFragmentToSecondFragment(args);

                NavHostFragment.findNavController(FirstFragment.this)
                        .navigate(action);
            }

            //TODO: fix the crash when no input
            private void errorPopup() {
                Toast.makeText(getActivity(), "Please enter valid values!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = BigDecimal.valueOf(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}
