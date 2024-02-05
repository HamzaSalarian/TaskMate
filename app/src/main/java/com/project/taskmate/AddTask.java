package com.project.taskmate;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


public class AddTask extends Fragment {

    ImageView time;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_add_task, container, false);

        // Find the time ImageView by its ID
        time = rootView.findViewById(R.id.time);

        // Now you can use the 'time' ImageView as needed
        time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerFragment timePickerFragment = new TimePickerFragment(R.style.CustomTimePickerDialog);
                timePickerFragment.show(getActivity().getSupportFragmentManager(), "timePicker");

            }
        });

        return rootView;
    }

}