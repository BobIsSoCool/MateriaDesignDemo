package com.example.wub.materialdemo;

import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

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
     * FloatingActionButton
     */
    FloatingActionButton fab;
    /**
     * TAG
     */
    private static String TAG = "MainActivityTag";
    /**
     * RecyclerView
     */
    RecyclerView recyclerView;
    /**
     * 水果数据
     */
    private Fruit[] fruits = {new Fruit("西瓜", R.drawable.img_watermelon),
            new Fruit("橘子", R.drawable.img_orange),
            new Fruit("苹果", R.drawable.img_apple),
            new Fruit("香蕉", R.drawable.img_banana),
            new Fruit("樱桃", R.drawable.img_cherry),
            new Fruit("梨", R.drawable.img_pear)};
    private List<Fruit> fruitList = new ArrayList<>();
    /**
     * 适配器
     */
    private FruitAdapter adapter;
    /**
     * 下拉刷新
     */
    SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initUI();
        initFruits();

    }

    /**
     * 初始化控件
     */
    public void initUI() {
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        mToolBar = (Toolbar) findViewById(R.id.toolBar);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh);

        //初始化RecyclerView
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new FruitAdapter(fruitList);
        recyclerView.setAdapter(adapter);

        //下拉刷新的一些设置
        swipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.colorPrimary));
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshFruits();
            }
        });

        //toolBar图标
        setSupportActionBar(mToolBar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
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

        //悬浮按钮点击事件
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Snackbar的使用
                Snackbar.make(view, "删除数据", Snackbar.LENGTH_SHORT)
                        .setAction("确定", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Toast.makeText(MainActivity.this, "删除成功", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .show();
            }
        });
    }

    /**
     * 点击事件
     *
     * @param view
     */
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //ToolBar菜单
        getMenuInflater().inflate(R.menu.toolbar, menu);
        return true;
    }

    /**
     * ToolBar菜单点击事件
     *
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                //点击ActionBar返回图标，打开侧滑菜单（注：HomeAsUp按钮的id永远都是android.R.id.home）
                mDrawerLayout.openDrawer(GravityCompat.START);
                break;
            case R.id.edit:
                Toast.makeText(this, "你点击了编辑图标", Toast.LENGTH_SHORT).show();
                break;
            case R.id.favorite:
                Toast.makeText(this, "你点击了收藏图标", Toast.LENGTH_SHORT).show();
                break;
            case R.id.set:
                Toast.makeText(this, "你点击了设置图标", Toast.LENGTH_SHORT).show();
                break;
            default:
        }
        return true;
    }

    public void initFruits() {
        fruitList.clear();
        //将数组中的水果对象随机添加到集合（假数据）
        for (int i = 0; i < 20; i++) {
            Random random = new Random();
            int index = random.nextInt(fruits.length);
            fruitList.add(fruits[index]);
        }
    }

    /**
     * 刷新
     */
    public void refreshFruits() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                //沉睡2秒
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //切回主线程
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //重新随机生成数据
                        initFruits();
                        adapter.notifyDataSetChanged();
                        swipeRefreshLayout.setRefreshing(false);
                    }
                });

            }
        }).start();
    }
}
