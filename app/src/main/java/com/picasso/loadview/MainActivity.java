package com.picasso.loadview;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements LoadingView.OnRefreshListener {

    @Bind(R.id.iv_bg)
    ImageView ivBg;
    @Bind(R.id.loading_view)
    LoadingView mLoadView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mLoadView.setStatue(LoadingView.LOADING);
        mLoadView.setRefrechListener(this);

        //刷新3s后模拟无网络
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                // TODO Auto-generated method stub
                mLoadView.setStatue(LoadingView.NO_NETWORK);
            }
        }, 5 * 1000);
    }

    //刷新界面方法
    @Override
    public void refresh() {
        // TODO Auto-generated method stub
        Toast.makeText(this, "refresh", Toast.LENGTH_SHORT).show();

        //刷新3s后模拟刷新成功
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                // TODO Auto-generated method stub
                mLoadView.setStatue(LoadingView.GONE);
                Picasso.with(MainActivity.this).load(R.mipmap.jozomu).into(ivBg);
            }
        }, 3 * 1000);

    }
}
