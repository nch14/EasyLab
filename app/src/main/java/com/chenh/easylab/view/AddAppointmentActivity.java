package com.chenh.easylab.view;

import android.app.FragmentManager;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.chenh.easylab.R;
import com.chenh.easylab.po.DeviceRequire;
import com.chenh.easylab.util.CurrentUser;
import com.chenh.easylab.util.Util;
import com.chenh.easylab.vo.AppointmentVO;

import java.util.ArrayList;

public class AddAppointmentActivity extends AppCompatActivity {

    public static final String CURRENT_MEMBER="com.chenh.easylab.current_member";
    private String currentMember;

    private static final String DIALOG_DATE="date";

    private TextView titleView;
    private TextView chooseDate;
    private TextView chooseStartTime;
    private TextView chooseEndTime;
    private TextView chooseMember;

    private TextView showMember;

    private ArrayList<DeviceRequire> devices;
    private ArrayAdapter<DeviceRequire> devicesAdapter;
    private ListView devicesListView;


    private ArrayList<String> addDevices;
    private ArrayAdapter<String> addDevicesAdapter;
    private ListView addDevicesListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_appointment);

        Toolbar toolbar= (Toolbar) findViewById(R.id.appointment_toolbar);
        toolbar.setTitle("新增预约");
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                int menuItemId = item.getItemId();
                if (menuItemId == R.id.action_commit) {

                    String date=((TextView) findViewById(R.id.date_make_appoint)).getText().toString();
                    String start=((TextView) findViewById(R.id.start_time_make_appoint)).getText().toString();
                    String end=((TextView) findViewById(R.id.end_time_make_appoint)).getText().toString();

                    String time=null;
                    if (date!=null&&start!=null&&end!=null){
                        if (!Util.aBeforeB(start,end)){
                            Toast.makeText(AddAppointmentActivity.this , "开始时间不能晚于结束时间", Toast.LENGTH_SHORT).show();
                            return false;
                        }
                        time=date+" "+start+" "+end;
                    }else {
                        Toast.makeText(AddAppointmentActivity.this , "请填写预约时间", Toast.LENGTH_SHORT).show();
                        return false;
                    }

                    String title="无标题";
                    if (titleView.getText()!=null){
                        title=titleView.getText().toString();
                    }

                    String roomId="400";
                    String tableId="001";

                    ArrayList<String> members=new ArrayList<>();
                    String[] memberName=currentMember.split(";");
                    for (int i=0;i<memberName.length;i++){
                        members.add(memberName[i]);
                    }

                    AppointmentVO appointmentVO=new AppointmentVO(time,title,roomId,tableId, members,devices,
                            "暂无细节");
                    CurrentUser.getUser().addAppointment(appointmentVO);
                    Toast.makeText(AddAppointmentActivity.this , "预约已提交", Toast.LENGTH_SHORT).show();


                }
                return true;
            }
        });


        titleView= (TextView) findViewById(R.id.title_make_appoint);

        chooseDate= (TextView)findViewById(R.id.choose_date_make_appoint);
        chooseDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm=getFragmentManager();
                DatePickerFragment dialog= DatePickerFragment.newInstance((TextView) findViewById(R.id.date_make_appoint));

                dialog.show(fm,DIALOG_DATE);
            }
        });

        chooseStartTime = (TextView)findViewById(R.id.choose_start_time_make_appoint);
        chooseStartTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm=getFragmentManager();
                TimePickerFragment dialog=TimePickerFragment.newInstance((TextView) findViewById(R.id.start_time_make_appoint));
                dialog.show(fm,DIALOG_DATE);
            }
        });

        chooseEndTime = (TextView)findViewById(R.id.choose_end_time_make_appoint);
        chooseEndTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm=getFragmentManager();
                TimePickerFragment dialog=TimePickerFragment.newInstance((TextView) findViewById(R.id.end_time_make_appoint));
                dialog.show(fm,DIALOG_DATE);
            }
        });

        chooseMember= (TextView)findViewById(R.id.choose_member_make_appoint);
        chooseMember.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(AddAppointmentActivity.this,AddMemberActivity.class);
                i.putExtra(CURRENT_MEMBER,currentMember);
                startActivityForResult(i,1);
            }
        });

        devices =new ArrayList<>();
        devices.add(new DeviceRequire("显示屏",1));
        devices.add(new DeviceRequire("主机",1));
        devices.add(new DeviceRequire("键盘",1));
        devices.add(new DeviceRequire("鼠标",1));
        devices.add(new DeviceRequire("万用表",1));
        devices.add(new DeviceRequire("示波器",1));
        devices.add(new DeviceRequire("稳压电源",1));
        devices.add(new DeviceRequire("信号发生器",1));


        devicesAdapter =new DeviceAdapter(devices);

        devicesListView = (ListView)findViewById(R.id.list_default_device);
        devicesListView.setAdapter(devicesAdapter);



        showMember= (TextView)findViewById(R.id.member_make_appoint);
    }

    private class DeviceAdapter extends ArrayAdapter<DeviceRequire> {

        public DeviceAdapter(ArrayList<DeviceRequire> appointmentItems) {
            super(AddAppointmentActivity.this, 0, appointmentItems);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            //如果没有，就inflate一个
            if (convertView == null) {
                convertView =getLayoutInflater().inflate(R.layout.list_item_device, null);
            }

            DeviceRequire c = getItem(position);

            TextView titleTextView = (TextView) convertView.findViewById(R.id.name_list_device);
            titleTextView.setText(c.getDeviceName());

            TextView dateTextView = (TextView) convertView.findViewById(R.id.num_list_device);
            dateTextView.setText(""+c.getNeedNum());

            TextView typeTextView = (TextView) convertView.findViewById(R.id.type_list_device);
            typeTextView.setText(c.isDefaultDevice()?"默认设备":"自定义设备");
            return convertView;
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.add_member_menu, menu);
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Check which request it is that we're responding to
        if (requestCode == 1) {
            // Make sure the request was successful
            if (resultCode == RESULT_OK) {
                String s=data.getStringExtra(AddMemberActivity.EXTRA_MEMBER_USER_ID);
                currentMember=s;
                showMember.setText(s);
            }
        }
    }

}
