package com.chenh.easylab;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

/**
 * Created by chenh on 2016/6/3.
 */
public class DatePickerFragment extends DialogFragment {
    private DatePicker datePicker;
    private TextView textView;
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View v=getActivity().getLayoutInflater().inflate(R.layout.dialog_date,null);
        datePicker= (DatePicker) v.findViewById(R.id.dialog_date_datePicker);
        return new AlertDialog.Builder(getActivity()).setView(v).setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String year=""+datePicker.getYear();
                int monthTemp=datePicker.getMonth();
                String month=(monthTemp<10)?("0"+monthTemp):""+monthTemp;
                int dayTemp=datePicker.getDayOfMonth();
                String day=(dayTemp<10)?("0"+dayTemp):""+dayTemp;
                textView.setText(year+"-"+month+"-"+day);
            }
        }).create();

    }

    public static DatePickerFragment newInstance(TextView textView){
        DatePickerFragment datePickerFragment=new DatePickerFragment();
        datePickerFragment.textView=textView;
        return datePickerFragment;
    }
}
