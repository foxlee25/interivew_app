package com.fox_lee.yunwen.lolinfimobile_struct.Fragment;

import android.app.AlertDialog;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.fox_lee.yunwen.lolinfimobile_struct.Adapter.DbAdapter;
import com.fox_lee.yunwen.lolinfimobile_struct.Utility.DbFavorite;
import com.fox_lee.yunwen.lolinfimobile_struct.Utility.DbRepo;
import com.fox_lee.yunwen.lolinfomobile_struct.R;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Yunwen on 4/11/2016.
 */
public class DbFragment extends Fragment implements View.OnClickListener{
    private RecyclerView mRecyclerView;
    private DbAdapter dbAdapter;
    private Button btnAdd,btnGetAll, btnDelete, btnGoto;
    private TextView student_Id;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_db, container, false);
        return view;
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btnGetAll = (Button) view.findViewById(R.id.btnGetAll);
        btnGetAll.setOnClickListener(this);
        listAll();
    }

    private void writeBoard() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
    }

    public void onClick(final View v){
        int _algorithm_id = 0;
        if (v == v.findViewById(R.id.btnGetAll)){// list db
            DbRepo repo = new DbRepo(v.getContext());
            DbFavorite dbFavorite = new DbFavorite();
            Log.d("MainActivity","The id is: " + _algorithm_id);
            dbFavorite = repo.getColumnById(_algorithm_id);
            ArrayList<HashMap<String, String>> algorithmList =  repo.getAlgorithmList();
            if(algorithmList.size()!=0) {//Show Db list
                initRecyclerView(algorithmList);
            }else{
                Toast.makeText(v.getContext(), "No Content!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void initRecyclerView(ArrayList<HashMap<String, String>> algorithmList){
        mRecyclerView = (RecyclerView) getActivity().findViewById(R.id.recycler_db);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        dbAdapter = new DbAdapter(getActivity(),algorithmList);
        mRecyclerView.setAdapter(dbAdapter);
    }

    public void listAll(){
        btnGetAll.callOnClick();
    }
}

