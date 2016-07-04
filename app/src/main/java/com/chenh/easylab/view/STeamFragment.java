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
import com.chenh.easylab.vo.TeamVO;

import java.util.ArrayList;

/**
 * Created by chenh on 2016/6/20.
 */
public class STeamFragment extends Fragment {

    private ArrayList<TeamVO> teamVO;
    private TeamAdapter adapter;
    private ListView listView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_student_appointment,container, false);
        getActivity().setTitle("小组信息");

        listView= (ListView) v.findViewById(R.id.appointment_list_s);

        teamVO= CurrentUser.getUser().teams;
        if (teamVO.size()==0){
            teamVO.add(new TeamVO(false));
        }

        adapter=new TeamAdapter(teamVO);
        listView.setAdapter(adapter);

        return v;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().setTitle(R.string.title_fragment_s_appontment);
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



    public static STeamFragment newInstance(){
       /* Bundle args=new Bundle();
        //args.putSerializable(EXTRA_CRIME_ID,crimeID);
*/
        STeamFragment fragment=new STeamFragment();
        //fragment.setArguments(args);

        return fragment;
    }
}
