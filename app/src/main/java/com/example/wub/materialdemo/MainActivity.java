package com.example.wub.materialdemo;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBar;
import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    /**
     * DrawerLayout
     */
    DrawerLayout mDrawerLayout;
    /**
     * NavigationView
     */
    NavigationView navigationView;
    /**
     * ToolBar
     */
    Toolbar mToolBar;
    /**
     * TAG
     */
    private static String TAG = "MainActivityTag";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initUI();

    }

    /**
     * 初始化控件
     */
    public void initUI(){
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        mToolBar = (Toolbar) findViewById(R.id.toolBar);

        //toolBar图标
        setSupportActionBar(mToolBar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar!=null){
            //ActionBar返回图标
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.icon_menu_top);
        }

        //NavigationView一些初始设置
        navigationView.setCheckedItem(R.id.edit);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                //点击事件
                mDrawerLayout.closeDrawers();
                return true;
            }
        });
    }

    /**
     * 点击事件
     * @param view
     */
    @Override
    public void onClick(View view) {
        switch (view.getId()){
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //ToolBar菜单
        getMenuInflater().inflate(R.menu.toolbar,menu);
        return true;
    }

    /**
     * ToolBar菜单点击事件
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                //点击ActionBar返回图标，打开侧滑菜单（注：HomeAsUp按钮的id永远都是android.R.id.home）
                mDrawerLayout.openDrawer(GravityCompat.START);
                break;
            case R.id.edit:
                Toast.makeText(this,"你点击了编辑图标",Toast.LENGTH_SHORT).show();
                break;
            case R.id.favorite:
                Toast.makeText(this,"你点击了收藏图标",Toast.LENGTH_SHORT).show();
                break;
            case R.id.set:
                Toast.makeText(this,"你点击了设置图标",Toast.LENGTH_SHORT).show();
                break;
            default:
        }
        return true;
    }
}
