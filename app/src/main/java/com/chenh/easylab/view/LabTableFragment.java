package com.chenh.easylab.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.chenh.easylab.R;
import com.chenh.easylab.vo.DeskVO;
import java.util.ArrayList;

/**
 * Created by chenh on 2016/7/2.
 */
public class LabTableFragment extends LabContentFragment {
    private ListView mListView;
    private ArrayList<DeskVO> desks;
    private DeskControlAdapter desksAdapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_device_controll, container, false);

        mListView= (ListView) rootView.findViewById(R.id.device_listView);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(getActivity(),AirConditionControl.class);
                startActivity(intent);
            }
        });

        desks=new ArrayList<>();
        DeskVO d=new DeskVO();
        d.tableId="第一桌";
        desks.add(d);
        DeskVO d2=new DeskVO();
        d2.tableId="第二桌";
        desks.add(d2);
        DeskVO d3=new DeskVO();
        d3.tableId="第三桌";
        desks.add(d3);
        desksAdapter=new DeskControlAdapter(desks);

        mListView.setAdapter(desksAdapter);

        return rootView;
    }



    private class DeskControlAdapter extends ArrayAdapter<DeskVO> {
        public DeskControlAdapter(ArrayList<DeskVO> devices){
            super(getActivity(),0,devices);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            //如果没有，就inflate一个
            DeskVO c=getItem(position);
            if (convertView==null){
                convertView=getActivity().getLayoutInflater().inflate(R.layout.list_item_table,null);
            }
            TextView titleTextView= (TextView) convertView.findViewById(R.id.item_name);
            titleTextView.setText(c.tableId);
            return convertView;
        }

    }

}
