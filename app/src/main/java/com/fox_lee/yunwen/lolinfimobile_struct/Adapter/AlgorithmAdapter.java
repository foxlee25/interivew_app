package com.fox_lee.yunwen.lolinfimobile_struct.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.fox_lee.yunwen.lolinfimobile_struct.Activity.MainActivity;
import com.fox_lee.yunwen.lolinfimobile_struct.Interface.IndexCallback;
import com.fox_lee.yunwen.lolinfomobile_struct.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class AlgorithmAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public enum ITEM_TYPE {
        ITEM_TYPE_IMAGE,
        ITEM_TYPE_TEXT
    }
    private final LayoutInflater mLayoutInflater;
    private final Context mContext;
    private String[] mTitles;
    private static IndexCallback indexCallback;

    public void onIndexUpdate(IndexCallback indexCallback) {
        this.indexCallback = indexCallback;
    }

    public AlgorithmAdapter(Context context) {
        mTitles = context.getResources().getStringArray(R.array.algorithm);
        mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new TextViewHolder(mLayoutInflater.inflate(R.layout.item_text, parent, false));
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof TextViewHolder) {
            ((TextViewHolder) holder).mTextView.setText(mTitles[position]);
            ((TextViewHolder) holder).mTextView.setTextSize(16);
            ((TextViewHolder) holder).mTextView.setTextColor(mContext.getResources().getColor(R.color.colorWhite));
            ((TextViewHolder) holder).mTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mContext instanceof View.OnClickListener) {
                        String pack = mContext.getPackageName();
                        String id = ((TextViewHolder) holder).mTextView.getText().toString().toLowerCase().replace(" ", "_");
                        int resId = mContext.getResources().getIdentifier(id, "array", pack);
                        String[] res = mContext.getResources().getStringArray(resId);
                        Log.d("AlgorithmAdapter", " The resource id is: " + id);
                        for(int i = 0; i < res.length; i++) {
                            Log.d("DbFavorite","The array is: " + res[i]);
                        }
                        ((MainActivity) mContext).startSubFragment(res);
                    }
                }
            });
        }
    }

    @Override
    public int getItemViewType(int position) {
        return position % 2 == 0 ? ITEM_TYPE.ITEM_TYPE_IMAGE.ordinal() : ITEM_TYPE.ITEM_TYPE_TEXT.ordinal();
    }

    @Override
    public int getItemCount() {
        return mTitles == null ? 0 : mTitles.length;
    }

    public static class TextViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.text_view)
        TextView mTextView;

        TextViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}