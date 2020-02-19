package com.pzr.desktoppet;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.blankj.utilcode.util.ToastUtils;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.pzr.desktoppet.utils.easyhttp.EasyHttp;
import com.pzr.desktoppet.utils.easyhttp.callback.SimpleCallBack;
import com.pzr.desktoppet.utils.easyhttp.exception.ApiException;
import com.pzr.desktoppet.utils.easyhttp.model.HttpParams;
import com.pzr.desktoppet.utils.easyhttp.utils.GsonUtils;

import java.util.List;

public class PetDetailActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView mIvImage;
    private TextView mTvName;
    private TextView mTvLike;
    private Button mBtnSetting;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet_detail);

        initViewId();
        initInfo();

    }

    private void initInfo() {
        Intent intent = getIntent();
        int position = intent.getIntExtra("position", 0);

        //提交
        final HttpParams param = new HttpParams();
//        param.put("",);
        EasyHttp.get("pet/" + (position + 1))
                .params(param)
                .execute(new SimpleCallBack<String>() {
                    @Override
                    public void onError(ApiException e) {
                        // 网络错误回调
                        ToastUtils.showShort("网路错误，请稍后重试");
                    }

                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onSuccess(String s) {
                        Gson gson = new Gson();
                        PetBean petBean = gson.fromJson(s, PetBean.class);
                        String image = petBean.getImage();
                        String name = petBean.getName();
                        String useCount = petBean.getUseCount();
                        String likeCount = petBean.getLikeCount();

                        Uri uri = Uri.parse(image);
                        Glide.with(PetApplication.getContext()).load(uri).into(mIvImage);

                        mTvName.setText(name);
                        mTvLike.setText("使用次数：" + useCount + " 喜欢次数：" + likeCount);

                    }
                });
    }

    private void initViewId() {
        mIvImage = findViewById(R.id.iv_image);
        mTvName = findViewById(R.id.tv_name);
        mTvLike = findViewById(R.id.tv_like);
        mBtnSetting = findViewById(R.id.btn_setting);
        mBtnSetting.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_setting) {
            Intent intent =new Intent(Settings.ACTION_SETTINGS);
            startActivity(intent);
        }
    }
}
