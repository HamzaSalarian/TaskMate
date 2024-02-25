package com.project.taskmate;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Arrays;

public class HomeFragment extends Fragment {

    ArrayList<TaskModel> taskModels = new ArrayList<TaskModel>();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        
        //Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.rv);

//        fillTasks();

        RecyclerViewAdapter adapter =new RecyclerViewAdapter(getContext(), taskModels);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        return view;
    }

//    private void fillTasks() {
//
//        taskModels.add(new TaskModel(1, "Task 1", "Description 1", new Time(System.currentTimeMillis()),"Home",1, false));
//        taskModels.add(new TaskModel(2, "Task 2", "Description 2", new Time(System.currentTimeMillis()),"Home",1, false));
//
//
//    }
}