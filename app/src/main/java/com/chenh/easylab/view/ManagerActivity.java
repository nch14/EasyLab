package com.chenh.easylab.view;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.chenh.easylab.R;

public class ManagerActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    FloatingActionButton fab;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_camera);


        FragmentManager fm = getFragmentManager();
        //这里是通过fragmentContainer——此处为CrimeAcitity来获取资源的
        Fragment fragment = fm.findFragmentById(R.id.managerFragmentContainer);
        if (fragment == null) {
            fragment =MLabManageFragment.newInstance();
        }
        //创建一个新的fragment事物，加入一个添加操作，然后提交该事物
        fm.beginTransaction().replace(R.id.managerFragmentContainer, fragment).commit();
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fm=getFragmentManager();
                AddLabDialog dialog=AddLabDialog.newInstance();
                dialog.show(fm,"lab");
            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.manager, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        FragmentManager fm = getFragmentManager();
        //这里是通过fragmentContainer——此处为CrimeAcitity来获取资源的
        Fragment fragment = fm.findFragmentById(R.id.fragmentContainer);


        if (id == R.id.nav_camera) {
            fragment = MLabManageFragment.newInstance();
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    FragmentManager fm=getFragmentManager();
                    AddLabDialog dialog=AddLabDialog.newInstance();
                    dialog.show(fm,"lab");
                }
            });
        } else if (id == R.id.nav_gallery) {
            fragment = MCourseManageFragment.newInstance();
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(ManagerActivity.this, AddAppointmentActivity.class);
                    startActivityForResult(i, 0);
                }
            });
        } else if (id == R.id.nav_slideshow) {
            fragment = MProjectManageFragment.newInstance();
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(ManagerActivity.this, AddTeamActivity.class);
                    startActivityForResult(i, 0);
                }
            });
        } else if (id ==R.id.nav_manage){
            fragment = MAuthorityManageFragment.newInstance();
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(ManagerActivity.this, AddTeamActivity.class);
                    startActivityForResult(i, 0);
                }
            });
        } else if (id == R.id.nav_share) {
            Intent i = new Intent(ManagerActivity.this, LoginActivity.class);
            startActivity(i);
            this.finish();
        }
        //创建一个新的fragment事物，加入一个添加操作，然后提交该事物
        fm.beginTransaction().replace(R.id.managerFragmentContainer, fragment).commit();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
