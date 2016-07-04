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
import com.chenh.easylab.vo.TeamVO;

import java.util.ArrayList;

/**
 * Created by chenh on 2016/6/1.
 */
public class SPCenterFragment extends Fragment {
    private TextView mName;
    private TextView mIdentify;

    private ArrayList<TeamVO> mTeams;
    private TeamAdapter teamAdapter;
    private ListView mTeamView;

    private ArrayList<AppointmentVO> appointments;
    private AppointmentAdapter adapter;
    private ListView mAppointmentView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {

        View v=inflater.inflate(R.layout.fragment_student_usercenter,parent,false);
        getActivity().setTitle("个人中心");

        mName= (TextView) v.findViewById(R.id.fragment_name_label);
        mName.setText(CurrentUser.getUser().name);

        mIdentify= (TextView) v.findViewById(R.id.fragment_level_ID);
        mIdentify.setText(CurrentUser.getUser().identify);

        mAppointmentView= (ListView) v.findViewById(R.id.center_appointment_list_view);

        appointments= CurrentUser.getUser().appointments;
        if (appointments.size()>2){
            while (appointments.size()>2){
                appointments.remove(0);
            }
        }else{
            while (appointments.size()<2){
                appointments.add(new AppointmentVO());
            }
        }
        adapter=new AppointmentAdapter(appointments);
        mAppointmentView.setAdapter(adapter);


        mTeamView= (ListView) v.findViewById(R.id.center_team_list_view);
        mTeams= CurrentUser.getUser().teams;
        mTeams.add(new TeamVO(false));
        teamAdapter=new TeamAdapter(mTeams);
        mTeamView.setAdapter(teamAdapter);

        return v;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    public static SPCenterFragment newInstance(){
        Bundle args=new Bundle();
        //args.putSerializable(EXTRA_CRIME_ID,crimeID);

        SPCenterFragment fragment=new SPCenterFragment();
        //fragment.setArguments(args);

        return fragment;
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

    private class TeamAdapter extends ArrayAdapter<TeamVO> {
        public TeamAdapter(ArrayList<TeamVO> teamVOs){
            super(getActivity(),0,teamVOs);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            //如果没有，就inflate一个
            if (convertView==null){
                convertView=getActivity().getLayoutInflater().inflate(R.layout.list_item_team,null);
            }

            TeamVO c=getItem(position);

            //title
            TextView titleTextView= (TextView) convertView.findViewById(R.id.title_list_team);
            titleTextView.setText(c.teamName);

            //members
            TextView dateTextView=(TextView)convertView.findViewById(R.id.member_list_team);
            dateTextView.setText(c.nameList.toString());

            //消息
            TextView typeTextView=(TextView)convertView.findViewById(R.id.detail_list_team);
            typeTextView.setText("小组类型："+c.type);

            //消息
            TextView detailTextView=(TextView)convertView.findViewById(R.id.notification_team_list);
            detailTextView.setText("最新通知："+c.notification.get(0));


            return convertView;
        }
    }
    public void onResume(){
        super.onResume();
        adapter.notifyDataSetChanged();
    }


}

