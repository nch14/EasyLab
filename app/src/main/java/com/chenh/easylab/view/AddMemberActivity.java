package com.chenh.easylab.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.chenh.easylab.R;
import com.chenh.easylab.vo.UserVO;

import java.util.ArrayList;

public class AddMemberActivity extends AppCompatActivity {
    private TextView textView;
    private Button button;
    private ListView listView;

    private ArrayList<UserVO> users;
    private MemberAdapter adapter;

    public static final String EXTRA_MEMBER_USER_ID="com.chenh.eastlab.user_id";
    private String userID="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_member);

        textView= (TextView) findViewById(R.id.input_member_name);
        button= (Button) findViewById(R.id.add_member_name_button);
        listView= (ListView) findViewById(R.id.member_name_list);

        userID=getIntent().getStringExtra(AddAppointmentActivity.CURRENT_MEMBER);

        Toolbar toolbar= (Toolbar) findViewById(R.id.member_toolbar);
        toolbar.setTitle("添加成员");
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
                    Toast.makeText(AddMemberActivity.this , "行吧", Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent();
                    intent.putExtra(EXTRA_MEMBER_USER_ID,userID);
                    setResult(RESULT_OK,intent);
                    finish();
                }
                return true;
            }
        });


        users=new ArrayList<>();
        if (userID!=null){
            String[] user=userID.split(":");
            for (int i=0;i<user.length;i++)
                users.add(new UserVO("mx","***",userID,"141250094",0));
        }
        adapter=new MemberAdapter(users);
        listView.setAdapter(adapter);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=textView.getText().toString();
                users.add(new UserVO("mx","***",name,"141250094",0));
                userID+=name+":";
                textView.setText("");
                adapter.notifyDataSetChanged();
            }
        });

    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.add_member_menu, menu);
        return true;
    }

    private class MemberAdapter extends ArrayAdapter<UserVO> {
        public MemberAdapter(ArrayList<UserVO> users){
            super(AddMemberActivity.this,0,users);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            //如果没有，就inflate一个
            if (convertView==null){
                convertView=AddMemberActivity.this.getLayoutInflater().inflate(R.layout.list_item_member,null);
            }

            UserVO c=getItem(position);

            //title
            TextView titleTextView= (TextView) convertView.findViewById(R.id.name_member_list);
            titleTextView.setText(c.name);

            //DateTime
            TextView dateTextView=(TextView)convertView.findViewById(R.id.sId_member_list);
            dateTextView.setText(c.sid);

            return convertView;
        }
    }
}
