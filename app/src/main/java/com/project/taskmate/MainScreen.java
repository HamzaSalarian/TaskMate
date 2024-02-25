package com.project.taskmate;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.ContentInfo;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.sql.Time;
import java.util.ArrayList;

public class MainScreen extends AppCompatActivity {
    FloatingActionButton fab1;

    ArrayList<TaskModel>  taskModels = new ArrayList<>();
    RecyclerViewAdapter adapter;

    SwipeRefreshLayout swipeRefreshLayout ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);

        RecyclerView recyclerView = findViewById(R.id.rv);
        swipeRefreshLayout = findViewById(R.id.swipeRefresh);

        fillTasks();

         adapter = new RecyclerViewAdapter(this, taskModels);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        fab1 = findViewById(R.id.fab1);

        fab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadFragment(new AddTask());
            }
        });

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                fetchDataAndUpdateRecyclerView();

                swipeRefreshLayout.setRefreshing(false);
            }
        });


        fetchDataAndUpdateRecyclerView();


    }

    protected void onResume(){
        super.onResume();

        fetchDataAndUpdateRecyclerView();
    }


    private void loadFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.index_frame, fragment);
        fragmentTransaction.commit();
        fragmentTransaction.addToBackStack(null);
    }


    private void fillTasks() {

        taskModels.add(new TaskModel(1, "Task 1", "Description 1", new Time(System.currentTimeMillis()),"Home",1));
        taskModels.add(new TaskModel(2, "Task 2", "Description 2", new Time(System.currentTimeMillis()),"Home",1));


    }

    private void fetchDataAndUpdateRecyclerView() {
        // Fetch data from the database
        DataBaseHelper dbHelper = new DataBaseHelper(this);
        ArrayList<TaskModel> fetchedTasks = dbHelper.getAllTasks(); // Implement this method in your database helper

        // Clear the existing data and add fetched data
        taskModels.clear();
        taskModels.addAll(fetchedTasks);

        // Notify the adapter that the data set has changed
        adapter.notifyDataSetChanged();

    }
}