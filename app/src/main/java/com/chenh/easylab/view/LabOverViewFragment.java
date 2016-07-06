package com.chenh.easylab.view;

import android.content.DialogInterface;
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
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.chenh.easylab.R;
import com.chenh.easylab.util.Client;
import com.chenh.easylab.util.LocalLabs;
import com.chenh.easylab.util.ServerBackData;
import com.chenh.easylab.vo.DeviceVO;
import com.chenh.easylab.vo.LabVO;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by chenh on 2016/7/2.
 */
public class LabOverViewFragment extends LabContentFragment {
    private ListView mListView;
    private ArrayList<DeviceVO> mDevices;
    private DeviceControlAdapter mDevicesAdapter;
    private Handler mHandler;

    public static final String ARG_SECTION_NUMBER = "section_number";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_lab_device_controll, container, false);


        mListView= (ListView) rootView.findViewById(R.id.device_listView);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                DeviceVO d= mDevices.get(position);
                if (d.type==DeviceVO.AIR_CONDITION) {
                    Intent intent = new Intent(getActivity(), AirConditionControl.class);
                    startActivity(intent);
                }
            }
        });
        mListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                final android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(getActivity());
                //    设置Title的图标
                //builder.setIcon(R.drawable.ic_launcher);
                //    设置Title的内容
                builder.setTitle("删除设备");
                //    设置Content来显示一个信息

                /*View vv = LayoutInflater.from(LabOverViewFragment.this.getActivity()).inflate(R.layout.sign_up, null);

                builder.setView(vv);*/
                builder.setMessage("确定删除吗？");
                //    设置一个PositiveButton
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Check for a valid password, if the user entered one.

                    }
                });
                //    设置一个NegativeButton
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.show();
                return true;
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
                        mDevicesAdapter.notifyDataSetChanged();
                        break;
                }
            }
        };


        mDevices =new ArrayList<>();
        mDevicesAdapter =new DeviceControlAdapter(mDevices);
        mListView.setAdapter(mDevicesAdapter);

        getData();

        return rootView;
    }



    private class DeviceControlAdapter extends ArrayAdapter<DeviceVO> {
        public DeviceControlAdapter(ArrayList<DeviceVO> devices){
            super(getActivity(),0,devices);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            //如果没有，就inflate一个
            DeviceVO c=getItem(position);

            if(c.type==DeviceVO.NORMAL_DEVICE){
                if (convertView==null){
                    convertView=getActivity().getLayoutInflater().inflate(R.layout.list_item_device_control,null);
                }
                TextView titleTextView= (TextView) convertView.findViewById(R.id.item_name);
                titleTextView.setText(c.name);

                Switch aSwitch= (Switch) convertView.findViewById(R.id.switch1);
                aSwitch.setChecked(c.open);
            }else if (c.type==DeviceVO.AMPEREMETER){
                if (convertView==null){
                    convertView=getActivity().getLayoutInflater().inflate(R.layout.list_item_amperemeter,null);
                }
                TextView titleTextView= (TextView) convertView.findViewById(R.id.item_name);
                titleTextView.setText(c.name);

                TextView value= (TextView) convertView.findViewById(R.id.item_value);
                value.setText(c.value+"A");

            }else if(c.type==DeviceVO.AIR_CONDITION){
                if (convertView==null){
                    convertView=getActivity().getLayoutInflater().inflate(R.layout.list_item_air_condition,null);
                }
                TextView titleTextView= (TextView) convertView.findViewById(R.id.item_name);
                titleTextView.setText(c.name);
            }else if (c.type==DeviceVO.TEMPSENSOR){
                if (convertView==null){
                    convertView=getActivity().getLayoutInflater().inflate(R.layout.list_item_amperemeter,null);
                }
                TextView titleTextView= (TextView) convertView.findViewById(R.id.item_name);
                titleTextView.setText(c.name);

                TextView value= (TextView) convertView.findViewById(R.id.item_value);
                value.setText(c.value+"°C");

            }


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
                ArrayList<String> devices=lab.devices;
                for (int i=0;i<devices.size();i++){
                    JSONObject js=new JSONObject();
                    try {
                        js.put("op","60002");
                        js.put("macAddress",devices.get(i));
                        boolean sendSuccess= Client.getInstance().sendRequest(js);
                        if (!sendSuccess){
                            mHandler.sendMessage(mHandler.obtainMessage(0,"网络无法连接"));
                        }else {
                            ServerBackData serverBackData=Client.getInstance().receiveData();
                            if (!serverBackData.isResultState()){
                                mHandler.sendMessage(mHandler.obtainMessage(0,"服务器无响应"));
                            }else {
                                DeviceVO deviceVO= (DeviceVO) serverBackData.getObject();
                                mDevices.add(deviceVO);
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
