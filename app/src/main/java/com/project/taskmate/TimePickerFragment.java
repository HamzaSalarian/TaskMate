package com.project.taskmate;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.widget.TimePicker;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import java.sql.Time;
import java.util.Calendar;

public class TimePickerFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener {

    private int theme;
    TimePickerDialog.OnTimeSetListener onTimeSetListener;
    public TimePickerFragment(int customTimePickerDialog) {

        this.theme = customTimePickerDialog;
    }

    @NonNull
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current time as the default values for the picker.
        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);

        // Create a new instance of TimePickerDialog and return it.
        return new TimePickerDialog(getActivity(), theme, this, hour, minute,
                DateFormat.is24HourFormat(getActivity()));
    }

    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        // Do something with the time the user picks.

        if (onTimeSetListener != null){
            onTimeSetListener.onTimeSet(view, hourOfDay, minute);
        }
    }

    public void setOnTimeSetListener(TimePickerDialog.OnTimeSetListener listener){
        this.onTimeSetListener = listener;
    }

}
