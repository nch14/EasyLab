package com.chenh.easylab.view;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.View;

import com.chenh.easylab.R;

/**
 * Created by chenh on 2016/6/3.
 */
public class MemberAddFragment extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View v=getActivity().getLayoutInflater().inflate(R.layout.dialog_member,null);
        return new AlertDialog.Builder(getActivity()).setView(v).setPositiveButton(android.R.string.ok,null).create();
    }
}
