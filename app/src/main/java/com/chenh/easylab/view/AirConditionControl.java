package com.chenh.easylab.view;

import android.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.chenh.easylab.R;

public class AirConditionControl extends AppCompatActivity {
    private TextView mTemperature;
    private TextView mModel;
    private ImageView mArrow;
    private Switch mSwitch;

    private ImageView mStudyArrow;
    private static final String NUMBER="number";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_air_condition_control);

        Toolbar toolbar= (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("空调控制");
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mTemperature= (TextView) findViewById(R.id.temperature);
        mModel=(TextView)findViewById(R.id.model);

        mModel.setOnClickListener(new InnerOnClickListener());

        mArrow= (ImageView) findViewById(R.id.arrow);
        mArrow.setOnClickListener(new InnerOnClickListener());

        mStudyArrow=(ImageView)findViewById(R.id.study_arrow);
        mStudyArrow.setOnClickListener(new InnerOnClickListener());

        mSwitch=(Switch)findViewById(R.id.air_switch);
        mSwitch.setOnClickListener(new InnerOnClickListener());

    }


/*    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }*/

    //监听类、用来避免大量生成匿名类
    class InnerOnClickListener implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            int id=v.getId();
            switch (id){
                case R.id.arrow:
                    if (mSwitch.isEnabled()){
                        FragmentManager fm=getFragmentManager();
                        TemperatureSelectorFragment dialog= TemperatureSelectorFragment.newInstance(mTemperature);
                        dialog.show(fm,NUMBER);
                    }
                    break;
                case R.id.model:
                    if(mSwitch.isEnabled()){
                        if(mModel.getText().toString().equals("制冷")){
                            mModel.setText("制热");
                        }else {
                            mModel.setText("制冷");
                        }
                    }
                    break;
                case R.id.study_arrow:
                    Toast.makeText(AirConditionControl.this,"学习指令已经送出",Toast.LENGTH_SHORT).show();
                    break;
                case R.id.air_switch:

                    break;
            }
        }
    }
}
