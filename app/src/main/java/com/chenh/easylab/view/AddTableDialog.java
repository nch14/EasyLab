package com.chenh.easylab.view;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.View;

import com.chenh.easylab.R;

/**
 * Created by chenh on 2016/7/3.
 */
public class AddTableDialog extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View v=getActivity().getLayoutInflater().inflate(R.layout.dialog_lab_add_table,null);

        return new AlertDialog.Builder(getActivity()).setView(v).setTitle("添加实验桌").setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        }).setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        }) .create();

    }

    public static AddTableDialog newInstance(){
        AddTableDialog addTableDialog=new AddTableDialog();

        return addTableDialog;
    }
}
