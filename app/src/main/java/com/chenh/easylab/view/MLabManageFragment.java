package com.chenh.easylab.view;

import android.app.Fragment;
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
import com.chenh.easylab.util.LocalUser;
import com.chenh.easylab.util.ServerBackData;
import com.chenh.easylab.vo.AppointmentVO;
import com.chenh.easylab.vo.LabVO;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by chenh on 2016/6/20.
 */
public class MLabManageFragment extends Fragment {
    private ListView mListView;
    private ArrayList<LabVO> rooms;
    private LabAdapter roomsAdapter;
    private Handler mHandler;


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
                        roomsAdapter.notifyDataSetChanged();
                        break;
                }
            }
        };

        rooms=new ArrayList<>();
        roomsAdapter=new LabAdapter(rooms);
        mListView.setAdapter(roomsAdapter);
        getData();
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

    private void getData(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                JSONObject js=new JSONObject();
                try {
                    js.put("op","40003");
                    LocalLabs.getInstance().clear();
                    boolean sendSuccess=Client.getInstance().sendRequest(js);
                    if (!sendSuccess){
                        mHandler.sendMessage(mHandler.obtainMessage(0,"网络无法连接"));
                    }else {
                        ServerBackData serverBackData=Client.getInstance().receiveData();
                        if (!serverBackData.isResultState()){
                            mHandler.sendMessage(mHandler.obtainMessage(0,"服务器无响应"));
                            //Toast.makeText(getActivity(),"服务器无响应",Toast.LENGTH_SHORT).show();
                        }else {
                            ArrayList<LabVO> labs=LocalLabs.getInstance().getLabs();
                            //rooms.clear();
                            for (int i=0;i<labs.size();i++){
                                rooms.add(labs.get(i));
                            }
                            mHandler.sendMessage(mHandler.obtainMessage(1,""));
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

}
