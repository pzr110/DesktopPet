package com.pzr.desktoppet;

import android.net.Uri;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

public class PetsAdapter extends BaseQuickAdapter<PetsBean, BaseViewHolder> {

    public PetsAdapter(@Nullable List<PetsBean> data) {
        super(R.layout.item_pets, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, PetsBean item) {
        helper.setText(R.id.tv_name, item.getName());

        Uri uri = Uri.parse(item.getImage());
        Glide.with(mContext).load(uri).into((ImageView) helper.getView(R.id.iv_image));
    }
}
