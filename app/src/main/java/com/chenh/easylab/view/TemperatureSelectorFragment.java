package com.chenh.easylab.view;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.Fragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.TimePicker;

import com.chenh.easylab.R;

/**
 * Created by chenh on 2016/7/3.
 */
public class TemperatureSelectorFragment extends DialogFragment {
    private NumberPicker numberPicker;
    private TextView textView;
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View v=getActivity().getLayoutInflater().inflate(R.layout.dialog_temperature_selector,null);
        numberPicker= (NumberPicker) v.findViewById(R.id.numberPicker);
        numberPicker.setMaxValue(30);
        numberPicker.setMinValue(18);
        numberPicker.setWrapSelectorWheel(false);

        numberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {

            }
        });
        return new AlertDialog.Builder(getActivity()).setView(v).setTitle("请设定温度").setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                textView.setText(numberPicker.getValue()+"°C");
            }
        }).setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        }).create();
    }

    public static TemperatureSelectorFragment newInstance(TextView textView){
        TemperatureSelectorFragment timePickerFragment=new TemperatureSelectorFragment();
        timePickerFragment.textView=textView;
        return timePickerFragment;
    }
}
