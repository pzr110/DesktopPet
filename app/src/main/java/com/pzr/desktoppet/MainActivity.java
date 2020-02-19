package com.pzr.desktoppet;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.provider.SyncStateContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
//import com.lljjcoder.style.citylist.Toast.ToastUtils;
import com.github.nuptboyzhb.lib.SuperSwipeRefreshLayout;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.pzr.desktoppet.utils.easyhttp.EasyHttp;
import com.pzr.desktoppet.utils.easyhttp.callback.SimpleCallBack;
import com.pzr.desktoppet.utils.easyhttp.exception.ApiException;
import com.pzr.desktoppet.utils.easyhttp.model.ApiResult;
import com.pzr.desktoppet.utils.easyhttp.model.HttpParams;
import com.pzr.desktoppet.utils.easyhttp.utils.GsonUtils;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private SuperSwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView mRecyclerView;

    private List<PetsBean> mPetsBeans;
    private PetsAdapter mAdapter;

    private Context mContext;
    // Header View
    private ProgressBar progressBar;
    private TextView textView;
    private ImageView imageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViewId();
        initInfo();
    }

    private void initInfo() {
        initView();
        getInfo(true);
        initListenerNew();
    }

    private void initListenerNew() {
/**
 * 下拉刷新
 */
        mSwipeRefreshLayout.setOnPullRefreshListener(new SuperSwipeRefreshLayout.OnPullRefreshListener() {
            @Override
            public void onRefresh() {
                textView.setText("正在刷新");
                imageView.setVisibility(View.GONE);
                progressBar.setVisibility(View.VISIBLE);
                new Handler().postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        mSwipeRefreshLayout.setRefreshing(false);
                        progressBar.setVisibility(View.GONE);
                    }
                }, 2000);
                getInfo(true);
            }

            @Override
            public void onPullDistance(int i) {

            }

            @Override
            public void onPullEnable(boolean enable) {
                textView.setText(enable ? "松开刷新" : "下拉刷新");
                imageView.setVisibility(View.VISIBLE);
                imageView.setRotation(enable ? 180 : 0);
            }
        });
    }

    private void initView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
        mRecyclerView.setLayoutManager(layoutManager);

        mPetsBeans = new ArrayList<>();
        mAdapter = new PetsAdapter(mPetsBeans);

        View emptyView = getLayoutInflater().inflate(R.layout.item_empty, null);
        mAdapter.setEmptyView(emptyView);
        mRecyclerView.setAdapter(mAdapter);
        mSwipeRefreshLayout.setHeaderViewBackgroundColor(0xff888888);
        mSwipeRefreshLayout.setHeaderView(createHeaderView());// add headerView
        mSwipeRefreshLayout.setTargetScrollWithLayout(true);
//        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorAccent);//这个方法是设置SwipeRefreshLayout刷新圈颜色
    }

    /**
     * create Header View
     */
    private View createHeaderView() {
        //TODO 创建下拉刷新头部的View样式
        View headerView = LayoutInflater.from(mSwipeRefreshLayout.getContext())
                .inflate(R.layout.view_swipe, null);
        progressBar = headerView.findViewById(R.id.pb_view);
        textView = headerView.findViewById(R.id.text_view);
        textView.setText("下拉刷新");
        imageView = headerView.findViewById(R.id.image_view);
        imageView.setVisibility(View.VISIBLE);
        imageView.setImageResource(R.drawable.ic_down_arrow);
        progressBar.setVisibility(View.GONE);

        return headerView;
    }

    private void initViewId() {
        mSwipeRefreshLayout = findViewById(R.id.swipeRefreshLayout);
        mRecyclerView = findViewById(R.id.recyclerView);
    }

    private void getInfo(final boolean mIsRefresh) {
        //提交
        HttpParams param = new HttpParams();

        EasyHttp.get("pets/")
                .params(param)
                .execute(new SimpleCallBack<String>() {
                    @Override
                    public void onError(ApiException e) {
                        // 网络错误回调
                        ToastUtils.showShort("网路错误，请稍后重试");
                        if (mSwipeRefreshLayout != null && mSwipeRefreshLayout.isRefreshing()) {
                            mSwipeRefreshLayout.setRefreshing(false);

                        }
                    }

                    @Override
                    public void onSuccess(String s) {

                        if (mSwipeRefreshLayout != null && mSwipeRefreshLayout.isRefreshing()) {
                            mSwipeRefreshLayout.setRefreshing(false);

                        }

                        List<PetsBean> userList = GsonUtils.fromJson(s, new TypeToken<List<PetsBean>>() {
                        }.getType());

                        Log.d("TAG", "Result" + userList.get(0).getName());
//                        if (result.getErrcode() == 200) {
//                            SeckillBean rows = result.getRows();
                        List<PetsBean> list = userList;
                        if (mIsRefresh) {
                            // 此时是刷新
                            mAdapter.setNewData(list);
                        } else {
                            mAdapter.addData(list);
                        }
//                            if (rows.getMsGoods().getList().size() < 10) {
//                                // 返回的数据小于 一页数量，说明没有数据了，不能上拉加载更多
//                                mMsAdapter.loadMoreEnd();
//                            } else {
//                                mMsAdapter.loadMoreComplete();
//                            }
//
//                        } else {
//                            ToastUtils.showShort(result.getErrmsg());
//                        }
                    }
                });
    }
}
