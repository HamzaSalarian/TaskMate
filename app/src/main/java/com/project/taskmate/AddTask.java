package com.project.taskmate;

import android.annotation.SuppressLint;
import android.app.TimePickerDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.sql.Time;
import java.util.ArrayList;


public class AddTask extends Fragment implements PriorityFragment.PrioritySelectedListener, CategoryFragment.CategorySelectedListener{

    ImageView time, flag, tag, send;
    Time selectedTime;
    int selectedPriority;

    String selectedCategory, selectedName, selectedDescription;

    EditText name,description;

    DataBaseHelper dbHelper;

    ArrayList<TaskModel>  taskModels = new ArrayList<>();


    public void onCategorySelected(String category) {
        // Handle the selected category here
        // You can store it in a global variable or pass it to a method for further processing
        this.selectedCategory = category;
    }

    @Override
    public void onPrioritySelected(int priority) {
        this.selectedPriority = priority;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_add_task, container, false);


        dbHelper = new DataBaseHelper(getActivity());
        // Find the time ImageView by its ID
        time = rootView.findViewById(R.id.time);
        flag = rootView.findViewById(R.id.flag);
        tag = rootView.findViewById(R.id.tag);
       name = rootView.findViewById(R.id.tasks);
        description = rootView.findViewById(R.id.description);
        send = rootView.findViewById(R.id.send);

        RecyclerViewAdapter adapter = new RecyclerViewAdapter(getContext(),taskModels);

        // Now you can use the 'time' ImageView as needed



        time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerFragment timePickerFragment = new TimePickerFragment(R.style.CustomTimePickerDialog);
                timePickerFragment.setOnTimeSetListener(new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        selectedTime =new Time(hourOfDay, minute,0);
                    }
                });
                timePickerFragment.show(getActivity().getSupportFragmentManager(), "timePicker");

            }
        });

        flag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                PriorityFragment priorityFragment = new PriorityFragment();
                priorityFragment.setPrioritySelectedListener(AddTask.this);

                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();

                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                fragmentTransaction.replace(R.id.fragment_container, priorityFragment);

                fragmentTransaction.commit();
                fragmentTransaction.addToBackStack(null);



            }
        });

        tag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                CategoryFragment categoryFragment = new CategoryFragment();
                categoryFragment.setCategorySelectedListener(AddTask.this); //


                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();

                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                fragmentTransaction.replace(R.id.fragment_container, categoryFragment);

                fragmentTransaction.commit();
                fragmentTransaction.addToBackStack(null);



            }
        });

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                selectedName = name.getText().toString();
                selectedDescription = description.getText().toString();
                boolean result = dbHelper.insertUser(selectedName,selectedDescription,selectedTime,selectedPriority,selectedCategory);

                if (result){
                    Toast.makeText(getActivity(), "User inserted successfully", Toast.LENGTH_SHORT).show();
                    getActivity().getSupportFragmentManager().popBackStack();

                }
                else {
                    Toast.makeText(getActivity(), "User not inserted successfully", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return rootView;
    }

}