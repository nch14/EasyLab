package com.chenh.easylab;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by chenh on 2016/6/1.
 */
public class SMakeAppointmentFragment extends Fragment {
    private static final String DIALOG_DATE="date";
    private TextView chooseDate;
    private TextView chooseTime;
    private TextView chooseMember;

    private TextView showDate;
    private TextView showTime;
    private TextView showMember;

    private ArrayList<String> defaultDevices;
    private ArrayAdapter<String> defaultDevicesAdapter;
    private ListView defaultDevicesListView;

    private ArrayList<String> addDevices;
    private ArrayAdapter<String> addDevicesAdapter;
    private ListView addDevicesListView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {

        View v=inflater.inflate(R.layout.fragment_student_make_appointment,parent,false);
        getActivity().setTitle("预约");

        chooseDate= (TextView) v.findViewById(R.id.choose_date_make_appoint);
        chooseDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm=getActivity().getFragmentManager();
                DatePickerFragment dialog= new DatePickerFragment();
                dialog.show(fm,DIALOG_DATE);
            }
        });

        chooseTime= (TextView) v.findViewById(R.id.choose_time_make_appoint);
        chooseTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm=getActivity().getFragmentManager();
                TimePickerFragment dialog= new TimePickerFragment();
                dialog.show(fm,DIALOG_DATE);
            }
        });

        chooseMember= (TextView) v.findViewById(R.id.choose_member_make_appoint);
        chooseMember.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm=getActivity().getFragmentManager();
                MemberAddFragment dialog= new MemberAddFragment();
                dialog.show(fm,DIALOG_DATE);
            }
        });

        defaultDevices=new ArrayList<>();
        defaultDevices.add("显示屏");
        defaultDevices.add("主机");
        defaultDevices.add("键盘");
        defaultDevices.add("鼠标");
        defaultDevices.add("万用表");
        defaultDevices.add("示波器");
        defaultDevices.add("稳压电源");
        defaultDevices.add("信号发生器");

        defaultDevicesAdapter=new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,defaultDevices);

        defaultDevicesListView = (ListView) v.findViewById(R.id.list_default_device);
        defaultDevicesListView.setAdapter(defaultDevicesAdapter);



        showDate= (TextView) v.findViewById(R.id.date_make_appoint);


        return v;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    public static SMakeAppointmentFragment newInstance(){
        Bundle args=new Bundle();
        //args.putSerializable(EXTRA_CRIME_ID,crimeID);

        SMakeAppointmentFragment fragment=new SMakeAppointmentFragment();
        //fragment.setArguments(args);

        return fragment;
    }
}

