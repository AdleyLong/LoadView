# LoadView

> �����ڽ���������ص�ʱ����ʱ����Ҫһ���ܸ��ǽ����loading���棬�����һ�ν�Ӧ�ã��������ݵ�ʱ����Ҫ��ô��loadingҳ�棬����Ƚ��Ѻã��ٱ����һ�ν��������ݼ���ʧ���ˣ������������ˣ���Ҫ�и��Ѻõ���ʾ���տյ�����Ч�����ã���ʱ����������ͼƬ��������ˢ�£�����д�˸�ͨ�õ�LoadingView��

---
��Ч��

![����дͼƬ����](http://img.blog.csdn.net/20160720172721707)
##ʹ�÷���
**1�� �ڲ�������ʹ�����ǵ��Զ���LoadingView**

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

**2�����ݲ�ͬ�������**

 2.1 ���ڼ���
 

```
mLoadView.setStatue(LoadingView.LOADING);
```
 2.2���������
 

```
mLoadView.setStatue(LoadingView.NO_NETWORK);
```
2.3���������

```
mLoadView.setStatue(LoadingView.NO_DATA);
```

2.4 ��������߼���ʧ�ܺ������Ҫ�����������ˢ�£�
��������Ҫ�ص�һ��refresh����

```
mLoadView.setRefrechListener(this);
```
2.5 loadingview��ʧ

```
mLoadView.setStatue(LoadingView.GONE);
```

---

����д�˸�demo��ģ��Ч���á�

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

        //ˢ��3s��ģ��������
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                // TODO Auto-generated method stub
                mLoadView.setStatue(LoadingView.NO_NETWORK);
            }
        }, 5 * 1000);
    }

    //ˢ�½��淽��
    @Override
    public void refresh() {
        // TODO Auto-generated method stub
        Toast.makeText(this, "refresh", Toast.LENGTH_SHORT).show();

        //ˢ��3s��ģ��ˢ�³ɹ�
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
