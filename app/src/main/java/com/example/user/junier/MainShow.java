package com.example.user.junier;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class MainShow extends AppCompatActivity {
    private TabLayout tabLayout;
    private TabFragment1 fragment1;
    private TabFragment2 fragment2;
    private TabFragment3 fragment3;
    private String uname;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_show);
        Intent intent = getIntent();
        uname = intent.getStringExtra("name");
        Log.v("name is : ", uname);


        // Initializing the TabLayout
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        tabLayout.addTab(tabLayout.newTab().setText("Main Page "));
        tabLayout.addTab(tabLayout.newTab().setText("Plan Page"));
        tabLayout.addTab(tabLayout.newTab().setText("My Page"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        fragment1 = new TabFragment1(uname);
        fragment2 = new TabFragment2(uname);
        fragment3 = new TabFragment3(uname);
        getSupportFragmentManager().beginTransaction().replace(R.id.frame, fragment1).commit();

        // Set TabSelectedListener
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {

            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                Fragment fragment = null;

                if (position == 0) {
                    fragment = fragment1;
                } else if (position == 1) {
                    fragment = fragment2;
                } else if (position == 2) {
                    fragment = fragment3;
                }

                getSupportFragmentManager().beginTransaction().replace(R.id.frame, fragment).commit();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });


    }
}