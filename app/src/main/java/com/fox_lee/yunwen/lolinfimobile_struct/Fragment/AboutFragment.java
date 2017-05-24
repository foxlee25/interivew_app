package com.fox_lee.yunwen.lolinfimobile_struct.Fragment;

import android.app.Fragment;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.fox_lee.yunwen.lolinfomobile_struct.R;

/**
 * Created by Yunwen on 4/18/2016.
 */
public class AboutFragment extends Fragment {
    private TextView tvTitle;
    private TextView tvVersion;
    private TextView tvAuthor;
    private TextView tvLicense;
    private TextView tvJiuzhang;
    private Button btnRate;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_about, container, false);
        return view;
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tvTitle = (TextView) view.findViewById(R.id.tv_about_title);
        tvTitle.setText(R.string.about_title);
        tvVersion = (TextView) view.findViewById(R.id.tv_about_version);
        tvVersion.setText(R.string.about_version);
        tvAuthor = (TextView) view.findViewById(R.id.tv_about_author);
        tvAuthor.setText(R.string.about_author);
        tvJiuzhang = (TextView) view.findViewById(R.id.tv_about_jiuzhang);
        tvJiuzhang.setText(R.string.about_jiuzhang_usage);
        tvLicense = (TextView) view.findViewById(R.id.tv_about_license);
        tvLicense.setText(R.string.about_license);
        btnRate = (Button) view.findViewById(R.id.btn_about_rate);
        btnRate.setText(R.string.about_rate);
        btnRate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String appPackageName = getActivity().getPackageName(); // getPackageName() from Context or Activity object
                try {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
                } catch (ActivityNotFoundException anfe) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)));
                }
            }
        });
    }
}
