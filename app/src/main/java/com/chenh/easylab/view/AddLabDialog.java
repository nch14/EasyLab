package com.chenh.easylab.view;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;

import com.chenh.easylab.R;

import java.util.ArrayList;

/**
 * Created by chenh on 2016/7/4.
 */
public class AddLabDialog extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View v=getActivity().getLayoutInflater().inflate(R.layout.dialog_lab_add_lab,null);

        return new AlertDialog.Builder(getActivity()).setView(v).setTitle("添加实验室").setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        }).setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        }) .create();

    }

    public static AddLabDialog newInstance(){
        AddLabDialog addLabDialog=new AddLabDialog();

        return addLabDialog;
    }
}
