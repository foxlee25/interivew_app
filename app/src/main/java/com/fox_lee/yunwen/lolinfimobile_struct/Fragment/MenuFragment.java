package com.fox_lee.yunwen.lolinfimobile_struct.Fragment;

import android.app.AlertDialog;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fox_lee.yunwen.lolinfimobile_struct.Adapter.MenuAdapter;
import com.fox_lee.yunwen.lolinfomobile_struct.R;

/**
 * Created by Yunwen on 2/10/2016.
 */
public class MenuFragment extends Fragment{
    RecyclerView mRecyclerView;
    private MenuAdapter codeInterviewAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recyclerview, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(),2));
        codeInterviewAdapter = new MenuAdapter(getActivity());
        mRecyclerView.setAdapter(codeInterviewAdapter);
    }

    private void writeBoard() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
    }
}