package com.project.taskmate;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    ArrayList<TaskModel> taskModels;
    Context context;

    public RecyclerViewAdapter(Context context, ArrayList<TaskModel> taskModels) {

        this.context = context;
        this.taskModels = taskModels;
    }


    @NonNull
    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.oneline,parent,false);
        return new RecyclerViewAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.ViewHolder holder, int position) {
        holder.name.setText(taskModels.get(position).getTask());
        holder.desc.setText(taskModels.get(position).getDescription());
        holder.time.setText(taskModels.get(position).getTime().toString());
        holder.priority.setText(String.valueOf(taskModels.get(position).getPriority()));
        holder.category.setText(taskModels.get(position).getCategory());

    }

    @Override
    public int getItemCount() {
        return taskModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView name, desc,time,priority,category;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.taskName);
            desc = itemView.findViewById(R.id.description);
            time = itemView.findViewById(R.id.time);
            priority = itemView.findViewById(R.id.priority);
            category = itemView.findViewById(R.id.category);
        }


    }
}
