package com.chenh.easylab.view;

import android.app.AlertDialog;
import android.app.Dialog;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

import com.chenh.easylab.R;

import java.util.ArrayList;

/**
 * Created by chenh on 2016/7/3.
 */
public class AddDeviceDialog extends DialogFragment {
    private ArrayAdapter spinnerAdapter;
    private String[] items=new String[]{"电闸","电流表","空调"};
    private ArrayList<String> spinners;
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View v=getActivity().getLayoutInflater().inflate(R.layout.dialog_lab_add_device,null);

        Spinner spinner=(Spinner)v.findViewById(R.id.spinner);
        spinnerAdapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_spinner_item,items);

        //设置下拉列表的风格
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        
        //将adapter 添加到spinner中
        spinner.setAdapter(spinnerAdapter);

        //添加事件Spinner事件监听
        //spinner.setOnItemSelectedListener(new SpinnerSelectedListener());

        //设置默认值
        spinner.setVisibility(View.VISIBLE);

        return new AlertDialog.Builder(getActivity()).setView(v).setTitle("添加设备").setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        }).setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        }) .create();

    }

    public static AddDeviceDialog newInstance(){
        AddDeviceDialog addDeviceDialog=new AddDeviceDialog();

        return addDeviceDialog;
    }
}
