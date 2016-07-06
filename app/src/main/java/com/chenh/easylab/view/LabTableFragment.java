package com.chenh.easylab.view;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.chenh.easylab.R;
import com.chenh.easylab.util.Client;
import com.chenh.easylab.util.LocalLabs;
import com.chenh.easylab.util.ServerBackData;
import com.chenh.easylab.vo.DeskVO;
import com.chenh.easylab.vo.LabVO;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by chenh on 2016/7/2.
 */
public class LabTableFragment extends LabContentFragment {
    private ListView mListView;
    private ArrayList<DeskVO> mDesks;
    private DeskControlAdapter mDesksAdapter;
    private Handler mHandler;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_lab_device_controll, container, false);

        mListView= (ListView) rootView.findViewById(R.id.device_listView);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });



        mHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                int what=msg.what;
                String message = msg.obj.toString();
                switch (what){
                    case 0:
                        Toast.makeText(getActivity(),message,Toast.LENGTH_SHORT).show();
                        break;
                    case 1:
                        mDesksAdapter.notifyDataSetChanged();
                        break;
                }
            }
        };

        mDesks =new ArrayList<>();
        mDesksAdapter =new DeskControlAdapter(mDesks);

        mListView.setAdapter(mDesksAdapter);
        getData();
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

    private void getData(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                ArrayList<LabVO> labs= LocalLabs.getInstance().getLabs();
                LabVO lab=null;
                for (int i=0;i<labs.size();i++){
                    String s1 = getActivity().getTitle().toString();

                    //if(labs.get(i).roomId.equals(getActivity().getTitle().toString())) {
                    lab=labs.get(i);
                    break;
                    //}
                }
                ArrayList<String> desks=lab.desks;
                for (int i=0;i<desks.size();i++){
                    JSONObject js=new JSONObject();
                    try {
                        js.put("op","50003");
                        js.put("tableId",desks.get(i));
                        boolean sendSuccess= Client.getInstance().sendRequest(js);
                        if (!sendSuccess){
                            mHandler.sendMessage(mHandler.obtainMessage(0,"网络无法连接"));
                        }else {
                            ServerBackData serverBackData=Client.getInstance().receiveData();
                            if (!serverBackData.isResultState()){
                                mHandler.sendMessage(mHandler.obtainMessage(0,"服务器无响应"));
                            }else {
                                DeskVO deskVO= (DeskVO) serverBackData.getObject();
                                mDesks.add(deskVO);
                            }
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                mHandler.sendMessage(mHandler.obtainMessage(1,""));


            }
        }).start();
    }

}
