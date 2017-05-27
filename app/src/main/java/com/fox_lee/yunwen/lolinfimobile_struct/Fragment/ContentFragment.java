package com.fox_lee.yunwen.lolinfimobile_struct.Fragment;

import android.app.AlertDialog;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.fox_lee.yunwen.lolinfimobile_struct.Activity.MainActivity;
import com.fox_lee.yunwen.lolinfimobile_struct.Utility.DbFavorite;
import com.fox_lee.yunwen.lolinfimobile_struct.Utility.DbRepo;
import com.fox_lee.yunwen.lolinfomobile_struct.R;

import java.util.ArrayList;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

/**
 * Created by Yunwen on 2/11/2016.
 */

//implements View.OnClickListener
public class ContentFragment extends Fragment {

    private String data;

    private ArrayList<String> dataContent;

    private String[] strings;

    private static boolean showingFirst = true;

    private static boolean existFavorite = false;

    private AdView mAdView;


    Button btnPre;

    Button btnNext;

    Button btnAnswer;

    ImageView imageFavorite;

    TextView tvTitle;

    TextView tvQuestion;

    TextView tvAnswer;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.detail_content_list, container, false);
        mAdView = (AdView) view.findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        if (mAdView != null) {
            mAdView.loadAd(adRequest);
        }
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        showingFirst = true;
        initViews(view);

        DbRepo repo = new DbRepo(getActivity());
        DbFavorite dbFavorite = repo.getColumnByTopic(data);
        try {
            if (dbFavorite.topic.equals(data)) {
                imageFavorite.setImageResource(R.drawable.favorite_red_rev);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        imageFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //if not exist in the Db then add to favorite
                addToFavorite();
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //add to favorite
                moveToNext();
            }
        });
        btnPre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //add to favorite
                moveToPrevious();
            }
        });
        btnAnswer.setVisibility(view.VISIBLE);

        btnAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (showingFirst) {
                    tvAnswer.setVisibility(v.VISIBLE);
                    btnAnswer.setText("Hide Answer");
                    showingFirst = false;
                } else {
                    tvAnswer.setVisibility(v.GONE);
                    showingFirst = true;
                    btnAnswer.setText("ANSWER");
                }
            }
        });

    }

    private void initViews(View view) {
        String pack = getActivity().getPackageName();
        String id = data.toLowerCase().replace(" ", "_") + "_";
        int resId = getActivity().getResources().getIdentifier(id, "array", pack);
        strings = getActivity().getResources().getStringArray(resId);

        tvTitle = (TextView) view.findViewById(R.id.text_title);
        imageFavorite = (ImageView) view.findViewById(R.id.img_favorite);
        tvQuestion = (TextView) view.findViewById(R.id.text_question);
        tvAnswer = (TextView) view.findViewById(R.id.text_getAnswer);
        btnAnswer = (Button) view.findViewById(R.id.btn_getAnswer);
        btnNext = (Button) view.findViewById(R.id.move_next);
        btnPre = (Button) view.findViewById(R.id.move_pre);

        tvTitle.setTextColor(getResources().getColor(R.color.colorBlack));
        btnAnswer.setTextColor(getResources().getColor(R.color.colorWhite));
        tvAnswer.setTextColor(getResources().getColor(R.color.colorBlack));
        tvAnswer.setBackgroundColor(getResources().getColor(R.color.colorLightGrey));

        tvQuestion.setText(strings[0]);
        tvAnswer.setText(strings[1]);
        try {
            float length = 500 / data.length();
            if (length > 25) {
                tvTitle.setTextSize(25);
            } else {
                tvTitle.setTextSize(length);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        tvTitle.setText(data);
    }


    private void addToFavorite() {
        int _algorithm_id = 0;
        DbRepo repo = new DbRepo(getActivity());

        DbFavorite dbFavorite = repo.getColumnByTopic(data);
        try {
            if (dbFavorite.topic.equals(data)) {
                repo.update(dbFavorite);
                Toast.makeText(getActivity(), "Content Record updated", Toast.LENGTH_SHORT).show();
            } else {

            }
        } catch (Exception e) {
            dbFavorite.age = 25;
            dbFavorite.content = "";//should be definition
            dbFavorite.topic = data;
            dbFavorite.algorithm_ID = _algorithm_id;
            _algorithm_id = repo.insert(dbFavorite);
            Toast.makeText(getActivity(), "Add to Favorite Menu", Toast.LENGTH_SHORT).show();
            ((MainActivity) getActivity()).startContentFragment(data, dataContent);
            e.printStackTrace();
        }
    }

    private void moveToNext() {
        try {
            int i = dataContent.indexOf(data);
            ((MainActivity) getActivity())
                    .startContent2Fragment(dataContent.get(i + 1), dataContent);
        } catch (Exception e) {
            Toast.makeText(getActivity(), "Last Content of this group", Toast.LENGTH_SHORT).show();
        }
    }

    private void moveToPrevious() {
        try {
            int i = dataContent.indexOf(data);
            ((MainActivity) getActivity())
                    .startContent2Fragment(dataContent.get(i - 1), dataContent);
        } catch (Exception e) {
            Toast.makeText(getActivity(), "First Content of this group", Toast.LENGTH_SHORT).show();
        }
    }

    public void changeData(String data, ArrayList dataContent) {
        this.data = data;
        this.dataContent = dataContent;
    }
}