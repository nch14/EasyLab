package com.chenh.easylab.view;

import android.app.FragmentManager;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.chenh.easylab.R;

public class AirConditionControl extends AppCompatActivity {
    private TextView mTemperature;
    private TextView mModel;
    private ImageView mArrow;
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
                    FragmentManager fm=getFragmentManager();
                    TemperatureSelectorFragment dialog= TemperatureSelectorFragment.newInstance(mTemperature);
                    dialog.show(fm,NUMBER);
                    break;
                case R.id.model:
                    if(mModel.getText().toString().equals("制冷")){
                        mModel.setText("制热");
                    }else {
                        mModel.setText("制冷");
                    }
                    break;
            }
        }
    }
}
