# LoadView

> 我们在进行网络加载的时候，有时候需要一个能覆盖界面的loading界面，比如第一次进应用，加载数据的时候，需要这么个loading页面，体验比较友好，再比如第一次进来，数据加载失败了，或者无网络了，需要有个友好的提示，空空荡荡的效果不好，有时候点击无网络图片可以重新刷新，所以写了个通用的LoadingView。

---
看效果

![这里写图片描述](http://img.blog.csdn.net/20160720172721707)
##使用方法
**1、 在布局里面使用我们的自定义LoadingView**

```
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context="com.picasso.loadview.MainActivity">

    <ImageView
        android:id="@+id/iv_bg"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:layout_centerInParent="true"
        />

    <com.picasso.loadview.LoadingView
        android:id="@+id/loading_view"
        android:layout_width="match_parent"
        android:layout_height="match_parent" />

</RelativeLayout>
```

**2、根据不同情况调用**

 2.1 正在加载
 

```
mLoadView.setStatue(LoadingView.LOADING);
```
 2.2无网络情况
 

```
mLoadView.setStatue(LoadingView.NO_NETWORK);
```
2.3无数据情况

```
mLoadView.setStatue(LoadingView.NO_DATA);
```

2.4 无网络或者加载失败后，如果需要点击界面重新刷新，
则这里需要回调一个refresh方法

```
mLoadView.setRefrechListener(this);
```
2.5 loadingview消失

```
mLoadView.setStatue(LoadingView.GONE);
```

---

下面写了个demo，模拟效果用。

```
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
```
