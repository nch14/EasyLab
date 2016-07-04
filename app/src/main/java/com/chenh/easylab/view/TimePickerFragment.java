package com.chenh.easylab.view;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.TimePicker;

import com.chenh.easylab.R;

/**
 * Created by chenh on 2016/6/3.
 */
public class TimePickerFragment extends DialogFragment {
    private TimePicker timePicker;
    private TextView textView;
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View v=getActivity().getLayoutInflater().inflate(R.layout.dialog_time,null);
        timePicker= (TimePicker) v.findViewById(R.id.dialog_date_timePicker);
        return new AlertDialog.Builder(getActivity()).setView(v).setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                int hourTemp=timePicker.getCurrentHour();
                String hour=(hourTemp<10)?("0"+hourTemp):""+hourTemp;
                int minTemp=timePicker.getCurrentMinute();
                String min=(minTemp<10)?("0"+minTemp):""+minTemp;

                textView.setText(hour+":"+min);
            }
        }).create();
    }

    public static TimePickerFragment newInstance(TextView textView){
        TimePickerFragment timePickerFragment=new TimePickerFragment();
        timePickerFragment.textView=textView;
        return timePickerFragment;
    }
}
