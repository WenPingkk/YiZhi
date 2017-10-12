package com.zyw.horrarndoo.yizhi.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zyw.horrarndoo.yizhi.R;
import com.zyw.horrarndoo.yizhi.model.bean.gankio.GankIoDayItemBean;

import java.util.List;

/**
 * Created by Horrarndoo on 2017/10/11.
 * <p>
 */

public class GankIoDayAdapter extends BaseMultiItemQuickAdapter<GankIoDayItemBean, BaseViewHolder> {
    private List<GankIoDayItemBean> mlist;

    public GankIoDayAdapter(@Nullable List<GankIoDayItemBean> data) {
        super(data);
        mlist = data;

        addItemType(GankIoDayItemBean.CLICK_ITEM_DAY_NORMAL, R.layout.item_gank_io_day_normal);
        addItemType(GankIoDayItemBean.CLICK_ITEM_DAY_REFESH, R.layout.item_gank_io_day_refesh);
    }

    public void addItem(GankIoDayItemBean bean, int position) {
        mlist.add(position, bean);
        notifyItemInserted(position);
    }

    public void removeItem(GankIoDayItemBean bean) {
        int position = mlist.indexOf(bean);
        mlist.remove(position);
        notifyItemRemoved(position);
    }

    public void removeItem(int position) {
        mlist.remove(position);
        notifyItemRemoved(position);
    }

    @Override
    protected void convert(BaseViewHolder helper, GankIoDayItemBean item) {
        helper.setText(R.id.tv_type_item_title, item.getType());
        helper.setText(R.id.tv_item_title, item.getDesc());

        if (item.getType().equals("福利")) {
            helper.setImageResource(R.id.iv_type_item_title, R.drawable.ic_vector_title_welfare);
            Glide.with(mContext).load(item.getUrl()).crossFade().into((ImageView) helper.getView(R
                    .id.iv_item_title));
        } else if (item.getType().equals("Android")) {
            helper.setImageResource(R.id.iv_type_item_title, R.drawable.ic_vector_title_android);
            helper.setImageResource(R.id.iv_item_title, R.mipmap.gank_io_day_item_android);
        } else if (item.getType().equals("iOS")) {
            helper.setImageResource(R.id.iv_type_item_title, R.drawable.ic_vector_title_ios);
            helper.setImageResource(R.id.iv_item_title, R.mipmap.gank_io_day_item_ios);
        } else if (item.getType().equals("前端")) {
            helper.setImageResource(R.id.iv_type_item_title, R.drawable.ic_vector_title_front);
            helper.setImageResource(R.id.iv_item_title, R.mipmap.gank_io_day_item_web);
        } else if (item.getType().equals("休息视频")) {
            helper.setImageResource(R.id.iv_type_item_title, R.drawable.ic_vector_title_video);
            helper.setImageResource(R.id.iv_item_title, R.mipmap.gank_io_day_item_video);
        }

        switch (helper.getItemViewType()) {
            case GankIoDayItemBean.CLICK_ITEM_DAY_NORMAL:
                helper.addOnClickListener(R.id.ll_more);
                break;
            case GankIoDayItemBean.CLICK_ITEM_DAY_REFESH:
                helper.addOnClickListener(R.id.ll_more).addOnClickListener(R.id.ll_refesh);
                break;
            default:
                break;
        }

        //GankIo 返回数据中，很多没有images，这里不用GankIo的图，直接写死
        //        if(item.getImages() == null)
        //            return;
        //
        //        Glide.with(mContext).load(item.getImages().get(0)).crossFade().into((ImageView)
        // helper.getView(R.id.iv_item_title));
    }
}