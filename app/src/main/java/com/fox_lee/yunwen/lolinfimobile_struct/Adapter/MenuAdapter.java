package com.fox_lee.yunwen.lolinfimobile_struct.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.fox_lee.yunwen.lolinfimobile_struct.Activity.MainActivity;
import com.fox_lee.yunwen.lolinfimobile_struct.Interface.IndexCallback;
import com.fox_lee.yunwen.lolinfomobile_struct.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MenuAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
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

    public MenuAdapter(Context context) {
        mTitles = context.getResources().getStringArray(R.array.titles);
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
            ((TextViewHolder) holder).mTextView.setTextColor(mContext.getResources().getColor(R.color.colorText));
                    ((TextViewHolder) holder).mTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mContext instanceof View.OnClickListener) {
                        switch (((TextViewHolder) holder).mTextView.getText().toString()) {
                            case "DbFavorite": /* start activity accordingly */
                                ((MainActivity) mContext).startAlgorithmFragment(mTitles[position]);
                                break;
                            default:
                                Toast.makeText(mContext, "Coming soon", Toast.LENGTH_SHORT).show();
                                break;
                        }
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

        @OnClick(R.id.cv_text)
        void onItemClick() {
            Log.d("TextViewHolder", "onClick--> position = " + getPosition());
        }
    }
}