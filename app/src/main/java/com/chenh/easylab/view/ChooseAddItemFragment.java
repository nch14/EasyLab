package com.chenh.easylab.view;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.chenh.easylab.R;

/**
 * Created by chenh on 2016/7/1.
 */
public class ChooseAddItemFragment extends DialogFragment implements View.OnClickListener{

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View v=getActivity().getLayoutInflater().inflate(R.layout.dialog_s_center_add,null);
        TextView addAppointment=(TextView)v.findViewById(R.id.add_appointment);
        addAppointment.setOnClickListener(this);

        TextView addTeam=(TextView)v.findViewById(R.id.add_team);
        addTeam.setOnClickListener(this);
        return new AlertDialog.Builder(getActivity()).setView(v).create();

    }

    public static ChooseAddItemFragment newInstance(){
        ChooseAddItemFragment chooseAddItemFragment=new ChooseAddItemFragment();
        return chooseAddItemFragment;
    }

    @Override
    public void onClick(View v) {
        Intent intent=null;
        switch (v.getId()){
            case R.id.add_appointment:
                intent=new Intent(getActivity(),AddAppointmentActivity.class);
                break;
            case R.id.add_team:
                intent=new Intent(getActivity(),AddTeamActivity.class);
                break;
        }
        startActivity(intent);
        this.dismiss();
    }

}
