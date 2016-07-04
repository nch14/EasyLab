package com.chenh.easylab.view;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.chenh.easylab.R;
import com.chenh.easylab.vo.AppointmentVO;
import com.chenh.easylab.vo.LabVO;

import java.util.ArrayList;

/**
 * Created by chenh on 2016/6/20.
 */
public class MLabManageFragment extends Fragment {
    private ListView mListView;
    private ArrayList<LabVO> rooms;
    private LabAdapter roomsAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_manager_lab_manage,container, false);

        mListView=(ListView) v.findViewById(R.id.lab_listView);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i=new Intent(getActivity(),LabContentActivity.class);
                LabVO l=rooms.get(position);
                i.putExtra(LabContentActivity.EXTRA_LAB_ID,l.roomId);
                startActivity(i);
            }
        });

        rooms=new ArrayList<>();
        LabVO l=new LabVO();
        l.roomId="甲区401";
        rooms.add(l);
        l=new LabVO();
        l.roomId="甲区402";
        rooms.add(l);
        l=new LabVO();
        l.roomId="甲区403" +
                "";
        rooms.add(l);
        roomsAdapter=new LabAdapter(rooms);
        mListView.setAdapter(roomsAdapter);
        return v;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().setTitle("实验室管理");
    }

    public static MLabManageFragment newInstance(){
        MLabManageFragment fragment=new MLabManageFragment();
        return fragment;
    }


    private class LabAdapter extends ArrayAdapter<LabVO> {
        public LabAdapter(ArrayList<LabVO> labs) {
            super(getActivity(), 0, labs);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            //如果没有，就inflate一个
            if (convertView == null) {
                convertView = getActivity().getLayoutInflater().inflate(R.layout.list_item_lab, null);
            }

            LabVO c = getItem(position);

            //title
            TextView titleTextView = (TextView) convertView.findViewById(R.id.lab_no);
            titleTextView.setText(c.roomId);


            return convertView;
        }
    }
}
