package com.example.solinari.navigationviewsample;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.LayoutRes;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

/**
 * Created by Solinari on 2017/02/15.
 */

public class Navigation_BaseActivity extends AppCompatActivity {
    private DrawerLayout DL;
    private FrameLayout FL;

    protected NavigationView NV;
    protected Toolbar toolbar;
    protected int CurrentMenuItem = 0;//紀錄目前User位於哪一個項目

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        DL = (DrawerLayout) getLayoutInflater().inflate(R.layout.navigation_drawer, null);
        FL = (FrameLayout) DL.findViewById(R.id.content_frame);
        NV = (NavigationView)DL.findViewById(R.id.Left_Navigation);
        getLayoutInflater().inflate(layoutResID, FL, true);
        super.setContentView(DL);
        toolbar = (Toolbar) findViewById(R.id.ToolBar);
        setHeaderLayout();
        setUpNavigation();
    }

    private void setHeaderLayout(){
     View headerLayout = NV.getHeaderView(0);
     TextView headerText = headerLayout.findViewById(R.id.navigation_header_userID);
     headerText.setText(getString(R.string.author_name));
    }

    private void setUpNavigation() {
        // Set navigation item selected listener
        NV.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                if(!(menuItem == NV.getMenu().getItem(CurrentMenuItem))) {//判斷使者者是否點擊當前畫面的項目，若不是，根據所按的項目做出分別的動作
                    switch (menuItem.getItemId()) {
                        case R.id.navItemOne:
                                Intent intent = new Intent();
                                intent.setClass(Navigation_BaseActivity.this, MainActivity.class);
                                startActivity(intent);
                                overridePendingTransition(0, 0);
                                finish();
                            break;
                        case R.id.navItemAbout:
                            Intent intent2 = new Intent();
                            intent2.setClass(Navigation_BaseActivity.this, About.class);
                            startActivity(intent2);
                            overridePendingTransition(0, 0);
                            finish();
                            break;
                        case R.id.navItemLogout:
                            new AlertDialog.Builder(Navigation_BaseActivity.this)
                                    .setTitle("Logout")
                                    .setMessage("Are you sure you want to Logout?")
                                    .setPositiveButton("Yes", new DialogInterface.OnClickListener()
                                    {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            finish();
                                        }

                                    })
                                    .setNegativeButton("No", null)
                                    .show();
                            break;
                    }
                }
                else {//點擊當前項目時，收起Navigation
                    DL.closeDrawer(GravityCompat.START);
                }
                return false;
            }
        });

    }
    public void setUpToolBar() {//設置ToolBar
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                DL.openDrawer(GravityCompat.START);
            }
        });
        //設定當使用者點擊ToolBar中的Navigation Icon時，Icon會隨著轉動
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle( this, DL, toolbar,R.string.open_navigation,R.string.close_navigation){
            @Override
            public void onDrawerClosed(View drawerView) {
                super .onDrawerClosed(drawerView);
            }
            @Override
            public void onDrawerOpened(View drawerView) {
                super .onDrawerOpened(drawerView);
            }
        };
        DL.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
    }
}
