package com.project.taskmate;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;


public class PriorityFragment extends Fragment {

    LinearLayout previouslyClickedFlag,flag1,flag2,flag3,flag4,flag5,flag6,flag7,flag8,flag9,flag10;
        Button cancelButton,okButton;

    private PrioritySelectedListener priorityListener;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_priority, container, false);

        flag1 = view.findViewById(R.id.flag1);
        flag1.setTag(1);
        flag2 = view.findViewById(R.id.flag2);
        flag2.setTag(2);
        flag3 = view.findViewById(R.id.flag3);
        flag3.setTag(3);
        flag4 = view.findViewById(R.id.flag4);
        flag4.setTag(4);
        flag5 = view.findViewById(R.id.flag5);
        flag5.setTag(5);
        flag6 = view.findViewById(R.id.flag6);
        flag6.setTag(6);
        flag7 = view.findViewById(R.id.flag7);
        flag7.setTag(7);
        flag8 = view.findViewById(R.id.flag8);
        flag8.setTag(8);
        flag9 = view.findViewById(R.id.flag9);
        flag9.setTag(9);
        flag10 = view.findViewById(R.id.flag10);
        flag10.setTag(10);
        cancelButton = view.findViewById(R.id.cancel);
        okButton = view.findViewById(R.id.ok);




        View.OnClickListener flagClickListener = new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                if (previouslyClickedFlag != null){
                    previouslyClickedFlag.setBackground(getResources().getDrawable(R.drawable.background_card,getActivity().getTheme()));
                }


                LinearLayout flagLayout = (LinearLayout) v;
                ((LinearLayout)v ).setBackground(getResources().getDrawable(R.drawable.btn_background,getActivity().getTheme()));

                previouslyClickedFlag = flagLayout;

                int selectedPriority = (int) flagLayout.getTag();

                // Call the method in AddTask fragment to handle the selected priority
                if (getParentFragment() != null) {
                    ((AddTask) getParentFragment()).onPrioritySelected(selectedPriority);
                }

            }
        };




        flag1.setOnClickListener(flagClickListener);
        flag2.setOnClickListener(flagClickListener);
        flag3.setOnClickListener(flagClickListener);
        flag4.setOnClickListener(flagClickListener);
        flag5.setOnClickListener(flagClickListener);
        flag6.setOnClickListener(flagClickListener);
        flag7.setOnClickListener(flagClickListener);
        flag8.setOnClickListener(flagClickListener);
        flag9.setOnClickListener(flagClickListener);
        flag10.setOnClickListener(flagClickListener);

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate back to the AddTaskFragment
                getActivity().getSupportFragmentManager().popBackStack();
            }
        });
        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate back to the AddTaskFragment

                if (priorityListener != null) {
                    int selectedPriority = (int) previouslyClickedFlag.getTag();
                    priorityListener.onPrioritySelected(selectedPriority);
                }
                getActivity().getSupportFragmentManager().popBackStack();
            }

        });

        return view;
    }



    public interface PrioritySelectedListener {

        void onPrioritySelected(int priority);
    }


    public void setPrioritySelectedListener(PrioritySelectedListener listener) {
        this.priorityListener = listener;
    }
}