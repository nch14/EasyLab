package com.chenh.easylab.view;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.chenh.easylab.R;
import com.chenh.easylab.util.CurrentUser;
import com.chenh.easylab.vo.AppointmentVO;

import java.util.ArrayList;

/**
 * Created by chenh on 2016/6/5.
 */
public class SAppointmentFragment extends Fragment {

    private ArrayList<AppointmentVO> appointments;
    private AppointmentAdapter adapter;
    private ListView listView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_student_appointment,container, false);

        listView= (ListView) v.findViewById(R.id.appointment_list_s);

        appointments= CurrentUser.getUser().appointments;
/*        if (appointments.size()==0){
            appointments.add(new AppointmentVO());
        }*/
        appointments.add(new AppointmentVO());
        appointments.add(new AppointmentVO());
        appointments.add(new AppointmentVO());

        adapter=new AppointmentAdapter(appointments);
        listView.setAdapter(adapter);

        return v;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().setTitle(R.string.title_fragment_s_appontment);
    }

    private class AppointmentAdapter extends ArrayAdapter<AppointmentVO> {
        public AppointmentAdapter(ArrayList<AppointmentVO> appointments){
            super(getActivity(),0,appointments);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            //如果没有，就inflate一个
            if (convertView==null){
                convertView=getActivity().getLayoutInflater().inflate(R.layout.list_item_appointment,null);
            }

            AppointmentVO c=getItem(position);

            //title
            TextView titleTextView= (TextView) convertView.findViewById(R.id.title_list_appointment);
            titleTextView.setText(c.getTitle());

            //DateTime
            TextView dateTextView=(TextView)convertView.findViewById(R.id.date_list_appointment);
            dateTextView.setText(c.getTime());

            //members
            TextView membetTextView= (TextView) convertView.findViewById(R.id.member_list_appointment);
            String s="";
            for (int i=0;i<c.getMembers().size();i++){
                s+=',';
                s+=c.getMembers().get(i);
            }
            if (s.length()>0)
            s=s.substring(1);
            membetTextView.setText(s);

            //members
            TextView roomTableTextView= (TextView) convertView.findViewById(R.id.room_table_list_appointment);
            roomTableTextView.setText(c.getRoomId()+"室"+c.getTableId()+"桌");


            //备注
            TextView detailTextView=(TextView)convertView.findViewById(R.id.detail_list_appointment);
            detailTextView.setText(c.getDetails());

            //备注
            TextView stateTextView=(TextView)convertView.findViewById(R.id.state_list_appointment);
            stateTextView.setText(c.getState());

            return convertView;
        }
    }

    public void onResume(){
        super.onResume();
        adapter.notifyDataSetChanged();
    }



    public static SAppointmentFragment newInstance(){
       /* Bundle args=new Bundle();
        //args.putSerializable(EXTRA_CRIME_ID,crimeID);
*/
        SAppointmentFragment fragment=new SAppointmentFragment();
        //fragment.setArguments(args);

        return fragment;
    }
}
