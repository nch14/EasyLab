package com.chenh.easylab;

import android.app.FragmentManager;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import java.util.ArrayList;

public class AddAppointmentActivity extends AppCompatActivity {

    public static final String CURRENT_MEMBER="com.chenh.easylab.current_member";
    private String currentMember;

    private static final String DIALOG_DATE="date";
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
        //setActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        toolbar.inflateMenu(R.menu.add_member_menu);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Toast.makeText(AddAppointmentActivity.this , "hhhh,并没什么用" , Toast.LENGTH_SHORT).show();

                return true;
            }
        });
        toolbar.setTitle("新增预约");


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
