package com.fox_lee.yunwen.lolinfimobile_struct.Fragment;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import android.app.AlertDialog;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.flurry.android.FlurryAgent;
import com.fox_lee.yunwen.lolinfimobile_struct.Adapter.AlgorithmAdapter;
import com.fox_lee.yunwen.lolinfomobile_struct.R;



/**
 * Created by Yunwen on 2/11/2016.
 */
public class AlgorithmFragment extends Fragment {

    RecyclerView mRecyclerView;

    private AlgorithmAdapter algorithmAdapter;

    private AdView mAdView;

    private AdRequest adRequest;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recyclerview, container, false);

        mAdView = (AdView) view.findViewById(R.id.adView);
        adRequest = new AdRequest.Builder().build();
        if (mAdView != null) {
            mAdView.loadAd(adRequest);
        }
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //display details
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        algorithmAdapter = new AlgorithmAdapter(getActivity());
        mRecyclerView.setAdapter(algorithmAdapter);
        //insert everything into the database
//        mAdView = (AdView) view.findViewById(R.id.adView);
//        adRequest = new AdRequest.Builder().build();
//        if(mAdView != null) {
//            mAdView.loadAd(adRequest);
//        }
    }

    private void writeBoard() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
    }
}