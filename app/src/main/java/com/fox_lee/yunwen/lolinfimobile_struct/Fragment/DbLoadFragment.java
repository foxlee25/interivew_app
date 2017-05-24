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
import android.widget.Toast;

import com.fox_lee.yunwen.lolinfimobile_struct.Adapter.DbLoadAdapter;
import com.fox_lee.yunwen.lolinfimobile_struct.Utility.Algorithm;
import com.fox_lee.yunwen.lolinfimobile_struct.Utility.DbFavorite;
import com.fox_lee.yunwen.lolinfimobile_struct.Utility.AlgorithmRepo;
import com.fox_lee.yunwen.lolinfomobile_struct.R;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Yunwen on 5/21/2016.
 */
public class DbLoadFragment extends Fragment implements View.OnClickListener {
    private RecyclerView mRecyclerView;
    private DbLoadAdapter dbAdapter;
    private Button btnAdd,btnGetAll,btnDel;
    private String[] strings;

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
        btnAdd = (Button) view.findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(this);
        btnAdd.setVisibility(View.VISIBLE);
        btnDel = (Button) view.findViewById(R.id.btnDel);
        btnDel.setOnClickListener(this);
        btnDel.setVisibility(View.VISIBLE);
        listAll();
    }

    private void writeBoard() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
    }

    public void onClick(final View v){
        int _algorithm_id = 0;
        AlgorithmRepo repo = new AlgorithmRepo(v.getContext());
        Algorithm algorithm;
        if (v == v.findViewById(R.id.btnGetAll)){// list db
            Log.d("MainActivity", "The id is: " + _algorithm_id);
            algorithm = repo.getColumnById(_algorithm_id);
            ArrayList<HashMap<String, String>> algorithmList =  repo.getAlgorithmList();
            if(algorithmList.size()!=0) {
            //Show Db list
                initRecyclerView(algorithmList);
            }else{
                Toast.makeText(v.getContext(), "No Content!", Toast.LENGTH_SHORT).show();
            }
        }
        else if( v == v.findViewById(R.id.btnAdd)){
            //add to favorite
            String[] dataContent = getResources().getStringArray(R.array.dynamic_programming);
            for(int i = 0; i < dataContent.length ; i++) {
                String pack = getActivity().getPackageName();
                Log.d("DbLoadFragment","The topic is: " + dataContent[i]);
                String id = dataContent[i].toLowerCase().replace(" ", "_") + "_";
                int resId = getActivity().getResources().getIdentifier(id, "array", pack);
                strings = getActivity().getResources().getStringArray(resId);
                algorithm = repo.getColumnById(_algorithm_id);
                algorithm.age = 25;
                algorithm.topic = dataContent[i];//strings[0]
                algorithm.content = strings[0];//start from linked list
                algorithm.code = strings[1];//strings[1]
                //load data starting from data structure
                algorithm.algorithm_ID = _algorithm_id;
                Log.d("DbLoadFragment","The insert id 3 is: " + _algorithm_id);
                _algorithm_id = repo.insert(algorithm);
                Log.d("DbLoadFragment","The insert id 4 is: " + _algorithm_id);
            }
            listAll();
        } else if(v == v.findViewById(R.id.btnDel)){
            algorithm = repo.getColumnById(0);
            repo.delete(algorithm.algorithm_ID);
            Toast.makeText(v.getContext(), "The content deleted", Toast.LENGTH_SHORT).show();
        }
    }

    private void initRecyclerView(ArrayList<HashMap<String, String>> algorithmList){
        mRecyclerView = (RecyclerView) getActivity().findViewById(R.id.recycler_db);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        dbAdapter = new DbLoadAdapter(getActivity(),algorithmList);
        mRecyclerView.setAdapter(dbAdapter);
    }

    public void listAll(){
        btnGetAll.callOnClick();
    }
}
