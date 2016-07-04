package com.chenh.easylab.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toolbar;

import com.chenh.easylab.R;

/**
 * Created by chenh on 2016/6/20.
 */

public class AddTeamActivity extends AppCompatActivity {

    public static final String CURRENT_MEMBER="com.chenh.easylab.current_member";
    private String currentMember;

    private TextView titleView;
    private TextView chooseMember;

    private TextView showMember;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_team);

        Toolbar toolbar= (Toolbar) findViewById(R.id.team_toolbar);
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
                return false;
            }
        });
        toolbar.setTitle("新增小组");

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Check which request it is that we're responding to
        if (requestCode == 1) {
            // Make sure the request was successful
            if (resultCode == RESULT_OK) {
                String s=data.getStringExtra(AddTeamActivity.CURRENT_MEMBER);
                currentMember=s;
                showMember.setText(s);
            }
        }
    }

}

