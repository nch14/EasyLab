package com.chenh.easylab.view;

import android.content.DialogInterface;
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
import com.chenh.easylab.vo.DeviceVO;
import java.util.ArrayList;

/**
 * Created by chenh on 2016/7/2.
 */
public class LabOverViewFragment extends LabContentFragment {
    private ListView mListView;
    private ArrayList<DeviceVO> devices;
    private DeviceControlAdapter devicesAdapter;

    public static final String ARG_SECTION_NUMBER = "section_number";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_device_controll, container, false);


        mListView= (ListView) rootView.findViewById(R.id.device_listView);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                DeviceVO d=devices.get(position);
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


        devices=new ArrayList<>();
        DeviceVO d= new DeviceVO();
        d.name="电闸";
        d.type=DeviceVO.NORMAL_DEVICE;
        devices.add(d);
        DeviceVO d2= new DeviceVO();
        d2.name="空调";
        d2.type=DeviceVO.AIR_CONDITION;
        devices.add(d2);
        d2= new DeviceVO();
        d2.name="超级电流表";
        d2.type=DeviceVO.AMPEREMETER;
        devices.add(d2);
        devicesAdapter=new DeviceControlAdapter(devices);

        mListView.setAdapter(devicesAdapter);

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
            System.out.println(c.type);
            if(c.type==DeviceVO.NORMAL_DEVICE){
                if (convertView==null){
                    convertView=getActivity().getLayoutInflater().inflate(R.layout.list_item_device_control,null);
                }
                TextView titleTextView= (TextView) convertView.findViewById(R.id.item_name);
                titleTextView.setText(c.name);
            }else if (c.type==DeviceVO.AMPEREMETER){
                if (convertView==null){
                    convertView=getActivity().getLayoutInflater().inflate(R.layout.list_item_amperemeter,null);
                }
                TextView titleTextView= (TextView) convertView.findViewById(R.id.item_name);
                titleTextView.setText(c.name);
            }else{
                if (convertView==null){
                    convertView=getActivity().getLayoutInflater().inflate(R.layout.list_item_air_condition,null);
                }
                TextView titleTextView= (TextView) convertView.findViewById(R.id.item_name);
                titleTextView.setText(c.name);
            }


            return convertView;
        }

    }

}
